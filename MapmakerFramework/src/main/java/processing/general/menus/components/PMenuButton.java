package processing.general.menus.components;

import processing.core.PApplet;
import static processing.core.PApplet.min;
import static processing.core.PConstants.CENTER;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class PMenuButton {

    private final PApplet app;
    private final PGraphics menuLayer;
    private final String label;
    private final float x;    // top left corner x position
    private final float y;    // top left corner y position
    private final float w;    // width of button
    private final float h;    // height of button
    private final float textSize;

    public PMenuButton(String label, float x, float y, float width, float height, float textSize, PGraphics menuLayer, PApplet app) {
        this.app = app;
        this.menuLayer = menuLayer;
        this.label = label;
        this.x = x;
        this.y = y;
        w = width;
        h = height;
        this.textSize = textSize;
    }

    public void draw() {
        menuLayer.fill(mouseIsOver() ? 204 : 235);
        menuLayer.stroke(0);
        menuLayer.rect(x, y, w, h, 10);
        menuLayer.textAlign(CENTER, CENTER);
        menuLayer.textSize(textSize);
        menuLayer.fill(0);
        menuLayer.text(label, x + (w / 2), y + (h / 2) - app.textDescent());
    }

    public boolean mouseIsOver() {
        return app.mouseX > x && app.mouseX < (x + w) && app.mouseY > y && app.mouseY < (y + h);
    }

    public static float getSmallestFontSize(String[] menuOptions, float buttonWidth, float buttonHeight, PApplet app) {
        Float result = null;
        for (String label : menuOptions) {
            float minSizeW = 12 / app.textWidth(label) * buttonWidth; // calculate minimum size to fit width
            float minSizeH = 12 / (app.textDescent() + app.textAscent()) * buttonHeight; // calculate minimum size to fit height
            float fontSize = min(minSizeW, minSizeH);
            if (result == null || result > fontSize) {
                result = fontSize;
            }
        }
        return result*0.75f;
    }

}
