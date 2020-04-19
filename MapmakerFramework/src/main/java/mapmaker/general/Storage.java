
package mapmaker.general;

import mapmaker.map.Map;

/**
 *
 * @author Simon Norup
 */
public interface Storage {
    
    public Map load(String path);
    
    public boolean save(Map map, String path);
    
}
