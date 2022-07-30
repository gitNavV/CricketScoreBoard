package main.java.models;

import java.util.UUID;

public class Player {
    private final UUID playerId;
    private final String name;
    private int score;
    private int fours;
    private int sixes;
    private int ballsFaced;
    private boolean onPitch;

    public Player(UUID playerId, String name, boolean onPitch) {
        this.playerId = playerId;
        this.name = name;
        this.score = 0;
        this.fours = 0;
        this.sixes = 0;
        this.ballsFaced = 0;
        this.onPitch = onPitch;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public boolean isOnPitch() {
        return onPitch;
    }

    public void setOnPitch(boolean onPitch) {
        this.onPitch = onPitch;
    }
}
