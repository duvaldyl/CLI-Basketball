import java.util.Random;

public class Game {
    private double clock;
    private int homePoints;
    private int awayPoints;
    private Player[] homePlayers;
    private Player[] awayPlayers;
    private int currRosterIndex;
    private Player currPlayer;
    private Player currDefender;
    private boolean homePossesion;

    public Game() {
        Random r = new Random();
        clock = 99.0;
        homePlayers = new Player[5];
        awayPlayers = new Player[5];

        for(int i = 0; i < 5; i++) {
            homePlayers[i] = new Player();
            awayPlayers[i] = new Player();
        }

        homePossesion = true;
        currRosterIndex = 0;
        currPlayer = homePlayers[currRosterIndex];
        currDefender = awayPlayers[currRosterIndex];
        homePoints = 0;
        awayPoints = 0;
    }

    public void changePossesion() {
        homePossesion = !homePossesion;

        if(homePossesion) {
            currPlayer = homePlayers[currRosterIndex];
            currDefender = awayPlayers[currRosterIndex];
        } else {
            currPlayer = awayPlayers[currRosterIndex];
            currDefender = homePlayers[currRosterIndex];
        }
    }

    public double calculate3PSProb() {
        double p = (currPlayer.getCondition() * currPlayer.getThree()) * (1 - currDefender.getShot());
        return p;
    }

    public double calculate2PSProb() {
        double p = (currPlayer.getCondition() * currPlayer.getTwo()) * (1 - currDefender.getShot());
        return p;
    }

    public double calculateLayProb() {
        double p = (currPlayer.getCondition() * currPlayer.getLayup()) * (1 - currDefender.getDrive());
        return p;
    }

    public double[] calculatePassProb() {
        double[] p = new double[5];
        for(int i = 0; i < 5; i++) {
            if(i == currRosterIndex) {
                p[i] = 1.0;
            } else {
                p[i] = homePlayers[i].getPass() * (1 - awayPlayers[i].getSteal());
            }
        }

        return p;
    }

    public void resetPass() {
        Random r = new Random();
        for(int i = 0; i < 5; i++) {
            double newPass = 0.75 + (r.nextDouble() * (1.0 - 0.75));
            if(homePossesion) {
                homePlayers[i].setPass(newPass);
            } else {
                awayPlayers[i].setPass(newPass);
            }
        }
    }


    public int getHomePoints() {
        return homePoints;
    }

    public int getAwayPoints() {
        return awayPoints;
    }

    public Player[] getHomePlayers() {
        return homePlayers;
    }

    public Player[] getAwayPlayers() {
        return awayPlayers;
    }

    public double getClock() {
        return clock;
    }

    public boolean getPossesion() {
        return homePossesion;
    }

    public int getCurrRosterIndex() {
        return currRosterIndex;
    }

    public Player getCurrHomePlayer() {
        return homePlayers[currRosterIndex];
    }

    public Player getCurrAwayPlayer() {
        return awayPlayers[currRosterIndex]; 
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }
        
    public int shootTwo() {
        Random r = new Random();
        clock -= 1;

        double p = currPlayer.getTwo() * (1 - currDefender.getShot());
        double d = r.nextDouble();

        if(d <= p) {
            if(homePossesion) {
                homePoints += 2;
            } else {
                awayPoints += 2;
            }

            return 0;
        } else if(p < currPlayer.getShot()) {
            return 1;
        } else {
            return 2;
        }
    }

    public int shootThree() {
        Random r = new Random();
        clock -= 1;

        double p = currPlayer.getThree() * (1 - currDefender.getShot());
        double d = r.nextDouble();

        if(d <= p) {
            if(homePossesion) {
                homePoints += 3;
            } else {
                awayPoints += 3;
            }

            return 0;
        } else if(p < currPlayer.getShot()) {
            return 1;
        } else {
            return 2;
        }
    }

    public int shootLayup() {
        Random r = new Random();
        clock -= 3;

        double p = currPlayer.getLayup() * (1 - currDefender.getDrive());
        double d = r.nextDouble();

        if(d <= p) {
            if(homePossesion) {
                homePoints += 2;
            } else {
                awayPoints += 2;
            }

            return 0;
        } else if(p < currPlayer.getLayup()) {
            return 1;
        } else {
            return 2;
        }
    }

    public int pass(int passIndex) {
        if(passIndex == currRosterIndex) {
            return 0;
        }

        Random r = new Random();
        clock -= 1;

        double p = currPlayer.getPass() * (1 - currDefender.getSteal());
        double d = r.nextDouble();

        if(d <= p) {
            changePlayer(passIndex);
            resetPass();
            return 0;
        } else if(p < currPlayer.getSteal()) {
            return 1;
        } else {
            return 2;
        }
    }

    public void changePlayer(int passIndex) {
        if(homePossesion) {
            currPlayer = homePlayers[passIndex];
            currDefender = awayPlayers[passIndex];
        } else {
            currPlayer = awayPlayers[passIndex];
            currDefender = homePlayers[passIndex];
        }
    }
}
