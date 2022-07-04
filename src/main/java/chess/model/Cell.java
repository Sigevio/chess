package chess.model;

public class Cell {

	private int x;
	private int y;

	private ChessPiece piece;
	private boolean selectable = false;

	public Cell(int x, int y, ChessPiece piece) {
		if (!isValidCoordinates(x, y)) {
			throw new IllegalArgumentException("Invalid coordinates!");
		}
		this.x = x;
		this.y = y;
		this.piece = piece;
	}

	public static boolean isValidCoordinates(int x, int y) {
		return x <= 7 && 0 <= x && y <= 7 && 0 <= y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isEmpty() {
		return piece == null;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

}
