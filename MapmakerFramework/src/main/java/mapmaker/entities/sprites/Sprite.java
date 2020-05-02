package mapmaker.entities.sprites;

import java.nio.file.Path;

/**
 *
 * @author Simon Norup
 */
public class Sprite {

    private Path spritePath;
    private int x, y;

    public Sprite(Path spritePath, int x, int y) {
        this.spritePath = spritePath;
        this.x = x;
        this.y = y;
    }

    public Path getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(Path spritePath) {
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
