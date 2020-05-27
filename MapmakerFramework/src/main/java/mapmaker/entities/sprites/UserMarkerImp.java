/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker.entities.sprites;

import processing.core.PApplet;
import static processing.core.PApplet.dist;
import static processing.core.PApplet.max;
import processing.core.PImage;

/**
 *
 * @author rasmu
 */
public class UserMarkerImp extends PApplet {

    PImage image;
    float x;
    float y;
    float w;
    float h;

    public UserMarkerImp(float tempX, float tempY,
            float tempW, float tempH, PImage im) {
        x = tempX;
        y = tempY;
        w = tempW;
        h = tempH;
        image = im;
    }

    public void incrementXY(float x_, float y_) {
        x += x_;
        y += y_;
    }

    public boolean over(int mouse_x, int mouse_y) {
        return dist(mouse_x, mouse_y, x, y) < max(w, h);
    }

    public PImage getImage() {
        return image;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

}
