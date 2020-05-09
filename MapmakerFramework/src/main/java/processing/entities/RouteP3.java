package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Route;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class RouteP3 implements Route, Serializable{
    private PGraphics route;

    public RouteP3(PGraphics route) {
        this.route = route;
    }

    @Override
    public PGraphics getRoute() {
        return route;
    }
    
    @Override
    public void setRoute(PGraphics route) {
        this.route = route;
    }

}
