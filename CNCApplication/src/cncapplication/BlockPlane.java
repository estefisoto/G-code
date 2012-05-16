/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * BlockPlane.java
 * 
 * This is an abstract class for a drawing plane that would contain a picture
 * of a block.  Every BlockPlane has a picture of a 2d block so this class 
 * contains the starting pixel of the x and y axis(pictured axis not necessarily
 * the axis that the block represents), the width, the height and the size of
 * the text to be drawn. The axes names will also be stored since the x and y
 * axis represent the actual axes of the block. A block plane is able to draw
 * the moves and cuts made on the block, the tool, and the actual block.
 * 
 * @author mwaldron74
 */
public abstract class BlockPlane extends Plane {
    protected int XStart, YStart, width, height, textSize;
    protected String axes1, axes2;
    
    public BlockPlane(Mill mill)
    { 
        super(mill);
        XStart = 50;
        YStart = 250;
        textSize = 18;
        setSize(300, 300);
        setPreferredSize(new Dimension(300, 300));
    }
    
    public abstract void paintTool(Graphics g);
    public abstract void paintMoves(Graphics g);
    public abstract void paintCuts(Graphics g);
    public abstract void paintAxes(Graphics g);
    
    @Override
    public void paint(Graphics g)
    {
        Color c = g.getColor();
//Draw the block
        g.setColor(Color.white);
        g.fillRect(XStart, YStart - height, width, height); 
//Paint the Cuts
        paintCuts(g);
//Draw the background used to cover cuts not completely in the box
        g.setColor(Color.blue);
        g.clearRect(0, 0, XStart, getHeight());
        g.clearRect(XStart, 0, getWidth() - XStart, YStart - height);
        g.clearRect(XStart, YStart, getWidth() - XStart, getHeight() - YStart);
        g.clearRect(XStart + width, YStart - height, 
                    getWidth() - XStart - width, height);
//Draw the borders
        paintAxes(g);
//Paint the Moves
        paintMoves(g);
//Paint the Tool
        paintTool(g);
        g.setColor(c);
    }
}
