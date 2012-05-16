/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cncapplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Mill.java
 * 
 * The mill is the model in the model view controller design.  It holds all 
 * information for the mill, as it contains the block, tool, moveQueue, etc.
 * When time tick is called, if running this moves a small amount to show the 
 * tool moving.  The mill also contains the machine's status, and contains the
 * various variables for things that cannot be represented graphically such as
 * coolant.
 * 
 * @author mwaldron74
 */



// The big problem with this class is the way moves are made.
// Instead of having a movequeue and continuing to add and delete
// lines, there should just be a temp move in the Block that is always drawn
// and only once the tool has completed a command should the move's line
// be added to the block class. (This i think is the problem with run vs. step)
class Mill {
    public String units, arcplane, pos, spindle, opt_s, blk_s, clamps, cycle;
    public String cool1, cool2, radius_comp, length_comp, feedunits;
    public String currentLine;
    public boolean running;
    public int sspeed, timeTick, time;
    public float moveX, moveY, moveZ, Xstep, Ystep, Zstep, millToPixelMultX;
    public float millToPixelMultY, millToPixelMultZ;
    public double feedr;
    public Tool tool;
    public ArrayList<float[]> moveQueue;
    public Block block;
    public File file;
    
    public Mill() 
    {
        moveQueue = new ArrayList<float[]>();
        //DEFAULTS
        tool = new Tool(0, 0, 0, 20, 20, 20);
        currentLine = "";
        cool1 = "OFF";
        cool2 = "OFF";
        radius_comp = "OFF";
        length_comp = "OFF";
        cycle = "OFF";
        blk_s = "OFF";
        opt_s = "OFF";
        clamps = "OFF";
        arcplane="XY";
        spindle = "STOP";
        sspeed=1200;
        units  = "INCH";
        pos = "REL";
        file = null;
        running = false;
        timeTick = 100;
        time = 0;
        block = new Block(200,200,100);
    }
   
    public void moveToolAbs(float x, float y, float z) 
    {
        moveToolRel(x - tool.getX(), y - tool.getY(), z - tool.getZ());
    }
    
    public void moveToolRel(float x, float y, float z) 
    {
        if(running == false)
        {
            running = true;
            float max = Math.abs(x) > Math.abs(y) ? Math.abs(x) : Math.abs(y);
            max = max > Math.abs(z) ? max : Math.abs(z);
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
    
    public void timeTick() 
    {
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
    
    public void resetBlock()
    {
        block.reset();
    }
    
    //accsessors
    public float getBlockX()
    {
        return (float) block.getXSize();
    }
    
    public float getBlockY()
    {
        return (float) block.getYSize();
    }
    
    public float getBlockZ()
    {
        return (float) block.getZSize();
    }
    
    public boolean isRunning()
    {
        return running;
    }

    public float getToolX() 
    {
        return (int) tool.getX();
    }

    public float getToolY() 
    {
        return tool.getY();
    }

    public float getToolZ() 
    {
        return tool.getZ();
    }

    public float getToolHeight() 
    {
        return tool.getHeight();
    }

    public float getToolDiamX() 
    {
        return tool.getDiamX();
    }
    
    public float getToolDiamY() 
    {
        return tool.getDiamY();
    }
    
    public String getCurrentLine()
    {
        return currentLine;
    }
    
    public ArrayList<Line> getMoves()
    {
        return block.getMoves();
    }
    
    public ArrayList<Cut> getCuts()
    {
        return block.getCuts();
    }
    
    //mutators
    public void setToolX(float x) 
    {
        tool.setX(x);
    }

    public void setToolY(float y) 
    {
        tool.setY(y);
    }

    public void setToolZ(float z) 
    {
        tool.setZ(z);
    }
    
    public void setToolSize(float diamX, float diamY, float height) 
    {
        tool.setDiamX(diamX);
        tool.setDiamY(diamY);
        tool.setHeight(height);
    }
    
    public void setAbsolute()
    {
        this.pos = "ABS";
    }
    
    public void setArcplane() {
    }

    public void setPos(String s) {
        pos = s;
    }

    public void setUnits(String s)
    {
        units = s;
    }
    
    public void setRelative()
    {
        this.pos = "REL";
    }
    
    public void setSpindle(String s)
    {
        this.spindle = s;
    }

    public void setCycle() 
    {
    }

    public void setCool1() 
    {
    }

    public void setCool2() 
    {
    }

    public void setOpt() 
    {
    }

    public void setBlk() 
    {
    }

    public void setClamps() 
    {
    }

    public void setRadiusComp() 
    {
    }

    public void setLenComp() 
    {
    }

    public void setFeedUnits() 
    {
    }

    public void setSspeed(int i) 
    {
        sspeed = i;
    }

    public void setFeedr(double d) 
    {
        feedr = d;
    }

    public void setCurrentLine(String current)
    {
        currentLine = current;
    }
}