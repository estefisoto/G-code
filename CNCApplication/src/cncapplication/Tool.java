/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

/**
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
