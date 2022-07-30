package main.java.controllers;

import main.java.models.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputInnings {
    private static final int BALLS_IN_OVER = 6;

    // Initializes players scores map
    private static HashMap<UUID, Player> initPlayerMap(Team team) {
        HashMap<UUID, Player> players = new HashMap<>();
        for (Player player : team.getPlayers()) players.put(player.getPlayerId(), player);
        return players;
    }

    // Processes ball with `RegEx` pattern matcher
    // Splits `ballType` and `runs`
    private static Ball processBall(String ballData) {
        String regex = "([a-zA-Z]*)([0-6]*)";
        Pattern reg = Pattern.compile(regex);
        Matcher matcher = reg.matcher(ballData);

        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));

        if (matcher.group(0).equalsIgnoreCase("w")) {
            return new Ball(BallType.WICKET_BALL, Integer.parseInt(matcher.group(1)));
        } else if (matcher.group(0).equalsIgnoreCase("wd")) {
            return new Ball(BallType.WIDE_BALL, Integer.parseInt(matcher.group(1)));
        } else if (matcher.group(0).equalsIgnoreCase("nb")) {
            return new Ball(BallType.NO_BALL, Integer.parseInt(matcher.group(1)));
        } else return new Ball(BallType.SIMPLE_BALL, Integer.parseInt(matcher.group(1)));
    }

    public static Inning inputInnings(Team team, Player strikePlayer, Player nonStrikePlayer, int noOfOvers,
                                      int prevInningScore) {
        // Sets inning and `playersOnPitch`(strike/nonStrike) metadata
        Scanner scn = new Scanner(System.in);
        Inning inning = new Inning();
        HashMap<UUID, Player> scoreBoard = initPlayerMap(team);
        int wickets = 0; // TODO: Improve this
        int inningScore = 0;
        int flag = 0; // TODO: Improve this

        List<Player> playersOnPitch = new ArrayList<>();
        playersOnPitch.add(strikePlayer);
        playersOnPitch.add(nonStrikePlayer);

        // Takes overs inputs and handles all-out or wicket-win situation
        // Handles different types of balls
        for (int j = 0; j < noOfOvers && flag == 0; j++) { // TODO: Improve this
            List<Ball> balls = new ArrayList<>();
            int overNumber = j + 1; // TODO: Improve this
            System.out.println("Over " + overNumber + ":");

            for (int k = 0; k < BALLS_IN_OVER; k++) {
                String ballInfo = scn.nextLine();
                Ball ball = processBall(ballInfo);

                if (Objects.equals(ball.getType(), BallType.WICKET_BALL)) wickets++;
                else if (Objects.equals(ball.getType(), BallType.WIDE_BALL)) k--;
                else if (Objects.equals(ball.getType(), BallType.NO_BALL)) k--;

                inningScore += ball.getRuns();
                balls.add(ball);

                if (wickets == team.getPlayers().size() - 1
                    || (prevInningScore != -1 && inningScore > prevInningScore)) {
                    flag = 1;
                    break;
                }
            }

            // Sets balls to over and processes over data, and finally prepares scorecard
            Over over = new Over();
            over.setBalls(balls);
            playersOnPitch = ProcessOver.processOver(team, over, inning, scoreBoard, playersOnPitch.get(0),
                    playersOnPitch.get(1));

            // Prints scorecard after each over
            System.out.println("Scorecard for " + team.getName() + ":");
            System.out.println("Player Name  Score  4s  6s  Balls  Strike Rate");
            for (Player player : team.getPlayers()) {
                String name = scoreBoard.get(player.getPlayerId()).getName();
                int score = scoreBoard.get(player.getPlayerId()).getScore();
                int fours = scoreBoard.get(player.getPlayerId()).getFours();
                int sixes = scoreBoard.get(player.getPlayerId()).getSixes();
                int ballsFaced = scoreBoard.get(player.getPlayerId()).getBallsFaced();
                if (player.isOnPitch()) System.out.println(name + "* " + score + " " + fours + " " + sixes + " " +
                        ballsFaced + " ");
                else if (score > 0) System.out.println(name + " " + score + " " + fours + " " + sixes + " " +
                        ballsFaced + " ");
                else System.out.println(name + " " + score + " " + fours + " " + sixes + " " + ballsFaced + " ");

                if (ballsFaced > 0) System.out.println((score * 100) / ballsFaced);
                else System.out.println("0");
            }

            if (inning.isAllOut()) System.out.println("Total: " + inning.getInningScore());
            else System.out.println("Total: " + inning.getInningScore() + "/" + inning.getWickets());

            int overs = inning.getBalls() / BALLS_IN_OVER;
            int ballsLeft = inning.getBalls() % BALLS_IN_OVER;
            System.out.println("Overs: " + overs + "." + ballsLeft);
        }

        return inning;
    }
}
