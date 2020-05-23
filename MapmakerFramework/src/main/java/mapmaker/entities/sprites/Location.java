
package mapmaker.entities.sprites;

import java.nio.file.Path;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public class Location extends Sprite {

    private EntityInfo info;

    public Location(EntityInfo info, Path sprite, int x, int y) {
        super(sprite, x, y);
        this.info = info;
    }

    public EntityInfo getInfo() {
        return info;
    }

    public void setInfo(EntityInfo info) {
        this.info = info;
    }
    
}
