/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
        this.setMinimumSize(new Dimension(900, 650));
        mill = new Mill();
        interpreter = null;
        machineStatus = new MachineStatus(600, 0, mill);
        xy = new XYPlane(mill);
        yz = new YZPlane(mill);
        xz = new XZPlane(mill);
        planeC = new Container();
        main = new Container();
        main.setSize(1100,700);
        main.setPreferredSize(new Dimension(800, 650));
        main.add(machineStatus);
        
        planeC.setSize(600, 600);
        planeC.setPreferredSize(new Dimension(600, 600));
        planeC.setLayout(new GridLayout(2,2));
        planeC.add(xy);
        planeC.add(yz);
        planeC.add(xz);
        planes = new ArrayList<Plane>();
        planes.add(xy);
        planes.add(xz);
        planes.add(yz);
        planes.add(machineStatus);
        
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
                if(!interpreter.step())
                    JOptionPane.showMessageDialog(this, "Program has completed");
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
            String block = JOptionPane.showInputDialog("Please enter the block"
                    + "size in inches\n X Y Z - Seperated by spaces");
            String tool = JOptionPane.showInputDialog("Please enter the tool"
                    + "size in inches\n height radius - Seperated by spaces");
            String toolLoc = JOptionPane.showInputDialog("Please enter the tool"
                    + "location\n X Y Z - Seperated by spaces");
            try {
                String[] bArr = block.split(" ");
                String[] tArr = tool.split(" ");
                String[] tlArr = toolLoc.split(" ");
                
                if(bArr.length != 3 ||  tArr.length != 2 || tlArr.length != 3)
                {
                    JOptionPane.showMessageDialog(this, "Size input error");
                    return;
                }
                
                float blockXSize = Float.parseFloat(bArr[0]);
                float blockYSize = Float.parseFloat(bArr[1]);
                float blockZSize = Float.parseFloat(bArr[2]);
                float toolHeight = Float.parseFloat(tArr[0]);
                float toolDiamX = Float.parseFloat(tArr[1]) * 2;
                float toolDiamY = Float.parseFloat(tArr[1]) * 2;
            
                mill.setToolX(Float.parseFloat(tlArr[0]));
                mill.setToolY(Float.parseFloat(tlArr[1]));
                mill.setToolZ(Float.parseFloat(tlArr[2]));
                mill.setToolSize((toolDiamX * mill.getBlockX()) / blockXSize,
                                 (toolDiamY * mill.getBlockY()) / blockYSize, 
                                 (toolHeight * mill.getBlockZ()) / blockZSize);
                for(Plane p : this.planes)
                    p.repaint();
            } catch(NullPointerException np) {
                JOptionPane.showMessageDialog(this, "Size input error");
                return;
            }
            
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                //Set file equal to the selected file
                File file = fc.getSelectedFile();
                interpreter = new Interpreter(planes, mill, file);
            }
            for(Plane p : this.planes)
                p.repaint();
        }
    }
}