# CricketScoreBoard

#### A scalable/maintainable/extensible/modular/object oriented implementation of Cricket Batting Scoreboard in Java

A cricket scorecard that shows the score for a team along with score of each player.

Given the number of players in each team, the number of overs, their batting order
as input, and overs data (ball by ball) with the runs scored on that ball (could be
wide, no ball or a wicket as well).

The project prints individual scores, number of balls faced, number of 4s, number of
6s for all the players from the batting side at the end of every over. It also print
total score, total wickets.

Essentially, it keeps a track of all the players, strike changes (at the end of the
over or after taking singles or 3s) and maintains their scores, also keeps track of
extra bowls that are being bowled (like wides or no balls).

It also prints which team won the match at the end and type of win (wickets/runs/draw).

```
These are the bare minimum features which are there in the project. The plan is to add
some more features, like maintaining bowlers record (total overs bowled, runs conceded,
wickets taken, maiden overs, dot balls, economy, etc.). Total team extras, batsman
strike rates, etc. can be added too. But these are "good to have" features, have currently
tried to complete the bare minimum only.
```

The code is readable, maintainable and object oriented. It is written in a modular and
extensible way, to add new features if needed.

<img width="426" alt="Screenshot 2022-07-30 at 8 54 58 PM" src="https://user-images.githubusercontent.com/6997802/181922447-d75e2a73-7798-430f-97d9-b41014080433.png">
<img width="288" alt="Screenshot 2022-07-30 at 8 55 57 PM" src="https://user-images.githubusercontent.com/6997802/181922621-0e020a16-b42d-479d-9913-5bbb110d26cc.png">
<img width="291" alt="Screenshot 2022-07-30 at 8 56 09 PM" src="https://user-images.githubusercontent.com/6997802/181922720-5a0aa9b6-2925-478b-a195-a0e05896b910.png">
