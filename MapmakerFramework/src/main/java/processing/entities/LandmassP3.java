package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Landmass;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class LandmassP3 implements Landmass, Serializable {

    private PGraphics graphics;

    public LandmassP3(PGraphics graphics) {
        this.graphics = graphics;
    }

    public PGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }

}
