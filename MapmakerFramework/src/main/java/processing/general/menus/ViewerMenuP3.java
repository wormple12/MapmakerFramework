package processing.general.menus;

import mapmaker.general.Storage;
import mapmaker.general.menus.StorageMenu;
import mapmaker.map.Canvas;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.general.menus.components.PMenuDisplay;
import processing.ProcessingMapmaker;

/**
 *
 * @author Simon Norup
 */
public class ViewerMenuP3 extends StorageMenu {

    private final PApplet app;
    private final PGraphics layer;
    private final PMenuDisplay menuDisplay;
    private final String[] menuOptions = {"Continue", "Load", "Return"};

    public ViewerMenuP3(Storage storage, Canvas canvas, PApplet app) {
        super(storage, canvas);
        this.app = app;

        layer = app.createGraphics(app.width, app.height);
        menuDisplay = new PMenuDisplay("Map Viewer", menuOptions, layer, app);
        layer.beginDraw();
        layer.background(102);
        menuDisplay.setup();
        layer.endDraw();
    }

    @Override
    public void show() {
        ((ProcessingMapmaker) app).setAppState(3);
    }

    public void run() {
        layer.beginDraw();
        menuDisplay.draw();
        layer.endDraw();
        app.image(layer, 0, 0);
    }

    public void mousePressed() {
        switch (menuDisplay.getButtonPressed()) {
            case 0:
                loadMap(true);
                break;
            case 1:
                loadMap(false);
                break;
            case 2:
                returnToPrevious();
                break;
            default:
            // nothing
        }
    }

    @Override
    public void returnToPrevious() {
        ((ProcessingMapmaker) app).setAppState(1);
    }

}
