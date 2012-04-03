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
class Mill{
    
    
    public String units, arcplane, pos, spindle;
    public String clycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp;
    public boolean relative, absolute;
    public int sspeed;
    public double feedr;
    public BufferedImage img;
    public Tool tool;
    
    public Mill()
    {
       tool = new Tool(0, 0, 50, 20, 2);
       //TODO : set all constants to default
    }
   
    public float getToolX()
    {
        return (int) tool.getX();
    }
    
    public float getToolY()
    {
        return tool.getY();
    }
    
    public float getToolZ()
    {
        return tool.getZ();
    }
    
    public float getToolHeight()
    {
        return tool.getHeight();
    }
    
    public float getToolDiameter()
    {
        return tool.getDiameter();
    }
    
    public void setToolX(float x)
    {
         tool.setX(x);
    }
    
    public void setToolY(float y)
    {
        tool.setY(y);
    }
    
    public void setToolZ(float z)
    {
        tool.setZ(z);
    }
    
    public void moveTool(float x, float y, float z)
    {
        tool.setX(x);
        tool.setY(y);
        tool.setZ(z);
    }
}
