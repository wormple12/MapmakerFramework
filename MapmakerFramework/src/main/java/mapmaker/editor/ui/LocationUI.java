package mapmaker.editor.ui;

import java.util.List;
import mapmaker.entities.sprites.Location;

/**
 *
 * @author Simon Norup
 */
public interface LocationUI {

    /**
     * Displays the UI Overlay for creation of new locations, making it possible
     * to select a particular type of location from a list of possibilities.
     *
     * @param locationTypes the list of possible location types to create
     */
    public void display(List<Location> locationTypes);

    /**
     * Retrieves all possible location types from a elsewhere defined file path.
     *
     * @return the list of possible location types to create
     */
    public List<Location> fetchAvailableTypes();

    /**
     * Selects the given location type as the current choice, automatically
     * "dragging" a location of that type over the map until it is released.
     * When released, the location is placed on the map, initialized with
     * coordinates.
     *
     * @param locationType the type of location to create
     */
    public void selectLocationType(Location locationType);

}
