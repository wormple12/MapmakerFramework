package processing.map;

import java.util.ArrayList;
import mapmaker.entities.WorldMap;
import mapmaker.map.Canvas;
import mapmaker.entities.EntityInfo;
import mapmaker.general.UserRole;
import processing.core.PGraphics;
import processing.entities.LandmassP3;
import processing.entities.RouteP3;
import processing.entities.sprites.LocationP3;
import processing.entities.sprites.UserMarkerP3;
import processing.ProcessingMapmaker;

/**
 *
 * @author Simon Norup
 */
public class CanvasP3 implements Canvas {

    private final ProcessingMapmaker app;
    private final PGraphics water;

    private WorldMap currentMap = null;

    public CanvasP3(ProcessingMapmaker app) {
        this.app = app;

        water = app.createGraphics(app.width, app.height);
        water.beginDraw();
        water.background(0, 126, 192);
        water.endDraw();
    }

    public void display() {
        app.background(255);
        app.image(water, 0, 0);

        currentMap.getRegions().stream().map(r -> (LandmassP3) r.getArea()).forEachOrdered(area -> {
            app.image(area.getBorderGraphics(), 0, 0);
            app.image(area.getGraphics(), 0, 0);
        });

        currentMap.getRoutes().stream().map(r -> (RouteP3) r).forEachOrdered(r -> app.image(r.getGraphics(), 0, 0));

        currentMap.getLocations().stream().map((loc) -> (LocationP3) loc)
                .forEachOrdered((loc) -> {
                    app.image(loc.getImage(), loc.getX(), loc.getY(), loc.getWidth(), loc.getHeight());
                });

        if (UserRole.getCurrentRole() == UserRole.VIEWER) {
            currentMap.getMarkers().stream().map((marker) -> (UserMarkerP3) marker)
                    .forEachOrdered((marker) -> {
                        app.image(marker.getImage(), marker.getX(), marker.getY(), marker.getWidth(), marker.getHeight());
                    });
        }
    }

    @Override
    public WorldMap loadEmpty(EntityInfo info) {
        app.setAppState(app.STATE_MAP);
        return new WorldMap(info, new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());
    }

    @Override
    public void loadWorldMap(WorldMap map) {
        app.setAppState(app.STATE_MAP);
        System.out.println(map.getFilePath());
        System.out.println(map.getInfo().getName());
    }

    @Override
    public WorldMap getCurrentMap() {
        return currentMap;
    }

    @Override
    public void setCurrentMap(WorldMap map) {
        currentMap = map;
    }

}
