package mapmaker.entities.sprites;

import java.nio.file.Path;

/**
 *
 * @author Simon Norup
 */
public class Player extends UserMarker {

    public Player(Path sprite, int x, int y) {
        super(null, sprite, x, y);
    }

}
