package processing.entities.sprites;

import java.util.Objects;
import mapmaker.entities.sprites.Marker;
import mapmaker.entities.sprites.UserMarker;
import static processing.core.PApplet.dist;
import static processing.core.PApplet.max;
import processing.core.PImage;

/**
 *
 * @author rasmu
 */
public class UserMarkerP3 extends UserMarker {

    private PImage image;

    public UserMarkerP3(UserMarkerInfoP3 info, String spritePath, float x, float y, float width, float height, PImage image) {
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
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.getInfo());
        hash = 23 * hash + Float.floatToIntBits(this.getX());
        hash = 23 * hash + Float.floatToIntBits(this.getY());
        hash = 23 * hash + Float.floatToIntBits(this.getWidth());
        hash = 23 * hash + Float.floatToIntBits(this.getHeight());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marker other = (Marker) obj;
        if (Float.floatToIntBits(this.getX()) != Float.floatToIntBits(other.getX())) {
            return false;
        }
        if (Float.floatToIntBits(this.getY()) != Float.floatToIntBits(other.getY())) {
            return false;
        }
        if (Float.floatToIntBits(this.getWidth()) != Float.floatToIntBits(other.getWidth())) {
            return false;
        }
        if (Float.floatToIntBits(this.getWidth()) != Float.floatToIntBits(other.getHeight())) {
            return false;
        }
        if (!Objects.equals(this.getInfo(), other.getInfo())) {
            return false;
        }
        return true;
    }

}
