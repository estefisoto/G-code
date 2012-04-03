/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author estefisoto
 */
public abstract class Plane extends Canvas {
    
    Mill mill;
    public boolean relative;
    
    public Plane(Mill mill)
    {    
        this.relative = true;
        this.mill = mill;
        setSize(240, 240);
    }
    
    public abstract void paint(Graphics g);
  
    @Override
    public void update(Graphics g) {
        Image im = createImage(getWidth(), getHeight());
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, Color.WHITE, null);
    }
}
