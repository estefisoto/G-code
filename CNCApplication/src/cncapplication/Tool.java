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
    
    private int x, y, z, diameter, height;
    
    public Tool(int x, int y, int z, int diameter, int height)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.diameter = diameter;
        this.height = height;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getZ()
    {
        return z;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getDiameter()
    {
        return diameter;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
}
