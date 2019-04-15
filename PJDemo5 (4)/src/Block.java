import java.io.Serializable;

public class Block implements Serializable {
    protected boolean cells[][];
    protected int rows;
    protected int columns;

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
