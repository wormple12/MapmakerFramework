
package processing.map;

import mapmaker.map.Camera;
import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class CameraP3 implements Camera {
    
    private final PApplet app;

    public CameraP3(PApplet app) {
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
