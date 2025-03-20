package lab5.game;

public class TurnRecord {
    private Player whoseTurn;
    private PlayerToken token;
    private Position positionPlayed;
    private Board newBoardState;

    public TurnRecord(Player whoseTurn, Position positionPlayed, Board newBoardState) {
        this.whoseTurn = whoseTurn;
        this.token = whoseTurn.getToken();
        this.positionPlayed = positionPlayed;
        this.newBoardState = newBoardState;
    }

    public Player whoseTurn() {
        return whoseTurn;
    }

    public PlayerToken token() {
        return token;
    }

    public Position positionPlayed() {
        return positionPlayed;
    }

    public Board newBoardState() {
        return newBoardState;
    }
}