package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Over {
    private List<Ball> balls;

    public Over() {
        this.balls = new ArrayList<>();
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }
}
