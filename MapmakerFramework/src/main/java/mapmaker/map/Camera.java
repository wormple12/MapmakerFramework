package mapmaker.map;

/**
 *
 * @author TeamOne
 */
public interface Camera {

    /**
     * Moves the point from which the camera displays the world map from one
     * two-dimensional coordinate above the map to another. This only updates
     * the camera angle's horisontal and vertical position, not the height from
     * which it hovers over the map level.
     *
     * @param x
     * @param y
     */
    public void drag(int x, int y);

    /**
     * Zooms closer to or further away from the world map, updating the height
     * from which the camera hovers over the map level.
     *
     * @param factor how quickly to zoom
     */
    public void zoom(double factor);

    /**
     * Allowed to be unsupported. Rotates the camera on the x and y angles. This
     * does not update the camera's position above the map level in any way,
     * only the angle from which it allows the user to observe the map.
     *
     * @param angleX
     * @param angleY
     */
    default public void rotate(double angleX, double angleY) {
        throw new UnsupportedOperationException("This framework does not support camera rotation.");
    }

}
