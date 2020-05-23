package temporary;

import java.nio.file.Paths;
import java.util.Arrays;
import mapmaker.entities.*;
import mapmaker.entities.sprites.*;
import mapmaker.general.files.FileStorage;

/**
 *
 * @author Simon Norup
 */
public class FileLoadSave {

    public static void main(String[] args) {
        FileStorage storage = new FileStorage();
        WorldMap map = new WorldMap(new EntityInfo("TestMap"),
                Arrays.asList(new Region(new Landmass() {
                }, new EntityInfo("TestRegion"), Biome.HILLS)),
                Arrays.asList(new Location(new EntityInfo("TestLocation"), Paths.get("/"), 5, 10)),
                Arrays.asList(new UserMarker(new EntityInfo("TestMarker"), Paths.get("/"), 0, -4)),
                Arrays.asList(new Route() {
                }));
        storage.attemptSave(map);
    }

}
