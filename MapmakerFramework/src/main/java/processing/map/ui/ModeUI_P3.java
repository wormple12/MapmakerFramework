package processing.map.ui;

import mapmaker.general.Mode;
import mapmaker.map.ui.ModeUI;
import processing.ProcessingMapmaker;
import processing.core.PGraphics;

/**
 *
 * @author TeamOne
 */
public final class ModeUI_P3 implements ModeUI {

    private final ProcessingMapmaker app;
    private final PGraphics uiLayer;

    private Mode currentMode = Mode.CONTROL;
    private Mode previousMode = null;

    public ModeUI_P3(ProcessingMapmaker app) {
        this.app = app;
        uiLayer = app.createGraphics(app.width, app.height);
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

    @Override
    public boolean isCurrentMode(Mode... modes) {
        for (Mode mode : modes) {
            if (currentMode == mode) {
                return true;
            }
        }
        return false;
    }

    public boolean isCurrentOrPreviousMode(Mode mode) {
        return isCurrentMode(mode) || isPreviousMode(mode);
    }

    public boolean isPreviousMode(Mode mode) {
        return previousMode == mode;
    }

    public Mode getPreviousMode() {
        return previousMode;
    }

    public void setPreviousMode(Mode previousMode) {
        this.previousMode = previousMode;
    }

    public boolean isInCTRLMode() {
        return currentMode == Mode.CONTROL;
    }

    @Override
    public void display() {
        app.image(loadModeUIGraphics(), 0, 0);
    }

    private PGraphics loadModeUIGraphics() {
        uiLayer.beginDraw();
        uiLayer.clear();
        uiLayer.textSize(37);
        uiLayer.text("Mode: " + currentMode.name(), 1, 50);
        uiLayer.endDraw();
        return uiLayer;
    }

}
