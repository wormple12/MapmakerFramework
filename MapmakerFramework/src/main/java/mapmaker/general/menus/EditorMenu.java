package mapmaker.general.menus;

import mapmaker.general.UserRole;
import mapmaker.entities.WorldMap;
import mapmaker.general.Storage;
import mapmaker.map.Canvas;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup Example of wishful programming. See superclass as well.
 */
abstract public class EditorMenu extends StorageMenu {

    public EditorMenu(Storage storage, Canvas canvas) {
        super(storage, canvas);
    }

    /**
     * Creates a new, empty map, using user-defined core information. Loads an
     * empty canvas, and links it to map file at a file path of your choice. You
     * can create a new map file in this way, or override existing ones.
     */
    public final void newMap() {
        EntityInfo info = initMapInfo();
        switchUserRole();
        WorldMap map = canvas.loadEmpty(info);
        canvas.setCurrentMap(map);
        saveMap();
    }

    /**
     * Requests that the user defines initial core information about the map.
     *
     * @return the user-defined map data
     */
    abstract protected EntityInfo initMapInfo();

    @Override
    protected void switchUserRole() {
        UserRole.setCurrentRole(UserRole.EDITOR);
    }

}
