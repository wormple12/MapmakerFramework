package processing.viewer;

import java.util.List;
import mapmaker.entities.Region;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import mapmaker.viewer.Viewer;
import processing.core.PApplet;
import processing.map.ui.ModeUI_P3;
import processing.map.ui.SpriteUI_P3;
import processing.entities.sprites.UserMarkerP3;
import processing.map.CanvasP3;

/**
 *
 * @author Simon Norup
 */
public class ViewerP3 implements Viewer {

    private final PApplet app;
    private final ModeUI_P3 modeUI;
    private final SpriteUI_P3 userMarkerUI;
    private final CanvasP3 canvas;

    private UserMarkerP3 selectedMarker = null;

    public ViewerP3(CanvasP3 canvas, ModeUI_P3 modeUI, PApplet app) {
        this.app = app;
        this.modeUI = modeUI;
        this.userMarkerUI = new SpriteUI_P3("sprites/markers/", app);
        this.canvas = canvas;
    }

    public void displayUI() {
        userMarkerUI.display();
    }

    public void run() {
        if (selectedMarker != null) {
            dragMarker(selectedMarker, app.mouseX, app.mouseY);
        }
    }

    public void mousePressed() {
        selectedMarker = attemptSelectMarker(app.mouseX, app.mouseY);
    }

    public void mouseReleased() {
        if (selectedMarker != null) {
            dropMarker(selectedMarker, app.mouseX, app.mouseY);
        }
    }

    public UserMarkerP3 getSelectedMarker() {
        return selectedMarker;
    }

    public UserMarkerP3 attemptSelectMarker(int x, int y) {
        UserMarkerP3 markerP3 = (UserMarkerP3) userMarkerUI.attemptSelectSpriteType();
        if (markerP3 != null) {
            return markerP3;
        } else {
            for (UserMarker marker : canvas.getCurrentMap().getMarkers()) {
                markerP3 = (UserMarkerP3) marker;
                if (markerP3.over(x, y)) {
                    return markerP3;
                }
            }
        }
        return null;
    }

    @Override
    public void dragMarker(UserMarker marker, int x, int y) {
        UserMarkerP3 markerP3 = (UserMarkerP3) marker;
        if (userMarkerUI.getSelectedSpriteType() != null) {
            app.image(markerP3.getImage(), x, y, markerP3.getWidth(), markerP3.getHeight());
        }
        markerP3.incrementCoordinates(x - app.pmouseX, y - app.pmouseY);
    }

    @Override
    public void dropMarker(UserMarker marker, int x, int y) {
        UserMarkerP3 markerP3 = (UserMarkerP3) marker;
//        locP3.setCoordinates(x, y);
        if (userMarkerUI.getSelectedSpriteType() != null) {
            canvas.getCurrentMap().addMarker(markerP3);
        }
        userMarkerUI.selectSpriteType(null);
        selectedMarker = null;
    }

    @Override
    public void viewMarkerInfo(UserMarker marker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editMarkerInfo(UserMarker original, UserMarker updated) {
        final List<UserMarker> markers = canvas.getCurrentMap().getMarkers();
        if (markers.contains(original)) {
            // DELETE LOCATION
            if (updated == null) {
                markers.remove(original);
                selectedMarker = null;
            }
        }
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
