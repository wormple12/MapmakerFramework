package processing.map.controllers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import mapmaker.entities.EntityInfo;
import mapmaker.general.Mode;
import mapmaker.entities.Region;
import mapmaker.general.UserRole;
import processing.core.PApplet;
import processing.general.menus.EditorMenuP3;
import processing.map.ui.ModeUI_P3;
import processing.viewer.ViewerP3;
import processing.ProcessingMapmaker;
import processing.editor.IEditorP3;
import processing.entities.sprites.LocationP3;
import processing.general.events.PEventListener;
import processing.general.menus.ViewerMenuP3;
import processing.map.CanvasP3;
import processing.map.ui.InfoUI_P3;

/**
 *
 * @author TeamOne
 */
public class MapHotkeyManagerP3 implements PEventListener {

    private final ProcessingMapmaker app;
    private final IEditorP3 editor;
    private final ViewerP3 viewer;
    private final CanvasP3 canvas;
    private final ModeUI_P3 modeUI;
    private final InfoUI_P3 infoUI;
    private final EditorMenuP3 editorMenu;
    private final ViewerMenuP3 viewerMenu;

    private Boolean isTyping = false;

    public MapHotkeyManagerP3(ProcessingMapmaker app, IEditorP3 editor, ViewerP3 viewer, CanvasP3 canvas, ModeUI_P3 modeUI, InfoUI_P3 infoUI, EditorMenuP3 menu, ViewerMenuP3 viewerMenu) {
        this.app = app;
        this.editor = editor;
        this.viewer = viewer;
        this.canvas = canvas;
        this.modeUI = modeUI;
        this.infoUI = infoUI;
        this.editorMenu = menu;
        this.viewerMenu = viewerMenu;
    }

    @Override
    public void keyPressed(processing.event.KeyEvent evt) {
        if (evt.isControlDown() && !modeUI.isInCTRLMode()) {
            modeUI.setPreviousMode(modeUI.getCurrentMode());
            modeUI.switchMode(Mode.CONTROL);

        } else if (isTyping) {
            if (app.keyCode == PApplet.ENTER) {
                isTyping = false;
                if (modeUI.isCurrentMode(Mode.LANDMASS, Mode.WATER)) {
                    Region selectedRegion = canvas.getCurrentMap().getRegion(editor.getLayer());
                    editor.editRegion(selectedRegion, new EntityInfo(infoUI.getInfoText()));
                } else if (modeUI.isCurrentMode(Mode.MARKER)) {
                    LocationP3 selectedLocation = editor.getSelectedLocation();
                    editor.editLocationInfo(selectedLocation, new EntityInfo(infoUI.getInfoText()));
                }
                infoUI.setInfoText(null);
            } else if (app.keyCode != PApplet.CONTROL && app.keyCode != PApplet.SHIFT && app.keyCode != PApplet.ALT) {
                infoUI.writeToText(app.keyCode);
            }

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
                        if (modeUI.isCurrentMode(Mode.LANDMASS, Mode.WATER)) {
                            editor.switchRegionLayer();
                        } else if (modeUI.isCurrentMode(Mode.ROUTE)) {
                            editor.switchRouteLayer();
                        }
                        break;

                    // EDITING ENTITY INFO
                    case 'T':
                        if (modeUI.isCurrentMode(Mode.LANDMASS, Mode.WATER)) {
                            Region selectedRegion = canvas.getCurrentMap().getRegion(editor.getLayer());
                            if (selectedRegion != null) {
                                infoUI.setInfoText(selectedRegion.getInfo().getName());
                                isTyping = true;
                            }
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

                    case 'T':
                        if (modeUI.isCurrentMode(Mode.MARKER)) {
                            if (UserRole.getCurrentRole() == UserRole.EDITOR && editor.getSelectedLocation() != null) {
                                infoUI.setInfoText(editor.getSelectedLocation().getInfo().getName());
                                isTyping = true;
                            } else if (UserRole.getCurrentRole() == UserRole.VIEWER && viewer.getSelectedMarker() != null) {
                                infoUI.setInfoText(viewer.getSelectedMarker().getInfo().getName());
                                isTyping = true;
                            }
                        }
                        break;

                    // DELETING MARKERS
                    case PApplet.BACKSPACE:
                    case PApplet.DELETE:
                        if (modeUI.isCurrentMode(Mode.MARKER)) {
                            if (UserRole.getCurrentRole() == UserRole.EDITOR && editor.getSelectedLocation() != null) {
                                editor.editLocationInfo(editor.getSelectedLocation(), null);
                            } else if (UserRole.getCurrentRole() == UserRole.VIEWER && viewer.getSelectedMarker() != null) {
                                viewer.editMarkerInfo(viewer.getSelectedMarker(), null);
                            }
                        }
                        break;

                    // SAVING/LOADING
                    case 'S':
                        if (modeUI.isInCTRLMode()) { // holding CTRL
                            editorMenu.saveMap();
                            new Robot().keyRelease(KeyEvent.VK_CONTROL); // 
                        }
                        break;
                    case 'O':
                        if (modeUI.isInCTRLMode()) { // holding CTRL
                            if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                                editorMenu.loadMap(false);
                            } else {
                                viewerMenu.loadMap(false);
                            }
                            new Robot().keyRelease(KeyEvent.VK_CONTROL);
                        }
                        break;

                    case PApplet.ESC:
                        app.setAppState(app.STATE_MENU_MAIN);
                        break;

                    default:
                    // ignore unused keys
                }
            } catch (AWTException e) {
            }
        }
    }

    @Override
    public void keyReleased(processing.event.KeyEvent evt) {
        if (!evt.isControlDown() && modeUI.isInCTRLMode()) {
            modeUI.switchMode(modeUI.getPreviousMode());
            modeUI.setPreviousMode(null);
        }
    }

}
