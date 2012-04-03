/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author mwaldron74
 */
public class MillGUI extends JFrame implements ActionListener {

    private Menu file;
    private MenuItem open, test;
    //Plane Container
    private Container planeC;
    private Container main;
    private Plane xy, yz, xz, threeD;
    MachineStatus machineStatus;
    private Mill mill;
    private TimeStepper timestepper;
    
    public MillGUI() 
    {
        super("Mill Simulator");
        mill = new Mill();
        machineStatus = new MachineStatus(0, 600, mill);
        add(machineStatus);
        xy = new XYPlane(mill);
        yz = new YZPlane(mill);
        xz = new XZPlane(mill);        
        threeD = new ThreeDPlane(mill);
        planeC = new Container();
        main = new Container();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        planes.add(xy);
        planes.add(xz);
        planes.add(yz);
        planes.add(machineStatus);
        timestepper = new TimeStepper(planes, mill);
        
        planeC.setSize(600, 600);
        planeC.setLayout(new GridLayout(2,2));
        planeC.add(xy);
        planeC.add(yz);
        planeC.add(xz);
        
        main.add(planeC);
        add(main);
        addWindowListener(new closeWindow());
        MenuBar bar = new MenuBar();
        file = new Menu("File");
        bar.add(file);
        test = new MenuItem("TEST");
        open = new MenuItem("Open");
        
        file.add(test);
        file.add(open);
        open.addActionListener(this);
        test.addActionListener(this);
        setMenuBar(bar);
        addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent ce) {
                
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
               
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                setSize(planeC.getSize());
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
               
            }
        
     
        });
      
        pack();
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(test.getActionCommand()))
        {
            //xy.makeCut(100, 100, 0);
            System.out.println("HELLO");
        }
        
        if(e.getActionCommand().equals(open.getActionCommand()))
        {
            //New file chooser
            final JFileChooser fc = new JFileChooser();
            //New Dialog

            //Set return val to Open file browse in frame
            int returnVal = fc.showOpenDialog(this);
            //Declare string to hold line from file
            String classPath = new String();
            String className = new String();

            //If a new file was selected from browser
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //Set file equal to the sellected file
                File file = fc.getSelectedFile();
                classPath = fc.getCurrentDirectory().toString();
                System.out.println(file);
                System.out.println(classPath);

                className = file.getName();
            }
        }
    }
}