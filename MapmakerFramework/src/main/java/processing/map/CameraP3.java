
package processing.map;

import mapmaker.map.Camera;
import processing.ProcessingMapmaker;

/**
 *
 * @author Simon Norup
 */
public class CameraP3 implements Camera {
    
    private final ProcessingMapmaker app;

    public CameraP3(ProcessingMapmaker app) {
        this.app = app;
    }

    @Override
    public void drag(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void zoom(double factor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
