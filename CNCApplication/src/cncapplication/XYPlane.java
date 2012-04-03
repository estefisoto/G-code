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
    public XYPlane(Mill mill)
    {
        super(mill);
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
        int rad = ( (int) mill.getToolDiameter()) / 2;
        g.fillOval(XStart + (int) mill.getToolX() - rad, 
                   YStart - (int) mill.getToolY() - rad, 
                   (int) mill.getToolDiameter(), (int) mill.getToolDiameter());
        g.setColor(c);
    }
}
