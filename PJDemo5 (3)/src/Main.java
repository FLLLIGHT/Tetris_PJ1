import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = Game.getInstance();
        while (true) {
            game.printInitial();
            String commandStartOrQuit = scanner.next();
            if (commandStartOrQuit.equals("s")) {
                game.initial(10, 10);
                game.nextNextBlock();
                game.nextBlock();
                game.nextNextBlock();
                game.print();
                System.out.println();


                while (true) {
                    if (game.isOver()) {
                        System.out.println("Game over!");
                        break;
                    }
                    String command = scanner.next();
                    if(command.equals("q"))
                    {
                        break;
                    }
                    game.executeCommand(command);
                    game.print();
                }
            }
            else if(commandStartOrQuit.equals("r"))
            {
                    game.initial(20, 20);
                    game.initialRandomBlock();
                    String commandStart = scanner.next();
                    System.out.println();
                    game.nextNextBlock();
                    game.nextBlock();
                    game.nextNextBlock();
                    game.print();
                    System.out.println();


                    while (true) {
                        if (game.isOver()) {
                            System.out.println("Game over!");
                            break;
                        }
                        String command = scanner.next();
                        if(command.equals("q"))
                        {
                            break;
                        }
                        game.executeCommand(command);
                        game.print();
                    }

            }
            else if(commandStartOrQuit.equals("c"))
            {
                game.initial(25, 25);
                game.nextNextBlock();
                game.nextBlock();
                game.nextNextBlock();
                game.printCross();
                System.out.println();


                while (true) {
                    if (game.isOverCross()) {
                        System.out.println("Game over!");
                        break;
                    }
                    String command = scanner.next();
                    if(command.equals("q"))
                    {
                        break;
                    }
                    game.executeCommandCross(command);
                    game.printCross();
                }
            }
            else if(commandStartOrQuit.equals("q")){
                break;
            }
            else
            {
                continue;
            }
        }
    }
}
