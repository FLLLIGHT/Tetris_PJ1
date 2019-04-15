import java.util.*;
public class BlockRandom {
    protected boolean cells[][];
    protected boolean bigCells[][];
    protected boolean examine[][];
    protected boolean finalCells[][];
    protected int rows;
    protected int columns;
    protected int finalRows;
    protected int finalColumns;




    public BlockRandom()
    {

    }

    public boolean[][] nextRotatedCells()
    {
        return cells;
    }

    public void rotate(boolean[][] cells)
    {

    }
    public int getRandomRows()
    {
        Random ran = new Random();
        return ran.nextInt(3) + 1;
    }
    public int getRandomColumns()
    {
        Random ran = new Random();
        return ran.nextInt(5) + 1;
    }
    public boolean[][] randomCells()
    {
        while (true)
        {
            Random ran = new Random();
            int blockNumbers = 0;
            int cellNumbers = 0;
            rows = getRandomRows();
            columns = getRandomColumns();
            cells = new boolean[rows][columns];
            bigCells = new boolean[rows + 2][columns + 2];
            examine = new boolean[rows + 2][columns + 2];

            for (int i = 0; i <= rows + 1; i++) {
                for (int j = 0; j <= columns + 1; j++) {
                    bigCells[i][j] = false;
                }
            }
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    if (ran.nextBoolean()) {
                        bigCells[i][j] = true;
                        cellNumbers++;

                    }
                }
            }
            //   if(cellNumbers<=3)                                          //因为生成方块个数多的话很难合法，因此会有很多只有一个的方块,在此控制cell个数，日后可写防重复算法
            //   {
            //       continue;
            //   }

            for (int i = 0; i <= rows + 1; i++) {
                for (int j = 0; j <= columns + 1; j++) {
                    examine[i][j] = bigCells[i][j];
                }
            }

            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    if (examine[i][j]) {
                        if (eliminate(i, j)) {
                            blockNumbers++;
                        }
                    }
                }
            }

            if (blockNumbers == 1) {
                for (int i = 1; i <= rows; i++) {
                    for (int j = 1; j <= columns; j++) {
                        if (bigCells[i][j]) {
                            cells[i - 1][j - 1] = true;
                        } else {
                            cells[i - 1][j - 1] = false;
                        }
                    }
                }

                finalRows = rows;
                finalColumns = columns;

                for (int correct = 0; correct <= 5; correct++) {         //除去空的行和列
                    rowsCorrect(finalRows);
                    columnsCorrect(finalColumns);
                }

                if (finalColumns <= 0 || finalRows <= 0) {
                    continue;
                }
                finalCells = new boolean[finalRows][finalColumns];
                for (int i = 0; i <= finalRows - 1; i++) {
                    for (int j = 0; j <= finalColumns - 1; j++) {
                        finalCells[i][j] = cells[i][j];
                    }
                }
                return finalCells;
            }
        }
    }

    private boolean eliminate(int i,int j)
    {
        examine[i][j] = false;
        if(examine[i+1][j])
        {
            eliminate(i+1,j);
        }
        if(examine[i-1][j])
        {
            eliminate(i-1,j);
        }
        if(examine[i][j-1])
        {
            eliminate(i,j-1);
        }
        if(examine[i][j+1])
        {
            eliminate(i,j+1);
        }
        return true;
    }

    private boolean rowEmpty(int row)
    {
        for(int i=0;i<=columns-1;i++){
            if(cells[row][i])
            {
                return false;
            }
        }
        return true;
    }
    private boolean columnEmpty(int column)
    {
        for(int i=0;i<=rows-1;i++){
            if(cells[i][column])
            {
                return false;
            }
        }
        return true;
    }

    private void rowsCorrect(int rows)
    {
        for (int i = 0; i <= rows - 1; i++) {
            if (rowEmpty(i)) {
                for (int j = i + 1; j <= rows - 1; j++) {
                    for (int k = 0; k <= columns - 1; k++) {
                        cells[j - 1][k] = cells[j][k];
                    }
                }
                finalRows--;
            }
        }
    }

    private void columnsCorrect(int columns)
    {
        for (int i = 0; i <= columns - 1; i++) {
            if (columnEmpty(i)) {
                for (int j = 0; j <= rows - 1; j++) {
                    for (int k = i + 1; k <= columns - 1; k++) {
                        cells[j][k - 1] = cells[j][k];
                    }
                }
                finalColumns--;
            }
        }


    }


}
