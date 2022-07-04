package chess.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class CellTest {

	private Cell cell;
	private Cell pawnCell;
	private ChessPiece pawn;

	@BeforeEach
	public void setup() {
		pawn = new Pawn('w');
		cell = new Cell(1, 2, null);
		pawnCell = new Cell(6, 7, pawn);
	}

	@Test
	@DisplayName("Test constructor with valid values.")
	public void testValidConstructor() {
		assertTrue(cell.isEmpty());
		assertFalse(pawnCell.isEmpty());
		assertFalse(cell.isSelectable());
		assertFalse(pawnCell.isSelectable());
		assertEquals(1, cell.getX());
		assertEquals(2, cell.getY());
		assertEquals(6, pawnCell.getX());
		assertEquals(7, pawnCell.getY());
		assertNull(cell.getPiece());
		assertEquals(pawn, pawnCell.getPiece());
	}

	@Test
	@DisplayName("Test constructor with invalid values.")
	public void testInvalidConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 7, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(7, -1, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-100, 7, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(7, -100, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(8, 5, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(5, 8, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(100, 5, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(5, 100, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-1, -1, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-100, -100, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(8, 8, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(100, 100, null),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 7, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(7, -1, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-100, 7, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(7, -100, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(8, 5, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(5, 8, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(100, 5, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(5, 100, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-1, -1, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(-100, -100, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(8, 8, pawn),
			"An exception should be thrown when coordinates are invalid!");
		assertThrows(IllegalArgumentException.class, () -> new Cell(100, 100, pawn),
			"An exception should be thrown when coordinates are invalid!");
	}

	@Test
	@DisplayName("Test setPiece().")
	public void testSetPiece() {
		cell.setPiece(pawn);
		assertFalse(cell.isEmpty());
		assertFalse(cell.isSelectable());
		assertEquals(pawn, cell.getPiece());
		cell.setPiece(null);
		assertTrue(cell.isEmpty());
		assertFalse(cell.isSelectable());
		assertNull(cell.getPiece());
	}

	@Test
	@DisplayName("Test setSelectable().")
	public void testSetSelectable() {
		cell.setSelectable(true);
		assertTrue(cell.isSelectable());
		cell.setSelectable(false);
		assertFalse(cell.isSelectable());
	}

}
