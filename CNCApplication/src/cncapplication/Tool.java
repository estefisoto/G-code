/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

/**
 * Tool.java
 * 
 * This serves as a subsidiary model to the Mill since a tool is part of an 
 * actual mill.  This contains all of the tools information including location
 * and size.  The location is at the bottom center of the tool as that is how
 * the actual mill coordinates work.  In the old CNC program, the tool can be 
 * chosen from a list.  For now, there are no hard coded tools so the size and
 * starting coordinate must be input by the user.
 * 
 * @author mwaldron74
 */
public class Tool {
    
    private float x, y, z, diamX, diamY, height;
    
    public Tool(float x, float y, float z, float diamX, float diamY, float height)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.diamX = diamX;
        this.diamY = diamY;
        this.height = height;
    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public float getZ()
    {
        return z;
    }
    
    public float getHeight()
    {
        return height;
    }
    
    public float getDiamX()
    {
        return diamX;
    }
    
    public float getDiamY()
    {
        return diamY;
    }
    
    public void setDiamX(float dx)
    {
        this.diamX = dx;
    }
    
    public void setDiamY(float dy)
    {
        this.diamY = dy;
    }
    
    public void setHeight(float h)
    {
        this.height = h;
    }
    
    public void setX(float x)
    {
        this.x = x;
    }
    
    public void setY(float y)
    {
        this.y = y;
    }
    
    public void setZ(float z)
    {
        this.z = z;
    }
}
