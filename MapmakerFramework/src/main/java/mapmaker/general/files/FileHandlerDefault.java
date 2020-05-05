package mapmaker.general.files;

import java.awt.Component;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Simon Norup
 */
public final class FileHandlerDefault implements FileHandler {

    private static final String DEFAULT_ROOT = "./worldmaps";
    private static final String DEFAULT_FILE_EXTENSION = "txt"; // .p3map

    private final JFileChooser J_FILE_CHOOSER;

    public FileHandlerDefault() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        // create root folder if it doesn't exist
        File dir = new File(getRootPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // open dialog to select file
        Path rootPath = Paths.get(getRootPath()).normalize().toAbsolutePath();
        J_FILE_CHOOSER = new JFileChooser(rootPath.toString());
        J_FILE_CHOOSER.setAcceptAllFileFilterUsed(false);
        J_FILE_CHOOSER.addChoosableFileFilter(new FileNameExtensionFilter("." + getMapFileExtension(), getMapFileExtension()));
    }

    @Override
    public String getRootPath() {
        return DEFAULT_ROOT;
    }

    @Override
    public String getMapFileExtension() {
        return DEFAULT_FILE_EXTENSION;
    }

    @Override
    public boolean isOpenPathFound() {
        return J_FILE_CHOOSER.showOpenDialog((Component) null) == JFileChooser.APPROVE_OPTION;
    }

    @Override
    public boolean isSavePathFound() {
        return J_FILE_CHOOSER.showSaveDialog((Component) null) == JFileChooser.APPROVE_OPTION;
    }

    @Override
    public File getSelectedFile() {
        return J_FILE_CHOOSER.getSelectedFile();
    }

    @Override
    public boolean getOverwriteConfirmation(File file) {
        return getUserConfirmation("You are about to overwrite " + file.getName() + " - are you sure?", "Confirm Overwrite");
    }

    protected static boolean getUserConfirmation(String question, String windowTitle) {
        int result = JOptionPane.showConfirmDialog((Component) null, question, windowTitle, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return result == 0;
    }

}
