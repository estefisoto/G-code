/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author mwaldron74
 */
public class TimeStepper extends Thread{
    private ArrayList<Plane> planes;
    private Mill mill;
    private long timestep = 8;
    
    public TimeStepper(ArrayList<Plane> planes, Mill mill)
    {
        this.planes = planes;
        this.mill = mill;
    }
    
    public ArrayList<Plane> getPlanes()
    {
        return planes;
    }
    
    @Override
    public void run()
    {
        while(mill.isRunning())
        {
            mill.timeTick();
            for(         Plane p : planes)
                p.repaint();
            try {
                sleep(timestep);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeStepper.class.getName()).log(Level.SEVERE, 
                                 null, ex);
            }
        }     
    }
}
