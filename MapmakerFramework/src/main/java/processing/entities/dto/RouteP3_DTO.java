package processing.entities.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import mapmaker.entities.Route;
import processing.entities.RouteP3;

/**
 *
 * @author Simon Norup
 */
public class RouteP3_DTO implements Route, Serializable {

    private final List<Pair<Float, Float>> coordinates = new ArrayList();

    public RouteP3_DTO(RouteP3 route) {
        route.getPoints().forEach((point) -> {
            coordinates.add(new Pair<>(point.x, point.y));
        });
    }

    public List<Pair<Float, Float>> getCoordinates() {
        return coordinates;
    }

}
