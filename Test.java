public class Test {
    public static void main(String args[]) {
        for (int n = 0; n <= 100; n++) {
            BlockRandom block = new BlockRandom();
            boolean cells[][] = block.randomCells();
            System.out.println("rows:"+block.rows+" finalRows:"+block.finalRows);
            System.out.println("columns:"+block.columns+" finalColumns:"+block.finalColumns);
            for (int i = 0; i <= block.finalRows - 1; i++) {
                for (int j = 0; j <= block.finalColumns - 1; j++) {
                    if (cells[i][j]) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println((-1)%25);
    }
}
