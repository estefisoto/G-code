/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author estefisoto
 */
public abstract class Plane extends Canvas {
    Tool tool;
    public boolean relative;
    public Plane(Tool tool)
    {    
        this.relative = true;
        this.tool = tool;
        setSize(240, 240);
    }
    
    public void toolChange(Tool tool)
    {
        this.tool = tool;
    }
    
    public abstract void paint(Graphics g);
    
    public abstract void makeCut(int x, int y, int z);
    
    public abstract void animate(int x, int y, int z);
  
    @Override
    public void update(Graphics g) {
        Image im = createImage(getWidth(), getHeight());
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, Color.WHITE, null);
    }
}
