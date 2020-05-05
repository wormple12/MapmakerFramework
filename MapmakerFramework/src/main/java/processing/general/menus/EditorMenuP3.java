
package processing.general.menus;

import mapmaker.general.Storage;
import mapmaker.general.menus.EditorMenu;
import mapmaker.map.Canvas;
import processing.core.PApplet;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public class EditorMenuP3 extends EditorMenu {
    
    private final PApplet app;

    public EditorMenuP3(PApplet app, Storage storage, Canvas canvas) {
        super(storage, canvas);
        this.app = app;
    }

    @Override
    protected EntityInfo initMapInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
