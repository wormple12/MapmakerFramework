
package processing.general.events;

import processing.event.KeyEvent;

/**
 *
 * @author TeamOne
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

    default public void keyPressed(KeyEvent evt) {
    }

    default public void keyReleased(KeyEvent evt) {
    }

}
