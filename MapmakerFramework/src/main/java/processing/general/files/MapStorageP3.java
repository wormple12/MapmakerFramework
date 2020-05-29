package processing.general.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.util.Pair;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import mapmaker.general.files.FileHandler;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.core.PImage;
import processing.entities.LandmassP3;
import processing.entities.LandmassP3_DTO;
import processing.entities.RouteP3;
import processing.entities.RouteP3_DTO;
import processing.entities.sprites.LocationInfoP3;
import processing.entities.sprites.LocationP3;
import processing.entities.sprites.LocationP3_DTO;
import processing.entities.sprites.UserMarkerInfoP3;
import processing.entities.sprites.UserMarkerP3;
import processing.entities.sprites.UserMarkerP3_DTO;

/**
 *
 * @author Simon Norup
 */
public class MapStorageP3 extends FileStorage {

    private final PApplet app;
    private final FileStorage infoStorage;

    public MapStorageP3(PApplet app) {
        super();
        this.app = app;
        this.infoStorage = new FileStorage();
    }

    public MapStorageP3(FileHandler fileHandler, PApplet app) {
        super(fileHandler);
        this.app = app;
        this.infoStorage = new FileStorage(fileHandler);
    }

    @Override
    public boolean attemptSave(WorldMap world) {
        // converting to DTO
        List<Region> regions = world.getRegions().stream().map(r -> new Region(new LandmassP3_DTO((LandmassP3) r.getArea()), r.getInfo(), r.getBiome())).collect(Collectors.toList());
        List<Route> routes = world.getRoutes().stream().map(r -> new RouteP3_DTO((RouteP3) r)).collect(Collectors.toList());
        List<Location> locations = world.getLocations().stream().map(loc -> new LocationP3_DTO((LocationP3) loc)).collect(Collectors.toList());
        List<UserMarker> markers = world.getMarkers().stream().map(m -> new UserMarkerP3_DTO((UserMarkerP3) m)).collect(Collectors.toList());
        WorldMap worldDTO = new WorldMap(world.getInfo(), regions, locations, markers, routes);

        // saving map file
        boolean success = infoStorage.attemptSave(worldDTO);
        world.setFilePath(worldDTO.getFilePath());

        if (success) {
            String mapFileName = world.getFileName();
            // saving graphics
            try {
                for (int i = 0; i < world.getRegions().size(); i++) {
                    LandmassP3 area = (LandmassP3) world.getRegions().get(i).getArea();
                    String regionPath = updateRegionPath(mapFileName, area);
                    area.getGraphics().save(regionPath);
                    String borderPath = area.getBorderGraphicsPath();
                    area.getBorderGraphics().save(borderPath);
                }
            } catch (Exception e) {
                success = false;
            }
        }
        return success;
    }

    @Override
    public WorldMap attemptLoad(boolean useLatestMap) {
        WorldMap world = super.attemptLoad(useLatestMap);

        if (world != null) {
            String mapFileName = world.getFileName();

            world.getRegions().forEach((r) -> {
                LandmassP3_DTO dto = (LandmassP3_DTO) r.getArea();
                LandmassP3 area = new LandmassP3(dto.getId(), null, null);
                updateRegionPath(mapFileName, area);

                PImage regionImg = app.loadImage(area.getGraphicsPath());
                area.setGraphics(app.createGraphics(regionImg.width, regionImg.height));
                area.getGraphics().beginDraw();
                area.getGraphics().image(regionImg, 0, 0);
                area.getGraphics().endDraw();
                PImage borderImg = app.loadImage(area.getBorderGraphicsPath());
                area.setBorderGraphics(app.createGraphics(borderImg.width, borderImg.height));
                area.getBorderGraphics().beginDraw();
                area.getBorderGraphics().image(borderImg, 0, 0);
                area.getBorderGraphics().endDraw();

                r.setArea(area);
            });

            world.setRoutes(
                    world.getRoutes().stream().map((r) -> {
                        RouteP3_DTO dto = (RouteP3_DTO) r;
                        RouteP3 route = new RouteP3(app.createGraphics(app.width, app.height));
                        dto.getCoordinates().forEach((coordinate) -> {
                            route.addPoint(coordinate.getKey(), coordinate.getValue());
                        });
                        return route;
                    }).collect(Collectors.toList())
            );

            world.setLocations(
                    world.getLocations().stream().map(loc -> {
                        LocationP3_DTO dto = (LocationP3_DTO) loc;
                        LocationP3 location = new LocationP3((LocationInfoP3) dto.getInfo(), dto.getSpritePath(), dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(), null);

                        PImage sprite = app.loadImage(location.getSpritePath());
                        location.setImage(sprite);

                        return location;
                    }).collect(Collectors.toList())
            );

            world.setMarkers(
                    world.getMarkers().stream().map(m -> {
                        UserMarkerP3_DTO dto = (UserMarkerP3_DTO) m;
                        UserMarkerP3 marker = new UserMarkerP3((UserMarkerInfoP3) dto.getInfo(), dto.getSpritePath(), dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(), null);

                        PImage sprite = app.loadImage(marker.getSpritePath());
                        marker.setImage(sprite);

                        return marker;
                    }).collect(Collectors.toList())
            );
        }
        return world;
    }

    private String updateRegionPath(String mapFileName, LandmassP3 landmass) {
        String regionPath = "worldmaps/" + mapFileName + "/regions/" + landmass.getId() + ".png";
        landmass.setGraphicsPath(regionPath);
        return regionPath;
    }

    public static String generateId() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
