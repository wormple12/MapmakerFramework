
package processing.general.menus;

import mapmaker.general.menus.EditorMenu;
import mapmaker.general.menus.MainMenu;
import mapmaker.general.menus.StorageMenu;
import mapmaker.general.menus.SubMenu;
import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class MainMenuP3 extends MainMenu {
    
    private final PApplet app;

    public MainMenuP3(PApplet app, EditorMenu editorMenu, StorageMenu viewerMenu, SubMenu settingsMenu) {
        super(editorMenu, viewerMenu, settingsMenu);
        this.app = app;
    }
    
    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void returnToPrevious() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
