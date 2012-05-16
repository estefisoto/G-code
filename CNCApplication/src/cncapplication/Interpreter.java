/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interpreter.java
 * 
 * This attempts to open an input file written in g code and sends movements to 
 * the mill.  When the file is opened each line is stored in an array list and
 * the line currently being implemented is found by the programCounter variable.
 * Once this is initialized, the process of opening the file and storing its 
 * contents is done.  This can either step through the file or run all of its
 * instructions.
 * 
 * @author mwaldron74
 */
public class Interpreter 
{
    private Mill mill;
    private ArrayList<Plane> planes;
    private ArrayList<String> instructions;
    private Integer programCounter;
    private Float regX, regY, regZ;
    
    public Interpreter(ArrayList<Plane> planes, Mill mill, File file) 
    {
        this.mill = mill;
        mill.file = file;
        this.planes = planes;
        programCounter = new Integer(0);
        instructions = new ArrayList<String>();
        regX = null; 
        regY = null; 
        regZ = null;    
        loadFile();
    }
    
    private void loadFile()
    {
        String line = "";
        Scanner scan;
        try 
        {
            scan = new Scanner(mill.file);
            boolean first = true;
            while (scan.hasNextLine()) 
            {
                line = scan.nextLine();
                instructions.add(line);
                if(first)
                {
//if this is the first line in the file, set the current line to the gui 
                    mill.setCurrentLine(line.split(" ")[0]);
                    for(Plane p : planes)
                        p.repaint();
                }
                first = false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, 
                                                              null, ex);
        }
    }
    
    public boolean step()
    {   
        TimeStepper timestepper = new TimeStepper(planes, mill);
        timestepper.start();
        if(programCounter < instructions.size())
        {  
            this.interpret(instructions.get(programCounter));
            programCounter++;
            if(programCounter < instructions.size())
            {
                String codeLine = instructions.get(programCounter);
                mill.setCurrentLine(codeLine.split(" ")[0]);
            }
            for(Plane p : planes)
                p.repaint();
            return true;
        }
        return false;
    }
    public void run()
    {   while(step()){};
        /*TimeStepper timestepper = new TimeStepper(planes, mill);
        timestepper.start();
        for(int i = programCounter; i < instructions.size(); i++)
        {  
            String codeLine = instructions.get(programCounter);
            this.interpret(instructions.get(programCounter));
            programCounter++;
            if(programCounter < instructions.size())
                mill.setCurrentLine(codeLine.split(" ")[0]);
            for(Plane p : planes)
                p.repaint();
        }*/
    }
    
    private void interpret(String line)
    {
        String[] commands = line.split(" ");
        for(int i = 1; i < commands.length; i++)
        {
            String command = commands[i];
            char letter = command.charAt(0);
            String number = command.substring(1);
            switch(letter)
            {
                case 'F':
                case 'f':
                    F(number);
                    break;
                case 'G':
                case 'g':
                    G(number);
                    break;
                case 'M':
                case 'm':
                    M(number);
                    break;
                case 'X':
                case 'x':
                    if(regX == null)
                        regX = Float.parseFloat(number);
                    break;
                case 'Y':
                case 'y':
                    if(regY == null)
                        regY = Float.parseFloat(number);
                    break;
                case 'Z':
                case 'z':
                    if(regZ == null)
                        regZ = Float.parseFloat(number);
                    break;
                default:
                    break;
            }
        }
        while(mill.isRunning()){}//busy wait
        makeMove();
    }
    
    private void makeMove()
    {
        if(mill.pos.equals("ABS"))
        {
            if(regX == null && regY == null && regZ == null)
            {
                return; //do nothing
            }
            else if(regX == null && regY == null)
                mill.moveToolAbs(mill.getToolX(), mill.getToolY(), regZ * mill.millToPixelMultZ);
            else if(regX == null && regZ == null)
                mill.moveToolAbs(mill.getToolX(), regY * mill.millToPixelMultY, mill.getToolZ());
            else if(regZ == null && regY == null)
                mill.moveToolAbs(regX * mill.millToPixelMultX, mill.getToolY(), mill.getToolZ());
            else if(regX == null)
                mill.moveToolAbs(mill.getToolX(), regY * mill.millToPixelMultY, regZ * mill.millToPixelMultZ);
            else if(regY == null)
                mill.moveToolAbs(regX * mill.millToPixelMultX, mill.getToolY(), regZ * mill.millToPixelMultZ);
            else if(regZ == null)
                mill.moveToolAbs(regX * mill.millToPixelMultX, regY * mill.millToPixelMultY, mill.getToolZ());
            else
                mill.moveToolAbs(regX * mill.millToPixelMultX, regY * mill.millToPixelMultY, regZ * mill.millToPixelMultZ);
        }
        else // REL
        {
            if(regX == null && regY == null && regZ == null)
            {
                return;
            }
            else if(regX == null && regY == null)
                mill.moveToolRel(0, 0, regZ * mill.millToPixelMultZ);
            else if(regX == null && regZ == null)
                mill.moveToolRel(0, regY * mill.millToPixelMultY, 0);
            else if(regZ == null && regY == null)
                mill.moveToolRel(regX * mill.millToPixelMultX, 0, 0);
            else if(regX == null)
                mill.moveToolRel(0, regY * mill.millToPixelMultY, regZ * mill.millToPixelMultZ);
            else if(regY == null)
                mill.moveToolRel(regX * mill.millToPixelMultX, 0, regZ * mill.millToPixelMultZ);
            else if(regZ == null)
                mill.moveToolRel(regX * mill.millToPixelMultX, regY * mill.millToPixelMultY, 0);
            else
                mill.moveToolRel(regX * mill.millToPixelMultX, regY * mill.millToPixelMultY, regZ * mill.millToPixelMultZ);
        }
        regX = null; 
        regY = null; 
        regZ = null;
    }
    
    private void F(String code)
    {
        mill.setFeedr(Integer.parseInt(code));
    }
    
    private void G(String c)
    {
        int code = Integer.parseInt(c);
        switch(code)
        {
            case 0:
                //rapid - does nothing
                break;
            case 1:
                //linear - does nothing
                break;
            case 20:
            case 70:
                mill.setUnits("INCH");
                break;
            case 21:
                mill.setUnits("MM");
                break;
            case 90:
                mill.setAbsolute();
                break;
            case 91:
                mill.setRelative();
                break;
            default:
                break;
        }
    }
    
    private void M(String code)
    {
        switch(Integer.parseInt(code))
        {
            case 2:
                return; //End of program
            case 3:
                mill.setSpindle("CW");
                break;
            default:
                break;
        }
    }
}
