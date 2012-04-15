/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author estefisoto
 */
class Mill {

    public String units, arcplane, pos, spindle;
    public String cycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp, feedunits;
    public boolean relative, absolute,rapid,linear,running;
    public int sspeed, timeTick, time;
    public float moveX, moveY, moveZ, Xstep, Ystep, Zstep;
    public double feedr;
    public BufferedImage img;
    public Tool tool;
    public ArrayList<float[]> moveQueue;
    public ArrayList<Plane> planes;
    public Block block;
    public Mill() {
        tool = new Tool(0, 0, 100, 20, 20);
        moveQueue = new ArrayList<float[]>();
        //TODO : set all constants to default
        //DEFAULTS
        cool1 = "OFF";
        cool2 = "OFF";
        radius_comp = "OFF";
        length_comp = "OFF";
        cycle = "OFF";
        blk_s = "OFF";
        opt_s = "OFF";
        clamps = "OFF";
        //Plane default G70=XY plane from documentation
        arcplane="XY";
        //TO DO:spindle speeds and feedrates depends on tool type
        sspeed=1200;
        units  = "";
        arcplane = "";
        pos = "";
        spindle = "";
        running = false;
        timeTick = 100;
        time = 0;
        block = new Block(2, 2, 2);
    }
    
    public boolean isRunning()
    {
        return running;
    }

    public float getToolX() {
        return (int) tool.getX();
    }

    public float getToolY() {
        return tool.getY();
    }

    public float getToolZ() {
        return tool.getZ();
    }

    public float getToolHeight() {
        return tool.getHeight();
    }

    public float getToolDiameter() {
        return tool.getDiameter();
    }

    public void setToolX(float x) {
        tool.setX(x);
    }

    public void setToolY(float y) {
        tool.setY(y);
    }

    public void setToolZ(float z) {
        tool.setZ(z);
    }

    public void moveToolAbs(float x, float y, float z) {
        moveToolRel(x, 0, 0);
        moveToolRel(0, y, 0);
        moveToolRel(0, 0, z);
        
    }
    
    public void moveToolRel(float x, float y, float z) {
        if(running == false)
        {
            running = true;
            Xstep = x / (float) timeTick;
            Ystep = y / (float) timeTick;
            Zstep = z / (float) timeTick;
            time = timeTick;
        } else {
            float X = x / (float) timeTick;
            float Y = y / (float) timeTick;
            float Z = z / (float) timeTick;
            float[] move = {X, Y, Z};
            moveQueue.add(move);
        }
    }
    
    public void timeTick() {
        if(time == 0)
        {
            float XStart = tool.getX() - (Xstep * (timeTick - time));
            float YStart = tool.getY() - (Ystep * (timeTick - time));
            float ZStart = tool.getZ() - (Zstep * (timeTick - time));
            block.makeMove(XStart, YStart, ZStart, tool);
            if(!moveQueue.isEmpty())
            {
                float[] move = moveQueue.get(0);
                Xstep = move[0];
                Ystep = move[1];
                Zstep = move[2];
                time = timeTick;
                moveQueue.remove(0);
            } else {
                running = false;
                return;
            }
        }
        block.removeLastMove();
        float XStart = tool.getX() - (Xstep * (timeTick - time));
        float YStart = tool.getY() - (Ystep * (timeTick - time));
        float ZStart = tool.getZ() - (Zstep * (timeTick - time));
        block.makeMove(XStart, YStart, ZStart, tool);
        tool.setX(Xstep + tool.getX());
        tool.setY(Ystep + tool.getY());
        tool.setZ(Zstep + tool.getZ());
        time --;
    }        
    
    public ArrayList<Line> getMoves()
    {
        return block.getMoves();
    }
    
    public ArrayList<Cut> getCuts()
    {
        return block.getCuts();
    }

    public void setUnits(String s) 
    {
        units = s;
    }

    public void setArcplane() {
    }

    public void setPos(String s) {
        pos=s;
    }

    public void setSpindle() {
    }

    public void setCycle() {
    }

    public void setCool1() {
    }

    public void setCool2() {
    }

    public void setOpt() {
    }

    public void setBlk() {
    }

    public void setClamps() {
    }

    public void setRadiusComp() {
    }

    public void setLenComp() {
    }

    public void setFeedUnits() {
    }

    public void setSspeed(int i) {
        sspeed=i;
    }

    public void setFeedr(double d) {
        feedr=d;
    }
    
    public void setRapid() {
        rapid=true;
    }
    
    public void setLinear() {
        rapid=true;
    }
     

}
