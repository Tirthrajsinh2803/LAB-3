package lab3.game;

public class Board {
    private final char[][] board; // 3x3 board to store 'X', 'O', or ' ' (empty)
    private static final int SIZE = 3;

    // Constructor to initialize an empty board
    public Board() {
        board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Place a mark (X or O) at a specific position
    public boolean placeMark(Position pos, char mark) {
        if (isPositionValid(pos) && isPositionEmpty(pos)) {
            board[pos.row().ordinal()][pos.column().ordinal()] = mark;
            return true;
        }
        return false;
    }

    // Check if a position is empty
    private boolean isPositionEmpty(Position pos) {
        return board[pos.row().ordinal()][pos.column().ordinal()] == ' ';
    }

    // Check if a position is valid
    private boolean isPositionValid(Position pos) {
        return pos.row() != null && pos.column() != null;
    }

    // Check if the board is full (draw)
    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a player has won
    public boolean hasWon(char mark) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) return true; // Rows
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) return true; // Columns
        }
        // Check diagonals
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) return true; // Diagonal 1
        return board[0][2] == mark && board[1][1] == mark && board[2][0] == mark; // Diagonal 2
    }

    // Override toString to display the board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(board[i][j]);
                if (j < SIZE - 1) sb.append(" | ");
            }
            if (i < SIZE - 1) sb.append("\n---------\n");
        }
        return sb.toString();
    }
}