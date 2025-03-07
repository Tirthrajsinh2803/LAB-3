package lab4.game;

import static lab4.game.Board.Status.InProgress;

public class TicTacToeGame {

    private final String[] players = new String[2];
    private final Board board = new Board();
    private int turnIdx = 0;

    public TicTacToeGame(String player1, String player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void doNextTurn(Position pos) {
        if (board.isOccupiedAt(pos)) {
            throw new IllegalArgumentException("Position %s is already taken".formatted(pos));
        }

        if (turnIdx == 0) {
            board.placeX(pos);
        } else {
            board.placeO(pos);
        }

        if (board.getStatus() == InProgress) {
            turnIdx = (turnIdx + 1) % 2;
        }
    }

    public Board getBoard() {
        return board;
    }

    public String whoseTurn() {
        return players[turnIdx];
    }
}