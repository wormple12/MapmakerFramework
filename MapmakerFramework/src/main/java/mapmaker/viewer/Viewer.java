
package mapmaker.viewer;

import mapmaker.map.Region;
import mapmaker.map.objects.Location;
import mapmaker.viewer.objects.Marker;

/**
 *
 * @author Simon Norup
 */
public interface Viewer {
    
    public void dragMarker(Marker location, int x, int y);
    public void dropMarker(Marker location, int x, int y);
    
    public void viewRegion(Region region);
    public void viewLocation(Location location);
    
}
