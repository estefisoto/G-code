/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Canvas;

/**
 *
 * @author mwaldron74
 */
public abstract class Plane extends Canvas{
    protected Mill mill;
    public Plane(Mill mill)
    {
        this.mill = mill;
    }
}
