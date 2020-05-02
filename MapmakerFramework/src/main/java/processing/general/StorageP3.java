package processing.general;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import mapmaker.entities.WorldMap;
import mapmaker.general.Storage;
import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class StorageP3 implements Storage {

    private final PApplet app;
    private Path latestMapPath = null;

    public StorageP3(PApplet app) {
        this.app = app;
    }

    @Override
    public WorldMap attemptLoad(Path path) throws FileSystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean attemptSave(WorldMap map, Path path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Path getLatestMapPath() {
        return latestMapPath;
    }

    @Override
    public void setLatestMapPath(Path path) {
        latestMapPath = path;
    }

}
