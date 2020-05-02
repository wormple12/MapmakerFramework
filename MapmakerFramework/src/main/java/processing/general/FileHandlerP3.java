package processing.general;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import mapmaker.entities.WorldMap;
import processing.core.PApplet;
import mapmaker.general.FileHandler;

/**
 *
 * @author Simon Norup
 */
public class FileHandlerP3 implements FileHandler {

    private final PApplet app;
    private Path latestMapPath = null;

    public FileHandlerP3(PApplet app) {
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

    @Override
    public Path selectPath() {
        // open project file folder to get path and return file
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleFileSystemException(FileSystemException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
