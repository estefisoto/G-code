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
                    if (gCode[i].equals("G70")) {
                        mill.setUnits("INCH");
                    }
                    else if (gCode[i].equals("G90")) {
                        mill.absolute=true;
                        mill.setPos("ABS");
                    }
                    else if (gCode[i].equals("G0")) {
                        //Rapid moves tool
                        mill.setRapid();
                    }
                    else if (gCode[i].equals("X*")) {
                        mill.tool.setX(Float.valueOf(gCode[i].substring(1)));
                    }
                    else if (gCode[i].equals("Y*")) {
                        mill.tool.setY(Float.valueOf(gCode[i].substring(1)));
                    }
                    else if (gCode[i].equals("G1")) {
                        //Linear makes cut
                        mill.setLinear();
                    }
                    else if (gCode[i].equals("F10")) {
                        mill.setFeedr(Double.valueOf(gCode[i].substring(1)));
                    }
                    else if (gCode[i].equals("M2")) {
                        //End of program
                        mill.running=false;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
