package mapmaker.entities.sprites;

import java.io.Serializable;

/**
 *
 * @author Simon Norup
 */
public class Sprite implements Serializable {

    private String spritePath;
    private float w, h;

    public Sprite(String spritePath, float w, float h) {
        this.spritePath = spritePath;
        this.w = w;
        this.h = h;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    public void setSize(float width, float height) {
        this.w = width;
        this.h = height;
    }

}
