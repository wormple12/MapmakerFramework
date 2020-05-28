package processing.entities.sprites;

import processing.core.PImage;
import processing.core.PVector;

/**
 *
 * @author Simon Norup
 */
public class SpriteTypeP3 {

    private final PVector vector;
    private final PImage image;
    private final String spritePath;
    private final float w, h;

    public SpriteTypeP3(PVector vector, PImage image, String spritePath, float w, float h) {
        this.vector = vector;
        this.image = image;
        this.spritePath = spritePath;
        this.w = w;
        this.h = h;
    }

    public PVector getVector() {
        return vector;
    }

    public PImage getImage() {
        return image;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

}
