package mapmaker.viewer;

import mapmaker.entities.Region;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;

/**
 *
 * @author Simon Norup
 */
public interface Viewer {

    /**
     * Moves the given marker from its current position (if any) to a set of new
     * coordinates, based on pointer (mouse or controller) movement.
     *
     * @param marker the marker to move
     * @param x
     * @param y
     */
    public void dragMarker(UserMarker marker, int x, int y);

    /**
     * Finalizes the movement or initial placement of a marker to a set of
     * coordinates. This should update the current Map object, if this is not
     * taken care of when dragging the marker.
     *
     * @param marker the marker that should have its position updated
     * @param x
     * @param y
     */
    public void dropMarker(UserMarker marker, int x, int y);

    /**
     * Displays all information about a given marker in a user-friendly manner.
     * This should enable the user to edit and update the information as well.
     *
     * @param marker the marker to view information about
     */
    public void viewMarkerInfo(UserMarker marker);

    /**
     * Finalizes updates to information about a specific marker. This should
     * update the current Map object.
     *
     * @param original the marker to edit
     * @param updated the edited marker info to replace the original with. If
     * this is null, it attempts to delete the original marker
     */
    public void editMarkerInfo(UserMarker original, UserMarker updated);

    /**
     * Displays all information about a given region in a user-friendly manner.
     *
     * @param region the region to view information about
     */
    public void viewRegionInfo(Region region);

    /**
     * Displays all information about a given location in a user-friendly
     * manner.
     *
     * @param location the location to view information about
     */
    public void viewLocationInfo(Location location);

}
