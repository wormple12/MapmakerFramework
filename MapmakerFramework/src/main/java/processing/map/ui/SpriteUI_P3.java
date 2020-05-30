package processing.map.ui;

import java.util.List;
import java.util.stream.Collectors;
import mapmaker.entities.sprites.Marker;
import mapmaker.entities.sprites.Sprite;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.entities.sprites.SpriteTypeP3;
import processing.general.files.SpriteLoaderP3;
import mapmaker.map.ui.SpriteUI;
import mapmaker.general.UserRole;
import static processing.core.PApplet.dist;
import processing.core.PImage;
import processing.entities.sprites.LocationInfoP3;
import processing.entities.sprites.LocationP3;
import processing.entities.sprites.UserMarkerInfoP3;
import processing.entities.sprites.UserMarkerP3;

/**
 *
 * @author Simon Norup
 */
public final class SpriteUI_P3 implements SpriteUI {

    private final PApplet app;
    private final PGraphics uiLayer;
    private final String spriteDirectory;
    private final List<SpriteTypeP3> spriteTypes;

//    private static final int UNDEFINED = -1;
    private static final float SPRITE_TYPE_BOUNDS = 30;
    private Marker newSpriteType = null;

    public SpriteUI_P3(String directoryToLoadFrom, PApplet app) {
        this.app = app;
        uiLayer = app.createGraphics(app.width, app.height);
        spriteDirectory = directoryToLoadFrom;
        spriteTypes = fetchAvailableTypes()
                .stream().map(sprite -> (SpriteTypeP3) sprite)
                .collect(Collectors.toList());
        loadUIGraphics();
    }

    @Override
    public List<Sprite> fetchAvailableTypes() {
        final SpriteLoaderP3 spriteLoader = new SpriteLoaderP3(app);
        return spriteLoader.loadFromConfig(spriteDirectory);
    }

    private void loadUIGraphics() {
        int imageY = 95;
        for (SpriteTypeP3 type : spriteTypes) {
            uiLayer.beginDraw();
            uiLayer.fill(255);
            uiLayer.rect(1, type.getVector().y - 30, SPRITE_TYPE_BOUNDS * 2.5f, SPRITE_TYPE_BOUNDS * 2);
            uiLayer.image(type.getImage(), 23, imageY, type.getWidth(), type.getHeight());
            uiLayer.endDraw();
            imageY += 70;
        }
    }

    @Override
    public void display() {
        app.image(uiLayer, 0, 0);
    }

    public Marker attemptSelectSpriteType() {
        for (SpriteTypeP3 type : spriteTypes) {
            if (dist(app.mouseX, app.mouseY, type.getVector().x, type.getVector().y) < SPRITE_TYPE_BOUNDS) {
                selectSpriteType(type);
                break;
            }
        }
        return newSpriteType;
    }

    @Override
    public void selectSpriteType(Sprite type) {
        if (type != null) {
            PImage typeImage = ((SpriteTypeP3) type).getImage();
            if (UserRole.getCurrentRole() == UserRole.EDITOR) {
                newSpriteType = new LocationP3(
                        new LocationInfoP3("New Location"), type.getSpritePath(),
                        app.mouseX, app.mouseY, type.getWidth(), type.getHeight(), typeImage
                );
            } else {
                newSpriteType = new UserMarkerP3(
                        new UserMarkerInfoP3("Custom Marker"), type.getSpritePath(),
                        app.mouseX, app.mouseY, type.getWidth(), type.getHeight(), typeImage
                );
            }
        } else {
            newSpriteType = null;
        }
    }

    public Marker getSelectedSpriteType() {
        return newSpriteType;
    }

}
