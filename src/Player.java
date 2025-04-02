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

        this.three = 0.2 + (r.nextDouble() * (0.6 - 0.2));
        this.two = 0.35 + (r.nextDouble() * (0.75 - 0.35));
        this.layup = 1.0;
        this.pass = 0.8 + (r.nextDouble() * (1.0 - 0.8));

        this.drive = 0.1 + (r.nextDouble() * (0.5 - 0.1));
        this.shot = 0.05 + (r.nextDouble() * (0.5 - 0.05));
        this.steal = 0.00 + (r.nextDouble() * (0.0 - 0.00)); 

        this.condition = 1.0;
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

    public double getAdjustedThree() {
        return condition * three;
    }
    
    public double getAdjustedTwo() {
        return condition * two;
    }

    public double getAdjustedLayup() {
        return condition * layup;
    }

    public double getAdjustedDrive() {
        return condition * drive;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }

    public void lowerCondition(double change) {
        this.condition -= change;
    }

    public void setPass(double pass) {
        this.pass = pass;
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
