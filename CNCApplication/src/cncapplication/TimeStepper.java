/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mwaldron74
 */
public class TimeStepper extends Thread{
    private ArrayList<Plane> planes;
    private Mill mill;
    private long timestep = 5;
    public TimeStepper(ArrayList<Plane> planes, Mill mill)
    {
        this.planes = planes;
        this.mill = mill;
    }
    
    public void timeStep()
    {
        for(Plane plane : planes)
            plane.repaint();
        try {
            sleep(timestep);
        } catch (InterruptedException ex) {
            Logger.getLogger(("Thread exception")).log(Level.SEVERE, null, ex);
        }
    }
}
