
package mapmaker.map;

/**
 *
 * @author Simon Norup
 */
public interface Camera {
    
    public void drag(int x, int y);
    public void zoom(double factor);
    public void rotate(double angleX, double angleY);
    
}
