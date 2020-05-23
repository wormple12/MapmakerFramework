package temporary;

import mapmaker.entities.WorldMap;
import mapmaker.general.files.FileStorage;

/**
 *
 * @author Simon Norup
 */
public class FileLoadTest {

    public static void main(String[] args) {
        FileStorage storage = new FileStorage();
        WorldMap map = storage.attemptLoad(false);
        System.out.println(map.getInfo().getName());
        System.out.println(map.getFilePath().toString());
        System.out.println(map.getFileName());
        System.out.println(map.getRegions().get(0).getBiome());
        System.out.println(map.getLocations().get(0).getSpritePath());
    }

}
