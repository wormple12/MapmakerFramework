package mapmaker.map;

import java.nio.file.Path;
import java.util.List;
import mapmaker.map.objects.Location;
import mapmaker.viewer.objects.UserMarker;

/**
 *
 * @author Simon Norup
 */
public class Map {

    private final MapInfo info;
    private final Landmass landmass;
    private final List<Region> regions;
    private final List<Route> routes;
    private final List<Location> locations;
    private final List<UserMarker> markers;
    private Path filePath;

    public Map(MapInfo info, Landmass landmass, List<Region> regions, List<Location> locations, List<UserMarker> markers, List<Route> routes) {
        this.info = info;
        this.landmass = landmass;
        this.regions = regions;
        this.locations = locations;
        this.markers = markers;
        this.routes = routes;
    }

    public MapInfo getInfo() {
        return info;
    }

    public Landmass getLandmass() {
        return landmass;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<UserMarker> getMarkers() {
        return markers;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

}
