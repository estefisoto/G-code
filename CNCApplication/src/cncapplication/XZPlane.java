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
 * XZPlane.java
 * 
 * This serves as the XZ Plane in the program.  It is the view of the block from
 * on the side.
 * 
 * @author mwaldron74
 */
public class XZPlane extends BlockPlane{
    public XZPlane(Mill mill)
    {
        super(mill);
        width = 200;
        height = 100;
        axes1 = "X";
        axes2 = "Z";
    }
    
    @Override
    public void paintTool(Graphics g)
    {
        g.setColor(Color.red);
        int radX = ( (int) mill.getToolDiamX()) / 2;
        g.fillRect(XStart + (int) mill.getToolX() - radX, 
                   YStart - (int) mill.getToolZ() - (int) mill.getToolHeight() - height,
                   (int) mill.getToolDiamX(), (int) mill.getToolHeight());
    }
    
    @Override
    public void paintMoves(Graphics g) 
    {
        ArrayList<Line> moves = mill.getMoves();
        for(int i  = 0; i < moves.size(); i++)
            moves.get(i).drawXZ(g, XStart, YStart - height);
    }
    
    @Override
    public void paintCuts(Graphics g)
    {
        ArrayList<Cut> cuts = mill.getCuts();
        for(int i = 0; i < cuts.size(); i++)
            cuts.get(i).drawXZ(g, XStart, YStart - height  - (int) mill.getToolHeight());
    }
    
    @Override
    public void paintAxes(Graphics g)
    {
        g.setColor(Color.black);
        g.drawLine(XStart, YStart - height - 100, XStart, YStart - height);
        g.drawLine(XStart, YStart - height, XStart + width, YStart - height);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
        g.drawString(axes1, XStart + width + textSize, YStart - height);
        g.drawString(axes2, XStart - textSize, YStart - height * 2);
    }
}
