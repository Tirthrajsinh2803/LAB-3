package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

class RandyTest {

    @Test
    void testPickNextMove() throws InterruptedException {
        Randy randy = new Randy("Randy");
        Board board = new Board("XOX-OX--O");

        Position move = randy.pickNextMove(board);
        Assertions.assertTrue(board.isEmptyAt(move));
    }
}