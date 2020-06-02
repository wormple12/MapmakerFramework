package processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mapmaker.editor.EditorProxy;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.editor.EditorP3;
import processing.editor.IEditorP3;
import processing.general.events.*;
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

    public final int STATE_MAP = 0, STATE_MENU_MAIN = 1, STATE_MENU_EDITOR = 2, STATE_MENU_VIEWER = 3;

    private PStateManager[] stateManagers;
    private List<PEventListener> eventListeners = new ArrayList<>();

    private MainMenuP3 mainMenu;
    private EditorMenuP3 editorMenu;
    private ViewerMenuP3 viewerMenu;
    private CanvasP3 canvas;
    private MapStateManagerP3 mapManager;

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
        mapManager = new MapStateManagerP3(this, canvas, editorMenu, viewerMenu);

        stateManagers = new PStateManager[]{mapManager, mainMenu, editorMenu, viewerMenu};
        setAppState(STATE_MENU_MAIN);

//        hint(DISABLE_ASYNC_SAVEFRAME); // enable this to prevent the black-box saving issue, when exporting PGraphics layers to files
    }

    public void setAppState(int state) {
        eventListeners = new ArrayList<>();
        stateManagers[state].startListening();
    }

    public void attach(PEventListener... listeners) {
        eventListeners.addAll(Arrays.asList(listeners));
    }

    @Override
    public void draw() {
        eventListeners.forEach(PEventListener::update);
    }

    @Override
    public void mousePressed() {
        eventListeners.forEach(PEventListener::mousePressed);
    }

    @Override
    public void mouseDragged() {
        eventListeners.forEach(PEventListener::mouseDragged);
    }

    @Override
    public void mouseReleased() {
        eventListeners.forEach(PEventListener::mouseReleased);
    }

    @Override
    public void keyPressed(processing.event.KeyEvent evt) {
        eventListeners.forEach((listener) -> listener.keyPressed(evt));
        key = 0; // prevents Processing default hotkey behavior (e.g. terminating on Escape)
    }

    @Override
    public void keyReleased(processing.event.KeyEvent evt) {
        eventListeners.forEach((listener) -> listener.keyReleased(evt));
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"Mapmaker (Processing 3)"};
        ProcessingMapmaker mySketch = new ProcessingMapmaker();
        PApplet.runSketch(appletArgs, mySketch);
    }

}
