package processing.general.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import mapmaker.general.files.FileHandler;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.core.PImage;
import processing.entities.LandmassP3;
import processing.entities.LandmassP3_DTO;
import processing.entities.RouteP3;
import processing.entities.RouteP3_DTO;

/**
 *
 * @author Simon Norup
 */
public class MapStorageP3 extends FileStorage {

    private final PApplet app;

    public MapStorageP3(PApplet app) {
        super();
        this.app = app;
    }

    public MapStorageP3(FileHandler fileHandler, PApplet app) {
        super(fileHandler);
        this.app = app;
    }

    private List<Region> regions;
    private List<Route> routes;
    private boolean graphicsWasConverted = false;

    @Override
    public boolean attemptSave(WorldMap map) {
        // converting to DTO
        if (!graphicsWasConverted) {
            regions = map.getRegions();
            routes = map.getRoutes();
            map.setRegions(new ArrayList<>());
            regions.forEach((r) -> map.addRegion(new Region(new LandmassP3_DTO((LandmassP3) r.getArea()), r.getInfo(), r.getBiome())));
            map.setRoutes(new ArrayList<>());
            routes.forEach((r) -> map.addRoute(new RouteP3_DTO((RouteP3) r)));
            graphicsWasConverted = true;
        }
        // saving map file
        boolean success = super.attemptSave(map);

        // reverting from DTO
        map.setRegions(regions);
        map.setRoutes(routes);
        graphicsWasConverted = false;

        if (!success) {
            return false;
        }
        String mapFileName = map.getFileName();
        // saving graphics
        try {
            for (int i = 0; i < map.getRegions().size(); i++) {
                LandmassP3 area = (LandmassP3) map.getRegions().get(i).getArea();
                String regionPath = updateRegionPath(mapFileName, area);
                area.getGraphics().save(regionPath);
                String borderPath = area.getBorderGraphicsPath();
                area.getBorderGraphics().save(borderPath);
            }

            for (int i = 0; i < map.getRoutes().size(); i++) {
                RouteP3 route = (RouteP3) map.getRoutes().get(i);
                String routePath = updateRoutePath(mapFileName, route);
                route.getGraphics().save(routePath);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public WorldMap attemptLoad(boolean useLatestMap) {
        WorldMap map = super.attemptLoad(useLatestMap);

        if (map != null) {
            String mapFileName = map.getFileName();

            map.getRegions().forEach((r) -> {
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

            map.setRoutes(
                    map.getRoutes().stream().map((r) -> {
                        RouteP3_DTO dto = (RouteP3_DTO) r;
                        RouteP3 route = new RouteP3(dto.getId(), null);
                        updateRoutePath(mapFileName, route);
                        PImage routeImg = app.loadImage(route.getGraphicsPath());
                        route.setGraphics(app.createGraphics(routeImg.width, routeImg.height));
                        route.getGraphics().beginDraw();
                        route.getGraphics().image(routeImg, 0, 0);
                        route.getGraphics().endDraw();
                        return route;
                    }).collect(Collectors.toList())
            );
        }
        return map;
    }

    private String updateRegionPath(String mapFileName, LandmassP3 landmass) {
        String regionPath = "worldmaps/" + mapFileName + "/regions/" + landmass.getId() + ".png";
        landmass.setGraphicsPath(regionPath);
        return regionPath;
    }

    private String updateRoutePath(String mapFileName, RouteP3 route) {
        String routePath = "worldmaps/" + mapFileName + "/routes/" + route.getId() + ".png";
        route.setGraphicsPath(routePath);
        return routePath;
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
