package processing.entities.sprites;

import mapmaker.entities.sprites.Location;
import static processing.core.PApplet.dist;
import static processing.core.PApplet.max;
import processing.core.PImage;

/**
 *
 * @author rasmu
 */
public class LocationP3 extends Location {

    private PImage image;

    public LocationP3(LocationInfoP3 info, String spritePath, float x, float y, float width, float height, PImage image) {
        super(info, spritePath, x, y, width, height);
        this.image = image;
    }

    public boolean over(int mouse_x, int mouse_y) {
        return dist(mouse_x, mouse_y, getX(), getY()) < max(getWidth(), getHeight());
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

}
