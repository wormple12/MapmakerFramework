package processing.entities.sprites;

import mapmaker.entities.sprites.UserMarker;

/**
 *
 * @author rasmu
 */
public class UserMarkerP3_DTO extends UserMarker {

    public UserMarkerP3_DTO(UserMarkerP3 marker) {
        super(marker.getInfo(), marker.getSpritePath(), marker.getX(), marker.getY(), marker.getWidth(), marker.getHeight());
    }

}
