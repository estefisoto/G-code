/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Plane.java
 * 
 * This is a drawing plane that points to a mill so that every time it is updated
 * ,the information from the mill will be the most current information possible.
 * It extends Canvas x and its subclasses are Blockplane and MachineStatus.
 * 
 * @author mwaldron74
 */
public abstract class Plane extends Canvas{
    protected Mill mill;
    public Plane(Mill mill)
    {
        this.mill = mill;
    }
    
    @Override
    public void update(Graphics g) 
    {
        Image im = createImage(getWidth(), getHeight());
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, Color.WHITE, null);
    }
}
