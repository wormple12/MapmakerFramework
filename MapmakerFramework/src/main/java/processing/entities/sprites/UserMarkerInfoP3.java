package processing.entities.sprites;

import java.util.Objects;
import mapmaker.entities.EntityInfo;

/**
 *
 * @author Simon Norup
 */
public class UserMarkerInfoP3 extends EntityInfo {

    public UserMarkerInfoP3(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.getName());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntityInfo other = (EntityInfo) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }

}
