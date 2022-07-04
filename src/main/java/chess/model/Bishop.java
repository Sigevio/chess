package chess.model;

public class Bishop extends ChessPiece {

	public Bishop(char color) {
		super(color);
	}

	public char getChessPieceType() {
		return 'b';
	}

	public boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells) {
		if (fromX == toX && fromY == toY) {
			return true;
		} else if ((toX - fromX == toY - fromY) && (toX - fromX > 0) && (toY - fromY > 0)) {
			for (int dig = 1; dig < (toX - fromX); dig++) {
				if (!cells[fromY + dig][fromX + dig].isEmpty()) {
					return false;
				}
			}
			return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
					&& cells[toY][toX].getPiece().getColor() != super.getColor());
		} else if ((toX - fromX == toY - fromY) && (toX - fromX < 0) && (toY - fromY < 0)) {
			for (int dig = -1; dig > (toX - fromX); dig--) {
				if (!cells[fromY + dig][fromX + dig].isEmpty()) {
					return false;
				}
			}
			return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
					&& cells[toY][toX].getPiece().getColor() != super.getColor());
		} else if ((-(toX - fromX) == toY - fromY) && (toX - fromX < 0) && (toY - fromY > 0)) {
			for (int dig = 1; dig < (toY - fromY); dig++) {
				if (!cells[fromY + dig][fromX - dig].isEmpty()) {
					return false;
				}
			}
			return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
					&& cells[toY][toX].getPiece().getColor() != super.getColor());
		} else if ((-(toX - fromX) == toY - fromY) && (toX - fromX > 0) && (toY - fromY < 0)) {
			for (int dig = 1; dig < (toX - fromX); dig++) {
				if (!cells[fromY - dig][fromX + dig].isEmpty()) {
					return false;
				}
			}
			return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
					&& cells[toY][toX].getPiece().getColor() != super.getColor());
		} else {
			return false;
		}
	}

}
