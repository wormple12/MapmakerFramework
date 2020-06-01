package processing.map.ui;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class InfoUI_P3 {

    private final PApplet app;

    public InfoUI_P3(PApplet app) {
        this.app = app;
    }
    
    public void display(int layer) {
        app.image(loadLayerUIGraphics(layer), 0, 0);
    }

    private PGraphics loadLayerUIGraphics(int layer) {
        PGraphics uiLayer = app.createGraphics(app.width, app.height);
        uiLayer.beginDraw();
        uiLayer.textSize(37);
        uiLayer.text("Active layer: " + layer, app.displayWidth - 285, 50);
        uiLayer.endDraw();
        return uiLayer;
    }

}
