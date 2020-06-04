package processing.entities.dto;

import mapmaker.entities.sprites.Location;
import processing.entities.sprites.LocationP3;

/**
 *
 * @author TeamOne
 */
public class LocationP3_DTO extends Location {

    public LocationP3_DTO(LocationP3 loc) {
        super(loc.getInfo(), loc.getSpritePath(), loc.getX(), loc.getY(), loc.getWidth(), loc.getHeight());
    }

}
