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
class Plane extends Canvas {
    int width,height,depth;
    public Rectangle tool;
    public Plane(int x, int y){
    
    //default tool
    tool=new Rectangle(0,50,20,5);
    this.setLocation(x, y);
    this.setSize(210, 210);
        
    
    }
    
    public void toolChange(int x,int y, int height,int width)
    {
        Rectangle tool=new Rectangle();
        tool.x=x;
        tool.y=y;
        tool.height=height;
        tool.width=width;
        this.tool=tool;
    }
    
  @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.white);
        //g.fillRect(0, 10, 200, 200);
        g.draw3DRect(0,10,200,200, true);
        
        g.setColor(Color.BLACK);
        g.drawLine(0, 10, 0, 200);
        g.drawLine(0, 200,200, 200);
        g.setColor(Color.RED);
        g.fillRect(tool.x, tool.y, tool.height,tool.width);    
        
    }
    
      @Override
    public void update(Graphics g) {
        Image im = createImage(getWidth(), getHeight());
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, Color.WHITE, null);
    }
    
    
}
