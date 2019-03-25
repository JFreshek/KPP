package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow {

	public void display(Stage primaryStage, Student student, Worker worker) {
		try {
			primaryStage.setResizable(false);
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,320,150);
			GridPane gridpane = new GridPane();
			gridpane.setPadding(new Insets(10));
			gridpane.setHgap(5);
			gridpane.setVgap(5);
			for(int i = 0; i < 4; i++) {
				ColumnConstraints column = new ColumnConstraints();
				column.setPercentWidth(100/4.0);
				gridpane.getColumnConstraints().add(column);
			}
			Label label = new Label("Студент");
			gridpane.add(label, 0, 0);
			label = new Label("Имя:");
			gridpane.add(label, 0, 1);
			TextField studentFirstNameField = new TextField();
			gridpane.add(studentFirstNameField, 1, 1);
			label = new Label("Фамилия:");
			gridpane.add(label, 0, 2);
			TextField studentLastNameField = new TextField();
			gridpane.add(studentLastNameField, 1, 2);
			Button moveInStudentButton = new Button("Заселить");
			gridpane.add(moveInStudentButton, 0, 3);
			Button moveOutStudentButton = new Button("Выселить");
			gridpane.add(moveOutStudentButton, 1, 3);
			Button getInfoAboutStudentButton = new Button("Отчёт");
			gridpane.add(getInfoAboutStudentButton, 0, 4);
			label = new Label("Рабочий");
			gridpane.add(label, 2, 0);
			label = new Label("Имя:");
			gridpane.add(label, 2, 1);
			TextField workerFirstNameField = new TextField();
			gridpane.add(workerFirstNameField, 3, 1);
			label = new Label("Фамилия:");
			gridpane.add(label, 2, 2);
			TextField workerLastNameField = new TextField();
			gridpane.add(workerLastNameField, 3, 2);
			Button moveInWorkerButton = new Button("Заселить");
			gridpane.add(moveInWorkerButton, 2, 3);
			Button moveOutWorkerButton = new Button("Выселить");
			gridpane.add(moveOutWorkerButton, 3, 3);
			Button getInfoAboutWorkerButton = new Button("Отчёт");
			gridpane.add(getInfoAboutWorkerButton, 2, 4);
			moveInStudentButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						MoveInWindow moveInWindow = new MoveInWindow();
						moveInWindow.display(primaryStage, student);
					}
			});
			moveOutStudentButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					student.moveOut();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Выселен!");
					alert.showAndWait();
				}
			});
			getInfoAboutStudentButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					student.setFirstName(studentFirstNameField.getText());
					student.setLastName(studentLastNameField.getText());
					StudentReportWindow getInfoAboutStudentWindow = new StudentReportWindow();
					getInfoAboutStudentWindow.display(primaryStage, student);
				}
			});
			moveInWorkerButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						MoveInWindow moveInWindow = new MoveInWindow();
						moveInWindow.display(primaryStage, worker);
					}
			});
			moveOutWorkerButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					worker.moveOut();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Выселен!");
				 	alert.showAndWait();
				}
			});
			
			getInfoAboutWorkerButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					worker.setFirstName(workerFirstNameField.getText());
					worker.setLastName(workerLastNameField.getText());
					WorkerReportWindow getInfoAboutWorkerWindow = new WorkerReportWindow();
					getInfoAboutWorkerWindow.display(primaryStage, worker);
				}
			});
			root.setCenter(gridpane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
