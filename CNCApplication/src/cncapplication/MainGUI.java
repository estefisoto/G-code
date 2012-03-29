/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

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
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author mwaldron74
 */
public class MainGUI extends JFrame implements ActionListener {

    private Menu file;
    private MenuItem open;
    //Plane Container
    private Container planeC;
    private Container main;
    private Plane xy, yz, xz, threeD;
    private Tool tool;
    private MachineStatus ms = new MachineStatus(600,0);
    
    public MainGUI() 
    {
        super("");
        
       
        add(ms);
        tool = new Tool(0, 0, 50, 20, 2);
        xy = new XYPlane(tool);
        yz = new YZPlane(tool);
        xz = new XZPlane(tool);        
        threeD = new ThreeDimPlane(tool);
        planeC = new Container();
        main = new Container();
        
        planeC.setSize(1000,700);
        
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
        open = new MenuItem("Open");
        file.add(open);
        open.addActionListener(this);
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
