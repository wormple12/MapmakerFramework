package processing.map.controllers;

import mapmaker.general.UserRole;
import processing.ProcessingMapmaker;
import processing.editor.IEditorP3;
import processing.general.events.PEventListener;
import processing.map.CanvasP3;
import processing.viewer.ViewerP3;

/**
 *
 * @author TeamOne
 */
public class MapUpdateManagerP3 implements PEventListener {

    private final ProcessingMapmaker app;
    private final IEditorP3 editor;
    private final ViewerP3 viewer;
    private final CanvasP3 canvas;

    public MapUpdateManagerP3(ProcessingMapmaker app, IEditorP3 editor, ViewerP3 viewer, CanvasP3 canvas) {
        this.app = app;
        this.editor = editor;
        this.viewer = viewer;
        this.canvas = canvas;
    }

    @Override
    public void update() {
        canvas.display();

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

        app.text(app.frameRate, app.width - 100, app.height - 50);
    }

}
