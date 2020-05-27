package processing.map;

import java.util.ArrayList;
import mapmaker.entities.WorldMap;
import mapmaker.map.Canvas;
import processing.core.PApplet;
import mapmaker.entities.EntityInfo;
import temporary.ProcessingMenuTest;

/**
 *
 * @author Simon Norup
 */
public class CanvasP3 implements Canvas {

    private final PApplet app;
    private WorldMap currentMap = null;

    public CanvasP3(PApplet app) {
        this.app = app;
    }

    @Override
    public WorldMap loadEmpty(EntityInfo info) {
        ((ProcessingMenuTest) app).setAppState(0);
        return new WorldMap(info, new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());
    }

    @Override
    public void loadWorldMap(WorldMap map) {
        ((ProcessingMenuTest) app).setAppState(0);
        app.loop();
        System.out.println(map.getFilePath());
        System.out.println(map.getInfo().getName());
    }

    @Override
    public WorldMap getCurrentMap() {
        return currentMap;
    }

    @Override
    public void setCurrentMap(WorldMap map) {
        currentMap = map;
    }

}
