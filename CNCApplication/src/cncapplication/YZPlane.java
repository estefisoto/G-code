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
public class YZPlane extends Plane{
    public YZPlane(Mill mill)
    {
        super(mill);
        setSize(300, 300);
    }

    @Override
    public void paint(Graphics g)
    {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(20, 70, 200, 100);
        g.setColor(Color.black);
        g.drawLine(20, 70, 20, 170);
        g.drawLine(20, 170, 220, 170);
        g.setColor(c);
    }
}
