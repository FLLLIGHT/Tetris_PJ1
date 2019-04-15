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
                game.nextBlock();
                game.print();
                System.out.println();


                while (true) {
                    if (game.isOver()) {
                        System.out.println("Game over!");
                        break;
                    }
                    String command = scanner.next();
                    game.executeCommand(command);
                    game.print();
                    System.out.println();
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
