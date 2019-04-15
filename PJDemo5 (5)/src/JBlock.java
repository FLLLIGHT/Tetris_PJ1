import java.io.Serializable;

public class JBlock extends Block implements Serializable {
    private boolean cells[][];
    // rows of cells
    protected int rows = 3;
    // columns of cells
    protected int columns = 3;

    // get and set methods
    public boolean[][] getCells() {
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
    /**
     * * Constructor of JBlock, initialize cells with the shape of "J".
     * */
    public JBlock() {
        // todo
        cells = new boolean[rows][columns];
        cells[0][0] = true;
        cells[0][1] = false;
        cells[0][2] = false;
        cells[1][0] = true;
        cells[1][1] = true;
        cells[1][2] = true;
        cells[2][0] = false;
        cells[2][1] = false;
        cells[2][2] = false;
    }

    /**
     * * Get the next cells 90-degree rotated from current cells.
     * * This method will not change the property "cells".
     * * @return the rotated cells     */
    public boolean[][] nextRotatedCells() {
        // todo
        boolean nextCells[][] = new boolean[rows][columns];
        for (int i = 0; i <= rows-1; i++) {
            for (int j = 0; j <= columns-1; j++) {
                nextCells[i][j] = cells[i][j];
            }
        }
        rotate(nextCells);
        return nextCells;
    }



    /**     * rotate cells by 90 degrees.     */
    public void rotate(boolean[][] cells){
        int rowsR = rows;
        int columnsR = columns;

        boolean[][] cellsR = new boolean[rowsR][columnsR];

        for(int i=0;i<=rowsR-1;i++){
            for(int j=0;j<=columnsR-1;j++){
                if(cells[i][j])
                {
                    cellsR[j][(rowsR-1)-i] = true;
                }
            }
        }
        for(int i=0;i<=rowsR-1;i++){
            for(int j=0;j<=columnsR-1;j++){
                cells[i][j] = cellsR[i][j];
            }
        }

    }
}
