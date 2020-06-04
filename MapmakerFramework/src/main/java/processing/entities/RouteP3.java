package processing.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mapmaker.entities.Route;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 *
 * @author TeamOne
 */
public class RouteP3 implements Route, Serializable {

    private PGraphics graphics = null;
    private List<PVector> points = new ArrayList<>();

    public RouteP3(PGraphics graphics) {
        this.graphics = graphics;
    }

    public PGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }

    public List<PVector> getPoints() {
        return points;
    }

    public void addPoint(float x, float y) {
        points.add(new PVector(x, y));
    }

    public void setPoints(List<PVector> points) {
        this.points = points;
    }

}
