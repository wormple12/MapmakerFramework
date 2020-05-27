package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Route;

/**
 *
 * @author Simon Norup
 */
public class RouteP3_DTO implements Route, Serializable {

    private String id;
    private String graphicsPath;

    public RouteP3_DTO(RouteP3 route) {
        this.id = route.getId();
        this.graphicsPath = route.getGraphicsPath();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGraphicsPath() {
        return graphicsPath;
    }

    public void setGraphicsPath(String graphicsPath) {
        this.graphicsPath = graphicsPath;
    }

}
