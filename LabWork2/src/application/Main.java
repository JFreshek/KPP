package application;


import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Student student = new Student();
		Worker worker = new Worker();
		MainWindow window = new MainWindow();
		window.display(primaryStage, student, worker);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
