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
 * MachineStatus.java
 * 
 * This class is a graphical representation of the current status of the machine
 * and program.  Things such as feed rate and coolant cannot be illustrated 
 * effectively, so this plane contains the text necessary to inform the user
 * what is going on that cannot be seen.  Furthermore, it contains program info
 * such as the file and the current line.
 * 
 * @author mwaldron74
 */
class MachineStatus extends Plane{
    
    
    public String units, arcplane, pos, spindle, cycle, cool1, cool2, opt_s;
    public String radius_comp, length_comp, feedunits, blk_s, clamps, sspeed;
    public String feedr;
    Mill mill;
    private int yText = 150, xText = 20, x1Text = 200;
    
     
    public MachineStatus(int x, int y, Mill mill)
    {            
       super(mill); 
       this.mill=mill;
       setSize(500,700);
       setPreferredSize(new Dimension(500, 700));
       this.setLocation(x, y);  
    }
    
    @Override
    public void paint(Graphics g)
    {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        g.setFont(font);        
        font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Machine Status", 80 ,130);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(font);
        g.drawString("Units",xText,170);
        g.drawString(mill.units,x1Text,170);
        g.drawString("Arc Plane",xText ,190);
        g.drawString(mill.arcplane,x1Text,190);
        g.drawString("Positioning",xText ,210);
        g.drawString(mill.pos,x1Text,210);
        g.drawString("Spindle",xText ,230);
        g.drawString(mill.spindle,x1Text,230);
        g.drawString("Spindle Speed",xText ,250);
        g.drawString(Integer.toString(mill.sspeed),x1Text,250);
        g.drawString("Feed Rate ", xText ,270);
        g.drawString(Double.toString(mill.feedr),x1Text,270);
        g.drawString("Cycle",xText ,290);
        g.drawString(mill.cycle,x1Text,290);
        g.drawString("Coolant 1",xText ,310);
        g.drawString(mill.cool1,x1Text,310);
        g.drawString("Coolant 2",xText ,330);
        g.drawString(mill.cool2,x1Text,330);
        g.drawString("Opt. Stop",xText ,350);
        g.drawString(mill.opt_s,x1Text,350);
        g.drawString("Blk. Skip",xText ,370);
        g.drawString(mill.blk_s,x1Text,370);
        g.drawString("Clamps",xText ,390);
        g.drawString(mill.clamps,x1Text,390);
        g.drawString("Radius Comp",xText ,410);
        g.drawString(mill.radius_comp,x1Text,410);
        g.drawString("Length Comp",xText ,430);
        g.drawString(mill.length_comp,x1Text,430);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Program Status", 80 ,470);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(font);
        g.drawString("File",xText ,510);
        if(mill.file != null)
            g.drawString(mill.file.getName(),x1Text,510);
        g.drawString("Current Line",xText ,530);
        if(mill.getCurrentLine() != null)
            g.drawString(mill.getCurrentLine(), x1Text, 530);
    }
}
