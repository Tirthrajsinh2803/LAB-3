package lab5;

import lab5.game.TicTacToeGame;
import lab5.game.Player;
import lab5.game.PlayerToken;
import lab5.game.Board;
import lab5.game.Position;
import lab5.game.TurnRecord;
import lab5.players.HumanPlayer;
import lab5.ui.Console;

import static lab5.game.PlayerToken.O;
import static lab5.game.PlayerToken.X;

public class Main {

    private static Console console;

    public static void main(String[] args) {

        // Initialize the console for user interaction
        console = new Console();

        console.println("""
                Welcome to Tic Tac Toe!
                Players can be human or computer.  When prompted for player names use one of the following:
                - To play as a human, just enter a name
                - To have the player played by the computer enter @ followed by one of the following names:
                  - TODO: Add the names of the computer players you have implemented here!
                """);

        // Prompt for player 1 (X) and player 2 (O)
        var player1 = console.promptForPlayer(X); // Pass PlayerToken.X directly
        var player2 = console.promptForPlayer(O); // Pass PlayerToken.O directly

        // Initialize the game with the two players
        var game = new TicTacToeGame((HumanPlayer) player1, player2);

        // Main game loop
        while (true) {

            // Advance the game based on the player's selected move, and get the results
            var turnRecord = game.doNextTurn();

            // Display the results of the turn
            console.println("%s plays %s at %s %s".formatted(
                    turnRecord.whoseTurn().getName(),
                    turnRecord.token(),
                    turnRecord.positionPlayed().row(),
                    turnRecord.positionPlayed().col()
            ));

            // Show the updated board state
            var newBoard = turnRecord.newBoardState();
            console.showBoard(newBoard);

            // Decide what to do next based on the current status of the game
            switch (newBoard.getStatus()) {
                case Draw -> {
                    console.println("It's a draw!");
                    System.exit(0);
                }
                case XWins -> {
                    console.println("X wins! Congratulations, %s!".formatted(player1.getName()));
                    System.exit(0);
                }
                case OWins -> {
                    console.println("O wins! Congratulations, %s!".formatted(player2.getName()));
                    System.exit(0);
                }
                default -> {
                    // Continue the game if no winner or draw
                }
            }
        }
    }
}