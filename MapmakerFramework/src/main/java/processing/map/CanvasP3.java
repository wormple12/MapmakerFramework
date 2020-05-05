
package processing.map;

import mapmaker.entities.WorldMap;
import mapmaker.map.Canvas;
import processing.core.PApplet;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public class CanvasP3 implements Canvas {
    
    private final PApplet app;

    public CanvasP3(PApplet app) {
        this.app = app;
    }

    @Override
    public WorldMap loadEmpty(EntityInfo info) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadWorldMap(WorldMap map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WorldMap getCurrentMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCurrentMap(WorldMap map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
