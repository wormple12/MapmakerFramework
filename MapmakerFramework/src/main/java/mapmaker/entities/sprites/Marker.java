package mapmaker.entities.sprites;

import mapmaker.entities.EntityInfo;

/**
 *
 * @author TeamOne
 */
public class Marker extends Sprite {

    private EntityInfo info;
    private float x, y;

    public Marker(EntityInfo info, String spritePath, float x, float y, float w, float h) {
        super(spritePath, w, h);
        this.info = info;
        this.x = x;
        this.y = y;
    }

    public EntityInfo getInfo() {
        return info;
    }

    public void setInfo(EntityInfo info) {
        this.info = info;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setCoordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void incrementCoordinates(float x, float y) {
        this.x += x;
        this.y += y;
    }

}
