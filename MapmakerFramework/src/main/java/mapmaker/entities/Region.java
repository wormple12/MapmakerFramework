package mapmaker.entities;

/**
 *
 * @author Simon Norup
 */
public class Region {

    private Landmass area;
    private EntityInfo info;
    private Biome biome;

    public Region(Landmass area, EntityInfo info, Biome biome) {
        this.area = area;
        this.info = info;
        this.biome = biome;
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

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

}
