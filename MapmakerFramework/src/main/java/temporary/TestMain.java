package temporary;

import java.nio.file.Path;
import processing.general.FileHandlerP3;

/**
 *
 * @author Simon Norup
 */
public class TestMain {

    public static void main(String[] args) {
        FileHandlerP3 fileHandler = new FileHandlerP3(null);
        Path path = fileHandler.selectPath();
        System.out.println(path);
    }

}
