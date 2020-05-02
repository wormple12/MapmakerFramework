package mapmaker.general;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import mapmaker.entities.WorldMap;

/**
 *
 * @author Simon Norup
 */
public interface Storage {

    /**
     * Attempts to convert a file at a given file path to a Map object.
     *
     * @param path the file path from which to load map data
     * @return the resulting map object
     * @throws java.nio.file.FileSystemException, if the given path could not be
     * loaded as a map
     */
    public WorldMap attemptLoad(Path path) throws FileSystemException;

    /**
     * Attempts to save a world map at a given file path.
     *
     * @param map the world map to save
     * @param path the file path to save it at
     * @return whether the map was successfully saved
     */
    public boolean attemptSave(WorldMap map, Path path);

    /**
     * @return the file path for the latest opened world map, or null if none
     * was found
     */
    public Path getLatestMapPath();

    public void setLatestMapPath(Path path);

}
