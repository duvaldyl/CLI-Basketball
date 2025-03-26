import java.util.Scanner;

class Driver {
    public static void processMatchup(int rosterIndex, Player homePlayer, Player awayPlayer) {
        System.out.println("PLAYER: " + rosterIndex);
        System.out.printf("%-10s %-15s %-15s%n", "STAT", "HOME", "AWAY");
        System.out.println("---------------------------------");
        System.out.printf("%-10s %-15f %-15f%n", "3PS", homePlayer.getThree(), awayPlayer.getThree());
        System.out.printf("%-10s %-15f %-15f%n", "2PS", homePlayer.getTwo(), awayPlayer.getTwo());
        System.out.printf("%-10s %-15f %-15f%n", "LAY", homePlayer.getLayup(), awayPlayer.getLayup());

        System.out.printf("%-10s %-15f %-15f%n", "PAS", homePlayer.getShot(), awayPlayer.getShot());
        System.out.printf("%-10s %-15f %-15f%n", "DRD", homePlayer.getDrive(), awayPlayer.getDrive());
        System.out.printf("%-10s %-15f %-15f%n", "PBL", homePlayer.getPass(), awayPlayer.getPass());
        System.out.println("---------------------------------");
    }

    public static void processPossesion(boolean homePossesion, int homePoints, int awayPoints) {
        String output = "";
        if(homePossesion) {
            output = output.concat("[HOME: " + homePoints + "] AWAY: " + awayPoints);
        } else {
            output = output.concat("HOME: " + homePoints + " [AWAY: " + awayPoints + "]");
        }

        System.out.println(output);
    }

    public static void processStatus(int status) {
        if(status == 0) {
            System.out.println("GOOD");
        } else if(status == 1) {
            System.out.println("BLOCKED");
        } else {
            System.out.println("MISSED");
        }
    }

    public static void processOption(int option) {
        String output = "Attempting...";
        if(option == 3) {
            output = output.concat("3pt Shot");
        } else if(option == 2) {
            output = output.concat("2pt Shot");
        } else if(option == 1) {
            output = output.concat("Layup");
        } else if(option == 0) {
            output = output.concat("Pass");
        }

        System.out.println(output);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();
        
        while(game.getClock() > 0) {
            System.out.println("------------------------------");
            System.out.println("GAME CLOCK: " + game.getClock());

            processPossesion(game.getPossesion(), game.getHomePoints(), game.getAwayPoints());
            System.out.println("PLAYER: " + game.getCurrRosterIndex() + " CONDITION:" + game.getCurrPlayer().getCondition());
            // processMatchup(game.getCurrRosterIndex(), game.getCurrHomePlayer(), game.getCurrAwayPlayer());

            if(game.getPossesion()) {
                System.out.println("(0) Pass");
                System.out.printf("%s %.2f%%%n", "(1) Layup ->", game.calculateLayProb() * 100);
                System.out.printf("%s %.2f%%%n", "(2) Shoot 2-pointer ->", game.calculate2PSProb() * 100);
                System.out.printf("%s %.2f%%%n", "(3) Shoot 3-pointer ->", game.calculate3PSProb() * 100);
                int option = sc.nextInt();
                processOption(option);

                if(option == 3) {
                    int status = game.shootThree();
                    processStatus(status);
                    game.changePossesion();
                } else if(option == 2) {
                    int status = game.shootTwo();
                    processStatus(status);
                    game.changePossesion();
                } else if(option == 1) {
                    int status = game.shootLayup();
                    processStatus(status);
                    game.changePossesion();
                } else if(option == 0) {
                    System.out.println("Select Number of Player to Pass To: ");
                    double[] p = game.calculatePassProb();

                    for(int i = 0; i < 5; i++) {
                        if(p[i] != 1.0) {
                            System.out.printf("%s %s %.2f%%%n", "Player", i + " ->", p[i] * 100);
                        } else {
                            System.out.printf("(" + i + ") -> to cancel\n");
                        }
                    }
                        
                    int passIndex = sc.nextInt();
                    int status = game.pass(passIndex);
                    processStatus(status);
                    if(status == 2) {
                        game.changePossesion();
                    }
                }

            try {
                Thread.sleep(2000);
            } catch(Exception e) {
                System.out.println("UNKNOWN ERROR");
            }

            } else {
                int option = -1;
                int status = -1;
                if(game.getAwayPoints() < game.getHomePoints()) {
                    if(game.getHomePoints() - game.getAwayPoints() > 6) {
                        option = 3;
                        status = game.shootThree();
                    } else {
                        option = 2;
                        status = game.shootTwo();
                    }
                } else {
                    option = 1;
                    status = game.shootLayup();
                }

                processOption(option);
                processStatus(status);
                game.changePossesion();
            }

            try {
                Thread.sleep(2000);
            } catch(Exception e) {
                System.out.println("UNKNOWN ERROR");
            }
        }

        System.out.println(game.getHomePoints());
        System.out.println(game.getAwayPoints());
    }
}
