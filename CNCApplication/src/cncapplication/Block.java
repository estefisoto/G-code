package cncapplication;

import java.util.ArrayList;

/**
 * Block.java
 * 
 * Contains all information for the current state of the block.  Contains width,
 * height, and length in pixels and is stored in widthX, lengthY and heightZ. 
 * This class contains the cuts that were made in the blocks, and the moves that
 * the tool made.  Each move is contained even if it did not cut the block.  
 * Moves are represented by lines and cuts are represented with coordinates and
 * the tool that committed the cut.
 * 
 * @author mwaldron74
 */
public class Block {
    
    private ArrayList<Line> moves;
    private ArrayList<Cut> cuts;
    public Line tempMove;
    private int widthX, heightZ, lengthY;
    
    public Block(int x, int y, int z)
    {
        widthX = x;
        lengthY = y;
        heightZ = z;
        tempMove = new Line();
        moves = new ArrayList<Line>();
        cuts = new ArrayList<Cut>();
    }

    public void reset()
    {
        moves = new ArrayList<Line>();
        cuts = new ArrayList<Cut>();
    }
    
    public void removeLastMove()
    {
        if(!moves.isEmpty())
            moves.remove(moves.size() - 1);
    }
    
    public void makeMove(float xStart, float yStart, float zStart, Tool tool, Block block)
    {
        moves.add(new Line((int) xStart,(int) yStart, (int) zStart, 
                 (int) tool.getX(),(int) tool.getY(), (int) tool.getZ()));
        
        if(tool.getX() + (tool.getDiamX() / 2) > 0 && tool.getX() - (tool.getDiamX() / 2) < block.getXSize() &&
           tool.getY() + (tool.getDiamY() / 2) > 0 && tool.getY() - (tool.getDiamY() / 2) < block.getYSize() &&
           tool.getZ() < 0)
        {
            cuts.add(new Cut(tool));
        }
    }
    
//Accessors
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
