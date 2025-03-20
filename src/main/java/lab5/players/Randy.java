package lab5.players;

import lab5.game.Board;
import lab5.game.Player;
import lab5.game.Position;

import java.util.List;
import java.util.Random;

/**
 * A simple computer player that picks random positions.
 */
public class Randy extends Player {

    private final Random random = new Random();

    /**
     * Constructor for Randy.
     * @param name The name of the player.
     */
    public Randy(String name) throws InterruptedException {
        super(name);
        wait(Long.parseLong(name));
    }

    /**
     * Picks a random position from the available empty cells.
     * @param board The current state of the game board.
     * @return A random position.
     */
    @Override
    public Position pickNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }
}