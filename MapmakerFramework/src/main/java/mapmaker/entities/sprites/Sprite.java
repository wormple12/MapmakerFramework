package mapmaker.entities.sprites;

import java.io.Serializable;
import mapmaker.entities.FilePath;

/**
 *
 * @author Simon Norup
 */
public class Sprite implements Serializable {

    private FilePath spritePath;
    private int x, y;

    public Sprite(FilePath spritePath, int x, int y) {
        this.spritePath = spritePath;
        this.x = x;
        this.y = y;
    }

    public FilePath getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(FilePath spritePath) {
        this.spritePath = spritePath;
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
