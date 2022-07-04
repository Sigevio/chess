package chess.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class GameTest {

	private Game game;

	@BeforeEach
	public void setup() {
		game = new Game();
	}

	@Test
	@DisplayName("Test constructor.")
	public void testConstructor() {
		assertTrue(game.getBoard().getCells()[0][1].isSelectable());
		assertTrue(game.getBoard().getCells()[0][6].isSelectable());
		for (int x = 0; x < 8; x++) {
			assertTrue(game.getBoard().getCells()[1][x].isSelectable());
		}
		assertFalse(game.getBoard().getCells()[7][1].isSelectable());
		assertFalse(game.getBoard().getCells()[7][6].isSelectable());
		for (int x = 0; x < 8; x++) {
			assertFalse(game.getBoard().getCells()[6][x].isSelectable());
		}
		assertNull(game.getSelectedCell());
	}

	@Test
	@DisplayName("Test selectCell().")
	public void testSelectCell() {
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(-1, 3),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(3, -1),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(-100, 5),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(5, -100),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(8, 3),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(3, 8),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(100, 5),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(5, 100),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(-1, -1),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(-100, -100),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(8, 8),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> game.selectCell(100, 100),
			"An exception should be thrown when coordinates are invalid!");

		assertNull(game.getSelectedCell());
		game.selectCell(7, 7);
		assertNull(game.getSelectedCell());
		game.selectCell(0, 6);
		assertNull(game.getSelectedCell());
		game.selectCell(0, 1);
		assertTrue(game.getSelectedCell() instanceof Cell);
		assertTrue(game.getSelectedCell().getPiece() instanceof Pawn);
	}

	@Test
	@DisplayName("Test checkmate.")
	public void testIsWon() {
		assertFalse(game.isWon());
		assertFalse(game.isDraw());

		game.selectCell(5, 1);
		game.selectCell(5, 2);

		assertFalse(game.isWon());
		assertFalse(game.isDraw());

		game.selectCell(4, 6);
		game.selectCell(4, 4);

		assertFalse(game.isWon());
		assertFalse(game.isDraw());

		game.selectCell(6, 1);
		game.selectCell(6, 3);

		assertFalse(game.isWon());
		assertFalse(game.isDraw());

		game.selectCell(3, 7);
		game.selectCell(7, 3);

		assertTrue(game.isWon());
		assertFalse(game.isDraw());
		assertEquals("Black", game.getVictor());
	}

	@Test
	@DisplayName("Test stalemate.")
	public void testIsDraw() {
		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(4, 1);
		game.selectCell(4, 2);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(0, 6);
		game.selectCell(0, 4);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(3, 0);
		game.selectCell(7, 4);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(0, 7);
		game.selectCell(0, 5);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(7, 4);
		game.selectCell(0, 4);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(7, 6);
		game.selectCell(7, 4);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(7, 1);
		game.selectCell(7, 3);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(0, 5);
		game.selectCell(7, 5);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(0, 4);
		game.selectCell(2, 6);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(5, 6);
		game.selectCell(5, 5);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(2, 6);
		game.selectCell(3, 6);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(4, 7);
		game.selectCell(5, 6);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(3, 6);
		game.selectCell(1, 6);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(3, 7);
		game.selectCell(3, 2);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(1, 6);
		game.selectCell(1, 7);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(3, 2);
		game.selectCell(7, 6);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(1, 7);
		game.selectCell(2, 7);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(5, 6);
		game.selectCell(6, 5);

		assertFalse(game.isDraw());
		assertFalse(game.isWon());

		game.selectCell(2, 7);
		game.selectCell(4, 5);

		assertTrue(game.isDraw());
		assertFalse(game.isWon());
		assertEquals("Black", game.getStalemated());
	}

}
