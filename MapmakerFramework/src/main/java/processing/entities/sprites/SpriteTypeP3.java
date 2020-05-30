package processing.entities.sprites;

import mapmaker.entities.sprites.Sprite;
import processing.core.PImage;
import processing.core.PVector;

/**
 *
 * @author Simon Norup
 */
public class SpriteTypeP3 extends Sprite {

    private final PVector vector;
    private final PImage image;

    public SpriteTypeP3(PVector vector, PImage image, String spritePath, float w, float h) {
        super(spritePath, w, h);
        this.vector = vector;
        this.image = image;
    }

    public PVector getVector() {
        return vector;
    }

    public PImage getImage() {
        return image;
    }

}
