/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Line.java
 * 
 * The line class holds the graphical representation for a line.  Each move is
 * represented by a line, and a line can draw itself on any view of a block.
 * (XY, YZ or XZ)
 * 
 * @author mwaldron74
 */
public class Line{
    public int x1, y1, z1, x2, y2, z2;
    public Line(){}
    public Line(int x1, int y1, int z1, int x2, int y2, int z2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public void drawXY(Graphics g, int xOffset, int yOffset) 
    {
        g.setColor(Color.black);
        g.drawLine(x1 + xOffset, yOffset - y1, x2 + xOffset, yOffset - y2);
    }
    
    public void drawXZ(Graphics g, int xOffset, int zOffset) 
    {
        g.setColor(Color.black);
        g.drawLine(x1 + xOffset, zOffset - z1, x2 + xOffset, zOffset - z2);
    }
    
    public void drawYZ(Graphics g, int yOffset, int zOffset) 
    {
        g.setColor(Color.black);
        g.drawLine(y1 + yOffset, zOffset - z1, y2 + yOffset, zOffset - z2);
    }
}
