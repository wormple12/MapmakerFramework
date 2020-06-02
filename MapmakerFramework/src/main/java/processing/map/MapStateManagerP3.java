package processing.map;

import mapmaker.editor.EditorProxy;
import mapmaker.editor.Mode;
import mapmaker.general.UserRole;
import processing.ProcessingMapmaker;
import processing.editor.EditorP3;
import processing.editor.IEditorP3;
import processing.general.menus.EditorMenuP3;
import processing.map.ui.ModeUI_P3;
import processing.viewer.ViewerP3;
import processing.general.events.PStateManager;
import processing.general.menus.ViewerMenuP3;
import processing.map.ui.InfoUI_P3;

/**
 * OBSERVER DESIGN PATTERN
 *
 * @author Simon Norup
 */
public class MapStateManagerP3 implements PStateManager {

    private final ProcessingMapmaker app;
    private final IEditorP3 editor;
    private final ViewerP3 viewer;
    private final ModeUI_P3 modeUI;
    private final InfoUI_P3 infoUI;
    private final MapMouseManagerP3 mapMouse;
    private final MapHotkeyManagerP3 mapHotkeys;
    private final MapUpdateManagerP3 mapUpdater;

    public MapStateManagerP3(ProcessingMapmaker app, CanvasP3 canvas, EditorMenuP3 editorMenu, ViewerMenuP3 viewerMenu) {
        this.app = app;
        this.modeUI = new ModeUI_P3(app);
        this.infoUI = new InfoUI_P3(app);
        this.editor = (IEditorP3) EditorProxy.getProxyInstance(
                new EditorP3(canvas, modeUI, infoUI, app),
                IEditorP3.class);
//        editor = new EditorP3(canvas, modeUI, this); // Using this instead will disable general draw() functionality
        this.viewer = new ViewerP3(canvas, app);
        this.mapMouse = new MapMouseManagerP3(app, editor, viewer, canvas, modeUI);
        this.mapHotkeys = new MapHotkeyManagerP3(app, editor, viewer, canvas, modeUI, infoUI, editorMenu, viewerMenu);
        this.mapUpdater = new MapUpdateManagerP3(app, editor, viewer, canvas);
    }

    @Override
    public void startListening() {
        app.attach(mapMouse, mapHotkeys, mapUpdater);

        // setting the default Mode when entering a map:
        if (UserRole.getCurrentRole() == UserRole.EDITOR) {
            modeUI.switchMode(Mode.LANDMASS);
        } else {
            modeUI.switchMode(Mode.MARKER);
        }
    }

}
