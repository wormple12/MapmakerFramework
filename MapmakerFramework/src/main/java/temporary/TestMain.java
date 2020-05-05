package temporary;

import mapmaker.entities.WorldMap;
import mapmaker.general.files.FileHandlerDefault;
import mapmaker.general.files.FileStorage;

/**
 *
 * @author Simon Norup
 */
public class TestMain {

    public static void main(String[] args) {
        FileStorage storage = new FileStorage(new FileHandlerDefault());
        WorldMap map = storage.attemptLoad(false);
        System.out.println(map);
    }

}
