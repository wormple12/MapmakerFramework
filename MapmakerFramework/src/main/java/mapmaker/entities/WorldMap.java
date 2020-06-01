package mapmaker.entities;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;

/**
 *
 * @author Simon Norup
 */
public class WorldMap implements Serializable {

    private EntityInfo info;
    private List<Region> regions;
    private List<Route> routes;
    private List<Location> locations;
    private List<UserMarker> markers;
    private String filePath;

    public WorldMap(EntityInfo info, List<Region> regions, List<Location> locations, List<UserMarker> markers, List<Route> routes) {
        this.info = info;
        this.regions = regions;
        this.locations = locations;
        this.markers = markers;
        this.routes = routes;
    }

    public EntityInfo getInfo() {
        return info;
    }

    public void setInfo(EntityInfo info) {
        this.info = info;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public Region getRegion(int i) {
        try {
            return regions.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addRegion(Region region) {
        regions.add(region);
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public Route getRoute(int i) {
        try {
            return routes.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<UserMarker> getMarkers() {
        return markers;
    }

    public void addMarker(UserMarker marker) {
        markers.add(marker);
    }

    public void setMarkers(List<UserMarker> markers) {
        this.markers = markers;
    }

    public Path getFilePath() {
        if (filePath != null) {
            return Paths.get(filePath);
        } else {
            return null;
        }
    }

    public String getFileName() {
        if (filePath != null) {
            String[] parts;
            if (filePath.contains("/")) {
                parts = filePath.split("/");
            } else if (filePath.contains("\\")) {
                parts = filePath.split("\\\\");
            } else {
                return null;
            }
            return parts[parts.length - 1].split("\\.")[0];
        }
        return null;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath.toString();
    }

}
