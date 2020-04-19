
package mapmaker.editor;

import mapmaker.map.objects.Location;

/**
 *
 * @author Simon Norup
 */
public interface Editor {
    
    public void draw(int x, int y);
    
    public void dragLocation(Location location, int x, int y);
    public void dropLocation(Location location, int x, int y);
    public void editLocation(Location original, Location updated);
    
    public void drawLandscape(int x, int y); // adds landmass
    public void drawBorder(int x, int y);
    //public Region createRegion(Coordinate[] coordinates); // is called when you're done drawing a border
    public void drawRoad(int x, int y);
    public void drawWater(int x, int y); // removes landmass
    
}

/* public onKeyDragged() {
    if (enum)
    Editor.drawLandscape(x, y);
*/