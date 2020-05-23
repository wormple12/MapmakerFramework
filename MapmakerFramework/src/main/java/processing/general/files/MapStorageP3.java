package processing.general.files;

import java.util.List;
import java.util.Random;
import mapmaker.entities.Region;
import mapmaker.entities.WorldMap;
import mapmaker.general.files.FileHandler;
import mapmaker.general.files.FileStorage;
import processing.core.PGraphics;
import processing.entities.LandmassP3;
import processing.entities.RouteP3;

/**
 *
 * @author Simon Norup
 */
public class MapStorageP3 {

    private final FileStorage infoStorage;

    public MapStorageP3() {
        infoStorage = new FileStorage();
    }

    public MapStorageP3(FileHandler fileHandler) {
        infoStorage = new FileStorage(fileHandler);
    }

    public boolean attemptSave(WorldMap map, List<PGraphics> regionLayers, List<PGraphics> routeLayers) {
        if (map.getRegions().size() != regionLayers.size()) {
            throw new IllegalArgumentException("There's a discrepancy between the number of regions in the map and the number of region graphics layers.");
        }
        if (map.getRoutes().size() != routeLayers.size()) {
            throw new IllegalArgumentException("There's a discrepancy between the number of routes in the map and the number of route graphics layers.");
        }

        boolean success = infoStorage.attemptSave(map);
        if (!success) {
            return false;
        }
        try {
            String mapFileName = map.getFileName();

            for (int i = 0; i < map.getRegions().size(); i++) {
                LandmassP3 area = (LandmassP3) map.getRegions().get(i).getArea();
                String regionPath = updateRegionPath(mapFileName, area);
                regionLayers.get(i).save(regionPath);
            }

            for (int i = 0; i < map.getRoutes().size(); i++) {
                RouteP3 route = (RouteP3) map.getRoutes().get(i);
                String routePath = updateRoutePath(mapFileName, route);
                routeLayers.get(i).save(routePath);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public WorldMap attemptLoad(boolean useLatestMap) {
        WorldMap map = infoStorage.attemptLoad(useLatestMap);

        if (map != null) {
            String mapFileName = map.getFileName();
            map.getRegions().stream().map((r) -> (LandmassP3) r.getArea()).forEach((area) -> {
                updateRegionPath(mapFileName, area);
            });
            map.getRoutes().stream().map((r) -> (RouteP3) r).forEach((route) -> {
                updateRoutePath(mapFileName, route);
            });
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
