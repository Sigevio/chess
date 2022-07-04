package chess.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class BoardTest {

	private Board board;
	private final static String INVALIDBOARDLENGTH = "wrwnwbwqwkwbwnwr";
	private final static String INVALIDBOARDCHARACTERS = "whwnwbwqwkwawnwr"
		+ "wpwpwpwpwpwpwpwp"
		+ "################"
		+ "################"
		+ "################"
		+ "################"
		+ "bbbpbpbpbpbpbpbp"
		+ "brbnbbbqbkbbbnbr";

	@BeforeEach
	public void setup() {
		board = new Board();
	}

	@Test
	@DisplayName("Test default constructor.")
	public void testConstructor() {
		assertEquals(8, board.getCells().length);
		for (int i = 0; i < board.getCells().length; i++) {
			assertEquals(8, board.getCells()[i].length);
		}
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				assertEquals(x, board.getCells()[y][x].getX());
				assertEquals(y, board.getCells()[y][x].getY());
			}
		}
		for (int y = 2; y < 6; y++) {
			for (int x = 0; x < 8; x++) {
				assertNull(board.getCells()[y][x].getPiece());
			}
		}
		assertTrue(board.getCells()[0][0].getPiece() instanceof Rook);
		assertTrue(board.getCells()[0][1].getPiece() instanceof Knight);
		assertTrue(board.getCells()[0][2].getPiece() instanceof Bishop);
		assertTrue(board.getCells()[0][3].getPiece() instanceof Queen);
		assertTrue(board.getCells()[0][4].getPiece() instanceof King);
		assertTrue(board.getCells()[0][5].getPiece() instanceof Bishop);
		assertTrue(board.getCells()[0][6].getPiece() instanceof Knight);
		assertTrue(board.getCells()[0][7].getPiece() instanceof Rook);
		for (int x = 0; x < 8; x++) {
			assertTrue(board.getCells()[1][x].getPiece() instanceof Pawn);
		}
		assertTrue(board.getCells()[7][0].getPiece() instanceof Rook);
		assertTrue(board.getCells()[7][1].getPiece() instanceof Knight);
		assertTrue(board.getCells()[7][2].getPiece() instanceof Bishop);
		assertTrue(board.getCells()[7][3].getPiece() instanceof Queen);
		assertTrue(board.getCells()[7][4].getPiece() instanceof King);
		assertTrue(board.getCells()[7][5].getPiece() instanceof Bishop);
		assertTrue(board.getCells()[7][6].getPiece() instanceof Knight);
		assertTrue(board.getCells()[7][7].getPiece() instanceof Rook);
		for (int x = 0; x < 8; x++) {
			assertTrue(board.getCells()[6][x].getPiece() instanceof Pawn);
		}
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 8; x++) {
				assertEquals('w', board.getCells()[y][x].getPiece().getColor());
			}
		}
		for (int y = 6; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				assertEquals('b', board.getCells()[y][x].getPiece().getColor());
			}
		}
	}

	@Test
	@DisplayName("Test load contructor with invalid values")
	public void testInvalidLoadContructor() {
		assertThrows(IllegalArgumentException.class, () -> new Board(INVALIDBOARDLENGTH),
			"An exception should be thrown when length of argument is invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Board(INVALIDBOARDCHARACTERS),
			"An exception should be thrown when characters in argument are invalid!");
	}
}
