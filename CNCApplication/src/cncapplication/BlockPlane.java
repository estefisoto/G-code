/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author estefisoto
 */
public abstract class BlockPlane extends Canvas {
    protected final int XStart = 50;
    protected final int YStart = 250;
    protected int width;
    protected int height;
    protected String axes1;
    protected String axes2;
    private int textSize = 18;
    Mill mill;
    public boolean relative;
    
    public BlockPlane(Mill mill)
    { 
        relative = true;
        this.mill = mill;
        setSize(300, 300);
    }
    
    public abstract void paintTool(Graphics g);
    public abstract void paintMoves(Graphics g);
    public abstract void paintCuts(Graphics g);
    
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
        g.setColor(Color.black);
        g.drawLine(XStart, YStart - height, XStart, YStart);
        g.drawLine(XStart, YStart, XStart + width, YStart);
//Draw Axes names
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
        g.drawString(axes1, XStart + width + textSize, YStart);
        g.drawString(axes2, XStart - textSize, YStart - height - textSize);
//Paint the Moves
        paintMoves(g);
//Paint the Tool
        paintTool(g);
        g.setColor(c);
    }
  
    @Override
    public void update(Graphics g) {
        Image im = createImage(getWidth(), getHeight());
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, Color.WHITE, null);
    }
}
