package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Route;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class RouteP3 implements Route, Serializable {

    private PGraphics graphics;

    public RouteP3(PGraphics graphics) {
        this.graphics = graphics;
    }

    public PGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }

}
