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
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author estefisoto
 */
class Mill {

    public String units, arcplane, pos, spindle;
    public String cycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp, feedunits;
    public String currentLine;
    public boolean relative, absolute,rapid,linear,running;
    public int sspeed, timeTick, time;
    public float moveX, moveY, moveZ, Xstep, Ystep, Zstep;
    public double feedr;
    public BufferedImage img;
    public Tool tool;
    public ArrayList<float[]> moveQueue;
    public ArrayList<Plane> planes;
    public Block block;
    public File file;
    private boolean inches, metric;
    public Mill() 
    {
        tool = new Tool(0, 0, 0, 20, 20, 20);
        moveQueue = new ArrayList<float[]>();
        //TODO : set all constants to default
        //DEFAULTS
        currentLine = "";
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
        spindle = "";
        sspeed=1200;
        units  = "inches";
        pos = "";
        file = null;
        running = false;
        timeTick = 100;
        time = 0;
        block = new Block(200,200,100);
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

    public float getToolDiamX() {
        return tool.getDiamX();
    }
    
    public float getToolDiamY() {
        return tool.getDiamY();
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
    
    public void setToolSize(float diamX, float diamY, float height) {
        
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
            float max = x > y ? x : y;
            max = max > z ? max : z;
            timeTick = (int) max;
            
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
            block.makeMove(XStart, YStart, ZStart, tool, block);
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
        block.makeMove(XStart, YStart, ZStart, tool, block);
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

    public void setAbsolute()
    {
        this.absolute = true;
        this.relative = false;
    }
    
    public void setInches()
    {
        this.inches = true;
        this.metric = false;
    }

    
    public void setArcplane() {
    }

    public void setPos(String s) {
        pos=s;
        if(s == "ABS")
            relative = false;
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

    public void setCurrentLine(String current)
    {
        currentLine = current;
    }

    public String getCurrentLine()
    {
        return currentLine;
    }
}
