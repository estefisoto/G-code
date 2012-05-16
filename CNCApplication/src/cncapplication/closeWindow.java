/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * closeWindow.java
 * 
 * This class serves as the closer for the user interface.  When 'x' is clicked,
 * this closes the program.
 * 
 * @author estefisoto
 */
class closeWindow extends WindowAdapter {

    public closeWindow()  
    {
    }
    
    @Override
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    
}