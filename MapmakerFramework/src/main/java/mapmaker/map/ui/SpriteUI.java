package mapmaker.map.ui;

import java.util.List;
import mapmaker.entities.sprites.Sprite;

/**
 *
 * @author Simon Norup
 */
public interface SpriteUI {

    /**
     * Displays the UI Overlay for creation of new locations or user markers,
     * making it possible to select a particular type of marker from a list of
     * possibilities.
     *
     */
    public void display();

    /**
     * Retrieves all possible location or user marker types from an elsewhere
     * defined file path.
     *
     * @return the list of possible sprite types to create
     */
    public List<Sprite> fetchAvailableTypes();

    /**
     * Selects the given location or user marker type as the current choice,
     * automatically "dragging" a marker of that type over the map until it is
     * released. When released, the marker is placed on the map, initialized
     * with coordinates.
     *
     * @param spriteType the type of sprite to create
     */
    public void selectSpriteType(Sprite spriteType);

}
