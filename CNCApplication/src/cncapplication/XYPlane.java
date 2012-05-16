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
 * XYPlane.java
 * 
 * This serves as the XY Plane in the program.  It is the view of the block from
 * on top.
 * 
 * @author mwaldron74
 */
public class XYPlane extends BlockPlane{
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
        int radX = ( (int) mill.getToolDiamX()) / 2;
        int radY = ( (int) mill.getToolDiamY()) / 2;
        g.fillOval(XStart + (int) mill.getToolX() - radX, 
                   YStart - (int) mill.getToolY() - radY, 
                   (int) mill.getToolDiamX(), (int) mill.getToolDiamY());
    }
    
    @Override
    public void paintMoves(Graphics g)
    {
        ArrayList<Line> moves = mill.getMoves();
        for(int i  = 0; i < moves.size(); i++)
            moves.get(i).drawXY(g, XStart, YStart);
    }
    
    @Override
    public void paintCuts(Graphics g)
    {
        ArrayList<Cut> cuts = mill.getCuts();
        for(int i = 0; i < cuts.size(); i++)
            cuts.get(i).drawXY(g, XStart, YStart);
    }
    
    @Override
    public void paintAxes(Graphics g)
    {
        g.setColor(Color.black);
        g.drawLine(XStart, YStart - height, XStart, YStart);
        g.drawLine(XStart, YStart, XStart + width, YStart);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
        g.drawString(axes1, XStart + width + textSize, YStart);
        g.drawString(axes2, XStart - textSize, YStart - height - textSize);
    }
}
