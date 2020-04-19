
package mapmaker.general.menus;

/**
 *
 * @author Simon Norup
 */
public interface MainMenu {
    
    public void open(); // graphic
    
    public void enterEditorMode(); // default: editorMenu.show()
    
    public void enterViewerMode(); // default: viewerMenu.show()
    
    public void close();
    
    public void openSettings();
    
    public void terminate();
    
}
