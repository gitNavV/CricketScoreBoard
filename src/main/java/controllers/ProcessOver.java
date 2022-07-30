package main.java.controllers;

import main.java.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.max;

public class ProcessOver {
    private static final int FOUR_RUNS = 4;
    private static final int SIX_RUNS = 6;

    // Swaps strike and `nonStrike` players, and updates `scoreBoard`
    private static void swapStrikeNonStrikePlayer(Player strikePlayer, Player nonStrikePlayer,
                                            HashMap<UUID, Player> scoreBoard) {
        Player tempPlayer = nonStrikePlayer;
        nonStrikePlayer = strikePlayer;
        strikePlayer = tempPlayer;

        scoreBoard.put(strikePlayer.getPlayerId(), strikePlayer);
        scoreBoard.put(nonStrikePlayer.getPlayerId(), nonStrikePlayer);
    }
    private static void addBoundaries(Ball ball, Player strikePlayer, HashMap<UUID, Player> scoreBoard) {
        if (ball.getRuns() == FOUR_RUNS) {
            int fours = strikePlayer.getFours();
            fours++;
            strikePlayer.setFours(fours);
        } else if (ball.getRuns() == SIX_RUNS) {
            int sixes = strikePlayer.getSixes();
            sixes++;
            strikePlayer.setSixes(sixes);
        }
        scoreBoard.put(strikePlayer.getPlayerId(), strikePlayer);
    }
    private static void incrementBallsAndRuns(Player strikePlayer, Inning inning, Ball ball) {
        int ballsFaced = strikePlayer.getBallsFaced();
        ballsFaced++;
        strikePlayer.setBallsFaced(ballsFaced);

        int balls = inning.getBalls();
        balls++;
        inning.setBalls(balls);

        int runs = strikePlayer.getScore();
        runs += ball.getRuns();
        strikePlayer.setScore(runs);

        int inningScore = inning.getInningScore();
        inningScore += ball.getRuns();
        inning.setInningScore(inningScore);
    }

    private static int declareOut(Player strikePlayer, HashMap<UUID, Player> scoreBoard, Inning inning) {
        strikePlayer.setOnPitch(false);
        scoreBoard.put(strikePlayer.getPlayerId(), strikePlayer);

        int wickets = inning.getWickets();
        wickets++;
        inning.setWickets(wickets);

        return wickets;
    }
    private static boolean ifNotAllOutHandlePlayerSpawning(int wickets, Team team, Inning inning, Player strikePlayer,
                                                     Player nonStrikePlayer, HashMap<UUID, Player> scoreBoard) {
        if (wickets == team.getPlayers().size() - 1) {
            inning.setAllOut(true);
            return true;
        } else {
            // Handles spawning of new player to the pitch
            int nonStrikeIndex = team.getPlayers().indexOf(nonStrikePlayer);
            int strikeIndex = team.getPlayers().indexOf(strikePlayer);
            int nextIndex = max(nonStrikeIndex, strikeIndex) + 1;

            strikePlayer = team.getPlayers().get(nextIndex);
            strikePlayer.setOnPitch(true);
            scoreBoard.put(strikePlayer.getPlayerId(), strikePlayer);
        }
        return false;
    }
    public static List<Player> processOver(Team team, Over over, Inning inning, HashMap<UUID,
            Player> scoreBoard, Player strikePlayer, Player nonStrikePlayer) {
        for (int m = 0; m < over.getBalls().size(); m++) {
            Ball ball = over.getBalls().get(m);
            if (ball.getType() == BallType.WICKET_BALL) {
                incrementBallsAndRuns(strikePlayer, inning, ball);
                if (ball.getRuns() % 2 != 0) swapStrikeNonStrikePlayer(strikePlayer, nonStrikePlayer, scoreBoard);

                int wickets = declareOut(strikePlayer, scoreBoard, inning);
                if (ifNotAllOutHandlePlayerSpawning(wickets, team, inning, strikePlayer, nonStrikePlayer, scoreBoard))
                    return new ArrayList<>();
            } else if (ball.getType() == BallType.NO_BALL || ball.getType() == BallType.WIDE_BALL) {
                int inningScore = inning.getInningScore();
                inning.setInningScore(inningScore + ball.getRuns() + 1);

                if (ball.getRuns() % 2 == 0) addBoundaries(ball, strikePlayer, scoreBoard);
                else swapStrikeNonStrikePlayer(strikePlayer, nonStrikePlayer, scoreBoard);
            } else if (ball.getType() == BallType.SIMPLE_BALL) {
                incrementBallsAndRuns(strikePlayer, inning, ball);

                if (ball.getRuns() % 2 == 0) addBoundaries(ball, strikePlayer, scoreBoard);
                else swapStrikeNonStrikePlayer(strikePlayer, nonStrikePlayer, scoreBoard);
            }
        }

        swapStrikeNonStrikePlayer(strikePlayer, nonStrikePlayer, scoreBoard);

        List<Player> playersOnPitch = new ArrayList<>();
        playersOnPitch.add(strikePlayer);
        playersOnPitch.add(nonStrikePlayer);
        return playersOnPitch;
    }
}
