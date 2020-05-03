package processing;

import processing.core.PApplet;

/**
 *
 * @author Simon Norup
 */
public class ProcessingMapmaker extends PApplet {

    final int EDIT = 1;
    final int RUN = 2;
    final int CAM = 3;
    final int OBJECT = 4;
    int state = CAM;

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

    int[] terrain;
    int[] biome;

    @Override
    public void settings() {
        size(1920, 1080);
    }

    @Override
    public void setup() {
        noiseSeed((long) random(10000000));
        loadPixels();
        terrain = new int[height * width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pixels[j * width + i] = cp[3];
                terrain[j * width + i] = 3;
            }
        }
        updatePixels();
    }

    @Override
    public void draw() {
        noLoop();
    }

    public void randomMap() {
        noiseSeed((long) random(10000000));
        loadPixels();
        float d0 = random(100, 200);
        float d1 = random(25, 75);
        terrain = new int[height * width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                float n0 = noise(i / d0, j / d0, 0);
                float n1 = noise(i / d1, j / d1, 10);
                float n = (float) (1 - (n0 * 0.75 + n1 * 0.25));
                int k = (int) (n * cp.length);
                pixels[j * width + i] = cp[k];
                terrain[j * width + i] = k;
            }
        }
        updatePixels();
    }

    @Override
    public void keyPressed() {
        loop();
        switch (key) {
            case '1':
                state = EDIT;
                break;
            case '2':
                state = RUN;
                break;
            case '3':
                state = CAM;
                break;
            case '4':
                state = OBJECT;
                break;
            default:
                break;
        }
        keySwitch();
    }

    @Override
    public void mouseDragged() {
        loop();
        mouseSwitch();
    }

    public void keySwitch() {
        switch (state) {

            case EDIT:
                stateEdit();
                break;

            case RUN:
                stateRun();
                break;

            case CAM:
                stateCam();
                break;

            case OBJECT:
                stateObject();
                break;
        }
    }

    public void stateEdit() {
        if (key == 'r') {
            randomMap();
        }
    }

    public void stateRun() {
    }

    public void stateCam() {
    }

    public void stateObject() {
    }

    public void mouseSwitch() {
        switch (state) {

            case CAM:
                mouseCam();
                break;

            case OBJECT:
                mouseStateObject();
                break;
        }
    }

    public void mouseCam() {
    }

    public void mouseStateObject() {
    }

    @Override
    public void mousePressed() {

    }

    @Override
    public void mouseReleased() {

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
