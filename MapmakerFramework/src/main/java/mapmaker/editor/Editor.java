package mapmaker.editor;

import java.util.List;
import mapmaker.entities.Landmass;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.sprites.Location;

/**
 *
 * @author Simon Norup
 */
public interface Editor {

    /**
     * General use function that is called whenever a user draws on the map,
     * independant of editor mode and other draw-based functionalities.
     *
     * @param x
     * @param y
     */
    default public void draw(int x, int y) {}

    /**
     * Adds landmass to the world map based on pointer (mouse or controller)
     * movement.
     *
     * @param x
     * @param y
     * @param radius
     */
    @DrawFunction
    public void drawLandmass(int x, int y, double radius);

    /**
     * Removes landmass from the world map based on pointer (mouse or
     * controller) movement.
     *
     * @param x
     * @param y
     * @param radius
     */
    @DrawFunction
    public void drawWater(int x, int y, double radius);

    /**
     * Finalizes landmass addition/removal, for example at mouse release. This
     * should update the current Map object, if this is not taken care of during
     * drawing.
     *
     * @return the updated lassmass
     */
    public Landmass updateLandmass();

    /**
     * Draws a border line on the landmass based on pointer (mouse or
     * controller) movement.This should not be able to draw on water surfaces.
     *
     * @param x
     * @param y
     * @param optionalRegion if this is not null, the drawn border attempts to
     * replace the existing border of the given region
     */
    @DrawFunction
    public void drawBorder(int x, int y, Region optionalRegion);

    /**
     * Finalizes the drawing of borders, for example at mouse release. Attempts
     * to split the drawn upon landmass into regions based on the borders drawn
     * and borders already existing. This should update the current Map object.
     *
     * @return the resulting regions, if any
     */
    public List<Region> createRegions();

    /**
     * Displays all information about a given region in a user-friendly manner.
     * This should enable the user to edit and update the information as well.
     *
     * @param region the region to view information about
     */
    public void viewRegion(Region region);

    /**
     * Finalizes updates to information about a specific region. This should
     * update the current Map object.
     *
     * @param original
     * @param updated
     */
    public void editRegion(Region original, Region updated);

    /**
     * Draws a route, road, river, or similar line on the landmass based on
     * pointer (mouse or controller) movement.
     *
     * @param x
     * @param y
     * @param radius
     */
    @DrawFunction
    public void drawRoute(int x, int y, double radius);

    /**
     * Erases routes, roads, rivers, or similar lines from the landmass based on
     * pointer (mouse or controller) movement.
     *
     * @param x
     * @param y
     * @param radius
     */
    @DrawFunction
    public void eraseRoute(int x, int y, double radius);

    /**
     * Finalizes the addition/removal of routes, roads, rivers, or similar
     * lines, for example at mouse release. This should update the current Map
     * object.
     *
     * @return the created route line
     */
    public Route createRoute();

    /**
     * Moves the given location from its current position (if any) to a set of
     * new coordinates, based on pointer (mouse or controller) movement.
     *
     * @param location the location to move
     * @param x
     * @param y
     */
    public void dragLocation(Location location, int x, int y);

    /**
     * Finalizes the movement or initial placement of a location to a set of
     * coordinates. This should update the current Map object, if this is not
     * taken care of when dragging the location.
     *
     * @param location the location that should have its position updated
     * @param x
     * @param y
     */
    public void dropLocation(Location location, int x, int y);

    /**
     * Displays all information about a given location in a user-friendly
     * manner. This should enable the user to edit and update the information as
     * well.
     *
     * @param location the location to view information about
     */
    public void viewLocationInfo(Location location);

    /**
     * Finalizes updates to information about a specific location. This should
     * update the current Map object.
     *
     * @param original the location to edit
     * @param updated the edited location info to replace the original with. If
     * this is null, it attempts to delete the original location
     */
    public void editLocationInfo(Location original, Location updated);

}
