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
 *
 * @author estefisoto
 */
public class Interpreter {
    //TODO: here when we figure out the tool and block size we convert the actual
    //given size by the code into our graphics sizes.\
    private Mill mill;
    private File file;
    TimeStepper timestepper;
    private ArrayList<Plane> planes;
    public Interpreter(ArrayList<Plane> planes, Mill mill, File file) {
        this.mill = mill;
        this.file = file;
        this.planes = planes;
    }
    
    public void step(){
        
    }

    public void test(){
        timestepper  = new TimeStepper(planes, mill);
        timestepper.start();
        for(int i = 0; i < 10; i++){
            mill.moveToolRel(10, 8, -4);
            mill.moveToolAbs(5, 8, 5);
        }
        mill.moveToolAbs(-50, -10, -100);
    }

    public void run(){ 
        String line = new String();
        String[] gCode;
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                gCode = line.split("  ");
                for (int i = 1; i < gCode.length; i++) {
                    interpret(gCode);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void interpret(String[] commands)
    {
        for(int i = 0; i < commands.length; i++)
        {
            char x = commands[i].charAt(0);
            switch(x)
            {
                case 'F':
                case 'f':
                break;
                case 'G':
                case 'g':
                break;
                case 'M':
                case 'm':
                break;
                case 'N':
                case 'n':
                    mill.setCurrentLine(commands[i]);
                break;
                case 'X':
                case 'x':
                break;
                case 'Y':
                case 'y':
                break;
                case 'Z':
                case 'z':
                break;
            }

        }
    }

    private int G0(int index, String[] commands)
    {
        int X = 0, Y = 0, Z = 0;
        while(index < commands.length)
        {

        }
        return index;
    }
}
