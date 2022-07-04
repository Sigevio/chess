package chess.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent game = FXMLLoader.load(getClass().getResource("Chess.fxml"));
		Scene introScene = new Scene(game);
		primaryStage.setTitle("Chess");
		primaryStage.setScene(introScene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(ChessApplication.class, args);
	}
}