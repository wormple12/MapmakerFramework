package mapmaker.map.objects;

import java.nio.file.Path;

/**
 *
 * @author Simon Norup
 */
public class Marker {

    private Path sprite;
    private int x, y;

    public Marker(Path sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public Path getSprite() {
        return sprite;
    }

    public void setSprite(Path sprite) {
        this.sprite = sprite;
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
