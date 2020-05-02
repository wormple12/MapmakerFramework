package mapmaker.general.menus;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import mapmaker.general.Storage;
import mapmaker.general.UserRole;
import mapmaker.map.Canvas;
import mapmaker.map.Map;

/**
 *
 * @author Simon Norup
 */
abstract public class StorageMenu implements SubMenu {

    Storage storage;
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
                path = selectPath();
                storage.setLatestMapPath(path);
            }
            Map map = storage.attemptLoad(path);
            map.setFilePath(path);
            canvas.loadWorldMap(map);
            canvas.setCurrentMap(map);
            switchUserRole();
        } catch (FileSystemException e) {
            handleFileSystemException(e);
        }
    }

    /**
     * Saves the currently loaded map to the file path defined for it.
     */
    public void saveMap() {
        Map currentMap = canvas.getCurrentMap();
        storage.attemptSave(currentMap, currentMap.getFilePath());
        storage.setLatestMapPath(currentMap.getFilePath());
    }

    /**
     * Displays a user-friendly method of selecting a valid file path for a
     * world map. This can be used to create new files, and load or override
     * existing ones.
     *
     * @return the file path selected
     */
    abstract protected Path selectPath(); // open project file folder to get path and return file

    /**
     * Handles situations where a selected file fails to load.
     *
     * @param e the exception to handle
     */
    abstract protected void handleFileSystemException(FileSystemException e);

    /**
     * Switches to editor mode or viewer mode depending on the type of submenu
     * you call the method from
     */
    protected void switchUserRole() {
        UserRole.setCurrentRole(UserRole.VIEWER);
    }

}
