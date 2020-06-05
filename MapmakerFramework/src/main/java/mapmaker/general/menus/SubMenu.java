package mapmaker.general.menus;

/**
 *
 * @author TeamOne
 */
public interface SubMenu {

    /**
     * Should save the previous menu in a field, if any, then stop displaying
     * it. Should then display this menu (GUI), so that you can access its
     * different methods in a user-friendly manner.
     */
    public void show();

    /**
     * Should return to the previous menu, if any. Otherwise it should return to
     * the current map, if any map is active.
     */
    public void returnToPrevious();

}
