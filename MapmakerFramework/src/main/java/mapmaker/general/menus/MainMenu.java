package mapmaker.general.menus;

/**
 *
 * @author Simon Norup
 */
abstract public class MainMenu implements SubMenu {

    EditorMenu editorMenu;
    StorageMenu viewerMenu;
    SubMenu settingsMenu;

    public void enterEditorMenu() {
        editorMenu.show();
    }

    public void enterViewerMenu() {
        viewerMenu.show();
    }

    public void openSettings() {
        settingsMenu.show();
    }

    public void terminate() {
        System.exit(0);
    }

}
