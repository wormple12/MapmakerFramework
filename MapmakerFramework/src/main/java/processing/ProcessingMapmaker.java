package processing;

import java.util.ArrayList;
import java.util.List;
import mapmaker.entities.EntityInfo;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PShape;
import processing.core.PVector;
import processing.entities.LandmassP3;
import processing.entities.RouteP3;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMapmaker extends PApplet {

    FileStorage fileStore = new FileStorage();

    ArrayList<PGraphics> layers = new ArrayList();

    EntityInfo info = new EntityInfo("NewWorldTest");
    List<Region> region = new ArrayList();
    List<Location> location = new ArrayList();
    List<UserMarker> userMarker = new ArrayList();
    List<Route> route = new ArrayList<>();

    public void saveWorld() {
        LandmassP3 land = new LandmassP3(layers.get(1));
        RouteP3 routeImpl = new RouteP3(layers.get(2));
        route.add(routeImpl);

        WorldMap world = new WorldMap(info, land, region, location, userMarker, route);
        boolean success = fileStore.attemptSave(world);
        System.out.println("Success on save : " + success);
    }

    public void loadWorld() {
        WorldMap world = fileStore.attemptLoad(true);
        layers.set(1, ((LandmassP3) world.getLandmass()).getLandMass());
        layers.set(2, ((RouteP3) world.getRoutes().get(0)).getRoute());
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

    final int layer1 = 1;
    final int layer2 = 2;
    final int layer3 = 3;
    final int layer4 = 4;
    final int layer5 = 5;
    int state = layer1;

    final int edit = 1;
    final int delete = 2;
    int mode = edit;

    ArrayList<PVector> points;
    PShape path;

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
        layers.add(pg);

        for (int i = 0; i < 5; i++) {
            layers.add(createGraphics(width, height));
        }

        points = new ArrayList<>();
    }

    @Override
    public void draw() {
        background(255);
        for (int i = 0; i < layers.size(); i++) {
            image(layers.get(i), 0, 0);
        }

        noLoop();
    }

    @Override
    public void mouseDragged() {
        loop();
        if (mode == edit) {
            if (state == layer1) {
                editDraw(state, col);
            }
        } else if (mode == delete) {
            deleteDraw(state);
        }
    }

    @Override
    public void mousePressed() {
        loop();
        if (mode == edit) {
            if (state == layer2) {
                editPath(state);
            }
        } else if (mode == delete) {
            if (state == layer2) {
                deletePath(state);
            }
        }
    }

    public void editDraw(int i, int c) {
        PGraphics pg = layers.get(i);
        if (mousePressed) {
            pg.beginDraw();
            pg.stroke(cp[c]);
            pg.strokeWeight(100);
            pg.line(pmouseX, pmouseY, mouseX, mouseY);
            pg.endDraw();
        }
        noStroke();
        ellipse(mouseX, mouseY, 100, 100);
    }

    public void deleteDraw(int i) {
        PGraphics pg = layers.get(i);
        if (mousePressed) {
            pg.beginDraw();
            pg.loadPixels();
            for (int x = 0; x < pg.width; x++) {
                for (int y = 0; y < pg.height; y++) {
                    float distance = dist(x, y, mouseX, mouseY);
                    if (distance <= 25) {
                        int loc = x + y * pg.width;
                        pg.pixels[loc] = 0x0;
                    }
                }
            }
            pg.updatePixels();
            pg.endDraw();
        }
        noStroke();
        ellipse(mouseX, mouseY, 50, 50);
    }

    public void editPath(int i) {
        points.add(new PVector(mouseX, mouseY));
        PGraphics pg = layers.get(i);
        pg.beginDraw();
        pg.noFill();
        pg.beginShape();
        if (points.size() > 0) {
            pg.curveVertex(points.get(0).x, points.get(0).y);
            points.stream().map((p) -> {
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
        if (points.size() > 2) {
            points.remove(points.size() - 1);
        }
        PGraphics pg = layers.get(i);
        pg.beginDraw();
        pg.clear();
        pg.endDraw();
        pg.beginDraw();
        pg.noFill();
        pg.beginShape();
        if (points.size() > 0) {
            pg.curveVertex(points.get(0).x, points.get(0).y);
            points.stream().map((p) -> {
                pg.curveVertex(p.x, p.y);
                return p;
            }).forEachOrdered((p) -> {
                pg.ellipse(p.x, p.y, 3, 3);
            });
        }
        pg.endShape();
        pg.endDraw();
    }

    @Override
    public void keyPressed() {
        loop();
        switch (key) {
            case '1':
                state = layer1;
                break;
            case '2':
                state = layer2;
                break;
            case '3':
                state = layer3;
                break;
            case '4':
                state = layer4;
                break;
            case '5':
                state = layer5;
                break;
            default:
                break;
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
        } else if (key == 'd') {
            mode = delete;
        }

        if (key == 's') {
            saveWorld();
        }

        if (key == 'l') {
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
