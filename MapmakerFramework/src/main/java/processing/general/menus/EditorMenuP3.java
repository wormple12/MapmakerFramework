package processing.general.menus;

import javax.swing.JOptionPane;
import mapmaker.general.Storage;
import mapmaker.general.menus.EditorMenu;
import mapmaker.map.Canvas;
import mapmaker.entities.EntityInfo;
import processing.core.PGraphics;
import processing.general.menus.components.PMenuDisplay;
import processing.ProcessingMapmaker;
import processing.general.events.PEventListener;
import processing.general.events.PStateManager;

/**
 *
 * @author Simon Norup
 */
public class EditorMenuP3 extends EditorMenu implements PStateManager, PEventListener {

    private final ProcessingMapmaker app;
    private final PGraphics layer;
    private final PMenuDisplay menuDisplay;
    private final String[] menuOptions = {"New", "Continue", "Load", "Return"};

    public EditorMenuP3(Storage storage, Canvas canvas, ProcessingMapmaker app) {
        super(storage, canvas);
        this.app = app;
        layer = app.createGraphics(app.width, app.height);
        menuDisplay = new PMenuDisplay("Map Editor", menuOptions, layer, 102, app);
        menuDisplay.setup();
    }

    @Override
    protected EntityInfo initMapInfo() {
        String name = (String) JOptionPane.showInputDialog(null, "Please enter a name for your world.", "Initial Map Data", JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (name != null) {
            return new EntityInfo(name);
        }
        return null;
    }

    @Override
    public void show() {
        app.setAppState(app.STATE_MENU_EDITOR);
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
                newMap();
                break;
            case 1:
                loadMap(true);
                break;
            case 2:
                loadMap(false);
                break;
            case 3:
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
