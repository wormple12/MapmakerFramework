package temporary;

import java.util.List;
import mapmaker.editor.DrawFunction;
import mapmaker.editor.Editor;
import mapmaker.editor.EditorProxy;
import mapmaker.entities.Landmass;
import mapmaker.entities.Region;
import mapmaker.entities.Route;
import mapmaker.entities.sprites.Location;

/**
 *
 * @author Simon Norup
 */
public class EditorProxyTest {

    public static void main(String[] args) {
        TestEditor automaticDrawer = (TestEditor) EditorProxy.getProxyInstance(new TestEditorImpl(), TestEditor.class);
        automaticDrawer.drawLandmass(0, 5, 10);
        automaticDrawer.drawWater(4, 14, 2.3);
        automaticDrawer.updateLandmass();
        automaticDrawer.randomDraw(7, 5);
    }

    public static interface TestEditor extends Editor {
        @DrawFunction
        public void randomDraw(int x, Integer y);
    }

    public static class TestEditorImpl implements TestEditor {

        @Override
        public void draw(int x, int y) {
            System.out.println("Draw(" + x + "," + y + ")");
        }

        @Override
        public void drawLandmass(int x, int y, double radius) {
            System.out.println("DrawLandmass(" + x + "," + y + "," + radius + ")");
        }

        @Override
        public void drawWater(int x, int y, double radius) {
            System.out.println("DrawWater(" + x + "," + y + "," + radius + ")");
        }

        @Override
        public Landmass updateLandmass() {
            System.out.println("Landmass updated...");
            return null;
        }

        @Override
        public void randomDraw(int x, Integer y) {
            System.out.println("randomDraw");
        }

        @Override
        public void drawBorder(int x, int y, Region optionalRegion) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Region> createRegions() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void viewRegion(Region region) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void editRegion(Region original, Region updated) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void drawRoute(int x, int y, double radius) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void eraseRoute(int x, int y, double radius) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Route createRoute() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void dragLocation(Location location, int x, int y) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void dropLocation(Location location, int x, int y) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void viewLocationInfo(Location location) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void editLocationInfo(Location original, Location updated) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
