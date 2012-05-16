/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cut.java
 * 
 * Contains the information for a cut. A cut has an x y and z coordinate and
 * diameter and height.  All information for a cut is stored in pixels.  A cut 
 * can draw itself to any of the three graphical planes (XY, YZ, or XZ).
 * 
 * @author mwaldron74
 */
public class Cut {
    protected int x, y, z, diamX, diamY, height;
    public Cut(Tool t)
    {
        this.x = (int) t.getX();
        this.y = (int) t.getY();
        this.z = (int) t.getZ();
        this.diamX = (int) t.getDiamX();
        this.diamY = (int) t.getDiamY();
        this.height = (int) t.getHeight();
    }
    
    public void drawXY(Graphics g, int xOffset, int yOffset)
    {
        g.setColor(Color.pink);
        g.fillOval( x + xOffset - (diamX / 2), yOffset - y - (diamY / 2), 
                    diamX, diamY);
    }
    
    public void drawXZ(Graphics g, int xOffset, int zOffset)
    {
        g.setColor(Color.pink);
        g.fillRect( x + xOffset - (diamX / 2), zOffset - z, 
                    diamX, height);
    }
    
    public void drawYZ(Graphics g, int yOffset, int zOffset)
    {
        g.setColor(Color.pink);
        g.fillRect( y + yOffset - (diamY / 2), zOffset - z, 
                    diamY, height);
    }
}
