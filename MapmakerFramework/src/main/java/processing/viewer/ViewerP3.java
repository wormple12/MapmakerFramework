package processing.viewer;

import mapmaker.entities.Region;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import mapmaker.viewer.Viewer;
import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class ViewerP3 implements Viewer {

    private final PApplet app;

    public ViewerP3(PApplet app) {
        this.app = app;
    }

    @Override
    public void dragMarker(UserMarker marker, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dropMarker(UserMarker marker, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewMarkerInfo(UserMarker marker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editMarkerInfo(UserMarker original, UserMarker updated) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewRegionInfo(Region region) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewLocationInfo(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
