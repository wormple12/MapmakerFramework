package mapmaker.entities.sprites;

import java.io.Serializable;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public class Sprite implements Serializable {

    private EntityInfo info;
    private String spritePath;
    private float x, y, w, h;

    public Sprite(EntityInfo info, String spritePath, float x, float y, float w, float h) {
        this.info = info;
        this.spritePath = spritePath;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public EntityInfo getInfo() {
        return info;
    }

    public void setInfo(EntityInfo info) {
        this.info = info;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    public void setCoordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void incrementCoordinates(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void setSize(float width, float height) {
        this.w = width;
        this.h = height;
    }

}
