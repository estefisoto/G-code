/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author mwaldron74
 */
public class YZPlane extends Plane{
    
    public YZPlane(Mill mill)
    {
        super(mill);
        width = 200;
        height = 100;
        axes1 = "Y";
        axes2 = "Z";
    }

    @Override
    public void paintTool(Graphics g)
    {
        g.setColor(Color.red);
        int rad = ( (int) mill.getToolDiameter()) / 2;
        g.fillRect(XStart + (int) mill.getToolY() - rad, 
                   YStart - (int) mill.getToolZ(), 
                   (int) mill.getToolDiameter(), (int) mill.getToolHeight());
    }
    
    @Override
    public void paintMoves(Graphics g) {
        for(Line l : mill.getMoves())
            l.drawYZ(g, XStart, YStart);
    }
    
    @Override
    public void paintCuts(Graphics g)
    {
        for(Cut c : mill.getCuts())
            c.drawYZ(g, XStart, YStart);
    }
}
