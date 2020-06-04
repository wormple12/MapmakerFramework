package mapmaker.general.menus;

/**
 *
 * @author Simon Norup
 */
abstract public class MainMenu implements SubMenu {

    EditorMenu editorMenu;
    StorageMenu viewerMenu;

    public MainMenu(EditorMenu editorMenu, StorageMenu viewerMenu) {
        this.editorMenu = editorMenu;
        this.viewerMenu = viewerMenu;
    }

    public final void enterEditorMenu() {
        editorMenu.show();
    }

    public final void enterViewerMenu() {
        viewerMenu.show();
    }

    public void terminate() {
        System.exit(0);
    }

}
