package processing.map;

import java.util.List;
import mapmaker.general.UserRole;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.entities.RouteP3;

/**
 *
 * @author TeamOne
 */
public class RouteLoaderP3 {

    public static void loadRoute(RouteP3 route) {
        PGraphics pg = route.getGraphics();
        pg.beginDraw();
        pg.clear();
        pg.endDraw();

        List<PVector> points = route.getPoints();
        if (!points.isEmpty()) {
            PVector first = points.get(0);
            PVector last = points.get(points.size() - 1);
            pg.beginDraw();
            pg.beginShape();
            if (points.size() > 0) {
                float strokeWeightOriginal = pg.strokeWeight;
                // curves
                pg.strokeWeight(2);
                pg.curveVertex(first.x, first.y);
                points.forEach((vector) -> {
                    pg.curveVertex(vector.x, vector.y);
                });
                pg.curveVertex(last.x, last.y);
                // points
                pg.stroke(strokeWeightOriginal);
                pg.fill(255, 0, 0);
                if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                    points.forEach((vector) -> {
                        pg.ellipse(vector.x, vector.y, 7, 7);
                    });
                }
                pg.noFill();
            }
            pg.endShape();
            pg.endDraw();
        }
    }

}
