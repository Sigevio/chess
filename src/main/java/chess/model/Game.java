package chess.model;

public class Game {
	
	private Board board;

	private char currentTurn;
	private Cell selectedCell = null;

	public Game() {
		this(new Board(), 'w');
	}

	public Game(Board board, char turn) {
		if (!isValidTurn(turn)) {
			throw new IllegalArgumentException("Invalid turn!");
		}
		this.board = board;
		this.currentTurn = Character.toLowerCase(turn);
		refreshSelectable();
	}

	public void selectCell(int x, int y) {
		if (!isValidCoordinates(x, y)) {
			throw new IllegalArgumentException("Illegal coordinates!");
		}

		if ((getSelectedCell() != null
				|| (!getCell(x, y).isEmpty() && getTurn() == getCell(x, y).getPiece().getColor()))
				&& (getCell(x, y).isSelectable())) {
			if (getSelectedCell() == null && !getCell(x, y).isEmpty() && getTurn() == getCell(x, y).getPiece().getColor()) {
				setSelectedCell(getCell(x, y));
			} else if (getSelectedCell() == getCell(x, y)) {
				setSelectedCell(null);
			} else {
				movePiece(getCell(x, y));
				setSelectedCell(null);
				nextTurn();
			}
		}
		if ( (y == 0 || y == 7) && getCell(x, y).getPiece() instanceof Pawn ) {
			getCell(x, y).setPiece( getCell(x, y).getPiece().getColor() == 'w' ? new Queen('w') : new Queen('b') );
		}
		refreshSelectable();
	}

	private void nextTurn() {
		currentTurn = getTurn() == 'w' ? 'b' : 'w';
	}

	private void movePiece(Cell cell) {
		if (getSelectedCell().getPiece() instanceof Pawn) {
			((Pawn) getSelectedCell().getPiece()).hasMoved();
		} else if (getSelectedCell().getPiece() instanceof Rook) {
			((Rook) getSelectedCell().getPiece()).hasMoved();
		} else if (getSelectedCell().getPiece() instanceof King) {
			((King) getSelectedCell().getPiece()).hasMoved();
		}
		if (getSelectedCell().getPiece() instanceof Rook && cell.getPiece() instanceof King && getSelectedCell().getPiece().getColor() == cell.getPiece().getColor()) {
			if (getSelectedCell().getX() == 0 && getSelectedCell().getY() == 0) {
				getCell(2, 0).setPiece(cell.getPiece());
				getCell(3, 0).setPiece(getSelectedCell().getPiece());
				cell.setPiece(null);
			} else if (getSelectedCell().getX() == 7 && getSelectedCell().getY() == 0) {
				getCell(6, 0).setPiece(cell.getPiece());
				getCell(5, 0).setPiece(getSelectedCell().getPiece());
				cell.setPiece(null);
			} else if (getSelectedCell().getX() == 0 && getSelectedCell().getY() == 7) {
				getCell(2, 7).setPiece(cell.getPiece());
				getCell(3, 7).setPiece(getSelectedCell().getPiece());
				cell.setPiece(null);
			} else if (getSelectedCell().getX() == 7 && getSelectedCell().getY() == 7) {
				getCell(6, 7).setPiece(cell.getPiece());
				getCell(5, 7).setPiece(getSelectedCell().getPiece());
				cell.setPiece(null);
			}
		} else {
			cell.setPiece(getSelectedCell().getPiece());
		}
		getSelectedCell().setPiece(null);
	}

	private void setSelectedCell(Cell cell) {
		selectedCell = cell;
	}

	public Cell getSelectedCell() {
		return selectedCell;
	}

	private boolean isValidCoordinates(int x, int y) {
		return x <= 7 && 0 <= x && y <= 7 && 0 <= y;
	}

	private boolean isValidTurn(char color) {
		return color == 'w' || color == 'b';
	}

	private Cell getCell(int x, int y) {
		return getBoard().getCells()[y][x];
	}

	private void refreshSelectable() {
		if (getSelectedCell() == null) {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					getCell(x, y).setSelectable(false);
					if (!getCell(x, y).isEmpty() && getTurn() == getCell(x, y).getPiece().getColor()) {
						for (int j = 0; j < 8; j++) {
							for (int i = 0; i < 8; i++) {
								if (getCell(x, y).getPiece().isMovableFromTo(x, y, i, j, getBoard().getCells())
								&& (!moveCausesCheck(x, y, i, j, getTurn()))
								&& !(x == i && y == j)) {
									getCell(x, y).setSelectable(true);
								}
							}
						}
					}
				}
			}
		} else {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (getSelectedCell().getPiece().isMovableFromTo(getSelectedCell().getX(), getSelectedCell().getY(), x, y, getBoard().getCells())
					&& (!moveCausesCheck(getSelectedCell().getX(), getSelectedCell().getY(), x, y, getTurn())
					|| (getSelectedCell().getX() == x && getSelectedCell().getY() == y))) {
						getCell(x, y).setSelectable(true);
					} else {
						getCell(x, y).setSelectable(false);
					}
				}
			}
		}
	}

	public boolean isCellSelectable(int x, int y) {
		return getCell(x, y).isSelectable();
	}

	public Board getBoard() {
		return board;
	}

	public char getTurn() {
		return currentTurn;
	}

	private int[] getKing(char player) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (getCell(x, y).getPiece() instanceof King && getCell(x, y).getPiece().getColor() == player) {
					return new int[]{x, y};
				}
			}
		}
		return null;
	}

	private boolean moveCausesCheck(int fromX, int fromY, int toX, int toY, char player) {
		boolean check = false;
		ChessPiece oldPiece = getCell(toX, toY).getPiece();
		if (!(oldPiece instanceof King)) {
			getCell(toX, toY).setPiece(getCell(fromX, fromY).getPiece());
			getCell(fromX, fromY).setPiece(null);
			if (isInCheck(player)) {
				check = true;
			}
			getCell(fromX, fromY).setPiece(getCell(toX, toY).getPiece());
			getCell(toX, toY).setPiece(oldPiece);
		}
		return check;
	}

	private boolean isStalemated(char player) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (!getCell(x, y).isEmpty()
				&& getCell(x, y).getPiece().getColor() == player) {
					for (int j = 0; j < 8; j++) {
						for (int i = 0; i < 8; i++) {
							if (getCell(x, y).getPiece().isMovableFromTo(x, y, i, j, getBoard().getCells())
							&& !(x == i && y == j)) {
								if (!isInCheck(player) && !moveCausesCheck(x, y, i, j, player)) {
									return false;
								} else if (isInCheck(player)) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	private boolean isInCheck(char player) {
		int[] kingCoordinates = getKing(player);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (!getCell(x, y).isEmpty()
				&& getCell(x, y).getPiece().getColor() != player
				&& getCell(x, y).getPiece().isMovableFromTo(x, y, kingCoordinates[0], kingCoordinates[1], board.getCells())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isCheckmated(char player) {
		if (!isInCheck(player) || isDraw()) {
			return false;
		}

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (!getCell(x, y).isEmpty()
				&& getCell(x, y).getPiece().getColor() == player) {
					for (int j = 0; j < 8; j++) {
						for (int i = 0; i < 8; i++) {
							if (getCell(x, y).getPiece().isMovableFromTo(x, y, i, j, getBoard().getCells())
							&& !(x == i && y == j)) {
								if (!moveCausesCheck(x, y, i, j, player)) {
									return false;
								};
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isWon() {
		return getTurn() == 'w' ? isCheckmated('w') : isCheckmated('b');
	}

	public boolean isDraw() {
		return getTurn() == 'w' ? isStalemated('w') : isStalemated('b');
	}

	public String getVictor() {
		if (isCheckmated('w')) {
			return "Black";
		} else if (isCheckmated('b')) {
			return "White";
		} else {
			throw new IllegalStateException("Not supposed to call getVictor() when game is not checkmate!");
		}
	}

	public String getStalemated() {
		if (isStalemated('w')) {
			return "White";
		} else if (isStalemated('b')) {
			return "Black";
		} else {
			throw new IllegalStateException("Not supposed to call getStalemated() when game is not draw!");
		}
	}

	public String getChessPieceColorAndType(int x, int y) {
		if (getCell(x, y).isEmpty()) {
			return null;
		}
		return Character.toString(getCell(x, y).getPiece().getColor())
				+ Character.toString(getCell(x, y).getPiece().getChessPieceType());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  +------+------+------+------+------+------+------+------+\n");
		for (int y = 0; y < 8; y++) {
			sb.append(y + 1 + " ");
			for (int x = 0; x < 8; x++) {
				if (!getCell(x, y).isEmpty()) {
					sb.append("| " + getCell(x, y).getPiece().getChessPieceType() + "("
							+ getCell(x, y).getPiece().getColor() + ") ");
				} else {
					sb.append("|      ");
				}
			}
			sb.append("|\n  +------+------+------+------+------+------+------+------+\n");
		}
		sb.append("     A      B      C      D      E      F      G      H");
		return sb.toString();
	}

	public static void main(String[] args) {
		Game g = new Game();
		System.out.println(g.toString());
	}

}