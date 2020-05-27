package processing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mapmaker.entities.Biome;
import mapmaker.entities.EntityInfo;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.entities.LandmassP3;
import processing.entities.RouteP3;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMapmaker extends PApplet {

    MapStorageP3 fileStore = new MapStorageP3(this);

    List<PGraphics> seaLevel = new ArrayList();
    List<PGraphics> border = new ArrayList();

    List<PGraphics> region = new ArrayList();
    List<PGraphics> location = new ArrayList();
    List<PGraphics> userMarker = new ArrayList();
    List<PGraphics> route = new ArrayList<>();

    public void saveWorld() {
        EntityInfo regionInfo = new EntityInfo("NewRegionTest");
        EntityInfo mapInfo = new EntityInfo("NewWorldTest");

        ArrayList<Region> regionList = new ArrayList();
        for (int i = 0; i < region.size(); i++) {
            regionList.add(new Region(
                    new LandmassP3(region.get(i), border.get(i)),
                    regionInfo,
                    Biome.HILLS
            ));
        }

        ArrayList<Route> routeList = new ArrayList<>();
        route.stream().map((r) -> new RouteP3(r))
                .forEachOrdered(routeList::add);

        ArrayList<Location> locationList = new ArrayList();
        ArrayList<UserMarker> userMarkerList = new ArrayList();

        WorldMap world = new WorldMap(mapInfo, regionList, locationList, userMarkerList, routeList);
        boolean success = fileStore.attemptSave(world);
        System.out.println("Success on save : " + success);
    }

    public void loadWorld() {
        WorldMap map = fileStore.attemptLoad(false);
        loop();
        region = map.getRegions().stream()
                .map((r) -> ((LandmassP3) r.getArea()).getGraphics())
                .collect(Collectors.toList());
        border = map.getRegions().stream()
                .map((r) -> ((LandmassP3) r.getArea()).getBorderGraphics())
                .collect(Collectors.toList());
        route = map.getRoutes().stream()
                .map((r) -> ((RouteP3) r).getGraphics())
                .collect(Collectors.toList());
    }

    int[] cp = {
        color(0, 126, 192),
        color(24, 154, 208),
        color(58, 168, 214),
        color(94, 186, 220),
        color(128, 199, 228),
        color(163, 216, 235),
        color(197, 229, 243),
        color(232, 244, 250),
        color(135, 209, 63),
        color(203, 227, 107),
        color(255, 227, 153),
        color(255, 205, 127),
        color(234, 136, 70),
        color(209, 104, 47),
        color(187, 76, 15),
        color(148, 56, 0)
    };
    int col = 1;

    final int state1 = 1;
    final int state2 = 2;
    final int state3 = 3;
    final int state4 = 4;
    int state = state1;

    int layer = 0;

    final int edit = 1;
    final int delete = 2;
    int mode = edit;

    ArrayList<ArrayList<PVector>> pointsForPaths;

    @Override
    public void settings() {
        size(1920, 1080);
    }

    @Override
    public void setup() {
        PGraphics pg = createGraphics(width, height);
        pg.beginDraw();
        pg.background(cp[0]);
        pg.endDraw();
        seaLevel.add(pg);

        location.add(createGraphics(width, height));
        userMarker.add(createGraphics(width, height));

        pointsForPaths = new ArrayList<>();
        ArrayList<PVector> points = new ArrayList<>();
        pointsForPaths.add(points);

        hint(DISABLE_ASYNC_SAVEFRAME); // prevents the black-box saving issue, when exporting PGraphics layers to files
    }

    @Override
    public void draw() {
        background(255);
        for (int i = 0; i < seaLevel.size(); i++) {
            image(seaLevel.get(i), 0, 0);
        }

        for (int i = 0; i < region.size(); i++) {
            image(border.get(i), 0, 0);
            image(region.get(i), 0, 0);
        }

        for (int i = 0; i < location.size(); i++) {
            image(location.get(i), 0, 0);
        }

        for (int i = 0; i < userMarker.size(); i++) {
            image(userMarker.get(i), 0, 0);
        }

        for (int i = 0; i < route.size(); i++) {
            image(route.get(i), 0, 0);
        }

        noLoop();
    }

    @Override
    public void mouseDragged() {
        loop();
        if (mode == edit) {
            if (state == state1) {
                editDraw(layer, col);
            }
        } else if (mode == delete) {
            if (state == state1) {
                deleteDraw(layer);
            }
        }
    }

    @Override
    public void mousePressed() {
        loop();
        if (mode == edit) {
            if (state == state4) {
                editPath(layer);
            }
        } else if (mode == delete) {
            if (state == state4) {
                deletePath(layer);
            }
        }
    }

    public void editDraw(int i, int c) {
        if (region.isEmpty()) {
            region.add(createGraphics(width, height));
            border.add(createGraphics(width, height));
        }
        PGraphics pg = region.get(i);
        PGraphics pgb = border.get(i);
        if (mousePressed) {
            pg.beginDraw();
            pg.stroke(cp[c]);
            pg.strokeWeight(100);
            pg.line(pmouseX, pmouseY, mouseX, mouseY);
            pg.endDraw();

            pgb.beginDraw();
            pgb.stroke(255);
            pgb.strokeWeight(110);
            pgb.line(pmouseX, pmouseY, mouseX, mouseY);
            pgb.endDraw();
        }
        noStroke();
        ellipse(mouseX, mouseY, 100, 100);
    }

    public void deleteDraw(int i) {
        PGraphics pg = region.get(i);
        PGraphics pgb = border.get(i);
        if (mousePressed) {
            pgb.beginDraw();
            pgb.loadPixels();
            pg.beginDraw();
            pg.loadPixels();
            for (int x = 0; x < pg.width; x++) {
                for (int y = 0; y < pg.height; y++) {
                    float distance = dist(x, y, mouseX, mouseY);
                    if (distance <= 25) {
                        int loc = x + y * pg.width;
                        pg.pixels[loc] = 0x0;
                    }
                    if (distance <= 20) {
                        int loc = x + y * pg.width;
                        pgb.pixels[loc] = 0x0;
                    }
                }
            }
            pg.updatePixels();
            pg.endDraw();
            pgb.updatePixels();
            pgb.endDraw();
        }
        noStroke();
        ellipse(mouseX, mouseY, 50, 50);
    }

    public void editPath(int i) {
        if (route.isEmpty()) {
            route.add(createGraphics(width, height));
        }
        pointsForPaths.get(i).add(new PVector(mouseX, mouseY));
        PGraphics pg = route.get(i);
        pg.beginDraw();
        pg.noFill();
        pg.beginShape();
        if (pointsForPaths.get(i).size() > 0) {
            pg.curveVertex(pointsForPaths.get(i).get(0).x, pointsForPaths.get(i).get(0).y);
            pointsForPaths.get(i).stream().map((p) -> {
                pg.curveVertex(p.x, p.y);
                return p;
            }).forEachOrdered((p) -> {
                pg.ellipse(p.x, p.y, 3, 3);
            });
        }
        pg.endShape();
        pg.endDraw();
    }

    public void deletePath(int i) {
        if (pointsForPaths.get(i).size() > 2) {
            pointsForPaths.get(i).remove(pointsForPaths.get(i).size() - 1);
        }
        PGraphics pg = route.get(i);
        pg.beginDraw();
        pg.clear();
        pg.endDraw();
        pg.beginDraw();
        pg.noFill();
        pg.beginShape();
        if (pointsForPaths.get(i).size() > 0) {
            pg.curveVertex(pointsForPaths.get(i).get(0).x, pointsForPaths.get(i).get(0).y);
            pointsForPaths.get(i).stream().map((p) -> {
                pg.curveVertex(p.x, p.y);
                return p;
            }).forEachOrdered((p) -> {
                pg.ellipse(p.x, p.y, 3, 3);
            });
        }
        pg.endShape();
        pg.endDraw();
    }

    public void randomMap() {
        PGraphics pg = seaLevel.get(0);
        float r = random(10000000);
        this.noiseSeed((long) r);
        pg.beginDraw();
        pg.loadPixels();
        float d0 = random(100, 200);
        float d1 = random(25, 75);
        int[] terrain;
        terrain = new int[height * width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                float n0 = noise(i / d0, j / d0, 0);
                float n1 = noise(i / d1, j / d1, 10);
                float n = (float) (1 - (n0 * 0.75 + n1 * 0.25));
                int k = (int) (n * cp.length);
                pg.pixels[j * width + i] = cp[k];
                terrain[j * width + i] = k;
            }
        }
        pg.updatePixels();
        pg.endDraw();
        loop();
    }

    @Override
    public void keyPressed() {
        loop();

        switch (key) {
            case '1':
                state = state1;
                layer = 0;
                System.out.println("state1 active");
                break;
            case '2':
                state = state2;
                layer = 0;
                System.out.println("state2 active");
                break;
            case '3':
                state = state3;
                layer = 0;
                System.out.println("state3 active");
                break;
            case '4':
                state = state4;
                layer = 0;
                System.out.println("state4 active");
                break;
            default:
                break;
        }

        if (key == 'l') {
            switch (state) {
                case state1:
                    if (layer < region.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state1 layer switch");
                    break;
                case state2:
                    if (layer < location.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state2 layer switch");
                    break;
                case state3:
                    if (layer < userMarker.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state3 layer switch");
                    break;
                case state4:
                    if (layer < route.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state4 layer switch");
                    break;
                default:
                    break;
            }
        }

        if (key == 'n') {
            switch (state) {
                case state1:
                    PGraphics pg2 = createGraphics(width, height);
                    region.add(pg2);
                    PGraphics pgBorder = createGraphics(width, height);
                    border.add(pgBorder);
                    System.out.println("Adding for layer 1");
                    break;
                case state2:
                    PGraphics pg3 = createGraphics(width, height);
                    location.add(pg3);
                    System.out.println("Adding for layer 2");
                    break;
                case state3:
                    PGraphics pg4 = createGraphics(width, height);
                    userMarker.add(pg4);
                    System.out.println("Adding for layer 3");
                    break;
                case state4:
                    PGraphics pg5 = createGraphics(width, height);
                    route.add(pg5);
                    ArrayList<PVector> points = new ArrayList<>();
                    pointsForPaths.add(points);
                    System.out.println("Adding for layer 4");
                    break;
                default:
                    break;
            }
        }

        if (key == 'q') {
            if (col == cp.length - 1) {
                col = 1;
            } else {
                col = col + 1;
            }
        }

        if (key == 'e') {
            mode = edit;
        }

        if (key == 'd') {
            mode = delete;
        }

        if (key == 'r' & state == state1) {
            randomMap();
        }

        if (key == 's') {
            saveWorld();
        }

        if (key == 'o') {
            loadWorld();
        }
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"ProcessingMapmaker"};
        ProcessingMapmaker mySketch = new ProcessingMapmaker();
        PApplet.runSketch(appletArgs, mySketch);
    }

}

//public class MySketch extends PApplet {
//
//    public void draw() {
//        Canvas canvas = new Canvas(this);
//        canvas.drawMap();
//    }
//
//}

/* public onKeyDragged() {
    if (enum)
    Editor.drawLandmass(x, y, r);
 */
