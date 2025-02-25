package lab3.ui;

import lab3.game.Position;
import lab3.game.Row;
import lab3.game.Column;

import java.util.Scanner;

public class Console {

    // Display the board
    public static void displayBoard(lab3.game.Board board) {
        System.out.println(board);
    }

    // Get the next move from the player
    public static Position getNextMove() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your move (e.g., '1 2' or 't r'): ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split("\\s+"); // Split input by whitespace

            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter two values (row and column).");
                continue;
            }

            Row row = Row.parse(parts[0]);
            Column col = Column.parse(parts[1]);

            if (row == null || col == null) {
                System.out.println("Invalid row or column. Please try again.");
                continue;
            }

            return new Position(row, col);
        }
    }
}