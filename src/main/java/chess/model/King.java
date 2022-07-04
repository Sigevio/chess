package chess.model;

public class King extends ChessPiece {

	private boolean firstMove = true;

	public King(char color) {
		super(color);
	}

	public char getChessPieceType() {
		return 'k';
	}

	public void hasMoved() {
		firstMove = false;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells) {
		if (fromX == toX && fromY == toY) {
			return true;
		} else if ((fromX == toX && (fromY + 1 == toY || fromY - 1 == toY))
				|| (fromY == toY && (fromX + 1 == toX || fromX - 1 == toX))
				|| (fromX + 1 == toX && fromY + 1 == toY)
				|| (fromX + 1 == toX && fromY - 1 == toY)
				|| (fromX - 1 == toX && fromY + 1 == toY)
				|| (fromX - 1 == toX && fromY - 1 == toY)) {
			return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
					&& cells[toY][toX].getPiece().getColor() != super.getColor());
		} else {
			return false;
		}
	}

}
