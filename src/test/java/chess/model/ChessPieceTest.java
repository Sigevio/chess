package chess.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ChessPieceTest {

	private Pawn pawnW1;
	private Rook rookW1;
	private Knight knightW1;
	private Bishop bishopW1;
	private Queen queenW1;
	private King kingW1;
	private Pawn pawnB1;
	private Rook rookB1;
	private Knight knightB1;
	private Bishop bishopB1;
	private Queen queenB1;
	private King kingB1;
	private Pawn pawnW2;
	private Rook rookW2;
	private Knight knightW2;
	private Bishop bishopW2;
	private Queen queenW2;
	private King kingW2;
	private Pawn pawnB2;
	private Rook rookB2;
	private Knight knightB2;
	private Bishop bishopB2;
	private Queen queenB2;
	private King kingB2;
	private char[] invalidChars = new char[] { 'a', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z', 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };

	@BeforeEach
	public void setup() {
		pawnW1 = new Pawn('w');
		rookW1 = new Rook('w');
		knightW1 = new Knight('w');
		bishopW1 = new Bishop('w');
		queenW1 = new Queen('w');
		kingW1 = new King('w');
		pawnB1 = new Pawn('b');
		rookB1 = new Rook('b');
		knightB1 = new Knight('b');
		bishopB1 = new Bishop('b');
		queenB1 = new Queen('b');
		kingB1 = new King('b');
		pawnW2 = new Pawn('W');
		rookW2 = new Rook('W');
		knightW2 = new Knight('W');
		bishopW2 = new Bishop('W');
		queenW2 = new Queen('W');
		kingW2 = new King('W');
		pawnB2 = new Pawn('B');
		rookB2 = new Rook('B');
		knightB2 = new Knight('B');
		bishopB2 = new Bishop('B');
		queenB2 = new Queen('B');
		kingB2 = new King('B');
	}

	@Test
	@DisplayName("Test constructors with valid values.")
	public void testValidConstructor() {
		assertEquals('w', pawnW1.getColor());
		assertEquals('b', pawnB1.getColor());
		assertEquals('w', pawnW2.getColor());
		assertEquals('b', pawnB2.getColor());
		assertEquals('w', rookW1.getColor());
		assertEquals('b', rookB1.getColor());
		assertEquals('w', rookW2.getColor());
		assertEquals('b', rookB2.getColor());
		assertEquals('w', knightW1.getColor());
		assertEquals('b', knightB1.getColor());
		assertEquals('w', knightW2.getColor());
		assertEquals('b', knightB2.getColor());
		assertEquals('w', bishopW1.getColor());
		assertEquals('b', bishopB1.getColor());
		assertEquals('w', bishopW2.getColor());
		assertEquals('b', bishopB2.getColor());
		assertEquals('w', queenW1.getColor());
		assertEquals('b', queenB1.getColor());
		assertEquals('w', queenW2.getColor());
		assertEquals('b', queenB2.getColor());
		assertEquals('w', kingW1.getColor());
		assertEquals('b', kingB1.getColor());
		assertEquals('w', kingW2.getColor());
		assertEquals('b', kingB2.getColor());
		assertEquals('p', pawnW1.getChessPieceType());
		assertEquals('p', pawnB1.getChessPieceType());
		assertEquals('p', pawnW2.getChessPieceType());
		assertEquals('p', pawnB2.getChessPieceType());
		assertEquals('r', rookW1.getChessPieceType());
		assertEquals('r', rookB1.getChessPieceType());
		assertEquals('r', rookW2.getChessPieceType());
		assertEquals('r', rookB2.getChessPieceType());
		assertEquals('n', knightW1.getChessPieceType());
		assertEquals('n', knightB1.getChessPieceType());
		assertEquals('n', knightW2.getChessPieceType());
		assertEquals('n', knightB2.getChessPieceType());
		assertEquals('b', bishopW1.getChessPieceType());
		assertEquals('b', bishopB1.getChessPieceType());
		assertEquals('b', bishopW2.getChessPieceType());
		assertEquals('b', bishopB2.getChessPieceType());
		assertEquals('q', queenW1.getChessPieceType());
		assertEquals('q', queenB1.getChessPieceType());
		assertEquals('q', queenW2.getChessPieceType());
		assertEquals('q', queenB2.getChessPieceType());
		assertEquals('k', kingW1.getChessPieceType());
		assertEquals('k', kingB1.getChessPieceType());
		assertEquals('k', kingW2.getChessPieceType());
		assertEquals('k', kingB2.getChessPieceType());
	}

	@Test
	@DisplayName("Test constructors with invalid values.")
	public void testInvalidConstructor() {
		for (char ch : invalidChars) {
			assertThrows(IllegalArgumentException.class, () -> new Pawn(ch),
				"An exception should be thrown when color is invalid!");
			assertThrows(IllegalArgumentException.class, () -> new Rook(ch),
				"An exception should be thrown when color is invalid!");
			assertThrows(IllegalArgumentException.class, () -> new Knight(ch),
				"An exception should be thrown when color is invalid!");
			assertThrows(IllegalArgumentException.class, () -> new Bishop(ch),
				"An exception should be thrown when color is invalid!");
			assertThrows(IllegalArgumentException.class, () -> new Queen(ch),
				"An exception should be thrown when color is invalid!");
			assertThrows(IllegalArgumentException.class, () -> new King(ch),
				"An exception should be thrown when color is invalid!");
		}
	}

	@Test
	@DisplayName("Test first move.")
	public void testFirstMove() {
		assertTrue(pawnW1.isFirstMove());
		assertTrue(pawnB1.isFirstMove());
		assertTrue(rookW1.isFirstMove());
		assertTrue(rookB1.isFirstMove());
		assertTrue(kingW1.isFirstMove());
		assertTrue(kingB1.isFirstMove());
		pawnW1.hasMoved();
		pawnB1.hasMoved();
		rookW1.hasMoved();
		rookB1.hasMoved();
		kingW1.hasMoved();
		kingB1.hasMoved();
		assertFalse(pawnW1.isFirstMove());
		assertFalse(pawnB1.isFirstMove());
		assertFalse(rookW1.isFirstMove());
		assertFalse(rookB1.isFirstMove());
		assertFalse(kingW1.isFirstMove());
		assertFalse(kingB1.isFirstMove());
	}

}
