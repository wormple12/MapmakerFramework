package processing.general.menus;

import mapmaker.general.menus.EditorMenu;
import mapmaker.general.menus.MainMenu;
import mapmaker.general.menus.StorageMenu;
import mapmaker.general.menus.SubMenu;
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
public class MainMenuP3 extends MainMenu implements PStateManager, PEventListener {

    private final ProcessingMapmaker app;
    private final PGraphics layer;
    private final PMenuDisplay menuDisplay;
    private final String[] menuOptions = {"Editor", "Viewer", "Exit"};

    public MainMenuP3(EditorMenu editorMenu, StorageMenu viewerMenu, SubMenu settingsMenu, ProcessingMapmaker app) {
        super(editorMenu, viewerMenu, settingsMenu);
        this.app = app;
        layer = app.createGraphics(app.width, app.height);
        menuDisplay = new PMenuDisplay("Processing Mapmaker", menuOptions, layer, 102, app);
        menuDisplay.setup();
    }

    @Override
    public void show() {
        app.setAppState(app.STATE_MENU_MAIN);
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
        app.setAppState(app.STATE_MAP);
    }

}
