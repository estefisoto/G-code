/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cncapplication;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author mwaldron74
 */
public class Block {
    private ArrayList<Line> moves;
    private ArrayList<Cut> cuts;
    private int widthX, heightZ, lengthY;
    public Block(int x, int y, int z)
    {
        widthX = x;
        lengthY = y;
        heightZ = z;
        moves = new ArrayList<Line>();
        cuts = new ArrayList<Cut>();
    }

    public void removeLastMove()
    {
        if(!moves.isEmpty())
            moves.remove(moves.size() - 1);
    }
    
    public void makeMove(float xStart, float yStart, float zStart, Tool tool)
    {
        moves.add(new Line((int) xStart,(int) yStart, (int) zStart, 
                 (int) tool.getX(),(int) tool.getY(), (int) tool.getZ()));
        cuts.add(new Cut(tool, this));
    }
    
    public ArrayList<Line> getMoves()
    {
        return moves;
    }
    
    public ArrayList<Cut> getCuts()
    {
        return cuts;
    }
    
    public int getXSize()
    {
        return widthX;
    }
    
    public int getYSize()
    {
        return lengthY;
    }
    
    public int getZSize()
    {
        return heightZ;
    }
}
