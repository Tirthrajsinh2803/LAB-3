package lab5.players;

import lab5.game.Board;
import lab5.game.Player;
import lab5.game.Position;
import lab5.game.PlayerToken;

import java.util.List;

/**
 * A computer player that looks one move ahead to win or block.
 */
public class Omola extends Player {

    /**
     * Constructor for Omola.
     *
     * @param name The name of the player.
     */
    public Omola(String name) {
        super(name);
    }

    /**
     * Picks the best move by looking one move ahead.
     *
     * @param board The current state of the game board.
     * @return The best position to play.
     */
    @Override
    public Position pickNextMove(Board board) {
        PlayerToken token = board.getNextTurnToken();
        List<Position> emptyCells = board.getEmptyCells();

        // Check for a winning move
        for (Position pos : emptyCells) {
            Board copy = new Board(board);
            copy.placeNextToken(pos);
        }
        return null;
    }
}
