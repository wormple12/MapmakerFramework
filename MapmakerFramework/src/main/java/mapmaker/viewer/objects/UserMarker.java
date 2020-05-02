
package mapmaker.viewer.objects;

import java.nio.file.Path;
import mapmaker.map.objects.Marker;

/**
 *
 * @author Simon Norup
 */
public class UserMarker extends Marker {
    
    private UserMarkerInfo info;

    public UserMarker(UserMarkerInfo info, Path sprite, int x, int y) {
        super(sprite, x, y);
        this.info = info;
    }

    public UserMarkerInfo getInfo() {
        return info;
    }

    public void setInfo(UserMarkerInfo info) {
        this.info = info;
    }
    
}
