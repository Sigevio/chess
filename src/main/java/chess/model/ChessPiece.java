package chess.model;

public abstract class ChessPiece {

	private char color;

	public ChessPiece(char color) {
		if (!isValidColor(color)) {
			throw new IllegalArgumentException("Invalid color!");
		}
		this.color = Character.toLowerCase(color);
	}

	private boolean isValidColor(char color) {
		return color == 'w' || color == 'b' || color == 'W' || color == 'B';
	}

	public char getColor() {
		return color;
	}

	abstract char getChessPieceType();

	abstract boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells);

}
