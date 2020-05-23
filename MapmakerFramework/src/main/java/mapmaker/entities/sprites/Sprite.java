package mapmaker.entities.sprites;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Simon Norup
 */
public class Sprite implements Serializable {

    private String spritePath;
    private int x, y;

    public Sprite(Path spritePath, int x, int y) {
        this.spritePath = spritePath.toString();
        this.x = x;
        this.y = y;
    }

    public Path getSpritePath() {
        return Paths.get(spritePath);
    }

    public void setSpritePath(Path spritePath) {
        this.spritePath = spritePath.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
