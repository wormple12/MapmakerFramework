package mapmaker.general;

import mapmaker.entities.WorldMap;

/**
 *
 * @author Simon Norup
 */
public interface Storage {

    public WorldMap attemptLoad(boolean useLatestMap);

    public boolean attemptSave(WorldMap map);

}
