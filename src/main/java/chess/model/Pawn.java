package chess.model;

public class Pawn extends ChessPiece {

	private boolean firstMove = true;

	public Pawn(char color) {
		super(color);
	}

	public char getChessPieceType() {
		return 'p';
	}

	public void hasMoved() {
		firstMove = false;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public boolean isMovableFromTo(int fromX, int fromY, int toX, int toY, Cell[][] cells) {
		int yDirection = super.getColor() == 'w' ? 1 : -1;
		if (fromX == toX && fromY == toY) {
			return true;
		} else if ((fromX + 1 == toX || fromX - 1 == toX) && fromY + yDirection == toY) {
			return ((!cells[toY][toX].isEmpty())
					&& (cells[toY][toX].getPiece().getColor() != super.getColor()));
		} else if (isFirstMove() && (fromX == toX && fromY + (2 * yDirection) == toY)) {
			return (cells[toY][toX].isEmpty()) && (cells[fromY + yDirection][toX].isEmpty());
		} else if ((!isFirstMove() && (fromX == toX && fromY + yDirection == toY))
				|| (fromX == toX && fromY + yDirection == toY)) {
			return (cells[toY][toX].isEmpty());
		} else {
			return false;
		}
	}

}
