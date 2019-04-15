import java.io.Serializable;
import java.util.*;
public class BlockRandom extends Block implements Serializable {
    protected boolean cells[][];
    protected boolean bigCells[][];
    protected boolean examine[][];
    protected boolean finalCells[][];
    protected boolean newCells[][];
    protected int rows;
    protected int columns;
    protected int zhuan;
    protected int finalRows;
    protected int finalColumns;
    protected int newRows;
    protected int newColumns;


    public BlockRandom()
    {

    }

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
    public int getZhuan()
    {
        return zhuan;
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

                for (int correct = 0; correct <= 15; correct++) {         //除去空的行和列
                    rowsCorrect(finalRows);
                    columnsCorrect(finalColumns);
                }

                if (finalColumns <= 0 || finalRows <= 0) {
                    continue;
                }



                if(finalRows==1&&finalColumns==1) {
                        newRows = 1;
                        newColumns = 1;
                }
                else if(finalRows==2&&finalColumns==2){
                    newRows = 2;
                    newColumns = 2;
                }
                else if(finalRows==2&&finalColumns==4){
                    newRows = 4;
                    newColumns = 4;
                }
                else if ((finalRows <= 3) && (finalColumns <= 3)) {
                        newRows = 3;
                        newColumns = 3;
                    } else {
                        newRows = 5;
                        newColumns = 5;
                    }


                finalCells = new boolean[finalRows][finalColumns];
                for (int i = 0; i <= finalRows - 1; i++) {
                    for (int j = 0; j <= finalColumns - 1; j++) {
                        finalCells[i][j] = cells[i][j];
                    }
                }

                newCells = new boolean[newRows][newColumns];

                if(finalRows==1&&finalColumns==1)
                {
                    newCells[0][0] = true;
                }
                else if(finalRows==1&&finalColumns==2)
                {
                    newCells[1][1] = finalCells[0][0];
                    newCells[1][2] = finalCells[0][1];
                }
                else if(finalRows==1&&finalColumns==3)
                {
                    newCells[1][0] = finalCells[0][0];
                    newCells[1][1] = finalCells[0][1];
                    newCells[1][2] = finalCells[0][2];
                }
                else if(finalRows==2&&finalColumns==1)
                {
                    newCells[0][1] = finalCells[0][0];
                    newCells[1][1] = finalCells[1][0];
                }
                else if(finalRows==2&&finalColumns==2)
                {
                    newCells[0][0] = finalCells[0][0];
                    newCells[1][0] = finalCells[1][0];
                    newCells[0][1] = finalCells[0][1];
                    newCells[1][1] = finalCells[1][1];
                }
                else if(finalRows==2&&finalColumns==3)
                {
                    newCells[0][0] = finalCells[0][0];
                    newCells[1][0] = finalCells[1][0];
                    newCells[0][1] = finalCells[0][1];
                    newCells[1][1] = finalCells[1][1];
                    newCells[0][2] = finalCells[0][2];
                    newCells[1][2] = finalCells[1][2];
                }
                else if(finalRows==3&&finalColumns==1)
                {
                    newCells[0][1] = finalCells[0][0];
                    newCells[1][1] = finalCells[1][0];
                    newCells[2][1] = finalCells[2][0];
                }
                else if(finalRows==3&&finalColumns==2)
                {
                    newCells[0][1] = finalCells[0][0];
                    newCells[1][1] = finalCells[1][0];
                    newCells[2][1] = finalCells[2][0];
                    newCells[0][2] = finalCells[0][1];
                    newCells[1][2] = finalCells[1][1];
                    newCells[2][2] = finalCells[2][1];
                }
                else if(finalRows==3&&finalColumns==3)
                {
                    for(int i=0;i<=2;i++)
                    {
                        for(int j=0;j<=2;j++)
                        {
                            newCells[i][j] = finalCells[i][j];
                        }
                    }
                }
                else if(finalRows==1&&finalColumns==4)
                {
                    newCells[2][1] = finalCells[0][0];
                    newCells[2][2] = finalCells[0][1];
                    newCells[2][3] = finalCells[0][2];
                    newCells[2][4] = finalCells[0][3];
                }
                else if(finalRows==1&&finalColumns==5)
                {
                    newCells[2][0] = finalCells[0][0];
                    newCells[2][1] = finalCells[0][1];
                    newCells[2][2] = finalCells[0][2];
                    newCells[2][3] = finalCells[0][3];
                    newCells[2][4] = finalCells[0][4];
                }
                else if(finalRows==2&&finalColumns==4)
                {
                    newCells[1][0] = finalCells[0][0];
                    newCells[1][1] = finalCells[0][1];
                    newCells[1][2] = finalCells[0][2];
                    newCells[1][3] = finalCells[0][3];
                    newCells[2][0] = finalCells[1][0];
                    newCells[2][1] = finalCells[1][1];
                    newCells[2][2] = finalCells[1][2];
                    newCells[2][3] = finalCells[1][3];
                }
                else if(finalRows==2&&finalColumns==5)
                {
                    newCells[1][0] = finalCells[0][0];
                    newCells[1][1] = finalCells[0][1];
                    newCells[1][2] = finalCells[0][2];
                    newCells[1][3] = finalCells[0][3];
                    newCells[1][4] = finalCells[0][4];
                    newCells[2][0] = finalCells[1][0];
                    newCells[2][1] = finalCells[1][1];
                    newCells[2][2] = finalCells[1][2];
                    newCells[2][3] = finalCells[1][3];
                    newCells[2][4] = finalCells[1][4];
                }
                else if(finalRows==3&&finalColumns==4)
                {
                    newCells[1][1] = finalCells[0][0];
                    newCells[1][2] = finalCells[0][1];
                    newCells[1][3] = finalCells[0][2];
                    newCells[1][4] = finalCells[0][3];
                    newCells[2][1] = finalCells[1][0];
                    newCells[2][2] = finalCells[1][1];
                    newCells[2][3] = finalCells[1][2];
                    newCells[2][4] = finalCells[1][3];
                    newCells[3][1] = finalCells[2][0];
                    newCells[3][2] = finalCells[2][1];
                    newCells[3][3] = finalCells[2][2];
                    newCells[3][4] = finalCells[2][3];
                }
                else if(finalRows==3&&finalColumns==5)
                {
                    newCells[1][0] = finalCells[0][0];
                    newCells[1][1] = finalCells[0][1];
                    newCells[1][2] = finalCells[0][2];
                    newCells[1][3] = finalCells[0][3];
                    newCells[1][4] = finalCells[0][4];
                    newCells[2][0] = finalCells[1][0];
                    newCells[2][1] = finalCells[1][1];
                    newCells[2][2] = finalCells[1][2];
                    newCells[2][3] = finalCells[1][3];
                    newCells[2][4] = finalCells[1][4];
                    newCells[3][0] = finalCells[2][0];
                    newCells[3][1] = finalCells[2][1];
                    newCells[3][2] = finalCells[2][2];
                    newCells[3][3] = finalCells[2][3];
                    newCells[3][4] = finalCells[2][4];
                }

                rows = finalRows;
                columns = finalColumns;

                return newCells;
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

    public void renewBlock()
    {

    }


}
