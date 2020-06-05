package mapmaker.entities;

import java.io.Serializable;

/**
 *
 * @author TeamOne
 */
public class Region implements Serializable {

    private Landmass area;
    private EntityInfo info;

    public Region(Landmass area, EntityInfo info) {
        this.area = area;
        this.info = info;
    }

    public Landmass getArea() {
        return area;
    }

    public void setArea(Landmass area) {
        this.area = area;
    }

    public EntityInfo getInfo() {
        return info;
    }

    public void setInfo(EntityInfo info) {
        this.info = info;
    }

}
