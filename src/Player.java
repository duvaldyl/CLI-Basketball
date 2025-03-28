import java.util.Random;

public class Player {
    // Offensive Ratings
    private double three;
    private double two;
    private double layup;
    private double pass;

    //Defensive Ratings
    private double drive;
    private double shot;
    private double steal;

    //Physical
    private double condition;


    public Player(double three, double two, double layup, double drive, double shot, double pass) {
        this.three = three;
        this.two = two;
        this.layup = layup;
        this.drive = drive;
        this.shot = shot;
        this.pass = pass;
        condition = 1.0;
    }

    public Player() {
        Random r = new Random();
        this.three = 0.2 + (r.nextDouble() * (0.6 - 0.5));
        this.two = 0.35 + (r.nextDouble() * (0.75 - 0.35));
        this.layup = 0.5 + (r.nextDouble() * (0.9 - 0.5));
        this.pass = 0.65 + (r.nextDouble() * (1.0 - 0.65));
        this.drive = 0.1 + (r.nextDouble() * (0.65 - 0.1));
        this.shot = 0.05 + (r.nextDouble() * (0.5 - 0.05));
        this.steal = 0.05 + (r.nextDouble() * (0.5 - 0.05)); 
        condition = 1.0;
    }

    public double getThree() {
        return three;
    }

    public double getTwo() {
        return two;
    }

    public double getLayup() {
        return layup;
    }

    public double getDrive() {
        return drive;
    }

    public double getShot() {
        return shot;
    }

    public double getPass() {
        return pass;
    }

    public double getSteal() {
        return steal;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }

    public void setPass(double pass) {
        this.pass = pass;
    }

    public boolean pass() {
        Random r = new Random();
        double d = r.nextDouble();
        condition -= 0.01;

        if (d < pass) {
            return true;
        } else {
            return false;
        }
    }
        
    public boolean shootTwo() {
        Random r = new Random();
        double d = r.nextDouble();
        condition -= 0.01;

        if (d < two) {
            return true;
        } else {
            return false;
        }
    }

    public boolean shootThree() {
        Random r = new Random();
        double d = r.nextDouble();
        condition -= 0.01;

        if (d < three) {
            return true;
        } else {
            return false;
        }
    }

    public boolean shootLayup() {
        Random r = new Random();
        double d = r.nextDouble();
        condition -= 0.05;

        if (d < layup) {
            return true;
        } else {
            return false;
        }
    }

    public boolean blockShot() {
        Random r = new Random();
        double d = r.nextDouble();

        if (d < shot) {
            return true;
        } else {
            return false;
        }
    }

    public boolean blockDrive() {
        Random r = new Random();
        double d = r.nextDouble();

        if (d < drive) {
            return true;
        } else {
            return false;
        }
    }

    public boolean blockPass() {
        Random r = new Random();
        double d = r.nextDouble();

        if (d < steal) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String output = "";
        output = output.concat("3PT: " + three + " ");
        output = output.concat("JMP: " + two + " ");
        output = output.concat("LAY: " + layup + " ");
        output = output.concat("DRV: " + drive + " ");
        output = output.concat("BLK: " + shot+ " ");
        output = output.concat("PAD: " + pass + " ");

        return output;
    }
}
