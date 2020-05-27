package processing.general.menus;

import mapmaker.general.menus.EditorMenu;
import mapmaker.general.menus.MainMenu;
import mapmaker.general.menus.StorageMenu;
import mapmaker.general.menus.SubMenu;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.general.menus.components.PMenuDisplay;
import temporary.ProcessingMenuTest;

/**
 *
 * @author Simon Norup
 */
public class MainMenuP3 extends MainMenu {

    private final PApplet app;
    private final PGraphics layer;
    private final PMenuDisplay menuDisplay;
    private final String[] menuOptions = {"Editor", "Viewer", "Exit"};

    public MainMenuP3(EditorMenu editorMenu, StorageMenu viewerMenu, SubMenu settingsMenu, PApplet app) {
        super(editorMenu, viewerMenu, settingsMenu);
        this.app = app;

        layer = app.createGraphics(app.width, app.height);
        menuDisplay = new PMenuDisplay("Processing Mapmaker", menuOptions, layer, app);
        layer.beginDraw();
        layer.background(102);
        menuDisplay.setup();
        layer.endDraw();
    }

    @Override
    public void show() {
        ((ProcessingMenuTest) app).setAppState(1);
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
                enterEditorMenu();
                break;
            case 1:
                enterViewerMenu();
                break;
            case 2:
                terminate();
                break;
            default:
            // nothing
        }
    }

    @Override
    public void returnToPrevious() {
        ((ProcessingMenuTest) app).setAppState(0);
    }

}
