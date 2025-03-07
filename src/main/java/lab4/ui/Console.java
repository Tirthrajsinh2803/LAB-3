package lab4.ui;

import lab4.game.*;
import java.util.Scanner;

public class Console {

    // ANSI escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void println(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    public static void printError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static String prompt(String promptMessage) {
        System.out.print(ANSI_GREEN + promptMessage + ANSI_RESET);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showBoard(Board board) {
        System.out.print(board);
    }

    public static Position promptForPosition(String prompt, Board board) {
        var scanner = new Scanner(System.in);
        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.";

        while (true) {
            System.out.print(ANSI_GREEN + prompt + ANSI_RESET);
            var input = scanner.nextLine().trim();

            if (input.length() != 3) {
                printError(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if (parts.length != 2) {
                printError(helpMessage);
                continue;
            }

            try {
                var pos = new Position(Row.from(parts[0]), Col.from(parts[1]));

                if (board.isOccupiedAt(pos)) {
                    printError("That position is already taken.");
                    continue;
                }

                return pos;
            } catch (IllegalArgumentException e) {
                printError(helpMessage);
            }
        }
    }
}