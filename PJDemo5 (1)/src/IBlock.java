public class IBlock extends Block33 {
    private boolean cells[][];
    // rows of cells
    public int rows = 4;
    // columns of cells
    public int columns = 4;
    private int zhuan = 1;

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
    public IBlock() {
        // todo
        cells = new boolean[rows][columns];
        cells[0][0] = false;
        cells[0][1] = false;
        cells[0][2] = false;
        cells[0][3] = false;
        cells[1][0] = true;
        cells[1][1] = true;
        cells[1][2] = true;
        cells[1][3] = true;
        cells[2][0] = false;
        cells[2][1] = false;
        cells[2][2] = false;
        cells[2][3] = false;
        cells[3][0] = false;
        cells[3][1] = false;
        cells[3][2] = false;
        cells[3][3] = false;
    }

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


    /**     * rotate cells by 90 degrees.     */
    public void rotate(boolean[][] cells)
    {
        // todo
        if(zhuan % 4 == 0)
        {
            cells[0][0] = false;
            cells[0][1] = false;
            cells[0][2] = false;
            cells[0][3] = false;
            cells[1][0] = true;
            cells[1][1] = true;
            cells[1][2] = true;
            cells[1][3] = true;
            cells[2][0] = false;
            cells[2][1] = false;
            cells[2][2] = false;
            cells[2][3] = false;
            cells[3][0] = false;
            cells[3][1] = false;
            cells[3][2] = false;
            cells[3][3] = false;
        }
        else if(zhuan % 4 == 1)
        {
            cells[0][0] = false;
            cells[0][1] = false;
            cells[0][2] = true;
            cells[0][3] = false;
            cells[1][0] = false;
            cells[1][1] = false;
            cells[1][2] = true;
            cells[1][3] = false;
            cells[2][0] = false;
            cells[2][1] = false;
            cells[2][2] = true;
            cells[2][3] = false;
            cells[3][0] = false;
            cells[3][1] = false;
            cells[3][2] = true;
            cells[3][3] = false;
        }
        else if(zhuan % 4 == 2)
        {
            cells[0][0] = false;
            cells[0][1] = false;
            cells[0][2] = false;
            cells[0][3] = false;
            cells[1][0] = false;
            cells[1][1] = false;
            cells[1][2] = false;
            cells[1][3] = false;
            cells[2][0] = true;
            cells[2][1] = true;
            cells[2][2] = true;
            cells[2][3] = true;
            cells[3][0] = false;
            cells[3][1] = false;
            cells[3][2] = false;
            cells[3][3] = false;
        }
        else if(zhuan % 4 == 3)
        {
            cells[0][0] = false;
            cells[0][1] = true;
            cells[0][2] = false;
            cells[0][3] = false;
            cells[1][0] = false;
            cells[1][1] = true;
            cells[1][2] = false;
            cells[1][3] = false;
            cells[2][0] = false;
            cells[2][1] = true;
            cells[2][2] = false;
            cells[2][3] = false;
            cells[3][0] = false;
            cells[3][1] = true;
            cells[3][2] = false;
            cells[3][3] = false;
        }
        zhuan = zhuan + 1;
    }
}

