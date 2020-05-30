package processing.map.ui;

import mapmaker.editor.Mode;
import mapmaker.map.ui.ModeUI;
import processing.core.PApplet;

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
        // currently hotkeys only
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
