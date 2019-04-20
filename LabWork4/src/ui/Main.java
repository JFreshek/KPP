package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		MatrixWindow window = new MatrixWindow();
		window.display();
	}

	public static void main(String[] args) {
		launch(args);
	}
}