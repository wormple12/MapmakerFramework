package mapmaker.general.menus;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import mapmaker.general.UserRole;
import mapmaker.map.Canvas;
import mapmaker.entities.WorldMap;
import mapmaker.general.FileHandler;

/**
 *
 * @author Simon Norup
 */
abstract public class StorageMenu implements SubMenu {

    FileHandler storage;
    Canvas canvas;

    /**
     * Loads a world map at a file path of your choice on the canvas.
     *
     * @param loadLatest true if you want to load the latest opened map, instead
     * of selecting a map file manually.
     */
    public void loadMap(boolean loadLatest) {
        try {
            Path path = storage.getLatestMapPath();
            if (!loadLatest || path != null) {
                path = storage.selectPath();
                storage.setLatestMapPath(path);
            }
            WorldMap map = storage.attemptLoad(path);
            map.setFilePath(path);
            canvas.loadWorldMap(map);
            canvas.setCurrentMap(map);
            switchUserRole();
        } catch (FileSystemException e) {
            storage.handleFileSystemException(e);
        }
    }

    /**
     * Saves the currently loaded map to the file path defined for it.
     */
    public void saveMap() {
        WorldMap currentMap = canvas.getCurrentMap();
        storage.attemptSave(currentMap, currentMap.getFilePath());
        storage.setLatestMapPath(currentMap.getFilePath());
    }

    /**
     * Switches to editor mode or viewer mode depending on the type of submenu
     * you call the method from
     */
    protected void switchUserRole() {
        UserRole.setCurrentRole(UserRole.VIEWER);
    }

}
