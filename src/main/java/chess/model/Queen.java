package chess.model;

public class Queen extends ChessPiece {

	public Queen(char color) {
		super(color);
	}

	public char getChessPieceType() {
		return 'q';
	}

	public boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells) {
		if (fromX == toX && fromY == toY) {
			return true;
		} else if (fromX == toX) {
			if (fromY < toY) {
				for (int y = fromY + 1; y < toY; y++) {
					if (!cells[y][toX].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			} else {
				for (int y = fromY - 1; y > toY; y--) {
					if (!cells[y][toX].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			}
		} else if (fromY == toY) {
			if (fromX < toX) {
				for (int x = fromX + 1; x < toX; x++) {
					if (!cells[toY][x].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			} else {
				for (int x = fromX - 1; x > toX; x--) {
					if (!cells[toY][x].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			}
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
