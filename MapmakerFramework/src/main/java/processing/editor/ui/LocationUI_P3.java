
package processing.editor.ui;

import java.util.List;
import mapmaker.editor.ui.LocationUI;
import mapmaker.entities.sprites.Location;
import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class LocationUI_P3 implements LocationUI {
    
    private final PApplet app;

    public LocationUI_P3(PApplet app) {
        this.app = app;
    }

    @Override
    public void display(List<Location> locationTypes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> fetchAvailableTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectLocationType(Location locationType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
