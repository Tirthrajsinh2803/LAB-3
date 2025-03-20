package lab5.game;

/**
 * Abstract base class for all players in the TicTacToe game.
 */
public abstract class Player {
    private String name = "";
    private PlayerToken token;

    /**
     * Constructor for Player.
     */
    public Player(String name) {
        this.name = this.name;
    }

    /**
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method to pick the next move.
     * @param board The current state of the game board.
     * @return The position where the player wants to place their token.
     */
    public abstract Position pickNextMove(Board board);

    public PlayerToken getToken() {
        return token;
    }
}