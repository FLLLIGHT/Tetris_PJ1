import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.*;
public class Game {

    DataOutputStream dout;
    FileOutputStream fout;
    DataInputStream din;
    FileInputStream fin;
    // the cells are used to hold the blocks
    // the cells are the big UI
    public boolean[][] cells;
    // rows and columns are the height and width od the cells
    public int rows;
    public int columns;

    // "next" is used to store the int representation of the next block.
    public short next;

    // "score" is used to record the score you get.
    public int score = 0;

    // "randomGame" is used to judge whether the game mode the player choose is random mode.
    // it will be changed to "true" when initializing random block if the game mode the player choose is random mode.
    public boolean randomGame = false;

    // "currentBlock" and "nextBlock" are respectively used to store the current block cells and the nextBlock cells(their property).
    public Block currentBlock;
    private Block nextBlock;

    // seven random block
    private RandomBlock randomBlock1;
    private RandomBlock randomBlock2;
    private RandomBlock randomBlock3;
    private RandomBlock randomBlock4;
    private RandomBlock randomBlock5;
    private RandomBlock randomBlock6;
    private RandomBlock randomBlock7;

    // the global position of the cells[0][0] of currentBlock
    public int posRow;
    public int posColumn;




    // create an instance of Game
    private static Game game = new Game();

    private Game() { }

    // get the single instance.
    // return the single instance of Game.
    public static Game getInstance() { return game; }


    /**
     * initialize the cells with height and width, assign all cells except the bottom cells with "false" and assign the bottom cells with "true" .

     * @param height: the rows of UI cells
     * @param width: the columns of UI cells
     */
    public void initial(int height, int width)
    {
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
     * randomize.
     * get next random block after current block.
     * record which random block you get with @param next.
     */
    public void nextNextBlock()
    {
        Random ran = new Random();
        int random = ran.nextInt(70) + 1;
        if (random >= 1 && random <= 10)
        {
            next = 1;
            nextBlock = new SBlock();
        }
        else if (random >= 11 && random <= 20)
        {
            next = 2;
            nextBlock = new ZBlock();
        }
        else if (random >= 21 && random <= 30)
        {
            next = 3;
            nextBlock = new TBlock();
        }
        else if (random >= 31 && random <= 40)
        {
            next = 4;
            nextBlock = new JBlock();
        }
        else if (random >= 41 && random <= 50)
        {
            next = 5;
            nextBlock = new LBlock();
        }
        else if (random >= 51 && random <= 60)
        {
            next = 6;
            nextBlock = new OBlock();
        }
        else
        {
            next = 7;
            nextBlock = new IBlock();
        }
    }

    /**
     * judge the game mode with @param randomGame.
     * assign currentBlock with nextBlock or based on @param next.
     * if the game mode is randomGame, employ @method renewBlock to reset the property of the random block.（因为random block在第一次实例化后就要定下来，不能每次都new一个新的对象，不然会导致每次随机出来不同的方块，所以只能写一个方法手动重置random block的属性，主要是重置它的旋转状态等。)
     * assign posRow and posColumn with 0 and columns / 2 - blockColumns / 2.
    */
    public void nextBlock()
    {
       if(!randomGame)
       {
         currentBlock = nextBlock;
       }
       else
           {
           if (next==1)
           {
               currentBlock = randomBlock1;
           }
           else if (next==2)
           {
               currentBlock = randomBlock2;
           }
           else if (next==3)
           {
               currentBlock = randomBlock3;
           }
           else if (next==4)
           {
               currentBlock = randomBlock4;
           }
           else if (next==5)
           {
               currentBlock = randomBlock5;
           }
           else if (next==6)
           {
               currentBlock = randomBlock6;
           } else
           {
               currentBlock = randomBlock7;
           }
           currentBlock.renewBlock();
       }
        posRow = 0;
        posColumn = columns / 2 - currentBlock.getColumns() / 2;
    }


    public void initialNextNextBlock()
    {

        if (next==1)
        {
            nextBlock = new SBlock();
        }
        else if (next==2)
        {
            nextBlock = new ZBlock();
        }
        else if (next==3)
        {
            nextBlock = new TBlock();
        }
        else if (next==4)
        {
            nextBlock = new JBlock();
        }
        else if (next==5)
        {
            nextBlock = new LBlock();
        }
        else if (next==6)
        {
            nextBlock = new OBlock();
        }
        else
        {
            nextBlock = new IBlock();
        }
    }
    public void initialRecord(int height, int width)
    {
        rows = height;
        columns = width;


    }


    /**
     * initialize the seven random block before the random game start.
     * employ @method isSame to judge whether the random block you get is the same as the block you get before.
     * if isSame ,get a new random block until it is different from all the blocks you get before.
     * print the random block you get.
     * employ @method printBlock to print.
     * change @param randomGame to "true" ,meaning the game mode is random game.
     */
    public void initialRandomBlock()
    {
        randomBlock1 = new RandomBlock();
        System.out.println("Block1:");
        printBlock(randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells());

        while(true)
        {
            randomBlock2 = new RandomBlock();
            if(!isSame(randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells(),randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells())) {
                break;
            }
        }
        System.out.println("Block2:");
        printBlock(randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells());

        while(true)
        {
            randomBlock3 = new RandomBlock();
            if(!isSame(randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells(),randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells())
                    &&!isSame(randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells(),randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells()))
            {
                break;
            }
        }
        System.out.println("Block3:");
        printBlock(randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells());

        while(true)
        {
            randomBlock4 = new RandomBlock();
            if(!isSame(randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells(),randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells())
             &&!isSame(randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells(),randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells())
             &&!isSame(randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells(),randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells()))
            {
                break;
            }
        }
        System.out.println("Block4:");
        printBlock(randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells());

        while(true)
        {
            randomBlock5 = new RandomBlock();
            if(!isSame(randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells(),randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells())
                    &&!isSame(randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells(),randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells())
                    &&!isSame(randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells(),randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells())
                    &&!isSame(randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells(),randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells()))
            {
                break;
            }
        }
        System.out.println("Block5:");
        printBlock(randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells());


        while(true)
        {
            randomBlock6 = new RandomBlock();
            if(!isSame(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells(),randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells())
                    &&!isSame(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells(),randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells())
                    &&!isSame(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells(),randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells())
                    &&!isSame(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells(),randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells())
                    &&!isSame(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells(),randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells()))
            {
                break;
            }
        }
        System.out.println("Block6:");
        printBlock(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells());


        while(true)
        {
            randomBlock7 = new RandomBlock();
            if(!isSame(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells(),randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells())
                    &&!isSame(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells(),randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells())
                    &&!isSame(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells(),randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells())
                    &&!isSame(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells(),randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells())
                    &&!isSame(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells(),randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells())
                    &&!isSame(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells(),randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells()))
            {
                break;
            }
        }
        System.out.println("Block7:");
        printBlock(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells());

        randomGame = true;

        System.out.println("请输入任意字母以开始:");
    }

    /**
     * since posRow, posColumn, currentBlock will never collide with game cells except when nextBlock appeared.
     * we can invoke "collide()" below to check whether the game is over.
     * @return: whether the game is over
     */
    public boolean isOver()
    {
        return collide(posRow, posColumn, currentBlock.getCells());
    }

    /**
     * since posRow, posColumn, currentBlock will never collide with game cells except when nextBlock appeared.
     * Different from normal game mode, we can invoke "collideCross()" below to check whether the game is over with different rules in case that the error of java.lang.ArrayIndexOutOfBoundsException.
     * @return: whether the game is over
     */
    public boolean isOverCross()
    {
        return collideCross(posRow,posColumn,currentBlock.getCells());
    }

    /**
     * print the game UI under the rules of the Cross game mode.
     */
    public void printCross()
    {
        int downToBottomRow = downToBottomRowCross(currentBlock.getCells());
        System.out.println("NEXT BLOCK:");
        printBlock(nextBlock.getRows(),nextBlock.getColumns(),nextBlock.getCells());
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
                else if((!isOverCross()||(empty(rowPrint)))&&((rowPrint-posRow)>=0)&&((rowPrint-posRow)<=currentBlock.getRows()-1)&&(Math.floorMod((columnPrint-posColumn),columns)>=0)&&((Math.floorMod((columnPrint-posColumn),columns))<=currentBlock.getColumns()-1)&&(currentBlock.getCells()[(rowPrint-posRow)][Math.floorMod((columnPrint-posColumn),columns)]))
                {
                    System.out.print("*");
                }
                else if((!isOverCross())&&((rowPrint-downToBottomRow)>=0)&&((rowPrint-downToBottomRow)<=currentBlock.getRows()-1)&&(Math.floorMod(columnPrint-posColumn,columns)>=0)&&(Math.floorMod(columnPrint-posColumn,columns)<=currentBlock.getColumns()-1)&&(currentBlock.getCells()[(rowPrint-downToBottomRow)][Math.floorMod(columnPrint-posColumn,columns)]))
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
        for(int i=0;i<=columns-1;i++)
        {
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


    /**
     * load current blocks to the UI cells when it collide the UI cells under the rules in the Cross game mode.
     */
    private void loadBlockCross() {
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                if(currentBlock.getCells()[i][j])
                {
                    cells[posRow+i][Math.floorMod(posColumn+j,columns)] = true;
                }
            }
        }
    }

    /**
     * when current blocks will collide the UI cells, load it under the rules in the Cross game mode, try to eliminate full rows, and get next block on the top of the UI.
     */
    private void downOneBlockCross()
    {
        posRow++;
        loadBlockCross();
        eliminate();
        nextBlock();
        nextNextBlock();
    }


    /**
     * @param nextPosRow:the row global position of the cells[0][0] of the block after computer execute the player's command.
     * @param nextPosColumn:the column global position of the cells[0][0] of the block after computer execute the player's command.
     * @param nextBlockCells:the cells of the block after computer execute the player's command.
     * @return whether the block after computer execute the player's command collide UI cells under the rules of Cross game mode.
     */
    private boolean collideCross(int nextPosRow, int nextPosColumn, boolean[][] nextBlockCells) {
        // todo
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                if(nextBlockCells[i][j]&&cells[nextPosRow+i+1][Math.floorMod((nextPosColumn+j),columns)])
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param currentBlockCells:current block.
     * @return the first row of current block when it collide the UI cells directly under it(under the rules of Cross game mode).
     */
    private int downToBottomRowCross(boolean[][] currentBlockCells)
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
        while(!collideCross(downToBottomposRow,posColumn,downToBottomCells))
        {
            downToBottomposRow = downToBottomposRow + 1;
        }
        return downToBottomposRow;
    }

    /**
     * make current block fall directly to the UI cells under it.
     */
    private void downToBottomCross()
    {
        boolean downToBottomCells[][] = new boolean[rows+1][columns];                //定义一个新的数组存储落到底部的cells，然后再将这个数组转移给cells，以免每次循环就赋cell true值会导致的叠加从而导致判断collide时的出错
        for(int i=0;i<=currentBlock.getRows()-1;i++)
        {
            for(int j=0;j<=currentBlock.getColumns()-1;j++)
            {
                if(currentBlock.getCells()[i][j])
                {
                    cells[i+posRow][Math.floorMod(j+posColumn,columns)] = false;
                    downToBottomCells[i+downToBottomRowCross(currentBlock.getCells())][Math.floorMod(j+posColumn,columns)] = true;
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


    /**
     * @param command:the command the player input.
     * execute the command under the rules of Cross game mode.
     */
    public void executeCommandCross(String command) {
        if(command.equals("s"))
        {
            if(!collideCross(posRow+1,posColumn,currentBlock.getCells())) {
                posRow++;
            }
            else
            {
                downOneBlockCross();                       //下降一格并且终止
            }
        }
        if(command.equals("a"))
        {
            if(!collideCross(posRow+1,posColumn-1,currentBlock.getCells()))
            {
                posRow++;
                posColumn--;
            }
            else if(!collideCross(posRow,posColumn-1,currentBlock.getCells()))
            {
                posColumn--;
                downOneBlockCross();
            }
            else if(!collideCross(posRow+1,posColumn,currentBlock.getCells()))
            {
                posRow++;
            }
            else
            {
                downOneBlockCross();
            }
        }
        if(command.equals("d"))
        {
            if(!collideCross(posRow+1,posColumn+1,currentBlock.getCells()))
            {
                posRow++;
                posColumn++;
            }
            else if(!collideCross(posRow,posColumn+1,currentBlock.getCells()))
            {
                posColumn++;
                downOneBlockCross();
            }
            else if(!collideCross(posRow+1,posColumn,currentBlock.getCells()))
            {
                posRow++;
            }
            else
            {
                downOneBlockCross();
            }
        }
        if(command.equals("w"))
        {
            if(!collideCross(posRow+1,posColumn,currentBlock.nextRotatedCells()))
            {
                posRow++;
                currentBlock.rotate(currentBlock.getCells());
            }
            else if(!collideCross(posRow,posColumn,currentBlock.nextRotatedCells()))
            {
                currentBlock.rotate(currentBlock.getCells());
                downOneBlockCross();
            }
            else if(!collideCross(posRow+1,posColumn,currentBlock.getCells()))
            {
                posRow++;
            }
            else
            {
                downOneBlockCross();
            }
        }
        if(command.equals("x"))
        {
            downToBottomCross();
            eliminate();
            nextBlock();
            nextNextBlock();
        }
    }





    /**
     * print the game UI based on the game mode(normal game mode or random game mode).
     */
    public void print()
    {
        int downToBottomRow = downToBottomRow(currentBlock.getCells());
        System.out.println("NEXT BLOCK:");
        if(!randomGame)
        {
                printBlock(nextBlock.getRows(),nextBlock.getColumns(),nextBlock.getCells());
        }
        else
            {
            if (next==1)
            {
                printBlock(randomBlock1.getRows(),randomBlock1.getColumns(),randomBlock1.getCells());
            }
            else if (next==2)
            {
                printBlock(randomBlock2.getRows(),randomBlock2.getColumns(),randomBlock2.getCells());
            }
            else if (next==3)
            {
                printBlock(randomBlock3.getRows(),randomBlock3.getColumns(),randomBlock3.getCells());
            }
            else if (next==4)
            {
                printBlock(randomBlock4.getRows(),randomBlock4.getColumns(),randomBlock4.getCells());
            }
            else if (next==5)
            {
                printBlock(randomBlock5.getRows(),randomBlock5.getColumns(),randomBlock5.getCells());
            }
            else if (next==6)
            {
                printBlock(randomBlock6.getRows(),randomBlock6.getColumns(),randomBlock6.getCells());
            }
            else
            {
                printBlock(randomBlock7.getRows(),randomBlock7.getColumns(),randomBlock7.getCells());
            }

        }
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
        for(int i=0;i<=columns-1;i++)
        {
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

    /**
     * execute next command, invoke collide() to check collision before rotating or moving.
     * when the command is "s" and the block has reached to the bottom,
     * loadBlock(), eliminate(), nextBlock() will be invoked sequentially.
     * @param command: the next command {"w","s","a","d"}
     */
    public void executeCommand(String command) {
        if(command.equals("s"))
        {
            if(!collide(posRow+1,posColumn,currentBlock.getCells()))
            {
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
     * judge whether blockCells at position(nextRow, nextColumn) collide with game cells.
     * @param nextPosRow: the next posRow after rotating or moving.
     * @param nextPosColumn: the next posColumn after rotating or moving.
     * @param nextBlockCells: the next blockCells after rotating or moving.
     * @return: true if collided, false otherwise.
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
     * assign "true" to the game cells occupied by currentBlock.
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

    /**
     * eliminate all the "full" rows. "full" means all true.
     */
    private void eliminate() {
        // todo
        int judge;
        int eliminateRows = 0;
        int thisScore = 0;
        for(int i=0;i<=rows-1;i++)
        {
            judge = 0;
            for(int j=0;j<=columns-1;j++)
            {
                if(cells[i][j])
                {
                    judge++;
                }
                if(judge == columns)
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

    /**
     *  Force to eliminate one row, and all the cells above it will drop by one row.
     *  @param row: the index of the eliminated row.
     */
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

    /**
     * 判断一行是否为空，用于决定gameover时是否要在第一行输出new的block，若gameover时恰好是第一行已经有了，则不必再输出
     * @param row:the row to judge.
     * @return whether the position of the new block is empty.
     */
    private boolean empty(int row)
    {
        for(int i=currentBlock.getColumns()/2;i<=currentBlock.getColumns()/2 + currentBlock.getColumns();i++)
        {
            if(cells[row][i])
            {
                return false;
            }
        }
        return true;
    }


    /**
     * when current blocks will collide the UI cells, load it, try to eliminate full rows, and get next block on the top of the UI.
     */
    private void downOneBlock()                    //下降一格并碰底
    {
        posRow++;
        loadBlock();
        eliminate();
        nextBlock();
        nextNextBlock();
    }

    /**
     * @param currentBlockCells:current block.
     * @return the first row of current block when it collide the UI cells directly under it.
     */
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


    /**
     * make current block fall directly to the UI cells under it.
     */
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

    /**
     * print the initial game UI.
     */
    public void printInitial()
    {
        System.out.println(" ----------------------------------------- ");
        System.out.println("|               Tetris                   |");
        System.out.println("|----------------------------------------|");
        System.out.println("|                input                   |");
        System.out.println("|            s:Start Normal Game         |");
        System.out.println("|            c:Start Cross Game          |");
        System.out.println("|            r:Start Random Game         |");
        System.out.println("|            q:Quit Game                 |");
        System.out.println("|            l:Ranking List              |");
        System.out.println(" ________________________________________ ");
        System.out.println("Please input your command:");
    }

    /**
     * print the block you want to print with its parameters.
     * @param rows:the rows of the the block you want to print.
     * @param columns:the columns of the block you want to print.
     * @param block:the cells of the block you want to print.
     */
    private void printBlock(int rows,int columns,boolean[][] block)
    {
        for(int i=0;i<=rows-1;i++){
            for(int j=0;j<=columns-1;j++){
                if(block[i][j])
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * jugde whether the two blocks is the same.
     * @param row1:the rows of the first block.
     * @param column1:the columns of the first block.
     * @param cells1:the cells of the first block.
     * @param row2:the rows of the second block.
     * @param column2:the columns of the second block.
     * @param cells2:the cells of the second block.
     * @return whether the two blocks is the same.
     */
    private boolean isSame(int row1,int column1,boolean[][] cells1,int row2,int column2,boolean[][] cells2){
        if(row1!=row2||column1!=column2)
        {
            return false;
        }
        for(int i=0;i<=row1-1;i++)
        {
            for(int j=0;j<=column1-1;j++){
                if(cells1[i][j]!=cells2[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * delete previous file with the same name as current file and create current file.
     * record the property of current game when the player quit.
     * @param name:player's name
     * @param mode:game mode
     */
    public void writeRecord(String name,String mode)
    {
        File file=new File("C:\\"+name+"\\"+"/"+mode);
        if(file.exists()&&file.isFile())
            file.delete();

        File dir=new File("C:\\"+name);
        if(!dir.exists())
            dir.mkdir();
        File dirMode=new File("C:\\"+name+"\\"+mode);
        if(!dirMode.exists())
            dirMode.mkdir();

        try {
            fout = new FileOutputStream("C:\\"+name+"\\"+mode+"\\temp.txt");
            dout = new DataOutputStream(fout);
            dout.writeInt(game.score);
            dout.writeShort(game.next);

            dout.close();

        } catch (IOException e)
        {
            System.out.println("fail");
        }

        try
        {
            fout = new FileOutputStream("C:\\"+name+"\\"+mode+"\\posrow.txt");
            dout = new DataOutputStream(fout);
            dout.writeInt(game.posRow);

            dout.close();

        } catch (IOException e)
        {
            System.out.println("fail");
        }

        try
        {
            fout = new FileOutputStream("C:\\"+name+"\\"+mode+"\\poscolumn.txt");
            dout = new DataOutputStream(fout);
            dout.writeInt(game.posColumn);

            dout.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }


        try
        {

            fout = new FileOutputStream("C:\\"+name+"\\"+mode+"\\cells.txt");
            dout = new DataOutputStream(fout);
            for (int i = 0; i <= rows; i++)
            {
                for (int j = 0; j <= columns-1; j++)
                {
                    fout = new FileOutputStream("C:\\"+name+"\\"+mode+"\\cells" + String.valueOf(i) + String.valueOf(j) + ".txt");
                    dout = new DataOutputStream(fout);
                    dout.writeBoolean(game.cells[i][j]);
                }

                dout.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("fail");
        }

        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\"+mode+"\\current.txt");

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.currentBlock);
            objectOut.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }
    }


    /**
     * read the property of the previous game under the player's name of the game mode.
     * @param name:player's name
     * @param mode:game mode
     */
    public void readRecord(String name,String mode)
    {
        try
        {
            fin = new FileInputStream("C:\\"+name+"\\"+mode+"\\temp.txt");
            din = new DataInputStream(fin);
            game.score = din.readInt();
            game.next = din.readShort();
            din.close();
        }
        catch (FileNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }
        try
        {
            fin = new FileInputStream("C:\\"+name+"\\"+mode+"\\posrow.txt");
            din = new DataInputStream(fin);
            game.posRow = din.readInt();
            din.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件未找到");
        }
        catch (IOException e)
        {
        }
        try
        {
            fin = new FileInputStream("C:\\"+name+"\\"+mode+"\\poscolumn.txt");
            din = new DataInputStream(fin);
            game.posColumn = din.readInt();
            din.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件未找到");
        }
        catch (IOException e)
        {
        }
        try
        {
            fin = new FileInputStream("C:\\"+name+"\\"+mode+"\\cells.txt");
            din = new DataInputStream(fin);
            game.cells = new boolean[rows+1][columns];
            for (int i = 0; i <= rows; i++)
            {
                for (int j = 0; j <= columns-1; j++)
                {
                    fin = new FileInputStream("C:\\"+name+"\\"+mode+"\\cells" + String.valueOf(i) + String.valueOf(j) + ".txt");
                    din = new DataInputStream(fin);
                    game.cells[i][j] = din.readBoolean();
                }
            }
            din.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件未找到");
        }
        catch (IOException e)
        {
        }
        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\"+mode+"\\current.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.currentBlock = (Block) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }
    }

    /**
     * when the game mode is random game mode, there are some more properties to record.
     * @param name:player's name
     */
    public void writeRandomBlock(String name)
    {
            try
            {
                FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock1.txt");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(game.randomBlock1);
                objectOut.close();

            }
            catch (IOException e)
            {
                System.out.println("fail");
            }
        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock2.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.randomBlock2);
            objectOut.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }
        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock3.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.randomBlock3);
            objectOut.close();

        } catch (IOException e)
        {
            System.out.println("fail");
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock4.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.randomBlock4);
            objectOut.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock5.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.randomBlock5);
            objectOut.close();

        } catch (IOException e) {
            System.out.println("fail");
        }
        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock6.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.randomBlock6);
            objectOut.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }

        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\"+name+"\\randomGameMode\\randomBlock7.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game.randomBlock7);
            objectOut.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }
    }

    /**
     * when the game mode is random game mode, there are some more properties to read.
     * @param name:player's name
     */
    public void readRandomBlock(String name)
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock1.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock1 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }

        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock2.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock2 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }

        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock3.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock3 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }

        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock4.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock4 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }

        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock5.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock5 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }

        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock6.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock6 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }

        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\"+name+"\\randomGameMode\\randomBlock7.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            game.randomBlock7 = (RandomBlock) objectIn.readObject();
            objectIn.close();
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }
    }

    /**
     * judge whether there has been any record under the name and the game mode.
     * @param name:player's name
     * @param mode:game mode
     * @return
     */
    public boolean hasRecord(String name,String mode)
    {

        try
        {
            fin = new FileInputStream("C:\\"+name+"\\"+mode+"\\temp.txt");
            din = new DataInputStream(fin);
            din.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("查无此档");
            return false;
        }
        catch (IOException e)
        {
        }
        return true;
    }


    /**
     * record the player's score under a specific game mode when game over.
     * used to compose the ranking list.
     * @param name:player's name
     * @param mode:game mode
     */
    public void writeScore(String name,String mode)
    {
        File dir=new File("C:\\score");
        if(!dir.exists())
            dir.mkdir();
        File dirMode=new File("C:\\score\\"+mode);
        if(!dirMode.exists())
            dirMode.mkdir();

        try
        {
            fout = new FileOutputStream("C:\\score\\"+mode+"\\"+name+".txt");
            dout = new DataOutputStream(fout);
            dout.writeInt(game.score);

            dout.close();

        }
        catch (IOException e)
        {
            System.out.println("fail");
        }
    }


    /**
     * print the ranking list.
     * @param mode:the game mode of the ranking list printed
     */
    public void readScore(String mode)
    {
        File file = new File("C:\\score\\"+mode);
        if (file.exists())
        {
            File[] files = file.listFiles();
            int[] score = new int[files.length];
            String[] name = new String[files.length];
            if (null == files || files.length == 0) {
                System.out.println();
                System.out.println("暂无排行数据~赶快来玩一下成为第一名叭。");
                System.out.println();
                return;
            }
            else
                {
                for (int i=0;i<files.length;i++)
                {
                    if (files[i].isDirectory())
                    {
                    }
                    else
                        {
                        try
                        {
                            fin = new FileInputStream(files[i].getAbsolutePath());
                            din = new DataInputStream(fin);
                            score[i] = din.readInt();
                            String str = files[i].getAbsolutePath();
                            File tempfile = new File(str.trim());
                            name[i] = tempfile.getName();
                            name[i] = name[i].substring(0, name[i].length() - 4);
                            din.close();
                        }
                        catch (FileNotFoundException e)
                        {
                        }
                        catch (IOException e)
                        {
                        }
                    }
                }
                for(int i=0;i<files.length;i++)
                {
                    for(int j=0;j<files.length-1;j++)
                    {
                        if(score[j]<score[j+1])
                        {
                            int temp;
                            temp = score[j];
                            score[j] = score[j+1];
                            score[j+1] = temp;
                            String tempS="";
                            tempS = name[j];
                            name[j] = name[j+1];
                            name[j+1] = tempS;
                        }
                    }
                }


                System.out.println();
                System.out.println("name        score");
                System.out.println();
                int n = files.length;
                if(n>=15)
                {
                    n=15;
                }
                for(int i=0;i<n;i++)
                {
                    System.out.print(name[i]);
                    for(int j=0;j<=12-name[i].length();j++)
                    {
                        System.out.print(" ");
                    }
                    System.out.println(score[i]);
                }
                System.out.println();

            }
        }
        else
            {
            System.out.println();
            System.out.println("暂无排行数据~赶快来玩一下成为第一名叭。");
            System.out.println();
        }
    }


}