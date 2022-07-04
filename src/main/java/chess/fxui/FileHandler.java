package chess.fxui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import chess.model.Board;
import chess.model.Game;

public class FileHandler implements IFileHandler {
	public final static String FILE_EXTENSION = "txt";

	private static Path getChessFolderPath() {
		return Path.of(System.getProperty("user.home"), "chess");
	}

	private void ensureSavesFolder() {
		try {
			Files.createDirectories(getChessFolderPath().resolve("saves"));
		} catch (IOException e) {
			System.out.println("Failed to create saves directory in FileHandler!");
		}
	}

	public static Path getSavesPath(String name) {
		return getChessFolderPath().resolve("saves/" + name + "." + FILE_EXTENSION);
	}

	public void saveProgress(String filename, Game game) throws IOException {
		ensureSavesFolder();
		if (filename.isEmpty()) {
			filename = "filename";
		}
		try (OutputStream os = new FileOutputStream(getSavesPath(filename).toFile())) {
			saveProgress(game, os);
		}
	}

	public void saveProgress(Game game, OutputStream os) {
		try (PrintWriter pw = new PrintWriter(os)) {
			pw.println(game.getTurn());

			StringBuilder sb = new StringBuilder();
			for (int y = 0; y < 8; y++) {
				sb.setLength(0);
				for (int x = 0; x < 8; x++) {
					sb.append(
							game.getChessPieceColorAndType(x, y) == null ? "##" : game.getChessPieceColorAndType(x, y));
				}
				pw.println(sb.toString());
			}
		}
	}

	public Game loadProgress(String filename) throws IOException {
		ensureSavesFolder();
		if (filename.isEmpty()) {
			filename = "filename";
		}
		try (InputStream is = new FileInputStream(getSavesPath(filename).toFile())) {
			return loadProgress(is);
		}
	}

	public Game loadProgress(InputStream is) {
		try (Scanner scanner = new Scanner(is)) {
			char turn = (scanner.nextLine()).charAt(0);
			String chessPiecesLocations = "";

			for (int y = 0; y < 8; y++) {
				chessPiecesLocations += scanner.nextLine();
			}

			Board board = new Board(chessPiecesLocations);
			Game game = new Game(board, turn);
			return game;
		}
	}

}
