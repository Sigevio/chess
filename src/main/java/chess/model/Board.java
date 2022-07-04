package chess.model;

public class Board {

	private Cell[][] cells;
	public final static String NEWBOARD = "wrwnwbwqwkwbwnwr"
		+ "wpwpwpwpwpwpwpwp"
		+ "################"
		+ "################"
		+ "################"
		+ "################"
		+ "bpbpbpbpbpbpbpbp"
		+ "brbnbbbqbkbbbnbr";

	public Board() {
		this(NEWBOARD);
	}

	public Board(String chessPiecesLocations) {
		if (chessPiecesLocations.length() != 128) {
			throw new IllegalArgumentException("File read has invalid format (length)!");
		}
		cells = new Cell[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				switch (chessPiecesLocations.charAt((y * 2 * 8) + (x * 2) + 1)) {
				case 'p':
					cells[y][x] = new Cell(x, y, new Pawn((chessPiecesLocations.charAt((y * 2 * 8) + (x * 2)))));
					break;
				case 'r':
					cells[y][x] = new Cell(x, y, new Rook((chessPiecesLocations.charAt((y * 2 * 8) + (x * 2)))));
					break;
				case 'n':
					cells[y][x] = new Cell(x, y, new Knight((chessPiecesLocations.charAt((y * 2 * 8) + (x * 2)))));
					break;
				case 'b':
					cells[y][x] = new Cell(x, y, new Bishop((chessPiecesLocations.charAt((y * 2 * 8) + (x * 2)))));
					break;
				case 'q':
					cells[y][x] = new Cell(x, y, new Queen((chessPiecesLocations.charAt((y * 2 * 8) + (x * 2)))));
					break;
				case 'k':
					cells[y][x] = new Cell(x, y, new King((chessPiecesLocations.charAt((y * 2 * 8) + (x * 2)))));
					break;
				case '#':
					cells[y][x] = new Cell(x, y, null);
					break;
				default:
					throw new IllegalArgumentException("File read has invalid format (characters)!");
				}
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

}
