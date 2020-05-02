package mapmaker.editor.ui;

import mapmaker.editor.Mode;

/**
 *
 * @author Simon Norup
 */
public interface ModeUI {

    /**
     * Displays the UI Overlay for selection of editing mode, making it possible
     * to select from the list of choices defined in the Mode.java enum.
     */
    public void display();

    /**
     * Switches the editing mode to the given selection, enabling and disabling
     * features accordingly.
     *
     * @param mode the selected mode
     */
    public void switchMode(Mode mode);

}
