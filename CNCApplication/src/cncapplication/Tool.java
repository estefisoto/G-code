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
    
    private float x, y, z, diameter, height;
    
    public Tool(float x, float y, float z, float diameter, float height)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.diameter = diameter;
        this.height = height;
    }
    
    public float getX()
    {
        return (int) x;
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
    
    public float getDiameter()
    {
        return diameter;
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
