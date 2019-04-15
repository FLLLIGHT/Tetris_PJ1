import java.io.Serializable;
//the father class of all the block
public class Block implements Serializable {
    //block cells
    protected boolean cells[][];

    //block rows and columns
    protected int rows;
    protected int columns;


    //all these methods will be overrode
    public boolean[][] getCells()
    {
        return cells;
    }
    public int getRows()
    {
        return rows;
    }
    public int getColumns()
    {
        return columns;
    }
    public Block(){

    }

    public boolean[][] nextRotatedCells()
    {
        return cells;
    }

    public void rotate(boolean[][] cells)
    {

    }
    public void renewBlock()
    {

    }


}
