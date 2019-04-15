import java.util.*;                                                   //待修复bug:有时最左边没有时也会算全满消掉
public class Game {
    // the cells are used to hold the blocks
    private boolean[][] cells;
    private int rows;
    private int columns;
    private String next;
    private int score = 0;

    // current moving block
    private Block33 currentBlock;
    // the global position of the cells[0][0] of currentBlock
    private int posRow;
    private int posColumn;

    // create an instance of Game
    private static Game game = new Game();

    /**
     * "private" makes sure that the instance cannot be created by invoking constructor,
     * * meaning that the "game" above became the single instance.
     */
    private Game()
    {
    }

    /**
     * * get the single instance
     * * @return the single instance of Game.
     */
    public static Game getInstance() {
        return game;
    }


    /**
     * * initialize the cells with height and width, and assign all cells with "false".
     * * YOU NEED TO:
     * * assign rows and columns with height and width
     * * initialize the cells and assign them with "false".
     * * @param height: the rows of cells
     * * @param width: the columns of cells
     */
    public void initial(int height, int width)
    {
        // todo
        rows = height;
        columns = width;
        cells = new boolean[rows+1][columns];
        for(int i=0;i<=rows-1;i++)
        {
            for(int j=0;j<=columns-1;j++)
            {
                cells[i][j] = false;
            }
        }
        for(int j=0;j<=columns-1;j++)
        {
            cells[rows][j] = true;
        }

    }

    /**
     * Get next block, invoked when the game started or the last block has reached the bottom.
     * * YOU NEED TO:
     * * assign posRow and posColumn with 0 and columns / 2 - blockColumns / 2.
     * * assign currentBlock with a new instance of ZBlock.
     */
    public void nextNextBlock() {
        // todo
        Random ran = new Random();
        int random = ran.nextInt(70) + 1;
        if (random >= 1 && random <= 10) {
            next = "S";
        } else if (random >= 11 && random <= 20) {
            next = "Z";
        } else if (random >= 21 && random <= 30) {
            next = "T";
        } else if (random >= 31 && random <= 40) {
            next = "J";
        } else if (random >= 41 && random <= 50) {
            next = "L";
        } else if (random >= 51 && random <= 60) {
            next = "O";
        } else {
            next = "I";
        }
    }
    public void nextBlock() {
        if (next.equals("S")) {
            currentBlock = new SBlock();
        } else if (next.equals("Z")) {
            currentBlock = new ZBlock();
        } else if (next.equals("T")) {
            currentBlock = new TBlock();
        } else if (next.equals("J")) {
            currentBlock = new JBlock();
        } else if (next.equals("L")) {
            currentBlock = new LBlock();
        } else if (next.equals("O")) {
            currentBlock = new OBlock();
        } else {
            currentBlock = new IBlock();
        }
        posRow = 0;
        posColumn = columns / 2 - currentBlock.getColumns() / 2;
    }

    /**
     * since posRow, posColumn, currentBlock will never collide with game cells except when nextBlock appeared.
     * * we can invoke "collide()" below to check whether the game is over.
     * * @return: whether the game is over
     */
    public boolean isOver() {
        return collide(posRow, posColumn, currentBlock.getCells());
    }

    /***
     *  * print the game UI
     *  */
    public void print() {
        // todo
        int downToBottomRow = downToBottomRow(currentBlock.getCells());


        System.out.println("NEXT BLOCK:");
        if (next.equals("S")) {
            System.out.println("  **");
            System.out.println(" **");
        } else if (next.equals("Z")) {
            System.out.println(" **");
            System.out.println("  **");
        } else if (next.equals("T")) {
            System.out.println("  * ");
            System.out.println(" ***");
        } else if (next.equals("J")) {
            System.out.println(" *");
            System.out.println(" ***");
        } else if (next.equals("L")) {
            System.out.println("   *");
            System.out.println(" ***");
        } else if (next.equals("O")) {
            System.out.println(" **");
            System.out.println(" **");
        } else {
            System.out.println(" ****");
        }

        System.out.println();
        System.out.println();

        for(int rowPrint=1;rowPrint<=rows-1;rowPrint++)
        {
            for(int columnPrint=-1;columnPrint<=columns;columnPrint++)
            {
                if(columnPrint==-1)
                {
                    System.out.print("|");
                }
                else if(columnPrint==columns)
                {
                    System.out.println("|");
                }
                else if(cells[rowPrint][columnPrint])
                {
                    System.out.print("*");
                }
                else if((!isOver()||(empty(rowPrint)))&&(rowPrint-posRow>=0)&&(rowPrint-posRow<=currentBlock.getRows()-1)&&(columnPrint-posColumn>=0)&&(columnPrint-posColumn<=currentBlock.getColumns()-1)&&(currentBlock.getCells()[rowPrint-posRow][columnPrint-posColumn]))
                {
                    System.out.print("*");
                }
                else if((!isOver())&&(rowPrint-downToBottomRow>=0)&&(rowPrint-downToBottomRow<=currentBlock.getRows()-1)&&(columnPrint-posColumn>=0)&&(columnPrint-posColumn<=currentBlock.getColumns()-1)&&(currentBlock.getCells()[rowPrint-downToBottomRow][columnPrint-posColumn]))
                {
                    System.out.print("+");
                }
                else
                {
                    System.out.print(" ");
                }
            }

        }
        System.out.print(" ");
        for(int i=0;i<=columns-1;i++) {
            System.out.print("_");
        }


        System.out.println();
        System.out.println("you score is:"+score);
        System.out.println();
        System.out.println("w:旋转  s:维持");
        System.out.println("a:左移  d:右移");
        System.out.println("x:下坠  q:退出");
        System.out.println("Please input your command");
    }

    /***
     * * execute next command, you may need to invoke collide() to check collision before rotating or moving.
     * when the command is "s" and the block has reached to the bottom,
     * * loadBlock(), eliminate(), nextBlock() will be invoked sequentially.
     * * @param command: the next command {"w","s","a","d"}
     * */
    public void executeCommand(String command) {
        // todo

        if(command.equals("s"))
        {
            if(!collide(posRow+1,posColumn,currentBlock.getCells())) {
                posRow++;
            }
            else
            {
                downOneBlock();                       //下降一格并且终止
            }



        }
        if(command.equals("a"))
        {
            if(!collide(posRow+1,posColumn-1,currentBlock.getCells()))
            {
                posRow++;
                posColumn--;
            }
            else if(!collide(posRow,posColumn-1,currentBlock.getCells()))
            {
                posColumn--;
                downOneBlock();
            }
            else if(!collide(posRow+1,posColumn,currentBlock.getCells()))
            {
                posRow++;
            }
            else
            {
                downOneBlock();
            }
        }
        if(command.equals("d"))
        {
            if(!collide(posRow+1,posColumn+1,currentBlock.getCells()))
            {
                posRow++;
                posColumn++;
            }
            else if(!collide(posRow,posColumn+1,currentBlock.getCells()))
            {
                posColumn++;
                downOneBlock();
            }
            else if(!collide(posRow+1,posColumn,currentBlock.getCells()))
            {
                posRow++;
            }
            else
            {
                downOneBlock();
            }
        }
        if(command.equals("w"))
        {
            if(!collide(posRow+1,posColumn,currentBlock.nextRotatedCells()))
            {
                posRow++;
                currentBlock.rotate(currentBlock.getCells());
            }
            else if(!collide(posRow,posColumn,currentBlock.nextRotatedCells()))
            {
                currentBlock.rotate(currentBlock.getCells());
                downOneBlock();
            }
            else if(!collide(posRow+1,posColumn,currentBlock.getCells()))
            {
                posRow++;
            }
            else
            {
                downOneBlock();
            }
        }
        if(command.equals("x"))
        {
            downToBottom();
            eliminate();
            nextBlock();
            nextNextBlock();
        }
    }

    /**
     * * judge whether blockCells at position(nextRow, nextColumn) collide with game cells.
     * * @param nextPosRow: the next posRow after rotating or moving.
     * * @param nextPosColumn: the next posColumn after rotating or moving.
     * * @param nextBlockCells: the next blockCells after rotating or moving.
     * * @return: true if collided, false otherwise.
     */
    private boolean collide(int nextPosRow, int nextPosColumn, boolean[][] nextBlockCells) {
        // todo
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                if(((nextPosRow+i>=rows)||(nextPosColumn+j<=-1)||(nextPosColumn+j>=columns))&&nextBlockCells[i][j])
                {
                    return true;
                }
                if(nextBlockCells[i][j]&&cells[nextPosRow+i+1][nextPosColumn+j])
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * * assign "true" to the game cells occupied by currentBlock.
     */
    private void loadBlock() {
        // todo
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                if(currentBlock.getCells()[i][j])
                {
                    cells[posRow+i][posColumn+j] = true;
                }
            }
        }
    }

    /***
     * * eliminate all the "full" rows. "full" means all true.
     * */
    private void eliminate() {
        // todo
        int judge;
        int eliminateRows = 0;
        int thisScore = 0;
        for(int i=0;i<=rows-1;i++)
        {
            judge = 0;
            for(int j=1;j<=columns-1;j++)
            {
                if(cells[i][j])
                {
                    judge++;
                }
                if(judge == columns-1)
                {
                    eliminateOneRow(i);
                    eliminateRows++;
                    thisScore = 10;
                }
            }
        }
        for(int i=2;i<=eliminateRows;i++)
        {
            thisScore = thisScore * 2;
        }
        thisScore = thisScore * eliminateRows;
        score = score + thisScore;
    }

    /***
     *  * Force to eliminate one row, and all the cells above it will drop by one row.
     *  * @param row: the index of the eliminated row.
     *  */
    private void eliminateOneRow(int row) {
        // todo
        boolean[][] rememberCells = new boolean[rows][columns];
        for(int i=0;i<=row-1;i++)
        {
            for(int j=0;j<=columns-1;j++)
            {
                rememberCells[i+1][j] = cells[i][j];
            }
        }
        for(int i=1;i<=row;i++)
        {
            for(int j=0;j<=columns-1;j++)
            {
                cells[i][j] = rememberCells[i][j];
            }
        }
    }

    private boolean empty(int row)                //判断一行是否为空，用于决定gameover时是否要在第一行输出new的block，若gameover时恰好是第一行已经有了，则不必再输出
    {                                             //严谨一点，应该是判断new的block出现的位置处是否为空，待会修改
        for(int i=0;i<=columns-1;i++)
        {
            if(cells[row][i])
            {
                return false;
            }
        }
        return true;
    }

    private void downOneBlock()                    //下降一格并碰底
    {
        posRow++;
        loadBlock();
        eliminate();
        nextBlock();
        nextNextBlock();
    }

    private int downToBottomRow(boolean[][] currentBlockCells)
    {
        boolean downToBottomCells[][] = new boolean[currentBlock.getRows()][currentBlock.getColumns()];        //此处33应为block的长宽3*3，待修改，同理，2也是3-1，上面也有一个同样的地方需要修改  ok
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                downToBottomCells[i][j] = currentBlockCells[i][j];
            }
        }
        int downToBottomposRow = posRow;
        while(!collide(downToBottomposRow,posColumn,downToBottomCells))
        {
            downToBottomposRow = downToBottomposRow + 1;
        }
        return downToBottomposRow;

    }

    private void downToBottom()
    {
        boolean downToBottomCells[][] = new boolean[rows+1][columns];                //定义一个新的数组存储落到底部的cells，然后再将这个数组转移给cells，以免每次循环就赋cell true值会导致的叠加从而导致判断collide时的出错
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                if(currentBlock.getCells()[i][j])
                {
                    cells[i+posRow][j+posColumn] = false;
                    downToBottomCells[i+downToBottomRow(currentBlock.getCells())][j+posColumn] = true;

                }
            }
        }
        for(int i=0;i<=rows;i++){
            for(int j=0;j<=columns-1;j++){
                if(downToBottomCells[i][j])
                {
                    cells[i][j] = true;
                }
            }
        }
    }

    public void printInitial()
    {
        System.out.println(" ------------------------------------ ");
        System.out.println("|               Tetris               |");
        System.out.println("|------------------------------------|");
        System.out.println("|                input               |");
        System.out.println("|            s:Start Game            |");
        System.out.println("|            q:Quit Game             |");
        System.out.println(" ____________________________________ ");
        System.out.println("Please input your command('s' to start,'q' to quit)");
    }


}