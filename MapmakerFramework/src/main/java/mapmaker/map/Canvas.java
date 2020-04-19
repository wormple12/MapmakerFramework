package mapmaker.map;

/**
 *
 * @author Simon Norup
 */
public interface Canvas {

    public void loadEmpty();
    
    public void loadWorldMap(Map map);
    
    //public void loadLocalMap(LocalMap map);

}

//public class MySketch extends PApplet {
//
//    public void draw() {
//        Canvas canvas = new Canvas(this);
//        canvas.drawMap();
//    }
//
//}
