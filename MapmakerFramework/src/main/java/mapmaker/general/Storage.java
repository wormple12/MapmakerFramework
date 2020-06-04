package mapmaker.general;

import mapmaker.entities.WorldMap;

/**
 *
 * @author TeamOne
 */
public interface Storage {

    public WorldMap attemptLoad(boolean useLatestMap);

    public boolean attemptSave(WorldMap map);

}
