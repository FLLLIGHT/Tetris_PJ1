import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        final int height = 20;
        final int width = 25;
        Scanner scanner = new Scanner(System.in);
        Game game = Game.getInstance();
        while (true) {
            game.printInitial();
            String commandStartOrQuit = scanner.next();
            if (commandStartOrQuit.equals("q")) {
                break;
            }
            if(commandStartOrQuit.equals("l")){
                System.out.println("normal game mode Ranking List:");
                game.readScore("normalGameMode");
                System.out.println("random game mode Ranking list:");
                game.readScore("randomGameMode");
                System.out.println("cross game mode Ranking list:");
                game.readScore("crossGameMode");
            }
              if (commandStartOrQuit.equals("s") || commandStartOrQuit.equals("r")) {
                  System.out.println("请输入你的昵称：");
                  String name = scanner.next();
                if (commandStartOrQuit.equals("s")) {
                    while(true) {
                        System.out.println("你要读入此昵称下的存档吗？是,请输入y，否,请输入n");
                        String yesOrNo = scanner.next();

                        if (yesOrNo.equals("y")) {
                            if (game.hasRecord(name, "normalGameMode")) {
                                game.initialRecord(height, width);
                                game.readRecord(name, "normalGameMode");
                                game.initialNextNextBlock();
                                game.print();
                                break;
                            } else {
                                continue;
                            }
                        } else {
                            game.initial(height, width);
                            game.nextNextBlock();
                            game.nextBlock();
                            game.nextNextBlock();
                            game.print();
                            System.out.println();
                            break;
                        }
                    }
                } else if (commandStartOrQuit.equals("r")) {

                    while (true) {
                        System.out.println("你要读入此昵称下的存档吗？是,请输入y，否,请输入n");
                        String yesOrNo = scanner.next();
                        if (yesOrNo.equals("y")) {
                            if (game.hasRecord(name, "randomGameMode")) {
                                game.initialRecord(height, width);
                                game.readRecord(name, "randomGameMode");
                                game.readRandomBlock(name);
                                game.initialNextNextBlock();
                                game.randomGame = true;
                                game.print();
                                break;
                            } else {
                                continue;
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
                            break;
                        }
                    }
                }
                while (true) {
                    if (game.isOver()) {
                        game.randomGame = false;

                        if(commandStartOrQuit.equals("s")) {
                            game.writeScore(name,"normalGameMode");
                            game.score = 0;
                            System.out.println("Game over!");
                            break;
                        }
                        if(commandStartOrQuit.equals("r")){
                            game.writeScore(name,"randomGameMode");
                            game.score = 0;
                            System.out.println("Game over!");
                            break;
                        }
                    }
                    String command = scanner.next();
                    if (command.equals("q")) {
                        game.randomGame = false;

                        if (commandStartOrQuit.equals("s")) {
                            game.writeRecord(name,"normalGameMode");
                        }

                        if (commandStartOrQuit.equals("r")) {
                            game.writeRecord(name,"randomGameMode");
                            game.writeRandomBlock(name);
                        }
                        game.score = 0;
                        break;
                    }
                    game.executeCommand(command);
                    game.print();
                }
            } else if (commandStartOrQuit.equals("c")) {
                  System.out.println("请输入你的昵称：");
                  String name = scanner.next();

                while (true) {
                    System.out.println("你要读入此昵称下的存档吗？是,请输入y，否,请输入n");
                    String yesOrNo = scanner.next();
                    if (yesOrNo.equals("y")) {
                        if (game.hasRecord(name, "crossGameMode")) {
                            game.initialRecord(height, width);
                            game.readRecord(name, "crossGameMode");
                            game.initialNextNextBlock();
                            game.printCross();
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        game.initial(height, width);
                        game.nextNextBlock();
                        game.nextBlock();
                        game.nextNextBlock();
                        game.printCross();
                        System.out.println();
                        break;
                    }
                }
                while (true) {
                    if (game.isOverCross()) {
                        game.writeScore(name,"crossGameMode");
                        game.score = 0;
                        System.out.println("Game over!");
                        break;
                    }
                    String command = scanner.next();
                    if (command.equals("q")) {
                        game.writeRecord(name,"crossGameMode");
                        game.score = 0;
                        break;
                    }
                    game.executeCommandCross(command);
                    game.printCross();


                }
            } else {
                continue;
            }
        }
    }
}
