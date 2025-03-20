package lab4;

import lab4.game.Board;
import lab4.game.Position;
import lab4.game.Row;
import lab4.game.Col;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testInitialBoardIsEmpty() {
        Board board = new Board();
        assertFalse(board.isFull());
        assertEquals(Board.Status.InProgress, board.getStatus());
    }

    @Test
    public void testPlaceXAndO() {
        Board board = new Board();
        Position pos = new Position(Row.Top, Col.Left);

        board.placeX(pos);
        assertTrue(board.isOccupiedAt(pos));
        assertEquals(Board.Status.InProgress, board.getStatus());

        board.placeO(new Position(Row.Middle, Col.Middle));
        assertEquals(Board.Status.InProgress, board.getStatus());
    }

    @Test
    public void testWinCondition() {
        Board board = new Board();

        // X wins in a row
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));
        assertEquals(Board.Status.XWins, board.getStatus());

        // Reset board
        board = new Board();

        // O wins in a column
        board.placeO(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Left));
        assertEquals(Board.Status.OWins, board.getStatus());

        // Reset board
        board = new Board();

        // X wins in a diagonal
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Right));
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    public void testDrawCondition() {
        Board board = new Board();

        // Fill the board without a winner
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));

        board.placeO(new Position(Row.Middle, Col.Left));
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeO(new Position(Row.Middle, Col.Right));

        board.placeX(new Position(Row.Bottom, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Right));

        assertTrue(board.isFull());
        assertEquals(Board.Status.Draw, board.getStatus());
    }
}