
package processing.editor;

import mapmaker.editor.Editor;
import processing.entities.sprites.LocationP3;

/**
 *
 * @author Simon Norup
 */
public interface IEditorP3 extends Editor {
    
    public void displayUI();
    
    public void run();
    
    public void mousePressed();
    
    public void mouseReleased();
    
    public void mouseDragged();
    
    public void createNewRegionLayer();
    
    public void createNewRouteLayer();
    
    public LocationP3 getSelectedLocation();
    
    public LocationP3 attemptSelectLocation(int x, int y);
    
    public int getLayer();
    
    public void setLayer(int layer);
    
    public void switchRegionLayer();
    
    public void switchRouteLayer();
    
    public void switchColorBrush();

}
