package chess.fxui;

import java.io.FileNotFoundException;
import java.io.IOException;

import chess.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ChessController {

	private Game game;
	private final String CELLSTYLE1 = "-fx-background-color: #6E470B; ";
	private final String CELLSTYLE2 = "-fx-background-color: #966F33; ";
	private String selectableStyle = "";
	private String hoverSelectableStyle = "-fx-cursor: hand; ";

	private FileHandler fileHandler = new FileHandler();

	@FXML
	private StackPane sp;

	@FXML
	private Pane board, checkmatedBlackScreen, checkmatedWhiteScreen, stalematedBlackScreen, stalematedWhiteScreen;

	@FXML
	private TextField filename;

	@FXML
	private Button saveButton, loadButton, newGameButton, toggleBordersButton;

	@FXML
	private Text defaultFilename, savingSuccessText, loadingSuccessText, savingFailText, loadingFailText, newGameText;

	@FXML
	private void toggleBorders() {
		selectableStyle = selectableStyle.equals("")
				? "-fx-border-color: blue; -fx-border-style: dashed; -fx-border-width: 2px; "
				: "";
		hoverSelectableStyle = hoverSelectableStyle.equals("-fx-cursor: hand; ")
				? "-fx-border-width: 3px; -fx-border-style: solid; -fx-cursor: hand; "
				: "-fx-cursor: hand; ";
		try {
			drawBoard();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void saveState() {
		try {
			fileHandler.saveProgress(filename.getText(), game);
			loadingFailText.setVisible(false);
			loadingSuccessText.setVisible(false);
			savingFailText.setVisible(false);
			newGameText.setVisible(false);
			savingSuccessText.setVisible(true);
		} catch (IOException e) {
			loadingFailText.setVisible(false);
			loadingSuccessText.setVisible(false);
			savingSuccessText.setVisible(false);
			newGameText.setVisible(false);
			savingFailText.setVisible(true);
			System.out.println("Failed to save progress in ChessController!");
		}
	}

	@FXML
	private void loadState() {
		try {
			game = fileHandler.loadProgress(filename.getText());
			savingFailText.setVisible(false);
			savingSuccessText.setVisible(false);
			loadingFailText.setVisible(false);
			newGameText.setVisible(false);
			loadingSuccessText.setVisible(true);
			createBoard();
		} catch (IOException e) {
			savingFailText.setVisible(false);
			savingSuccessText.setVisible(false);
			loadingSuccessText.setVisible(false);
			newGameText.setVisible(false);
			loadingFailText.setVisible(true);
			System.out.println("Failed to load progress in ChessController!");
		}
	}

	@FXML
	private void newGame() throws FileNotFoundException {
		game = new Game();
		savingFailText.setVisible(false);
		savingSuccessText.setVisible(false);
		loadingFailText.setVisible(false);
		loadingSuccessText.setVisible(false);
		newGameText.setVisible(true);
		createBoard();
	}

	@FXML
	private void initialize() throws FileNotFoundException {
		game = new Game();
		createBoard();

		filename.setOnKeyTyped(event -> {
			if (!filename.getText().isEmpty()) {
				defaultFilename.setVisible(false);
			} else {
				defaultFilename.setVisible(true);
			}
		});
	}

	private void createBoard() throws FileNotFoundException {
		stalematedWhiteScreen.setVisible(false);
		stalematedBlackScreen.setVisible(false);
		checkmatedWhiteScreen.setVisible(false);
		checkmatedBlackScreen.setVisible(false);
		saveButton.setVisible(true);
		loadButton.setTranslateX(30);
		board.getChildren().clear();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Pane cell = new Pane();
				cell.setPrefSize(60, 60);
				cell.setTranslateX(x * 60 + 40);
				cell.setTranslateY(y * 60 + 40);
				board.getChildren().add(cell);
				if ((x + y) % 2 == 0) {
					cell.setStyle(CELLSTYLE1);
				} else {
					cell.setStyle(CELLSTYLE2);
				}
				cell.setOnMouseEntered(event -> {
					if (game.isCellSelectable(getLocationX(cell), getLocationY(cell))
							&& (getLocationX(cell) + getLocationY(cell)) % 2 == 0) {
						cell.setStyle(CELLSTYLE1 + selectableStyle + hoverSelectableStyle);
					} else if (game.isCellSelectable(getLocationX(cell), getLocationY(cell))
							&& (getLocationX(cell) + getLocationY(cell)) % 2 != 0) {
						cell.setStyle(CELLSTYLE2 + selectableStyle + hoverSelectableStyle);
					}
				});
				cell.setOnMouseExited(event -> {
					if (game.isCellSelectable(getLocationX(cell), getLocationY(cell))
							&& (getLocationX(cell) + getLocationY(cell)) % 2 == 0) {
						cell.setStyle(CELLSTYLE1 + selectableStyle);
					} else if (game.isCellSelectable(getLocationX(cell), getLocationY(cell))
							&& (getLocationX(cell) + getLocationY(cell)) % 2 != 0) {
						cell.setStyle(CELLSTYLE2 + selectableStyle);
					} else if ((getLocationX(cell) + getLocationY(cell)) % 2 == 0) {
						cell.setStyle(CELLSTYLE1);
					} else {
						cell.setStyle(CELLSTYLE2);
					}
				});
				cell.setOnMouseClicked(event -> {
					if (event.getButton().equals(MouseButton.PRIMARY)
							&& game.isCellSelectable(getLocationX(cell), getLocationY(cell))) {
						game.selectCell(getLocationX(cell), getLocationY(cell));
						if (game.isDraw()) {
							saveButton.setVisible(false);
							loadButton.setTranslateX(-30);
							if (game.getStalemated() == "White") {
								stalematedWhiteScreen.setVisible(true);
							} else {
								stalematedBlackScreen.setVisible(true);
							}
						} else if (game.isWon()) {
							saveButton.setVisible(false);
							loadButton.setTranslateX(-30);
							if (game.getVictor() == "White") {
								checkmatedBlackScreen.setVisible(true);
							} else {
								checkmatedWhiteScreen.setVisible(true);
							}
						} else {
							try {
								drawBoard();
								savingSuccessText.setVisible(false);
								loadingSuccessText.setVisible(false);
								savingFailText.setVisible(false);
								loadingFailText.setVisible(false);
								newGameText.setVisible(false);
							} catch (FileNotFoundException e) {
								System.out.println("Could not draw board in ChessController! Chess piece images not found.");
							}
						}
					}
				});
			}
		}
		drawBoard();
	}

	private void drawBoard() throws FileNotFoundException {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Pane cell = getPane(x, y);
				cell.getChildren().clear();
				if (game.getChessPieceColorAndType(x, y) != null) {
					Image piece = new Image("/chess/chesspieces/" + game.getChessPieceColorAndType(x, y) + ".png");
					ImageView iv = new ImageView(piece);

					cell.getChildren().add(iv);
				}
				if (game.isCellSelectable(x, y) && (x + y) % 2 == 0) {
					cell.setStyle(CELLSTYLE1 + selectableStyle);
				} else if (game.isCellSelectable(x, y) && (x + y) % 2 != 0) {
					cell.setStyle(CELLSTYLE2 + selectableStyle);
				} else if ((x + y) % 2 == 0) {
					cell.setStyle(CELLSTYLE1);
				} else {
					cell.setStyle(CELLSTYLE2);
				}
			}
		}
	}

	private Pane getPane(int x, int y) {
		return (Pane) board.getChildren().get(y * 8 + x);
	}

	private int getLocationX(Pane cell) {
		return (int) ((cell.getBoundsInParent().getMaxX() / 60) - 1);
	}

	private int getLocationY(Pane cell) {
		return (int) ((cell.getBoundsInParent().getMaxY() / 60) - 1);
	}

}
