package mapmaker.general.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import mapmaker.entities.WorldMap;
import mapmaker.general.Storage;

/**
 *
 * @author TeamOne
 */
public class FileStorage implements Storage {

    private final FileHandler fileHandler;
    private Path latestMapPath = null;

    public FileStorage() {
        this.fileHandler = new FileHandlerDefault();
    }

    public FileStorage(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Displays a user-friendly method of selecting a valid file path, and
     * attempts to convert the file at that path to a WorldMap object.
     *
     * @param useLatestMap whether to skip the file selection and simply load
     * the latest opened world map
     * @return the resulting map object, or null if no map was successfully
     * found and converted
     */
    @Override
    public WorldMap attemptLoad(boolean useLatestMap) {
        File file = null;
        if (useLatestMap && getLatestMapPath() != null) {
            file = getLatestMapPath().toFile();
        } else if (fileHandler.isOpenPathFound(getLatestMapPath())) {
            file = fileHandler.getSelectedFile();
        }
        if (file == null || !file.exists()) {
            return null;
        }
        WorldMap map = fileHandler.convertFileToMap(file);
        if (map != null) {
            map.setFilePath(file.toPath());
            setLatestMapPath(file.toPath());
        }
        return map;
    }

    /**
     * Displays a user-friendly method of selecting a valid file path, and
     * attempts to save a new world map file or override an existing one at that
     * path.
     *
     * @param map the world map to save
     * @return whether the map was successfully saved
     */
    @Override
    public boolean attemptSave(WorldMap map) {
        if (map.getFilePath() == null) {

            if (fileHandler.isSavePathFound(Paths.get(map.getInfo().getName()))) {
                File file = fileHandler.getSelectedFile();
                String filename = file.toString();
                String fileExtension = "." + fileHandler.getMapFileExtension();
                if (!filename.endsWith(fileExtension)) {
                    filename += fileExtension;
                    file = new File(filename);
                }
                if (!file.exists() || fileHandler.getOverwriteConfirmation(file)) {
                    map.setFilePath(file.toPath());
                    return doSave(map);
                }
            }
            return false;

        } else {
            return doSave(map);
        }
    }

    private boolean doSave(WorldMap map) {
        File file = map.getFilePath().toFile();
        try {
            file.createNewFile(); //only creates if not already existing
        } catch (IOException ex) {
            return false;
        }
        fileHandler.overwriteFileWithMap(file, map);
        setLatestMapPath(file.toPath());
        return true;
    }

    /**
     * @return the file path for the latest opened world map, or null if none
     * was found
     */
    public final Path getLatestMapPath() {
        return latestMapPath;
    }

    public final void setLatestMapPath(Path path) {
        latestMapPath = path;
    }

}
