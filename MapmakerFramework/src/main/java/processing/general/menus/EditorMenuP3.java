package processing.general.menus;

import javax.swing.JOptionPane;
import mapmaker.general.Storage;
import mapmaker.general.menus.EditorMenu;
import mapmaker.map.Canvas;
import processing.core.PApplet;
import mapmaker.entities.EntityInfo;
import processing.core.PGraphics;
import processing.entities.MapInfoP3;
import processing.general.menus.components.PMenuDisplay;
import temporary.ProcessingMenuTest;

/**
 *
 * @author Simon Norup
 */
public class EditorMenuP3 extends EditorMenu {

    private final PApplet app;
    private final PGraphics layer;
    private final PMenuDisplay menuDisplay;
    private final String[] menuOptions = {"New", "Continue", "Load", "Return"};

    public EditorMenuP3(Storage storage, Canvas canvas, PApplet app) {
        super(storage, canvas);
        this.app = app;

        layer = app.createGraphics(app.width, app.height);
        menuDisplay = new PMenuDisplay("Map Editor", menuOptions, layer, app);
        layer.beginDraw();
        layer.background(102);
        menuDisplay.setup();
        layer.endDraw();
    }

    @Override
    protected EntityInfo initMapInfo() {
        String name = (String) JOptionPane.showInputDialog(null, "Please enter a name for your world.", "Initial Map Data", JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (name != null) {
            return new MapInfoP3(name);
        }
        return null;
    }

    @Override
    public void show() {
        ((ProcessingMenuTest) app).setAppState(2);
    }

    public void draw() {
        layer.beginDraw();
        menuDisplay.draw();
        layer.endDraw();
        app.image(layer, 0, 0);
    }

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
        ((ProcessingMenuTest) app).setAppState(1);
    }

}
