
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

}
