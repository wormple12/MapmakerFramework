package processing.general.menus.components;

import processing.core.PApplet;
import static processing.core.PConstants.CENTER;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class PMenuDisplay {

    private final PApplet app;
    private final PGraphics displayLayer;
    private final String title;
    private final String[] menuOptions;
    private final PMenuButton[] menuButtons;
    private final int buttonHeight = 90;
    private final int buttonWidth = 170;
    private final int topElementY;

    public PMenuDisplay(String title, String[] menuOptions, PGraphics displayLayer, PApplet app) {
        this.app = app;
        this.displayLayer = displayLayer;
        this.title = title;
        this.menuOptions = menuOptions;
        this.menuButtons = new PMenuButton[menuOptions.length];
        this.topElementY = app.height / (menuButtons.length + 3);
    }

    public void setup() {
        float textSize = PMenuButton.getSmallestFontSize(menuOptions, buttonWidth, buttonHeight, app);

        for (int i = 0; i < menuButtons.length; i++) {
            int x = app.width / 2 - buttonWidth / 2;
            int y = topElementY * (i + 2) - buttonHeight / 2;
            menuButtons[i] = new PMenuButton(menuOptions[i], x, y, buttonWidth, buttonHeight, textSize, displayLayer, app);
        }

        displayTitle();
    }

    private void displayTitle() {
        displayLayer.textAlign(CENTER, CENTER);
        displayLayer.textSize(40);
        displayLayer.fill(255);
        displayLayer.text(title, app.width / 2, topElementY);
    }

    public void draw() {
        for (PMenuButton menuButton : menuButtons) {
            menuButton.draw();
        }
    }

    public int getButtonPressed() {
        for (int i = 0; i < menuButtons.length; i++) {
            if (menuButtons[i].mouseIsOver()) {
                return i;
            }
        }
        return -1;
    }

}
