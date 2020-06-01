package processing.map;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import mapmaker.editor.Mode;
import mapmaker.general.UserRole;
import processing.core.PApplet;
import processing.general.menus.EditorMenuP3;
import processing.map.ui.ModeUI_P3;
import processing.viewer.ViewerP3;
import processing.ProcessingMapmaker;
import processing.editor.IEditorP3;

/**
 *
 * @author Simon Norup
 */
public class HotkeyManagerP3 {

    private final PApplet app;
    private final IEditorP3 editor;
    private final ViewerP3 viewer;
    private final ModeUI_P3 modeUI;
    private final EditorMenuP3 menu;

    public HotkeyManagerP3(PApplet app, IEditorP3 editor, ViewerP3 viewer, ModeUI_P3 modeUI, EditorMenuP3 menu) {
        this.app = app;
        this.editor = editor;
        this.viewer = viewer;
        this.modeUI = modeUI;
        this.menu = menu;
    }

    public void keyPressed(processing.event.KeyEvent evt) {
        if (evt.isControlDown() && modeUI.getCurrentMode() != Mode.CAMERA) {
            modeUI.setPreviousMode(modeUI.getCurrentMode());
            modeUI.switchMode(Mode.CAMERA);
        } else {
            if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                switch (app.keyCode) {
                    case '1':
                        modeUI.switchMode(Mode.LANDMASS);
                        editor.setLayer(0);
                        break;
                    case '2':
                        modeUI.switchMode(Mode.WATER);
                        editor.setLayer(-1);
                        break;
                    case '3':
                        modeUI.switchMode(Mode.MARKER);
                        editor.setLayer(0);
                        break;
                    case '4':
                        modeUI.switchMode(Mode.ROUTE);
                        editor.setLayer(0);
                        break;

                    // LAYER SWITCH FOR REGIONS AND ROUTES
                    case 'L':
                        switch (modeUI.getCurrentMode()) {
                            case LANDMASS:
                            case WATER:
                                editor.switchRegionLayer();
                                break;
                            case ROUTE:
                                editor.switchRouteLayer();
                                break;
                            default:
                            // ignore if in another mode
                        }
                        break;

                    // SWITCHING COLOR BRUSH
                    case 'Q':
                        editor.switchColorBrush();
                        break;

                    default:
                    //
                }
            }
            try {
                switch (app.keyCode) {

                    // DELETING LOCATIONS
                    case PApplet.BACKSPACE:
                    case PApplet.DELETE:
                        if (modeUI.getCurrentMode() == Mode.MARKER) {
                            if (UserRole.getCurrentRole() == UserRole.EDITOR && editor.getSelectedLocation() != null) {
                                editor.editLocationInfo(editor.getSelectedLocation(), null);
                            } else if (UserRole.getCurrentRole() == UserRole.VIEWER && viewer.getSelectedMarker() != null) {
                                viewer.editMarkerInfo(viewer.getSelectedMarker(), null);
                            }
                        }

                    // SAVING/LOADING
                    case 'S':
                        if (modeUI.getCurrentMode() == Mode.CAMERA) { // holding CTRL
                            menu.saveMap();
                            new Robot().keyRelease(KeyEvent.VK_CONTROL); // 
                        }
                        break;
                    case 'O':
                        if (modeUI.getCurrentMode() == Mode.CAMERA) { // holding CTRL
                            menu.loadMap(false);
                            new Robot().keyRelease(KeyEvent.VK_CONTROL);
                        }
                        break;

                    case PApplet.ESC:
                        app.key = 0;
                        ((ProcessingMapmaker) app).setAppState(1);
                        break;

                    default:
                    // ignore unused keys
                }
            } catch (AWTException e) {
            }
        }
    }

    public void keyReleased(processing.event.KeyEvent evt) {
        if (!evt.isControlDown() && modeUI.getCurrentMode() == Mode.CAMERA) {
            modeUI.switchMode(modeUI.getPreviousMode());
            modeUI.setPreviousMode(null);
        }
    }

}
