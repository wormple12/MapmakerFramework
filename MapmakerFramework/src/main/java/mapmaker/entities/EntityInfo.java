
package mapmaker.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Simon Norup
 */
public class EntityInfo implements Serializable {
    
    private String name;

    public EntityInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
