package processing.map;

import mapmaker.general.UserRole;
import processing.core.PApplet;
import processing.editor.IEditorP3;
import processing.viewer.ViewerP3;

/**
 *
 * @author Simon Norup
 */
public class MapRunnerP3 {

    private final PApplet app;
    private final IEditorP3 editor;
    private final ViewerP3 viewer;
    private final CanvasP3 canvas;

    public MapRunnerP3(PApplet app, IEditorP3 editor, ViewerP3 viewer, CanvasP3 canvas) {
        this.app = app;
        this.editor = editor;
        this.viewer = viewer;
        this.canvas = canvas;
    }

    public void run() {
        canvas.run();
        if (UserRole.getCurrentRole() == UserRole.EDITOR) {
            editor.displayUI();
            if (editor.getSelectedLocation() != null) {
                editor.dragLocation(editor.getSelectedLocation(), app.mouseX, app.mouseY);
            }

        } else {
            viewer.displayUI();
            if (viewer.getSelectedMarker() != null) {
                viewer.dragMarker(viewer.getSelectedMarker(), app.mouseX, app.mouseY);
            }
        }
    }

}
