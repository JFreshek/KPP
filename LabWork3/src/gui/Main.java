package gui;


import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		StartingWindow window = new StartingWindow();
		window.display(primaryStage);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
