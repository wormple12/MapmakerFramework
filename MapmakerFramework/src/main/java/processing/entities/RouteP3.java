package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Route;
import processing.core.PGraphics;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class RouteP3 implements Route, Serializable {

    private String id;
    private String graphicsPath;

    private PGraphics graphics = null;

    public RouteP3(PGraphics graphics) {
        this(MapStorageP3.generateId(), graphics);
    }

    public RouteP3(String id, PGraphics graphics) {
        this.id = id;
        this.graphics = graphics;
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

    public PGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }

}
