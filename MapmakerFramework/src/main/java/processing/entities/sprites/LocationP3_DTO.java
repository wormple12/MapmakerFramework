package processing.entities.sprites;

import mapmaker.entities.sprites.Location;

/**
 *
 * @author rasmu
 */
public class LocationP3_DTO extends Location {

    public LocationP3_DTO(LocationP3 loc) {
        super(loc.getInfo(), loc.getSpritePath(), loc.getX(), loc.getY(), loc.getWidth(), loc.getHeight());
    }

}
