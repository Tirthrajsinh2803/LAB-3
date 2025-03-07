package lab4.game;

import static lab4.game.Board.PlayerToken.O;
import static lab4.game.Board.PlayerToken.X;

public class Board {

    public enum PlayerToken { X, O }
    public enum Status { InProgress, Draw, XWins, OWins }

    private final PlayerToken[][] board = new PlayerToken[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
    }

    public Status getStatus() {
        switch (this.getWinner()) {
            case X: return Status.XWins;
            case O: return Status.OWins;
            case null: {
                if (this.isFull()) {
                    return Status.Draw;
                } else {
                    return Status.InProgress;
                }
            }
        }
    }

    private int rowIdx(Position pos) {
        return switch (pos.row()) {
            case Top -> 0;
            case Middle -> 1;
            case Bottom -> 2;
        };
    }

    private int colIdx(Position pos) {
        return switch (pos.col()) {
            case Left -> 0;
            case Middle -> 1;
            case Right -> 2;
        };
    }

    private PlayerToken getWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        // Check diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return null;
    }

    public boolean isFull() {
        for (var row : board) {
            for (var cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isOccupiedAt(Position pos) {
        return board[rowIdx(pos)][colIdx(pos)] != null;
    }

    public void placeX(Position pos) {
        board[rowIdx(pos)][colIdx(pos)] = X;
    }

    public void placeO(Position pos) {
        board[rowIdx(pos)][colIdx(pos)] = O;
    }

    @Override
    public String toString() {
        var boardString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardString.append(board[i][j] == null ? '.' : board[i][j]);
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}