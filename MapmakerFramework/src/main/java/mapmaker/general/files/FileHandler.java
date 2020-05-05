package mapmaker.general.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mapmaker.entities.WorldMap;

/**
 *
 * @author Simon Norup
 */
public interface FileHandler {

    public String getRootPath();

    public String getMapFileExtension();

    public boolean isOpenPathFound();

    public boolean isSavePathFound();

    public File getSelectedFile();

    default public WorldMap convertFileToMap(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            try (ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                return (WorldMap) objectIn.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    default public void overwriteFileWithMap(File file, WorldMap map) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(map);
            }
        } catch (IOException ex) {
            throw new UnsupportedOperationException("FileNotFoundException/IOException has not been handled yet.");
        }
    }

    public boolean getOverwriteConfirmation(File file);

}
