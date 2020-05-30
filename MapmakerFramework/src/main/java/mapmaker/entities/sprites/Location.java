
package mapmaker.entities.sprites;

import java.nio.file.Path;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public class Location extends Marker {

    public Location(EntityInfo info, String spritePath, float x, float y, float w, float h) {
        super(info, spritePath, x, y, w, h);
    }
    
}
