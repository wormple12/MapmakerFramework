package mapmaker.general.menus;

import mapmaker.general.UserRole;
import mapmaker.entities.WorldMap;
import mapmaker.entities.MapInfo;

/**
 *
 * @author Simon Norup
 */
abstract public class EditorMenu extends StorageMenu {

    /**
     * Creates a new, empty map, using user-defined core information. Loads an
     * empty canvas, and links it to map file at a file path of your choice. You
     * can create a new map file in this way, or override existing ones.
     */
    public void newMap() {
        MapInfo info = initMapInfo();
        WorldMap map = canvas.loadEmpty(info);
        map.setFilePath(storage.selectPath());
        canvas.setCurrentMap(map);
        saveMap();
        switchUserRole();
    }

    /**
     * Requests that the user defines initial core information about the map.
     *
     * @return the user-defined map data
     */
    abstract protected MapInfo initMapInfo();

    @Override
    protected void switchUserRole() {
        UserRole.setCurrentRole(UserRole.EDITOR);
    }

}
