package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Landmass;
import processing.general.files.MapStorageP3;

/**
 *
 * @author Simon Norup
 */
public class LandmassP3 implements Landmass, Serializable {

    private String id;
    private String graphicsPath;

    public LandmassP3() {
        this.id = MapStorageP3.generateId();
    }

    public LandmassP3(String id) {
        this.id = id;
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

}
