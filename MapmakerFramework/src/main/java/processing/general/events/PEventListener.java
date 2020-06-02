
package processing.general.events;

/**
 *
 * @author Simon Norup
 */
public interface PEventListener {
    
    default public void update() {
    }

    default public void mousePressed() {
    }

    default public void mouseReleased() {
    }

    default public void mouseDragged() {
    }

    default public void keyPressed(processing.event.KeyEvent evt) {
    }

    default public void keyReleased(processing.event.KeyEvent evt) {
    }

}
