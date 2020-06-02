package processing.editor;

import mapmaker.editor.Editor;
import processing.entities.sprites.LocationP3;

/**
 *
 * @author Simon Norup
 */
public interface IEditorP3 extends Editor {

    public void displayUI();

    public void createNewRegionLayer();

    public void createNewRouteLayer();

    public LocationP3 getSelectedLocation();

    public void attemptSelectLocation(int x, int y);

    public int getLayer();

    public void setLayer(int layer);

    public void switchRegionLayer();

    public void switchRouteLayer();

    public void switchColorBrush();

    public void setText(String text);

    public String getText();

    public void editEntityInfo();

    public void saveEntityInfo();

}
