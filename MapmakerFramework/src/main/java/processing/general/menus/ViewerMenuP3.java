package processing.general.menus;

import mapmaker.general.Storage;
import mapmaker.general.menus.StorageMenu;
import mapmaker.map.Canvas;
import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class ViewerMenuP3 extends StorageMenu {
    
    private final PApplet app;

    public ViewerMenuP3(PApplet app, Storage storage, Canvas canvas) {
        super(storage, canvas);
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
