package mapmaker.general.menus;

/**
 *
 * @author Simon Norup
 */
abstract public class MainMenu implements SubMenu {

    EditorMenu editorMenu;
    StorageMenu viewerMenu;
    SubMenu settingsMenu;

    public MainMenu(EditorMenu editorMenu, StorageMenu viewerMenu, SubMenu settingsMenu) {
        this.editorMenu = editorMenu;
        this.viewerMenu = viewerMenu;
        this.settingsMenu = settingsMenu;
    }

    public final void enterEditorMenu() {
        editorMenu.show();
    }

    public final void enterViewerMenu() {
        viewerMenu.show();
    }

    public final void openSettings() {
        settingsMenu.show();
    }

    public void terminate() {
        System.exit(0);
    }

}
