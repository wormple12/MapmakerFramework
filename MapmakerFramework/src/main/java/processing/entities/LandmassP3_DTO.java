package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Landmass;

/**
 *
 * @author Simon Norup
 */
public class LandmassP3_DTO implements Landmass, Serializable {

    private String id;
    private String graphicsPath;

    public LandmassP3_DTO(LandmassP3 landmass) {
        this.id = landmass.getId();
        this.graphicsPath = landmass.getGraphicsPath();
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
