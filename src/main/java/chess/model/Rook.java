package chess.model;

public class Rook extends ChessPiece {

	private boolean firstMove = true;

	public Rook(char color) {
		super(color);
	}

	public char getChessPieceType() {
		return 'r';
	}

	public void hasMoved() {
		firstMove = false;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells) {
		// ROKADE
		if (super.getColor() == 'w' && isFirstMove() && cells[0][4].getPiece() instanceof King
				&& ((King) cells[0][4].getPiece()).isFirstMove() && (toX == 4 && toY == 0)) {
			if (fromX < toX) {
				for (int x = 1; x < toX; x++) {
					if (!cells[0][x].isEmpty()) {
						return false;
					}
				}
				return true;
			} else {
				for (int x = 6; x > toX; x--) {
					if (!cells[0][x].isEmpty()) {
						return false;
					}
				}
				return true;
			}
		} else if (super.getColor() == 'b' && isFirstMove() && cells[7][4].getPiece() instanceof King
				&& ((King) cells[7][4].getPiece()).isFirstMove() && (toX == 4 && toY == 7)) {
			if (fromX < toX) {
				for (int x = 1; x < toX; x++) {
					if (!cells[7][x].isEmpty()) {
						return false;
					}
				}
				return true;
			} else {
				for (int x = 6; x > toX; x--) {
					if (!cells[7][x].isEmpty()) {
						return false;
					}
				}
				return true;
			}
		}

		// CANCEL MOVE
		if (fromX == toX && fromY == toY) {
			return true;
		// CHECK VERTICAL MOVEMENT
		} else if (fromX == toX) {
			// DOWNWARDS MOVEMENT
			if (fromY < toY) {
				for (int y = fromY + 1; y < toY; y++) {
					if (!cells[y][toX].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			// UPWARDS MOVEMENT
			} else {
				for (int y = fromY - 1; y > toY; y--) {
					if (!cells[y][toX].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			}
		// CHECK HORIZONTAL MOVEMENT
		} else if (fromY == toY) {
			// MOVEMENT TO THE RIGHT
			if (fromX < toX) {
				for (int x = fromX + 1; x < toX; x++) {
					if (!cells[toY][x].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			// MOVEMENT TO THE LEFT
			} else {
				for (int x = fromX - 1; x > toX; x--) {
					if (!cells[toY][x].isEmpty()) {
						return false;
					}
				}
				return cells[toY][toX].isEmpty() || (!cells[toY][toX].isEmpty()
						&& cells[toY][toX].getPiece().getColor() != super.getColor());
			}
		// ALL OTHER MOVES ARE ILLEGAL MOVES
		} else {
			return false;
		}
	}

}
