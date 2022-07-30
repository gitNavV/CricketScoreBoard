package main.java.models;

public class Inning {
    private int inningScore;
    private int wickets;
    private int balls;
    private boolean allOut;
    // TODO: We can have teamName and scoreBoard

    public Inning() {
        this.inningScore = 0;
        this.wickets = 0;
        this.balls = 0;
    }

    public int getInningScore() {
        return inningScore;
    }

    public void setInningScore(int inningScore) {
        this.inningScore = inningScore;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public boolean isAllOut() {
        return allOut;
    }

    public void setAllOut(boolean allOut) {
        this.allOut = allOut;
    }
}
