import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = Game.getInstance();
        while (true) {
            game.printInitial();
            String commandStartOrQuit = scanner.next();
            if (commandStartOrQuit.equals("s")) {
                game.initial(14, 10);
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
