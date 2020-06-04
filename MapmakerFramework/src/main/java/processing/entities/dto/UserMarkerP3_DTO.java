package processing.entities.dto;

import mapmaker.entities.sprites.UserMarker;
import processing.entities.sprites.UserMarkerP3;

/**
 *
 * @author TeamOne
 */
public class UserMarkerP3_DTO extends UserMarker {

    public UserMarkerP3_DTO(UserMarkerP3 marker) {
        super(marker.getInfo(), marker.getSpritePath(), marker.getX(), marker.getY(), marker.getWidth(), marker.getHeight());
    }

}
