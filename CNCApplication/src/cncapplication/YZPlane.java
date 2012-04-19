/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author mwaldron74
 */
public class YZPlane extends BlockPlane{
    
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
        ArrayList<Line> moves = mill.getMoves();
        for(int i  = 0; i < moves.size(); i++)
            moves.get(i).drawYZ(g, XStart, YStart);
    }
    
    @Override
    public void paintCuts(Graphics g)
    {
        ArrayList<Cut> cuts = mill.getCuts();
        for(int i = 0; i < cuts.size(); i++)
            cuts.get(i).drawYZ(g, XStart, YStart);
    }
}
