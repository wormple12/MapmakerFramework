package processing.general;

import java.awt.Component;
import java.io.File;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
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

    private static final String ROOT = "./worldmaps";
    private static final String MAP_FILE_EXTENSION = "txt"; // .p3map

    public FileHandlerP3(PApplet app) {
        this.app = app;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
    }

    @Override
    public Path selectPath() {
        // create root folder if it doesn't exist
        File dir = new File(ROOT);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // open dialog to select file
        Path rootPath = Paths.get(ROOT).normalize().toAbsolutePath();
        JFileChooser chooser = new JFileChooser(rootPath.toString());
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("." + MAP_FILE_EXTENSION, MAP_FILE_EXTENSION));
        if (chooser.showOpenDialog((Component) null) == JFileChooser.APPROVE_OPTION) {
            Path path = chooser.getSelectedFile().toPath();
            return path;
        }
        return null;
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
    public void handleFileSystemException(FileSystemException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
