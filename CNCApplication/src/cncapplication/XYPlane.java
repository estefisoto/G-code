/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mwaldron74
 */
public class XYPlane extends Plane{
    private final int XStart = 50;
    private final int YStart = 250;
    public XYPlane(Tool tool)
    {
        super(tool);
        setSize(300, 300);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(50, 50, 200, 200);
        g.setColor(Color.black);
        g.drawLine(50, 50, XStart, YStart);
        g.drawLine(XStart, YStart, 250, 250);
        g.setColor(Color.red);
        int rad = tool.getDiameter() / 2;
        g.fillOval(XStart + tool.getX() - rad, YStart - tool.getY() - rad, 
                   tool.getDiameter(), tool.getDiameter());
        g.setColor(c);
    }
    
    public void makeCut(int x, int y, int z)
    {
        if(relative)
        {
            animate(x, 0, 0);
            animate(0, y, 0);
        }
        else
        {
            animate(x, y, z);
        }
    }
    
    public void animate(int x, int y, int z)
    {
        if(x != 0)
        {
            tool.setX(tool.getX() + 1);
            animate(x - 1, y, z);
        }
        else if (y != 0)
        {
            tool.setY(tool.getY() + 1);
            animate(x, y - 1, z);
        }
        repaint();
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(XYPlane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
