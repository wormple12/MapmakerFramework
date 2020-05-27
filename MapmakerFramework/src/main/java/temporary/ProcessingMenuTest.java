package temporary;

import mapmaker.general.files.FileStorage;
import mapmaker.map.Canvas;
import processing.core.PApplet;
import processing.general.files.MapStorageP3;
import processing.general.menus.*;
import processing.map.CanvasP3;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMenuTest extends PApplet {

    private int appState = 1; // should rather just save the current PGraphics layer to display
    // 0 = actual map
    // 1 = main menu
    // 2 = editor menu
    // 3 = viewer menu

    private MainMenuP3 mainMenu;
    private EditorMenuP3 editorMenu;
    private ViewerMenuP3 viewerMenu;

    @Override
    public void settings() {
        size(1920, 1080);
    }

    @Override
    public void setup() {
        FileStorage mapStorage = new MapStorageP3(this);
        Canvas canvas = new CanvasP3(this);
        editorMenu = new EditorMenuP3(mapStorage, canvas, this);
        viewerMenu = new ViewerMenuP3(mapStorage, canvas, this);
        mainMenu = new MainMenuP3(editorMenu, viewerMenu, null, this);
    }

    public void setAppState(int state) {
        appState = state;
    }

    @Override
    public void draw() {
        background(255);

        switch (appState) {
            case 1:
                mainMenu.draw();
                break;
            case 2:
                editorMenu.draw();
                break;
            case 3:
                viewerMenu.draw();
                break;
            default:
                throw new AssertionError();
        }
        // image(currentLayer)

        noLoop();
    }

    @Override
    public void mousePressed() {
        loop();
        switch (appState) {
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
    public void mouseMoved() {
        if (appState != 0) {
            loop();
        }
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"ProcessingMenuTest"};
        ProcessingMenuTest mySketch = new ProcessingMenuTest();
        PApplet.runSketch(appletArgs, mySketch);
    }

}
