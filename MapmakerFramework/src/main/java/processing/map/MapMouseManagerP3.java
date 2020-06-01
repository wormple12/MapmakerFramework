package processing.map;

import mapmaker.editor.Mode;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.general.UserRole;
import processing.core.PApplet;
import processing.editor.IEditorP3;
import processing.map.ui.ModeUI_P3;
import processing.viewer.ViewerP3;

/**
 *
 * @author Simon Norup
 */
public class MapMouseManagerP3 {

    private final PApplet app;
    private final IEditorP3 editor;
    private final ViewerP3 viewer;
    private final CanvasP3 canvas;
    private final ModeUI_P3 modeUI;

    private final float brushRadius = 100;

    public MapMouseManagerP3(PApplet app, IEditorP3 editor, ViewerP3 viewer, CanvasP3 canvas, ModeUI_P3 modeUI) {
        this.app = app;
        this.editor = editor;
        this.viewer = viewer;
        this.canvas = canvas;
        this.modeUI = modeUI;
    }

    public void mousePressed() {
        if (UserRole.getCurrentRole() == UserRole.EDITOR) {
            if (modeUI.isCurrentMode(Mode.MARKER)) {
                editor.attemptSelectLocation(app.mouseX, app.mouseY);
            }

            if (modeUI.isCurrentOrPreviousMode(Mode.ROUTE)) {
                Route optionalRoute = canvas.getCurrentMap().getRoute(editor.getLayer());
                if (modeUI.isInCTRLMode()) { // TEMPORARY; should probably instead erase the selected point in the same manner as with markers
                    editor.eraseRoute(app.mouseX, app.mouseY, 5, optionalRoute);
                } else {
                    editor.drawRoute(app.mouseX, app.mouseY, 5, optionalRoute);
                }
            }

        } else {
            viewer.attemptSelectMarker(app.mouseX, app.mouseY);
        }
    }

    public void mouseReleased() {
        if (UserRole.getCurrentRole() == UserRole.EDITOR) {
            if (modeUI.isCurrentMode(Mode.LANDMASS, Mode.WATER)) {
                editor.updateRegions();
            }
            if (editor.getSelectedLocation() != null) {
                editor.dropLocation(editor.getSelectedLocation(), app.mouseX, app.mouseY);
            }
            if (modeUI.isCurrentOrPreviousMode(Mode.ROUTE)) { // TEMPORARY; should probably instead erase the selected point in the same manner as with markers
                editor.updateRoute();
            }

        } else {
            if (viewer.getSelectedMarker() != null) {
                viewer.dropMarker(viewer.getSelectedMarker(), app.mouseX, app.mouseY);
            }
        }
    }

    public void mouseDragged() {
        if (UserRole.getCurrentRole() == UserRole.EDITOR) {
            if (modeUI.isCurrentMode(Mode.LANDMASS, Mode.WATER)) {
                Region optionalRegion = canvas.getCurrentMap().getRegion(editor.getLayer());
                if (modeUI.isCurrentMode(Mode.LANDMASS)) {
                    editor.drawLandmass(app.mouseX, app.mouseY, brushRadius, optionalRegion);
                } else {
                    if (optionalRegion == null) {
                        editor.drawWater(app.mouseX, app.mouseY, brushRadius);
                    } else {
                        editor.deleteLandmassFromRegion(app.mouseX, app.mouseY, brushRadius, optionalRegion);
                    }
                }
            }
        }
    }

}
