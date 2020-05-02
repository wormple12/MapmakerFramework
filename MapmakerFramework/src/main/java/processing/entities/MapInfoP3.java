package processing.entities;

import mapmaker.entities.MapInfo;

/**
 *
 * @author Simon Norup
 */
public class MapInfoP3 implements MapInfo {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
