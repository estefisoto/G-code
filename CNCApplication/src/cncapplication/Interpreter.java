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
public class Interpreter {
    //TODO: here when we figure out the tool and block size we convert the actual
    //given size by the code into our graphics sizes.
    private Mill mill;
    private TimeStepper timestepper;
    private ArrayList<Plane> planes;
    private HashMap<Integer, String> linetoCodeLineMap;
    private ArrayList<String> instructions;
    private Integer programCounter;
    private Double regX, regY, regZ;
    public Interpreter(ArrayList<Plane> planes, Mill mill, File file) 
    {
        this.mill = mill;
        mill.file = file;
        this.planes = planes;
        //****************************************************
       // linetoInstructionMap = new HashMap<String, String>();
        //lines = new ArrayList<String>();
        //*************************************************
        programCounter = new Integer(0);
        regX = null; regY = null; regZ = null;
        loadFile();
    }
    
    private void loadFile()
    {
        String line = new String("");
        String[] gCode;
        Scanner scanner;
        Integer counter = new Integer(0);
        try 
        {
            scanner = new Scanner(mill.file);
            while (scanner.hasNextLine()) 
            {
                line = scanner.nextLine();
                gCode = line.split(" ");
                for(int i = 1; i < gCode.length; i++) 
                {
                    linetoCodeLineMap.put(counter, gCode[0]);
                    instructions.add(gCode[i]);
                    counter++;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean step()
    {
        if(programCounter < instructions.size())
        {  
            String codeLine = linetoCodeLineMap.get(programCounter);
            for(Integer i = programCounter ; i < linetoCodeLineMap.size(); i++)
            {
                if(linetoCodeLineMap.get(i).equals(linetoCodeLineMap.get(codeLine)))
                {
                    this.interpret(instructions.get(programCounter));
                    programCounter++;
                    return true;
                }
            }
        }
        return false;
    }

    public void test()
    {
        timestepper  = new TimeStepper(planes, mill);
        timestepper.start();
        for(int i = 0; i < 5; i++)
        {
            mill.moveToolRel(25, 25, 0);
        }
        mill.moveToolAbs(-50, -10, -100);
    }

    public void run()
    { 
        while(step());
    }

    private void interpret(String command)
    {
        char letter = command.charAt(0);
        //to do doubles
        int number = Integer.parseInt(command.substring(1));
        //if(x,y,z) do whatever else
        regX = null; regY = null; regZ = null;
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
            default:
        }
    }
    
    private void F(int code)
    {
        mill.setFeedr(code);
    }
    
    private void G(int code)
    {
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
    
    private void M(int code)
    {
        switch(code)
        {
            case 2:
                //End of program
            default:
        }
    }
    
    private void X(int move)
    {
    }
    
    private void Y(int move)
    {
      
    }
    
    private void Z(int move)
    {
        
    }
}
