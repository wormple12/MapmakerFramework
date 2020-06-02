package processing.map.ui;

import processing.ProcessingMapmaker;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class InfoUI_P3 {

    private final ProcessingMapmaker app;

    public InfoUI_P3(ProcessingMapmaker app) {
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
