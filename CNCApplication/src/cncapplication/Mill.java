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

/**
 *
 * @author estefisoto
 */
class Mill {

    public String units, arcplane, pos, spindle;
    public String cycle, cool1, cool2, opt_s, blk_s, clamps;
    public String radius_comp, length_comp, feedunits;
    public boolean relative, absolute,rapid,linear,running;
    public int sspeed;
    public double feedr;
    public BufferedImage img;
    public Tool tool;

    public Mill() {
        tool = new Tool(0, 0, 50, 20, 2);
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

    public void moveTool(float x, float y, float z) {
        tool.setX(x);
        tool.setY(y);
        tool.setZ(z);
    }

    public void setUnits(String s) {
        units=s;
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
