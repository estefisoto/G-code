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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mwaldron74
 */
public class MillGUI extends JFrame implements ActionListener {

    private Menu file;
    private MenuItem test, open, step, run;
    //Plane Container
    private Container planeC;
    private Container main;
    private Plane xy, yz, xz, threeD;
    MachineStatus machineStatus;
    private Mill mill;
    private ArrayList<Plane> planes;
    private Interpreter interpreter;
    
    public MillGUI() 
    {
        super("Mill Simulator");
        mill = new Mill();
        interpreter = null;
        machineStatus = new MachineStatus(600, 0, mill);
        xy = new XYPlane(mill);
        yz = new YZPlane(mill);
        xz = new XZPlane(mill);
        planeC = new Container();
        main = new Container();
        main.add(machineStatus);
        
        planeC.setSize(600, 600);
        planeC.setLayout(new GridLayout(2,2));
        planeC.add(xy);
        planeC.add(yz);
        planeC.add(xz);
        planes = new ArrayList<Plane>();
        planes.add(xy);
        planes.add(xz);
        planes.add(yz);
        //TODO:
        //planes.add(machineStatus);
        
        main.add(planeC);
        add(main);
        addWindowListener(new closeWindow());
        MenuBar bar = new MenuBar();
        file = new Menu("File");
        bar.add(file);
        test = new MenuItem("TEST");
        open = new MenuItem("Open");
        step = new MenuItem("Step");
        run = new MenuItem("Run");
        file.add(test);
        file.add(open);
        file.add(step);
        file.add(run);
        step.addActionListener(this);
        run.addActionListener(this);
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
            if(interpreter == null)
            {
                JOptionPane.showMessageDialog(this, 
                                              "No file has been loaded");
            } else {
                interpreter.test();
            }
        }
        
        if(e.getActionCommand().equals(step.getActionCommand()))
        {
            if(interpreter == null)
            {
                JOptionPane.showMessageDialog(this, 
                                              "No file has been loaded");
            } else {
                //TODO: check status.... if end of file send the message
                interpreter.step();
            }
        }
        
        if(e.getActionCommand().equals(run.getActionCommand()))
        {
            if(interpreter == null)
            {
                JOptionPane.showMessageDialog(this, 
                                              "No file has been loaded");
            } else {
                interpreter.test();
            }
        }
        
        if(e.getActionCommand().equals(open.getActionCommand()))
        {   
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //Set file equal to the selected file
                File file = fc.getSelectedFile();
                interpreter = new Interpreter(planes, mill, file);
            }
        }
    }
}