package processing;

import mapmaker.editor.EditorProxy;
import mapmaker.editor.Mode;
import mapmaker.general.UserRole;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.editor.EditorP3;
import processing.editor.IEditorP3;
import processing.general.files.MapStorageP3;
import processing.general.menus.*;
import processing.map.*;
import processing.map.ui.ModeUI_P3;
import processing.viewer.ViewerP3;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMapmaker extends PApplet {

    private int appState = 1;
    // 0 = actual map
    // 1 = main menu
    // 2 = editor menu
    // 3 = viewer menu

    private MainMenuP3 mainMenu;
    private EditorMenuP3 editorMenu;
    private ViewerMenuP3 viewerMenu;
    private CanvasP3 canvas;
    private IEditorP3 editor;
    private ViewerP3 viewer;
    private ModeUI_P3 modeUI;
    private MapMouseManagerP3 mapMouse;
    private MapHotkeyManagerP3 mapHotkeys;
    private MapRunnerP3 mapRunner;

    @Override
    public void settings() {
        size(displayWidth, displayHeight);
    }

    @Override
    public void setup() {
        surface.setTitle("Mapmaker (Processing 3)");
        surface.setResizable(true);

        FileStorage mapStorage = new MapStorageP3(this);
        canvas = new CanvasP3(this);
        editorMenu = new EditorMenuP3(mapStorage, canvas, this);
        viewerMenu = new ViewerMenuP3(mapStorage, canvas, this);
        mainMenu = new MainMenuP3(editorMenu, viewerMenu, null, this);
        modeUI = new ModeUI_P3(this);
<<<<<<< HEAD
//        editor = (EditorP3) EditorProxy.getProxyInstance(new EditorP3(canvas, modeUI, this), Editor.class);
        editor = new EditorP3(canvas, modeUI, this);
        viewer = new ViewerP3(canvas, modeUI, this);
        hotkeys = new HotkeyManagerP3(this, editor, viewer, modeUI, editorMenu);
=======
        editor = (IEditorP3) EditorProxy.getProxyInstance(
                new EditorP3(canvas, modeUI, this),
                IEditorP3.class);
//        editor = new EditorP3(canvas, modeUI, this); // Using this instead will disable general draw() functionality
        viewer = new ViewerP3(canvas, this);
        mapMouse = new MapMouseManagerP3(this, editor, viewer, canvas, modeUI);
        mapHotkeys = new MapHotkeyManagerP3(this, editor, viewer, modeUI, editorMenu);
        mapRunner = new MapRunnerP3(this, editor, viewer, canvas);
>>>>>>> 287664cc1201847e2eb244951874922b1064f209

        //hint(DISABLE_ASYNC_SAVEFRAME); // enable this to prevent the black-box saving issue, when exporting PGraphics layers to files
    }

    public void setAppState(int state) {
        appState = state;
        // setting the default Mode when entering a map:
        if (state == 0) {
            if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                modeUI.switchMode(Mode.LANDMASS);
            } else {
                modeUI.switchMode(Mode.MARKER);
            }
        }
    }

    @Override
    public void draw() {
        background(255);

        switch (appState) {
            case 0:
                mapRunner.run();
                break;
            case 1:
                mainMenu.run();
                break;
            case 2:
                editorMenu.run();
                break;
            case 3:
                viewerMenu.run();
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void mousePressed() {
        switch (appState) {
            case 0:
                mapMouse.mousePressed();
                break;
            case 1:
                mainMenu.mousePressed();
                break;
            case 2:
                editorMenu.mousePressed();
                break;
            case 3:
                viewerMenu.mousePressed();
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void mouseDragged() {
        if (appState == 0) {
            mapMouse.mouseDragged();
        }
    }

    @Override
    public void mouseReleased() {
        if (appState == 0) {
            mapMouse.mouseReleased();
        }
    }

    @Override
    public void keyPressed(processing.event.KeyEvent evt) {
        if (appState == 0) {
            mapHotkeys.keyPressed(evt);
        }
        key = 0; // prevents Processing default hotkey behavior (e.g. terminating on Escape)
    }

    @Override
    public void keyReleased(processing.event.KeyEvent evt) {
        if (appState == 0) {
            mapHotkeys.keyReleased(evt);
        }
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"Mapmaker (Processing 3)"};
        ProcessingMapmaker mySketch = new ProcessingMapmaker();
        PApplet.runSketch(appletArgs, mySketch);
    }

}
