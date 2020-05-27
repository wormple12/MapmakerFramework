package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Landmass;
import processing.core.PGraphics;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class LandmassP3 implements Landmass, Serializable {

    private String id;
    private String graphicsPath;
    private PGraphics graphics = null;
    private PGraphics borderGraphics = null;

    public LandmassP3(PGraphics graphics, PGraphics graphicsBorder) {
        this(MapStorageP3.generateId(), graphics, graphicsBorder);
    }

    public LandmassP3(String id, PGraphics graphics, PGraphics borderGraphics) {
        this.id = id;
        this.graphics = graphics;
        this.borderGraphics = borderGraphics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGraphicsPath() {
        return graphicsPath;
    }

    public void setGraphicsPath(String graphicsPath) {
        this.graphicsPath = graphicsPath;
    }

    public String getBorderGraphicsPath() {
        String[] parts = graphicsPath.split("\\.");
        return parts[0] + "_border.png";
    }

    public PGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }

    public PGraphics getBorderGraphics() {
        return borderGraphics;
    }

    public void setBorderGraphics(PGraphics borderGraphics) {
        this.borderGraphics = borderGraphics;
    }

}
