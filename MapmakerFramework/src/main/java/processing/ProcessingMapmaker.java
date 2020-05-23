package processing;

import java.util.ArrayList;
import java.util.Arrays;
import mapmaker.entities.Biome;
import mapmaker.entities.EntityInfo;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import mapmaker.general.files.FileStorage;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import processing.entities.LandmassP3;
import processing.entities.RouteP3;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMapmaker extends PApplet {

    MapStorageP3 fileStore = new MapStorageP3();

    ArrayList<PGraphics> landMass = new ArrayList();
    ArrayList<PGraphics> region = new ArrayList();
    ArrayList<PGraphics> location = new ArrayList();
    ArrayList<PGraphics> userMarker = new ArrayList();
    ArrayList<PGraphics> route = new ArrayList<>();

    public void saveWorld() {
        EntityInfo regionInfo = new EntityInfo("NewRegionTest");
        EntityInfo mapInfo = new EntityInfo("NewWorldTest");

        ArrayList<Region> regionList = new ArrayList();
        regionList.add(new Region(new LandmassP3(), regionInfo, Biome.HILLS)); // replace this with the line below, when region layers are used correctly
//        region.stream().map((r) -> new Region(new LandmassP3(), regionInfo, Biome.HILLS))
//                .forEachOrdered(regionList::add);

        ArrayList<Route> routeList = new ArrayList<>();
        route.stream().map((r) -> new RouteP3())
                .forEachOrdered(routeList::add);

        ArrayList<Location> locationList = new ArrayList();
        ArrayList<UserMarker> userMarkerList = new ArrayList();

        WorldMap world = new WorldMap(mapInfo, regionList, locationList, userMarkerList, routeList);
        boolean success = fileStore.attemptSave(world, Arrays.asList(landMass.get(1)), route); // replace this with the line below, when region layers are used correctly
//        boolean success = fileStore.attemptSave(world, region, route);
        System.out.println("Success on save : " + success);
    }

    public void loadWorld() {
        WorldMap world = fileStore.attemptLoad(false);
        if (world != null) {
            System.out.println("Loaded Map: " + world.getInfo().getName());
            loop();
            for (int i = 0; i < world.getRegions().size(); i++) {
                LandmassP3 area = (LandmassP3) world.getRegions().get(i).getArea();
                PImage regionImg = loadImage(area.getGraphicsPath());
                // change to region.get/set(i), when region layers are used correctly
                landMass.set(1, createGraphics(regionImg.width, regionImg.height));
                landMass.get(1).beginDraw();
                landMass.get(1).image(regionImg, 0, 0);
                landMass.get(1).endDraw();
            }
            for (int i = 0; i < world.getRoutes().size(); i++) {
                RouteP3 r = (RouteP3) world.getRoutes().get(i);
                PImage routeImg = loadImage(r.getGraphicsPath());
                route.set(i, createGraphics(routeImg.width, routeImg.height));
                route.get(i).beginDraw();
                route.get(i).image(routeImg, 0, 0);
                route.get(i).endDraw();
            }
        }
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
    final int state5 = 5;
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
        landMass.add(pg);

        landMass.add(createGraphics(width, height));
        landMass.get(1).beginDraw();
        landMass.get(1).background(0f, 0f);
        landMass.get(1).endDraw();
        // should be created when starting to draw/place elements instead
        // otherwise, save functionality will act weirdly
        region.add(createGraphics(width, height));
        location.add(createGraphics(width, height));
        userMarker.add(createGraphics(width, height));
        route.add(createGraphics(width, height));

        pointsForPaths = new ArrayList<>();
        ArrayList<PVector> points = new ArrayList<>();
        pointsForPaths.add(points);

        hint(DISABLE_ASYNC_SAVEFRAME); // prevents the black-box saving issue, when exporting PGraphics layers to files
    }

    @Override
    public void draw() {
        background(255);
        for (int i = 0; i < landMass.size(); i++) {
            image(landMass.get(i), 0, 0);
        }

        for (int i = 0; i < region.size(); i++) {
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
                editDraw(1, col);
            }
        } else if (mode == delete) {
            if (state == state1) {
                deleteDraw(1);
            }
        }
    }

    @Override
    public void mousePressed() {
        loop();
        if (mode == edit) {
            if (state == state5) {
                editPath(layer);
            }
        } else if (mode == delete) {
            if (state == state5) {
                deletePath(layer);
            }
        }
    }

    public void editDraw(int i, int c) {
        PGraphics pg = landMass.get(i);
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
        PGraphics pg = landMass.get(i);
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
        PGraphics pg = landMass.get(1);
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
                System.out.println("state1 active");
                break;
            case '2':
                state = state2;
                System.out.println("state2 active");
                break;
            case '3':
                state = state3;
                System.out.println("state3 active");
                break;
            case '4':
                state = state4;
                System.out.println("state4 active");
                break;
            case '5':
                state = state5;
                System.out.println("state5 active");
                break;
            default:
                break;
        }

        if (key == 'l') {
            switch (state) {
                case state1:
                    if (state == 1) {
                        layer = 1;
                    }
                    System.out.println("state1 layer switch");
                    break;
                case state2:
                    if (layer < region.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state2 layer switch");
                    break;
                case state3:
                    if (layer < location.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state3 layer switch");
                    break;
                case state4:
                    if (layer < userMarker.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state4 layer switch");
                    break;
                case state5:
                    if (layer < route.size() - 1) {
                        layer = layer + 1;
                    } else {
                        layer = 0;
                    }
                    System.out.println("state5 layer switch");
                    break;
                default:
                    break;
            }
        }

        if (key == 'n') {
            switch (state) {
                case state2:
                    PGraphics pg2 = createGraphics(width, height);
                    region.add(pg2);
                    System.out.println("Adding for layer 2");
                    break;
                case state3:
                    PGraphics pg3 = createGraphics(width, height);
                    location.add(pg3);
                    System.out.println("Adding for layer 3");
                    break;
                case state4:
                    PGraphics pg4 = createGraphics(width, height);
                    userMarker.add(pg4);
                    System.out.println("Adding for layer 4");
                    break;
                case state5:
                    PGraphics pg5 = createGraphics(width, height);
                    route.add(pg5);
                    ArrayList<PVector> points = new ArrayList<>();
                    pointsForPaths.add(points);
                    System.out.println("Adding for layer 5");
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

        if (key == 's') {
            saveWorld();
        }

        if (key == 'r' & state == state1) {
            randomMap();
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
