package processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mapmaker.entities.Biome;
import mapmaker.entities.EntityInfo;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.WorldMap;
import processing.entities.sprites.LocationP3;
import mapmaker.entities.sprites.Location;
import mapmaker.entities.sprites.UserMarker;
import processing.entities.sprites.UserMarkerP3;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import processing.entities.LandmassP3;
import processing.entities.RouteP3;
import processing.entities.sprites.LocationInfoP3;
import processing.entities.sprites.SpriteTypeP3;
import processing.entities.sprites.UserMarkerInfoP3;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMapmaker extends PApplet {

    MapStorageP3 fileStore = new MapStorageP3(this);

    List<PGraphics> water = new ArrayList();

    List<PGraphics> regions = new ArrayList();
    List<PGraphics> borders = new ArrayList();
    List<Location> locations = new ArrayList();
    List<UserMarker> userMarkers = new ArrayList();
    List<PGraphics> routes = new ArrayList<>();

    // TEMPORARY SAVING METHOD
    // Instead a WorldMap object (stored in CanvasP3) should be kept updated at all times
    public void saveWorld() {
        EntityInfo regionInfo = new EntityInfo("NewRegionTest");
        EntityInfo mapInfo = new EntityInfo("NewWorldTest");

        ArrayList<Region> regionList = new ArrayList();
        for (int i = 0; i < regions.size(); i++) {
            regionList.add(new Region(
                    new LandmassP3(regions.get(i), borders.get(i)),
                    regionInfo,
                    Biome.HILLS
            ));
        }

        ArrayList<Route> routeList = new ArrayList<>();
        routes.stream().map((r) -> new RouteP3(r))
                .forEachOrdered(routeList::add);

        WorldMap world = new WorldMap(mapInfo, regionList, locations, userMarkers, routeList);
        boolean success = fileStore.attemptSave(world);
        System.out.println("Success on save : " + success);
    }

    // TEMPORARY LOADING METHOD
    // Instead the map components should be stored in a WorldMap object in CanvasP3
    public void loadWorld() {
        WorldMap world = fileStore.attemptLoad(false);
        if (world != null) {
            loop();
            regions = world.getRegions().stream()
                    .map((r) -> ((LandmassP3) r.getArea()).getGraphics())
                    .collect(Collectors.toList());
            borders = world.getRegions().stream()
                    .map((r) -> ((LandmassP3) r.getArea()).getBorderGraphics())
                    .collect(Collectors.toList());
            routes = world.getRoutes().stream()
                    .map((r) -> ((RouteP3) r).getGraphics())
                    .collect(Collectors.toList());
            locations = world.getLocations();
            userMarkers = world.getMarkers();
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
    int state = state1;

    int layer = 0;

    final int edit = 1;
    final int delete = 2;
    int mode = edit;

    ArrayList<ArrayList<PVector>> pointsForPaths;

    //Drag drop locations
    final int DRAG_NONE = -1;
    int dragging = DRAG_NONE;
    final int UNDEFINED = -1;
    int draggingAnExistingLocation = UNDEFINED;
    int active = 0;
    List<SpriteTypeP3> locationTypes = new ArrayList<>();

    //Drag drop userMarkers
    final int USER_UNDEFINED = -1;
    int draggingAnExistingUserMarker = USER_UNDEFINED;
    int activeMarker = 0;
    List<SpriteTypeP3> userMarkerTypes = new ArrayList<>();

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
        water.add(pg);

//        location.add(createGraphics(width, height));
//        userMarker.add(createGraphics(width, height));
        pointsForPaths = new ArrayList<>();
        ArrayList<PVector> points = new ArrayList<>();
        pointsForPaths.add(points);

        loadSpritesFromConfig(locationTypes, "sprites/locations/");
        loadSpritesFromConfig(userMarkerTypes, "sprites/markers/");

        hint(DISABLE_ASYNC_SAVEFRAME); // prevents the black-box saving issue, when exporting PGraphics layers to files
    }

    private void loadSpritesFromConfig(List<SpriteTypeP3> listToLoadInto, String configPath) {
        try {
            int vectorY = 110;

            try (FileReader fr = new FileReader(configPath + "settings.config")) {
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                String line;
                while ((line = br.readLine()) != null) {
                    String[] spriteArgs = line.split(" ");
                    String imagePath = configPath + spriteArgs[0];
                    final PImage sprite = loadImage(imagePath);
                    final PVector vector = new PVector(40, vectorY);
                    vectorY += 70;
                    listToLoadInto.add(new SpriteTypeP3(vector, sprite, imagePath, Float.parseFloat(spriteArgs[1]), Float.parseFloat(spriteArgs[2])));
                }
            }
        } catch (IOException ex) {
            System.out.println("Couldn't load " + configPath);
        }
    }

    @Override
    public void draw() {
        background(255);
        for (int i = 0; i < water.size(); i++) {
            image(water.get(i), 0, 0);
        }

        for (int i = 0; i < regions.size(); i++) {
            image(borders.get(i), 0, 0);
            image(regions.get(i), 0, 0);
        }

        for (int i = 0; i < routes.size(); i++) {
            image(routes.get(i), 0, 0);
        }

        for (int i = 0; i < locations.size(); i++) {
            LocationP3 currentNode = (LocationP3) locations.get(i);
            image(currentNode.getImage(), currentNode.getX(), currentNode.getY(), currentNode.getWidth(), currentNode.getHeight());
        }
        if (state == state2) {
            drawForStateEdit();
        }

        for (int i = 0; i < userMarkers.size(); i++) {
            UserMarkerP3 currentUserNode = (UserMarkerP3) userMarkers.get(i);
            image(currentUserNode.getImage(), currentUserNode.getX(), currentUserNode.getY(), currentUserNode.getWidth(), currentUserNode.getHeight());
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

        draggingAnExistingLocation = UNDEFINED;
        if (state == state2) {
            for (int i = 0; i < locationTypes.size(); i++) {
                SpriteTypeP3 type = locationTypes.get(i);
                if (dist(mouseX, mouseY, type.getVector().x, type.getVector().y) < 20) {
                    dragging = i;
                    break;
                }
            }
            if (dragging == DRAG_NONE) {
                for (int i = 0; i < locations.size(); i++) {
                    LocationP3 location = (LocationP3) locations.get(i);
                    if (location.over(mouseX, mouseY)) {
                        draggingAnExistingLocation = i;
                        active = i;
                    }
                }
            }
        }

        draggingAnExistingUserMarker = USER_UNDEFINED;
        if (state == state3) {
            for (int i = 0; i < userMarkerTypes.size(); i++) {
                SpriteTypeP3 type = userMarkerTypes.get(i);
                if (dist(mouseX, mouseY, type.getVector().x, type.getVector().y) < 20) {
                    dragging = i;
                    break;
                }
            }
            if (dragging == DRAG_NONE) {
                for (int i = 0; i < userMarkers.size(); i++) {
                    UserMarkerP3 marker = (UserMarkerP3) userMarkers.get(i);
                    if (marker.over(mouseX, mouseY)) {
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
            if (dragging != DRAG_NONE) {
                SpriteTypeP3 type = locationTypes.get(dragging);
                locations.add(new LocationP3(
                        new LocationInfoP3("TO DO"), type.getSpritePath(),
                        mouseX, mouseY, type.getWidth(), type.getHeight(), type.getImage()
                ));
                dragging = DRAG_NONE;
            }
        }
        draggingAnExistingLocation = UNDEFINED;

        if (state == state3) {
            if (dragging != DRAG_NONE) {
                SpriteTypeP3 type = userMarkerTypes.get(dragging);
                userMarkers.add(new UserMarkerP3(
                        new UserMarkerInfoP3("TO DO"), type.getSpritePath(),
                        mouseX, mouseY, type.getWidth(), type.getHeight(), type.getImage()
                ));
                dragging = DRAG_NONE;
            }
        }
        draggingAnExistingUserMarker = USER_UNDEFINED;
    }

    void drawForStateEdit() {
        int imageY = 95;
        for (SpriteTypeP3 type : locationTypes) {
            fill(255);
            rect(1, type.getVector().y - 30, 76, 60);
            image(type.getImage(), 23, imageY, type.getWidth(), type.getHeight());
            imageY += 70;
        }
        if (dragging != DRAG_NONE) {
            SpriteTypeP3 currentType = locationTypes.get(dragging);
            image(currentType.getImage(), mouseX, mouseY, currentType.getWidth(), currentType.getHeight());
        } else if (draggingAnExistingLocation != UNDEFINED) {
            LocationP3 currentLoc = (LocationP3) locations.get(draggingAnExistingLocation);
            currentLoc.incrementCoordinates(mouseX - pmouseX, mouseY - pmouseY);
        }
    }

    void userDrawForStateEdit() {
        int imageY = 95;
        for (SpriteTypeP3 type : userMarkerTypes) {
            fill(255);
            rect(1, type.getVector().y - 30, 76, 60);
            image(type.getImage(), 23, imageY, type.getWidth(), type.getHeight());
            imageY += 70;
        }
        if (dragging != DRAG_NONE) {
            SpriteTypeP3 currentType = userMarkerTypes.get(dragging);
            image(currentType.getImage(), mouseX, mouseY, currentType.getWidth(), currentType.getHeight());
        } else if (draggingAnExistingUserMarker != UNDEFINED) {
            UserMarkerP3 currentMarker = (UserMarkerP3) userMarkers.get(draggingAnExistingUserMarker);
            currentMarker.incrementCoordinates(mouseX - pmouseX, mouseY - pmouseY);
        }
    }

    public void editDraw(int i, int c) {
        if (regions.isEmpty()) {
            regions.add(createGraphics(width, height));
            borders.add(createGraphics(width, height));
        }
        PGraphics pg = regions.get(i);
        PGraphics pgb = borders.get(i);
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
        PGraphics pg = regions.get(i);
        PGraphics pgb = borders.get(i);
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
        if (routes.isEmpty()) {
            routes.add(createGraphics(width, height));
        }
        pointsForPaths.get(i).add(new PVector(mouseX, mouseY));
        PGraphics pg = routes.get(i);
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
        PGraphics pg = routes.get(i);
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
        PGraphics pg = water.get(0);
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
                    if (layer < regions.size() - 1) {
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
                    if (layer < routes.size() - 1) {
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
                    regions.add(pg2);
                    PGraphics pgBorder = createGraphics(width, height);
                    borders.add(pgBorder);
                    System.out.println("Adding for layer 1");
                    break;
                case state2:
                    break;
                case state3:
                    break;
                case state4:
                    PGraphics pg5 = createGraphics(width, height);
                    routes.add(pg5);
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
