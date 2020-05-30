package processing;

import mapmaker.editor.Mode;
import mapmaker.general.UserRole;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.editor.EditorP3;
import processing.general.files.MapStorageP3;
import processing.general.menus.*;
import processing.map.CanvasP3;
import processing.map.HotkeyManagerP3;
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
    private EditorP3 editor;
    private ViewerP3 viewer;
    private ModeUI_P3 modeUI;
    private HotkeyManagerP3 hotkeys;
    
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
        editor = new EditorP3(canvas, modeUI, this);
        viewer = new ViewerP3(canvas, modeUI, this);
        hotkeys = new HotkeyManagerP3(this, editor, viewer, modeUI, editorMenu);

        //hint(DISABLE_ASYNC_SAVEFRAME); // enable this to prevent the black-box saving issue, when exporting PGraphics layers to files
    }
    
    public void setAppState(int state) {
        appState = state;
        if (state == 0) {
            if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                modeUI.switchMode(Mode.LANDMASS);
            } else {
                modeUI.switchMode(Mode.MARKER);
            }
        }
    }
    
    @Override
    public void keyPressed(processing.event.KeyEvent evt) {
        if (appState == 0) {
            hotkeys.keyPressed(evt);
        }
    }
    
    @Override
    public void keyReleased(processing.event.KeyEvent evt) {
        if (appState == 0) {
            hotkeys.keyReleased(evt);
        }
    }
    
    @Override
    public void draw() {
        background(255);
        
        switch (appState) {
            case 0:
                canvas.run();
                if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                    editor.displayUI();
                    editor.run();
                } else {
                    viewer.displayUI();
                    viewer.run();
                }
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
        // image(currentLayer)
    }
    
    @Override
    public void mousePressed() {
        switch (appState) {
            case 0:
                if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                    editor.mousePressed();
                } else {
                    viewer.mousePressed();
                }
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
        if (appState == 0 && UserRole.getCurrentRole() == UserRole.EDITOR) {
            editor.mouseDragged();
        }
    }
    
    @Override
    public void mouseReleased() {
        if (appState == 0) {
            if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                editor.mouseReleased();
            } else {
                viewer.mouseReleased();
            }
        }
    }
    
    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"Mapmaker (Processing 3)"};
        ProcessingMapmaker mySketch = new ProcessingMapmaker();
        PApplet.runSketch(appletArgs, mySketch);
    }
    
}
