package mapmaker.entities;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;

/**
 *
 * @author Simon Norup
 */
public class WorldMap implements Serializable {

    private EntityInfo info;
    //private final Landmass landmass;
    private final List<Region> regions;
    private final List<Route> routes;
    private final List<Location> locations;
    private final List<UserMarker> markers;
    private FilePath filePath;

    public WorldMap(EntityInfo info, /*Landmass landmass,*/ List<Region> regions, List<Location> locations, List<UserMarker> markers, List<Route> routes) {
        this.info = info;
//        this.landmass = landmass;
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

    public void addRegion(Region region) {
        regions.add(region);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public List<UserMarker> getMarkers() {
        return markers;
    }

    public void addMarker(UserMarker marker) {
        markers.add(marker);
    }

    public FilePath getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = (FilePath) filePath;
    }

}
