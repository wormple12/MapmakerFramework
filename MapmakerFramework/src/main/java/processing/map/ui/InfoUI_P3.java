package processing.map.ui;

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

    private String text = null;

    public InfoUI_P3(ProcessingMapmaker app) {
        this.app = app;
        layerGraphics = app.createGraphics(app.width, app.height);
        infoGraphics = app.createGraphics(app.width, app.height);
    }

    public void loadLayerUIGraphics(int layer) {
        layerGraphics.beginDraw();
        layerGraphics.clear();
        layerGraphics.textSize(37);
        layerGraphics.text("Active layer: " + layer, app.displayWidth - 285, 50);
        layerGraphics.endDraw();
        app.image(layerGraphics, 0, 0);
    }

    public void loadInfoUIGraphics(EntityInfo info) {
        String textToDisplay = "Name: ";
        if (text != null) {
            textToDisplay += text;
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
            if (!"".equals(text)) {
                text = text.substring(0, text.length() - 1);
            }
        } else {
            text += app.key;
        }
    }

    public void setText(String t) {
        text = t;
    }

    public String getText() {
        return text;
    }

}
