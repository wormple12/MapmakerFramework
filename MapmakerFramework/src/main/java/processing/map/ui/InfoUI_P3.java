package processing.map.ui;

import mapmaker.general.Mode;
import mapmaker.entities.EntityInfo;
import processing.ProcessingMapmaker;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class InfoUI_P3 {

    private final ProcessingMapmaker app;
    private final PGraphics layerGraphics;
    private final PGraphics infoGraphics;

    private String infoText = null;

    public InfoUI_P3(ProcessingMapmaker app) {
        this.app = app;
        layerGraphics = app.createGraphics(app.width, app.height);
        infoGraphics = app.createGraphics(app.width, app.height);
    }

    public void loadLayerUIGraphics(int layer) {
        String layerStr = (layer == -1) ? "All" : Integer.toString(layer);
        layerGraphics.beginDraw();
        layerGraphics.clear();
        layerGraphics.textSize(37);
        layerGraphics.text("Active layer: " + layerStr, app.displayWidth - 285, 50);
        layerGraphics.endDraw();
        app.image(layerGraphics, 0, 0);
    }

    public void loadInfoUIGraphics(EntityInfo info) {
        String textToDisplay = "Name: ";
        if (infoText != null) {
            textToDisplay += infoText;
        } else if (info != null) {
            textToDisplay += info.getName();
        }
        infoGraphics.beginDraw();
        infoGraphics.clear();
        infoGraphics.textSize(37);
        infoGraphics.text(textToDisplay, 1, app.displayHeight - 100);
        infoGraphics.endDraw();
        app.image(infoGraphics, 0, 0);
    }

    public void writeToText(int keycode) {
        if (keycode == PApplet.BACKSPACE) {
            if (!"".equals(infoText)) {
                infoText = infoText.substring(0, infoText.length() - 1);
            }
        } else {
            infoText += app.key;
        }
    }

    public void setInfoText(String t) {
        infoText = t;
    }

    public String getInfoText() {
        return infoText;
    }

}
