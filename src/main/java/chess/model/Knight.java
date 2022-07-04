package chess.model;

public class Knight extends ChessPiece {

	public Knight(char color) {
		super(color);
	}

	public char getChessPieceType() {
		return 'n';
	}

	public boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells) {
		if (fromX == toX && fromY == toY) {
			return true;
		} else if ((fromX + 1 == toX && fromY + 2 == toY) || (fromX - 1 == toX && fromY + 2 == toY)
				|| (fromX + 1 == toX && fromY - 2 == toY) || (fromX - 1 == toX && fromY - 2 == toY)
				|| (fromX + 2 == toX && fromY + 1 == toY) || (fromX - 2 == toX && fromY + 1 == toY)
				|| (fromX + 2 == toX && fromY - 1 == toY) || (fromX - 2 == toX && fromY - 1 == toY)) {
			return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
					&& cells[toY][toX].getPiece().getColor() != super.getColor());
		} else {
			return false;
		}
	}

}
