
package mapmaker.entities.sprites;

import mapmaker.entities.EntityInfo;
import mapmaker.entities.FilePath;

/**
 *
 * @author Simon Norup
 */
public class UserMarker extends Sprite {
    
    private EntityInfo info;

    public UserMarker(EntityInfo info, FilePath sprite, int x, int y) {
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
