/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author mwaldron74
 */
public class Cut {
    protected int x, y, z, diameter, height;
    int bX, by, bz;
    public Cut(Tool t, Block b)
    {
        this.x = (int) t.getX();
        this.y = (int) t.getY();
        this.z = (int) t.getZ();
        this.diameter = (int) t.getDiameter();
        this.height = (int) t.getHeight();
        this.bX = b.getXSize();
    }
    public void drawXY(Graphics g, int xOffset, int yOffset)
    {
        g.setColor(Color.pink);
        g.fillOval( x + xOffset - (diameter / 2), yOffset - y - (diameter / 2), 
                    diameter, diameter);
    }
    public void drawXZ(Graphics g, int xOffset, int zOffset)
    {
        g.setColor(Color.pink);
        g.fillRect( x + xOffset - (diameter / 2), zOffset - z, 
                    diameter, height);
    }
    public void drawYZ(Graphics g, int yOffset, int zOffset)
    {
        g.setColor(Color.pink);
        g.fillRect( y + yOffset - (diameter / 2), zOffset - z, 
                    diameter, height);
    }
}
