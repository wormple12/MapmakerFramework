package processing.general.menus;

import mapmaker.general.Storage;
import mapmaker.general.menus.StorageMenu;
import mapmaker.map.Canvas;
import processing.core.PGraphics;
import processing.general.menus.components.PMenuDisplay;
import processing.ProcessingMapmaker;
import processing.event.MouseEvent;
import processing.general.events.PEventListener;
import processing.general.events.PStateManager;

/**
 *
 * @author Simon Norup
 */
public class ViewerMenuP3 extends StorageMenu implements PStateManager, PEventListener {

    private final ProcessingMapmaker app;
    private final PGraphics layer;
    private final PMenuDisplay menuDisplay;
    private final String[] menuOptions = {"Continue", "Load", "Return"};

    public ViewerMenuP3(Storage storage, Canvas canvas, ProcessingMapmaker app) {
        super(storage, canvas);
        this.app = app;

        layer = app.createGraphics(app.width, app.height);
        menuDisplay = new PMenuDisplay("Map Viewer", menuOptions, layer, 102, app);
        menuDisplay.setup();
    }

    @Override
    public void show() {
        app.setAppState(app.STATE_MENU_VIEWER);
    }

    @Override
    public void startListening() {
        app.attach(this);
    }

    @Override
    public void update() {
        menuDisplay.draw();
        app.image(layer, 0, 0);
    }

    @Override
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
        app.setAppState(app.STATE_MENU_MAIN);
    }

}
