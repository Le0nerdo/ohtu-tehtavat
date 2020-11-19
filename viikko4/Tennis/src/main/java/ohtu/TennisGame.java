package ohtu;

public class TennisGame {
    
    private static final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score += 1;
        else if (playerName.equals(player2Name))
            player2Score += 1;
    }

    private String evenScore() {
        if (player1Score > 3) return "Deuce";
        return TennisGame.scores[player1Score] + "-All";
    }

    public String getScore() {
        if (player1Score==player2Score) return evenScore();

        if (player1Score>=4 || player2Score>=4){
            int differenceInScore = player1Score-player2Score;
            if (differenceInScore==1) return "Advantage " + player1Name;
            else if (differenceInScore ==-1) return "Advantage " + player2Name;
            else if (differenceInScore>=2) return "Win for " + player1Name;
            else return "Win for " + player2Name;
        }

        return TennisGame.scores[player1Score] + "-" + TennisGame.scores[player2Score];
    }
}