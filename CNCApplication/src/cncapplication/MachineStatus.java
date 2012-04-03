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
import java.awt.image.BufferedImage;

/**
 *
 * @author estefisoto
 */
class MachineStatus extends Plane{
    
    
    public String units, arcplane, pos, spindle;
    public String clycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp;
    public int sspeed;
    public double feedr;
    public BufferedImage img;
    
    
    public MachineStatus(int x, int y, Mill mill)
    {
       super(mill);
       setSize(500,700);
       this.setLocation(x, y);
       img = new BufferedImage(70,70, BufferedImage.TYPE_INT_RGB);
       Graphics g = img.createGraphics();
       g.setColor(Color.WHITE);
       g.fillRect(0, 0, img.getWidth(), img.getHeight());  
    }
    
    @Override
    public void paint(Graphics g)
    {
        
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        g.setFont(font);
        g.drawImage(img, 100,0,this);
        //Can be changed by G-Code - dynamic
        g.drawString("Tooltype",10 ,100);
        
        font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Machine Status",100 ,130);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(font);
        int y=150;
        int x=10;
        g.drawString("Units",x,y);
        y+=18;
        g.drawString("Arc Plane",x ,y);
        y+=18;
        g.drawString("Positioning",x ,y);
        y+=18;
        g.drawString("Spindle",x ,y);
        y+=18;
        g.drawString("Spindle Speed",x ,y);
        y+=18;
        //could change to feed rate metric
        g.drawString("Feed Rate(in/min)",x ,y);
        y+=18;
        g.drawString("Cycle",x ,y);
        y+=18;
        g.drawString("Coolant 1",x ,y);
        y+=18;
        g.drawString("Coolant 2",x ,y);
        y+=18;
        g.drawString("Opt. Stop",x ,y);
        y+=18;
        g.drawString("Blk. Skip",x ,y);
        y+=18;
        g.drawString("Clamps",x ,y);
        y+=18;
        g.drawString("Radius Comp",x ,y);
        y+=18;
        g.drawString("Length Comp",x ,y);
          
        
    }
    
      @Override
    public void update(Graphics g) {
        Image im = createImage(getWidth(), getHeight());
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, Color.WHITE, null);
    }
    
    
    
}
