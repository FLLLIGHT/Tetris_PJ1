public class OBlock extends Block33{
    private boolean cells[][];
    // rows of cells
    public int rows = 2;
    // columns of cells
    public int columns = 2;
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
    public OBlock() {
        // todo
        cells = new boolean[rows][columns];
        cells[0][0] = true;
        cells[0][1] = true;
        cells[1][0] = true;
        cells[1][1] = true;
    }
    public boolean[][] nextRotatedCells() {
        // todo

        return cells;
    }


    /**     * rotate cells by 90 degrees.     */
    public void rotate(boolean[][] cells) {

    }

}
