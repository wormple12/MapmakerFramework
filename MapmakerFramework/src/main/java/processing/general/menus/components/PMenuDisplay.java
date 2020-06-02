package processing.general.menus.components;

import processing.ProcessingMapmaker;
import static processing.core.PConstants.CENTER;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class PMenuDisplay {
    
    private final ProcessingMapmaker app;
    private final PGraphics displayLayer;
    private final String title;
    private final String[] menuOptions;
    private final PMenuButton[] menuButtons;
    private final int backgroundColor;
    private final int buttonHeight = 90;
    private final int buttonWidth = 170;
    private final int topElementY;
    
    public PMenuDisplay(String title, String[] menuOptions, PGraphics displayLayer, int backgroundColor, ProcessingMapmaker app) {
        this.app = app;
        this.displayLayer = displayLayer;
        this.title = title;
        this.menuOptions = menuOptions;
        this.menuButtons = new PMenuButton[menuOptions.length];
        this.backgroundColor = backgroundColor;
        this.topElementY = app.height / (menuButtons.length + 3);
    }
    
    public void setup() {
        displayLayer.beginDraw();
        displayBackground();
        displayTitle();
        
        float textSize = PMenuButton.getSmallestFontSize(menuOptions, buttonWidth, buttonHeight, app);
        for (int i = 0; i < menuButtons.length; i++) {
            int x = app.width / 2 - buttonWidth / 2;
            int y = topElementY * (i + 2) - buttonHeight / 2;
            menuButtons[i] = new PMenuButton(menuOptions[i], x, y, buttonWidth, buttonHeight, textSize, displayLayer, app);
        }
        displayLayer.endDraw();
    }
    
    private void displayBackground() {
        displayLayer.background(backgroundColor);
    }
    
    private void displayTitle() {
        displayLayer.textAlign(CENTER, CENTER);
        displayLayer.textSize(40);
        displayLayer.fill(255);
        displayLayer.text(title, app.width / 2, topElementY);
    }
    
    public void draw() {
        displayLayer.beginDraw();
        for (PMenuButton menuButton : menuButtons) {
            menuButton.draw();
        }
        displayLayer.endDraw();
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
