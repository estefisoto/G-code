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
class MachineStatus extends Canvas{
    
    
    public String units, arcplane, pos, spindle;
    public String cycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp, feedunits;
    public String sspeed, feedr;
    public BufferedImage img;
    Mill mill;
    private int y=150, x=10, x1=50;
    
     
    public MachineStatus(int x, int y, Mill mill)
    {            
       super(); 
       this.mill=mill;
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
        g.drawString("Units",x,y);
        g.drawString(mill.units,x1,y);
        y+=18;
        g.drawString("Arc Plane",x ,y);
        g.drawString(mill.arcplane,x1,y);
        y+=18;
        g.drawString("Positioning",x ,y);
        g.drawString(mill.pos,x1,y);
        y+=18;
        g.drawString("Spindle",x ,y);
        g.drawString(mill.spindle,x1,y);
        y+=18;
        g.drawString("Spindle Speed",x ,y);
        g.drawString(Integer.toString(mill.sspeed),x1,y);
        y+=18;
        //could change to feed rate metric
        g.drawString("Feed Rate "+mill.feedunits,x ,y);
        g.drawString(Double.toString(mill.feedr),x1,y);
        y+=18;
        g.drawString("Cycle",x ,y);
        g.drawString(mill.cycle,x1,y);
        y+=18;
        g.drawString("Coolant 1",x ,y);
        g.drawString(mill.cool1,x1,y);
        y+=18;
        g.drawString("Coolant 2",x ,y);
        g.drawString(mill.cool2,x1,y);
        y+=18;
        g.drawString("Opt. Stop",x ,y);
        g.drawString(mill.opt_s,x1,y);
        y+=18;
        g.drawString("Blk. Skip",x ,y);
        g.drawString(mill.blk_s,x1,y);
        y+=18;
        g.drawString("Clamps",x ,y);
        g.drawString(mill.clamps,x1,y);
        y+=18;
        g.drawString("Radius Comp",x ,y);
        g.drawString(mill.radius_comp,x1,y);
        y+=18;
        g.drawString("Length Comp",x ,y);
        g.drawString(mill.length_comp,x1,y);
        
    }
}
