package processing.entities;

import java.io.Serializable;
import mapmaker.entities.Landmass;
import processing.core.PGraphics;

/**
 *
 * @author Simon Norup
 */
public class LandmassP3 implements Landmass, Serializable {

    private PGraphics landMass;

    public LandmassP3(PGraphics landMass) {
        this.landMass = landMass;
    }

    public PGraphics getLandMass() {
        return landMass;
    }

    public void setLandMass(PGraphics landMass) {
        this.landMass = landMass;
    }

}
