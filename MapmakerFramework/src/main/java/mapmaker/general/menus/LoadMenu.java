package mapmaker.general.menus;

/**
 *
 * @author Simon Norup
 */
public interface LoadMenu extends SubMenu {

    public void loadMap(String path); // no parameter needed?
    /* default: 
    - open project file folder to get path
    - Storage: Get Map
    - set loaded map as current map
    - Canvas: load map graphics and info on screen
     */

}
