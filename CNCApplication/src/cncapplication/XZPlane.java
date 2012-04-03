/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author mwaldron74
 */
public class XZPlane extends Plane{
    public XZPlane(Mill mill)
    {
        super(mill);
        setSize(300, 300);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(50, 100, 200, 100);
        g.setColor(Color.black);
        g.drawLine(50, 100, 50, 200);
        g.drawLine(50, 200, 250, 200);
        g.setColor(c);
    }   
}
