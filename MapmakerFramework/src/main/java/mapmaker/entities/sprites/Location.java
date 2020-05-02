
package mapmaker.entities.sprites;

import java.nio.file.Path;

/**
 *
 * @author Simon Norup
 */
public class Location extends Sprite {

    private LocationInfo info;

    public Location(LocationInfo info, Path sprite, int x, int y) {
        super(sprite, x, y);
        this.info = info;
    }

    public LocationInfo getInfo() {
        return info;
    }

    public void setInfo(LocationInfo info) {
        this.info = info;
    }
    
}
