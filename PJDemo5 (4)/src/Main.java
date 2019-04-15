import java.util.Scanner;                                                      //在完成一次俄罗斯方块后所有数据清零
public class Main {
    public static void main(String[] args) {
        final int height = 20;
        final int width = 25;
        Scanner scanner = new Scanner(System.in);
        Game game = Game.getInstance();
        while (true) {
            game.printInitial();
            String commandStartOrQuit = scanner.next();
            if (commandStartOrQuit.equals("s") || commandStartOrQuit.equals("r")) {
                if (commandStartOrQuit.equals("s")) {
                    if (game.hasRecord("normalGameMode")) {
                        String yesOrNo = scanner.next();
                        if (yesOrNo.equals("y")) {
                            game.initialRecord(height, width);
                            game.readRecord("normalGameMode");
                            game.initialNextNextBlock();
                            game.print();
                        } else {
                            game.initial(height, width);
                            game.nextNextBlock();
                            game.nextBlock();
                            game.nextNextBlock();
                            game.print();
                            System.out.println();
                        }
                    } else {
                        game.initial(height, width);
                        game.nextNextBlock();
                        game.nextBlock();
                        game.nextNextBlock();
                        game.print();
                        System.out.println();
                    }
                } else if (commandStartOrQuit.equals("r")) {

                    if (game.hasRecord("randomGameMode")) {
                        String yesOrNo = scanner.next();
                        if (yesOrNo.equals("y")) {
                            game.initialRecord(height, width);
                            game.readRecord("randomGameMode");
                            game.readRandomBlock();
                            game.initialNextNextBlock();
                            game.print();
                            game.randomGame = true;
                        } else {
                            game.initial(height, width);
                            game.initialRandomBlock();
                            String commandStart = scanner.next();
                            System.out.println();
                            game.nextNextBlock();
                            game.nextBlock();
                            game.nextNextBlock();
                            game.print();
                            System.out.println();
                        }
                    } else {
                        game.initial(height, width);
                        game.initialRandomBlock();
                        String commandStart = scanner.next();
                        System.out.println();
                        game.nextNextBlock();
                        game.nextBlock();
                        game.nextNextBlock();
                        game.print();
                        System.out.println();
                    }
                }
                while (true) {
                    if (game.isOver()) {
                        game.randomGame = false;
                        System.out.println("Game over!");
                        break;
                    }
                    String command = scanner.next();
                    if (command.equals("q")) {
                        game.randomGame = false;

                        if (commandStartOrQuit.equals("s")) {
                            game.writeRecord("normalGameMode");
                        }

                        if (commandStartOrQuit.equals("r")) {
                            game.writeRecord("randomGameMode");
                            game.writeRandomBlock();
                        }
                        break;
                    }
                    game.executeCommand(command);
                    game.print();
                }
            } else if (commandStartOrQuit.equals("c")) {

                if (game.hasRecord("crossGameMode")) {
                    String yesOrNo = scanner.next();
                    if (yesOrNo.equals("y")) {
                        game.initialRecord(height, width);
                        game.readRecord("crossGameMode");
                        game.initialNextNextBlock();
                        game.printCross();
                    } else {
                        game.initial(height, width);
                        game.nextNextBlock();
                        game.nextBlock();
                        game.nextNextBlock();
                        game.printCross();
                        System.out.println();
                    }
                } else {
                    game.initial(height, width);
                    game.nextNextBlock();
                    game.nextBlock();
                    game.nextNextBlock();
                    game.printCross();
                    System.out.println();
                }
                while (true) {
                    if (game.isOverCross()) {
                        System.out.println("Game over!");
                        break;
                    }
                    String command = scanner.next();
                    if (command.equals("q")) {
                        System.out.println("请输入你的昵称：");
                        
                        game.writeRecord("crossGameMode");
                        break;
                    }
                    game.executeCommandCross(command);
                    game.printCross();


                }
            }else if (commandStartOrQuit.equals("q")) {
                break;
            } else {
                continue;
            }
        }
    }
}
