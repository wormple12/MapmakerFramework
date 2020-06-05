package processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.general.events.*;
import processing.general.files.MapStorageP3;
import processing.general.menus.*;
import processing.map.*;
import processing.general.events.PEventConsumer;

/**
 *
 * @author TeamOne
 */
public class ProcessingMapmaker extends PApplet {

    public final int STATE_MAP = 0, STATE_MENU_MAIN = 1, STATE_MENU_EDITOR = 2, STATE_MENU_VIEWER = 3;

    // manages which listeners should listen in which states
    private PStateManager[] stateManagers;
    // listens and acts upon events
    protected List<PEventListener> eventListeners = new ArrayList<>();

    // collects mouse events and lets us activate them in another thread for optimization
    private final BlockingQueue<Runnable> mouseEvents = new ArrayBlockingQueue(500);
    // the thread that consumes and runs all functionality based on mouse events
    private Thread mouseThread;
    // NOTE 1: The cause of most issues concerning performance is the image() rendering of PGraphics entities (see CanvasP3).
    // Unfortunately, we have not been able to optimize this with threading, because:
    // (A) Processing is designed as a mono-threaded framework, and attempts to force it into a multi-threaded context 
    // makes for unstable results to say the least;
    // (B) a world map is rendered one layer at a time, meaning that we have to wait for one image() call before we can 
    // continue to the next - otherwise the layers load in the wrong order.
    // // NOTE 2: Spreading the mouseEvent workload (via Executors.newFixedThreadPool()) has been tested, but also didn't 
    // work, presumably because of the Reason A as describe above, and because values probably need to be synchronized 
    // to allow simultenous editing/reading

    @Override
    public void settings() {
        size(displayWidth, displayHeight);
    }

    @Override
    public void setup() {
        surface.setTitle("Mapmaker (Processing 3)");
        surface.setResizable(true);

        FileStorage mapStorage = new MapStorageP3(this);
        CanvasP3 canvas = new CanvasP3(this);
        EditorMenuP3 editorMenu = new EditorMenuP3(mapStorage, canvas, this);
        ViewerMenuP3 viewerMenu = new ViewerMenuP3(mapStorage, canvas, this);
        MainMenuP3 mainMenu = new MainMenuP3(editorMenu, viewerMenu, this);
        MapStateManagerP3 mapManager = new MapStateManagerP3(this, canvas, editorMenu, viewerMenu);

        stateManagers = new PStateManager[]{mapManager, mainMenu, editorMenu, viewerMenu};
        setAppState(STATE_MENU_MAIN);

        mouseThread = new Thread(new PEventConsumer(mouseEvents));
        mouseThread.start();
    }

    public void setAppState(int state) {
        eventListeners = new ArrayList<>();
        stateManagers[state].startListening();
    }

    public void attach(PEventListener... listeners) {
        eventListeners.addAll(Arrays.asList(listeners));
    }

    public void putEventInQueue(Runnable event) {
        try {
            mouseEvents.put(event);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted...");
        }
    }

    @Override
    public void draw() {
        eventListeners.forEach(PEventListener::update);
    }

    @Override
    public void mousePressed() {
        putEventInQueue(() -> eventListeners.forEach(PEventListener::mousePressed));
    }

    @Override
    public void mouseDragged() {
        putEventInQueue(() -> eventListeners.forEach(PEventListener::mouseDragged));
    }

    @Override
    public void mouseReleased() {
        putEventInQueue(() -> eventListeners.forEach(PEventListener::mouseReleased));
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
