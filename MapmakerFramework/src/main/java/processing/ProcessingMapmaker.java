package processing;

import java.util.ArrayList;
import mapmaker.entities.Biome;
import mapmaker.entities.EntityInfo;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import mapmaker.entities.sprites.LocationImp;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import mapmaker.entities.sprites.UserMarkerImp;
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

    ArrayList<PGraphics> seaLevel = new ArrayList();

    ArrayList<PGraphics> region = new ArrayList();
    ArrayList<PGraphics> border = new ArrayList();

    ArrayList<LocationImp> locations = new ArrayList();
    ArrayList<UserMarkerImp> userMarkers = new ArrayList();

    ArrayList<PGraphics> route = new ArrayList<>();

//    public void saveWorld() {
//        EntityInfo regionInfo = new EntityInfo("NewRegionTest");
//        EntityInfo mapInfo = new EntityInfo("NewWorldTest");
//
//        ArrayList<Region> regionList = new ArrayList();
//        region.stream().sign((r) -> new Region(new LandmassP3(), regionInfo, Biome.HILLS))
//                .forEachOrdered(regionList::add);
//
//        ArrayList<Route> routeList = new ArrayList<>();
//        route.stream().sign((r) -> new RouteP3())
//                .forEachOrdered(routeList::add);
//
//        ArrayList<Location> locationList = new ArrayList();
//        ArrayList<UserMarker> userMarkerList = new ArrayList();
//
//        WorldMap world = new WorldMap(mapInfo, regionList, locationList, userMarkerList, routeList);
//        boolean success = fileStore.attemptSave(world, region, border, route);
//        System.out.println("Success on save : " + success);
//    }
//
//    public void loadWorld() {
//        WorldMap world = fileStore.attemptLoad(false);
//        if (world != null) {
//            System.out.println("Loaded Map: " + world.getInfo().getName());
//            loop();
//            for (int i = 0; i < world.getRegions().size(); i++) {
//                LandmassP3 area = (LandmassP3) world.getRegions().get(i).getArea();
//                PImage regionImg = loadImage(area.getGraphicsPath());
//                region.set(i, createGraphics(regionImg.width, regionImg.height));
//                region.get(i).beginDraw();
//                region.get(i).image(regionImg, 0, 0);
//                region.get(i).endDraw();
//                PImage borderImg = loadImage(area.getBorderGraphicsPath());
//                border.set(i, createGraphics(regionImg.width, regionImg.height));
//                border.get(i).beginDraw();
//                border.get(i).image(borderImg, 0, 0);
//                border.get(i).endDraw();
//            }
//            for (int i = 0; i < world.getRoutes().size(); i++) {
//                RouteP3 r = (RouteP3) world.getRoutes().get(i);
//                PImage routeImg = loadImage(r.getGraphicsPath());
//                route.set(i, createGraphics(routeImg.width, routeImg.height));
//                route.get(i).beginDraw();
//                route.get(i).image(routeImg, 0, 0);
//                route.get(i).endDraw();
//            }
//        }
//    }
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

    //Drag drop locations
    final int DRAG_NONE = 0;
    final int DRAG_1 = 1;
    final int DRAG_2 = 2;
    final int DRAG_3 = 3;
    int dragging = DRAG_NONE;
    final int UNDEFINED = -1;
    int draggingAnExistingRectClass = UNDEFINED;
    int active = 0;
    final PVector pv1 = new PVector(40, 110);
    final PVector pv2 = new PVector(40, 180);
    final PVector pv3 = new PVector(40, 250);
    PImage sign;
    PImage circle;
    PImage square;

    //Drag drop userMarkers
    final int USER_DRAG_NONE = 0;
    final int USER_DRAG_1 = 1;
    int draggingUser = USER_DRAG_NONE;
    final int USER_UNDEFINED = -1;
    int draggingAnExistingUserMarker = USER_UNDEFINED;
    int activeMarker = 0;
    final PVector userPv1 = new PVector(40, 110);
    PImage user;

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

        // should be created when starting to draw/place elements instead
        // otherwise, save functionality will act weirdly
        // to start with they are empty!
        region.add(createGraphics(width, height));
        border.add(createGraphics(width, height));
        route.add(createGraphics(width, height));

        pointsForPaths = new ArrayList<>();
        ArrayList<PVector> points = new ArrayList<>();
        pointsForPaths.add(points);

        sign = loadImage("C:\\Users\\rasmu\\Desktop\\sketch_200501b\\sign.png");
        circle = loadImage("C:\\Users\\rasmu\\Desktop\\sketch_200501b\\circle.png");
        square = loadImage("C:\\Users\\rasmu\\Desktop\\sketch_200501b\\square.png");

        user = loadImage("C:\\Users\\rasmu\\Desktop\\sketch_200501b\\map.png");

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

        for (int i = 0; i < route.size(); i++) {
            image(route.get(i), 0, 0);
        }

        for (int i = 0; i < locations.size(); i++) {
            LocationImp currentNode = locations.get(i);
            image(currentNode.getImage(), currentNode.getX(), currentNode.getY(), currentNode.getW(), currentNode.getH());
        }
        if (state == state2) {
            drawForStateEdit();
        }

        for (int i = 0; i < userMarkers.size(); i++) {
            UserMarkerImp currentUserNode = userMarkers.get(i);
            image(currentUserNode.getImage(), currentUserNode.getX(), currentUserNode.getY(), currentUserNode.getW(), currentUserNode.getH());
        }
        if (state == state3) {
            userDrawForStateEdit();
        }
    }

    @Override
    public void mouseDragged() {
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
        if (mode == edit) {
            if (state == state4) {
                editPath(layer);
            }
        } else if (mode == delete) {
            if (state == state4) {
                deletePath(layer);
            }
        }

        draggingAnExistingRectClass = UNDEFINED;
        if (state == state2) {
            if (dist(mouseX, mouseY, pv1.x, pv1.y) < 20) {
                dragging = DRAG_1;
            } else if (dist(mouseX, mouseY, pv2.x, pv2.y) < 20) {
                dragging = DRAG_2;
            } else if (dist(mouseX, mouseY, pv3.x, pv3.y) < 20) {
                dragging = DRAG_3;
            } else {
                for (int i = 0; i < locations.size(); i++) {
                    LocationImp RectClass = locations.get(i);
                    if (RectClass.over(mouseX, mouseY)) {
                        draggingAnExistingRectClass = i;
                        active = i;
                    }
                }
            }
        }

        draggingAnExistingUserMarker = USER_UNDEFINED;
        if (state == state3) {
            if (dist(mouseX, mouseY, userPv1.x, userPv1.y) < 20) {
                draggingUser = USER_DRAG_1;
            } else {
                for (int i = 0; i < userMarkers.size(); i++) {
                    UserMarkerImp RectClass = userMarkers.get(i);
                    if (RectClass.over(mouseX, mouseY)) {
                        draggingAnExistingUserMarker = i;
                        activeMarker = i;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased() {
        if (state == state2) {
            switch (dragging) {
                case DRAG_1:
                    dragging = DRAG_NONE;
                    locations.add(new LocationImp(mouseX, mouseY,
                            30, 30, circle));
                    break;
                case DRAG_2:
                    dragging = DRAG_NONE;
                    locations.add(new LocationImp(mouseX, mouseY,
                            30, 30, square));
                    break;
                case DRAG_3:
                    dragging = DRAG_NONE;
                    locations.add(new LocationImp(mouseX, mouseY,
                            25, 35, sign));
                    break;
                default:
                    break;
            }

        }
        draggingAnExistingRectClass = UNDEFINED;

        if (state == state3) {
            switch (draggingUser) {
                case USER_DRAG_1:
                    draggingUser = USER_DRAG_NONE;
                    userMarkers.add(new UserMarkerImp(mouseX, mouseY,
                            25, 35, user));
                    break;
                default:
                    break;
            }

        }
        draggingAnExistingUserMarker = USER_UNDEFINED;
    }

    void drawForStateEdit() {
        fill(255);
        rect(1, pv1.y - 30, 76, 60);
        image(circle, 23, 95, 30, 30);

        fill(255);
        rect(1, pv2.y - 30, 76, 60);
        image(square, 23, 166, 30, 30);

        fill(255);
        rect(1, pv3.y - 30, 76, 60);
        image(sign, 27, 235, 25, 35);

        if (dragging == DRAG_1) {
            image(circle, mouseX, mouseY, 30, 30);
        } else if (dragging == DRAG_2) {
            image(square, mouseX, mouseY, 30, 30);
        } else if (dragging == DRAG_3) {
            image(sign, mouseX, mouseY, 25, 35);
        } else if (draggingAnExistingRectClass != UNDEFINED) {
            LocationImp RectClass = locations.get(draggingAnExistingRectClass);
            RectClass.incrementXY(mouseX - pmouseX, mouseY - pmouseY);
        }
    }

    void userDrawForStateEdit() {
        fill(255);
        rect(1, userPv1.y - 30, 76, 60);
        image(user, 23, 95, 25, 35);

        if (draggingUser == USER_DRAG_1) {
            image(user, mouseX, mouseY, 25, 35);
        } else if (draggingAnExistingUserMarker != USER_UNDEFINED) {
            UserMarkerImp UserRectClass = userMarkers.get(draggingAnExistingUserMarker);
            UserRectClass.incrementXY(mouseX - pmouseX, mouseY - pmouseY);
        }
    }

    public void editDraw(int i, int c) {
        PGraphics pg = region.get(i);
        PGraphics pgb = border.get(i);
        if (mousePressed) {
            pg.beginDraw();
            pg.stroke(cp[c]);
            pg.strokeWeight(100);
            pg.line(pmouseX, pmouseY, mouseX, mouseY);
            pg.endDraw();

            pgb.beginDraw();
            pgb.stroke(0);
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
    }

    @Override
    public void keyPressed() {
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
                    System.out.println("state2 layer switch");
                    break;
                case state3:
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
                    break;
                case state3:
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

//        if (key == 's') {
//            saveWorld();
//        }
//
//        if (key == 'o') {
//            loadWorld();
//        }
        if (state == state2) {
            if (key == BACKSPACE) {
                if (locations.size() > 0 && locations.size() > active) {
                    locations.remove(active);
                }
            }
        }

        if (state == state3) {
            if (key == BACKSPACE) {
                if (userMarkers.size() > 0 && userMarkers.size() > activeMarker) {
                    userMarkers.remove(activeMarker);
                }
            }
        }
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"ProcessingMapmaker"};
        ProcessingMapmaker mySketch = new ProcessingMapmaker();
        PApplet.runSketch(appletArgs, mySketch);
    }

}
