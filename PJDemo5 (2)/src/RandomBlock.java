public class RandomBlock extends BlockRandom{

    private int zhuan = 1;
    private boolean cells[][];
    private boolean recordCells[][];
    // rows of cells
    protected int rows;
    // columns of cells
    protected int columns;
    protected int finalRows;
    protected int finalColumns;
    // get and set methods
    // todo
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
     * * Constructor of ZBlock, initialize cells with the shape of "Z".
     * */
    public RandomBlock() {
        // todo
        BlockRandom blockRandom = new BlockRandom();
        cells = new boolean[rows][columns];
        cells = blockRandom.randomCells();
        rows = blockRandom.newRows;
        columns = blockRandom.newColumns;
        finalRows = blockRandom.finalRows;
        finalColumns = blockRandom.finalColumns;

        recordCells = new boolean[rows][columns];
        for(int i=0;i<=rows-1;i++){
            for(int j=0;j<=columns-1;j++){
                recordCells[i][j] = cells[i][j];
            }
        }
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
        zhuan--;
        return nextCells;
    }


    public void rotate(boolean[][] cells){
        if((super.finalRows % 2 == 0)&&(super.finalColumns % 2 == 0))
        {
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

    public void renewBlock(){
        for(int i=0;i<=rows-1;i++){
            for(int j=0;j<=columns-1;j++){
                cells[i][j] = recordCells[i][j];
            }
        }
    }
}
