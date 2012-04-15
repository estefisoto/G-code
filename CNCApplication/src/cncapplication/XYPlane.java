/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mwaldron74
 */
public class XYPlane extends Plane{
    public XYPlane(Mill mill)
    {
        super(mill);
        width = 200;
        height = 200;
        axes1 = "X";
        axes2 = "Y";
    }
    
    @Override
    public void paintTool(Graphics g)
    {
        g.setColor(Color.red);
        int rad = ( (int) mill.getToolDiameter()) / 2;
        g.fillOval(XStart + (int) mill.getToolX() - rad, 
                   YStart - (int) mill.getToolY() - rad, 
                   (int) mill.getToolDiameter(), (int) mill.getToolDiameter());
    }
    
    public void paintCuts(Graphics g)
    {
        for(Shape s : mill.getXYCuts())
            s.draw(g, XStart, YStart);
    }
}
