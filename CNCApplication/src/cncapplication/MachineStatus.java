/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
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
    public String cycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp, feedunits;
    public String sspeed, feedr;
    public BufferedImage img;
    //private int yPadding = 18;
    Mill mill;
    private int y=150, x=20, x1=200;
    
     
    public MachineStatus(int x, int y, Mill mill)
    {            
       super(mill); 
       this.mill=mill;
       setSize(500,700);
       setPreferredSize(new Dimension(500, 700));
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
        //g.drawImage(img, 100,0,this);
        //Can be changed by G-Code - dynamic
        //g.drawString("Tooltype",25 ,100);        
        font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Machine Status", 80 ,130);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(font);
        g.drawString("Units",x,170);
        g.drawString(mill.units,x1,170);
        g.drawString("Arc Plane",x ,190);
        g.drawString(mill.arcplane,x1,190);
        g.drawString("Positioning",x ,210);
        g.drawString(mill.pos,x1,210);
        g.drawString("Spindle",x ,230);
        g.drawString(mill.spindle,x1,230);
        g.drawString("Spindle Speed",x ,250);
        g.drawString(Integer.toString(mill.sspeed),x1,250);
        //could change to feed rate metric
        g.drawString("Feed Rate " + mill.feedunits,x ,270);
        g.drawString(Double.toString(mill.feedr),x1,270);
        g.drawString("Cycle",x ,290);
        g.drawString(mill.cycle,x1,290);
        g.drawString("Coolant 1",x ,310);
        g.drawString(mill.cool1,x1,310);
        g.drawString("Coolant 2",x ,330);
        g.drawString(mill.cool2,x1,330);
        g.drawString("Opt. Stop",x ,350);
        g.drawString(mill.opt_s,x1,350);
        g.drawString("Blk. Skip",x ,370);
        g.drawString(mill.blk_s,x1,370);
        g.drawString("Clamps",x ,390);
        g.drawString(mill.clamps,x1,390);
        g.drawString("Radius Comp",x ,410);
        g.drawString(mill.radius_comp,x1,410);
        g.drawString("Length Comp",x ,430);
        g.drawString(mill.length_comp,x1,430);
        g.drawString("File",x ,450);
        if(mill.file != null)
            g.drawString(mill.file.getName(),x1,450);
        g.drawString("Current Line",x ,470);
        g.drawString("Block Size (in.)",x, 490);
        g.drawString("Tool Size",x,510);
    }
}
