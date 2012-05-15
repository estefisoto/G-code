/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estefisoto
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
        regX = null; regY = null; regZ = null;
        loadFile();
    }
    
    private void loadFile()
    {
        String line = new String("");
        Scanner scanner;
        try 
        {
            scanner = new Scanner(mill.file);
            boolean first = true;
            while (scanner.hasNextLine()) 
            {
                line = scanner.nextLine();
                instructions.add(line);
                if(first)
                {
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
        if(programCounter < instructions.size())
        {  
            String codeLine = instructions.get(programCounter);
            this.interpret(instructions.get(programCounter));
            programCounter++;
            if(programCounter < instructions.size())
                mill.setCurrentLine(instructions.get(programCounter).split(" ")[0]);
            for(Plane p : planes)
                p.repaint();
            return true;
        }
        return false;
    }

    public void test()
    {
//        timestepper  = new TimeStepper(planes, mill);
//        timestepper.start();
//        for(int i = 0; i < 5; i++)
//        {
//            mill.moveToolRel(25, 25, 0);
//        }
//        mill.moveToolAbs(0, 0, 0);
    }

    public void run()
    { 
        while(step());
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
                case 'G':
                case 'g':
                    G(number);
                case 'M':
                case 'm':
                    M(number);
                case 'X':
                case 'x':
                    
                case 'Y':
                case 'y':
                    
                case 'Z':
                case 'z':
                    
                default:
            }
        }
        makeMove();
    }
    
    private void makeMove()
    {
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
                mill.setRapid();
            case 1:
                mill.setLinear();
            case 70:
                mill.setInches();
            case 90:
                mill.setAbsolute();
            default:
        }
    }
    
    private void M(String code)
    {
        switch(Integer.parseInt(code))
        {
            case 2:
                //End of program
            default:
        }
    }
}
