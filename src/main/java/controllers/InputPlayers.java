package main.java.controllers;

import main.java.models.Inning;
import main.java.models.Player;
import main.java.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class InputPlayers {
    public static Inning inputPlayers(int noOfPlayers, String teamName, int noOfOvers, int prevInningScore) {
        // Takes players order input
        Scanner scan = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.println("Batting Order for " + teamName + ":");

        // Sets strike, `nonStrike`, and other players
        Player strikePlayer = new Player(UUID.randomUUID(), "", true);
        Player nonStrikePlayer = new Player(UUID.randomUUID(), "", true);
        for (int i = 0; i < noOfPlayers; i++) {
            String playerName = scan.nextLine();
            UUID playerID = UUID.randomUUID();
            Player player;

            if (i == 0) { // TODO: Improve this
                player = new Player(playerID, playerName, true);
                strikePlayer = player;
            }
            else if (i == 1) { // TODO: Improve this
                player = new Player(playerID, playerName, true);
                nonStrikePlayer = player;
            }
            else player = new Player(playerID, playerName, false);
            players.add(player);
        }

        // Takes overs data inputs and returns inning
        Team team = new Team(teamName, players);
        return InputInnings.inputInnings(team, strikePlayer, nonStrikePlayer, noOfOvers, prevInningScore);
    }
}
