package main.java.controllers;

import main.java.models.Inning;

import java.util.Scanner;

public class InputMatchMetadata {
    public static void inputMatchMetadata() {
        // Takes match inputs
        Scanner sc = new Scanner(System.in);

        System.out.print("No. of players for each team: ");
        int noOfPlayers = sc.nextInt();

        System.out.println("No. of overs: ");
        int noOfOvers = sc.nextInt();

        // Takes innings inputs
        String teamOneName = "Team 1";
        Inning firstInning = InputPlayers.inputPlayers(noOfPlayers, teamOneName, noOfOvers, -1);

        String teamTwoName = "Team 2";
        Inning secondInning = InputPlayers.inputPlayers(noOfPlayers, teamTwoName, noOfOvers, firstInning.getInningScore());

        // Prints match verdict
        if (firstInning.getInningScore() > secondInning.getInningScore()) {
            int diff = firstInning.getInningScore() - secondInning.getInningScore();
            System.out.println(teamOneName + " won the match by " + diff + " runs");
        } else if (secondInning.getInningScore() > firstInning.getInningScore()) {
            int diff = noOfPlayers - secondInning.getWickets();
            System.out.println(teamTwoName + " won the match by " + diff + " wickets");
        } else System.out.println("Match drawn");
    }
}
