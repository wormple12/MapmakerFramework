package processing.map.ui;

import mapmaker.editor.Mode;
import mapmaker.map.ui.ModeUI;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public final class ModeUI_P3 implements ModeUI {

    private final PApplet app;

    private Mode currentMode = Mode.CAMERA;
    private Mode previousMode = null;

    public ModeUI_P3(PApplet app) {
        this.app = app;
    }

    @Override
    public void display() {
        app.image(loadModeUIGraphics(), 0, 0);
    }

    private PGraphics loadModeUIGraphics() {
        PGraphics uiLayer = app.createGraphics(app.width, app.height);
        uiLayer.beginDraw();
        uiLayer.textSize(37);
        uiLayer.text("Mode: " + currentMode.name(), 1, 50);
        uiLayer.endDraw();
        return uiLayer;
    }

    @Override
    public void switchMode(Mode mode) {
        currentMode = mode;
        System.out.println(mode + " mode entered.");
    }

    @Override
    public Mode getCurrentMode() {
        return currentMode;
    }

    public Mode getPreviousMode() {
        return previousMode;
    }

    public void setPreviousMode(Mode previousMode) {
        this.previousMode = previousMode;
    }

}
