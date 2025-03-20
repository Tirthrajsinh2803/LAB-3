package lab5.ui;

import lab5.game.Player;
import lab5.game.PlayerToken;

public class Console {

    public Player promptForPlayer(PlayerToken token) {
        // Prompt the user for a player name and create a Player object
        System.out.println("Enter name for player " + token + ":");
        String name = System.console().readLine();
        return new Player(name, token);
    }
}