package mapmaker.general.menus;

import mapmaker.general.UserRole;
import mapmaker.map.Canvas;
import mapmaker.entities.WorldMap;
import mapmaker.general.Storage;

/**
 *
 * @author Simon Norup
 */
abstract public class StorageMenu implements SubMenu {

    final Storage storage;
    final Canvas canvas;

    public StorageMenu(Storage storage, Canvas canvas) {
        this.storage = storage;
        this.canvas = canvas;
    }

    /**
     * Loads a world map at a file path of your choice on the canvas.
     *
     * @param loadLatest true if you want to load the latest opened map, instead
     * of selecting a map file manually.
     */
    public final void loadMap(boolean loadLatest) {
        WorldMap mapToLoad;
        if (loadLatest && canvas.getCurrentMap() != null) {
            mapToLoad = canvas.getCurrentMap();
        } else {
            mapToLoad = storage.attemptLoad(loadLatest);
        }
        if (mapToLoad != null) {
            canvas.loadWorldMap(mapToLoad);
            canvas.setCurrentMap(mapToLoad);
            switchUserRole();
        }
    }

    /**
     * Saves the currently loaded map to the file path defined for it. If no
     * file path was defined, it allows the user to define a new one for it.
     */
    public final void saveMap() {
        WorldMap currentMap = canvas.getCurrentMap();
        storage.attemptSave(currentMap);
    }

    /**
     * Switches to editor mode or viewer mode depending on the type of submenu
     * you call the method from.
     */
    protected void switchUserRole() {
        UserRole.setCurrentRole(UserRole.VIEWER);
    }

}
