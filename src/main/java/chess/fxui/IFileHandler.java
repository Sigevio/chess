package chess.fxui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import chess.model.Game;

public interface IFileHandler {

	void saveProgress(String filename, Game game) throws IOException;

	void saveProgress(Game game, OutputStream os);

	Game loadProgress(String filename) throws IOException;

	Game loadProgress(InputStream is);

}