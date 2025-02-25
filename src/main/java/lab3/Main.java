package lab3;

import lab3.game.Board;
import lab3.game.Position;
import lab3.ui.Console;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        char currentPlayer = 'X';

        while (true) {
            // Display the board
            Console.displayBoard(board);

            // Check if the game is over
            if (board.hasWon('X')) {
                System.out.println("Player X wins!");
                break;
            } else if (board.hasWon('O')) {
                System.out.println("Player O wins!");
                break;
            } else if (board.isFull()) {
                System.out.println("It's a draw!");
                break;
            }

            // Get the next move from the player
            System.out.println("Player " + currentPlayer + "'s turn:");
            Position pos = Console.getNextMove();

            // Update the game state
            if (board.placeMark(pos, currentPlayer)) {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch players
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}