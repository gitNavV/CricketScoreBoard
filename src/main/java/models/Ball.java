package main.java.models;

public class Ball {
    private final BallType type;
    private final int runs;

    public Ball(BallType type, int runs) {
        this.type = type;
        this.runs = runs;
    }

    public BallType getType() {
        return type;
    }

    public int getRuns() {
        return runs;
    }
}
