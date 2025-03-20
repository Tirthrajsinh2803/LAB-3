package lab5.players;

import lab5.game.Board;
import lab5.game.Player;
import lab5.game.Position;
import lab5.ui.Console;

/**
 * Represents a human player in the TicTacToe game.
 */
public class HumanPlayer extends Player {

    /**
     * Constructor for HumanPlayer.
     * @param name The name of the human player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Prompts the human player to pick their next move.
     * @param board The current state of the game board.
     * @return The position where the player wants to place their token.
     */
    @Override
    public Position pickNextMove(Board board) {
        return Console.promptForPosition(getName());
    }
}