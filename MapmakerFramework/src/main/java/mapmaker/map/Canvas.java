package mapmaker.map;

import mapmaker.entities.WorldMap;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public interface Canvas {

    /**
     * Creates and displays a clean world map on the canvas using the given
     * data, enabling the user to interact with it.
     *
     * @param info the initial map info
     * @return the resulting Map object
     */
    public WorldMap loadEmpty(EntityInfo info);

    /**
     * Displays the given world map on the canvas, enabling the user to interact
     * with it.
     *
     * @param map the map to display
     */
    public void loadWorldMap(WorldMap map);

    /**
     * @return the currently loaded world map
     */
    public WorldMap getCurrentMap();

    /**
     * Updates the singular world map that should be remembered as the currently
     * loaded map.
     *
     * @param map the map that is currently loaded
     */
    public void setCurrentMap(WorldMap map);
}
