package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Route;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class RouteP3 implements Route, Serializable {

    private String id;
    private String graphicsPath;

    public RouteP3() {
        this.id = MapStorageP3.generateId();
    }

    public RouteP3(String id) {
        this.id = id;
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
