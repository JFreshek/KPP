package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WorkerReportWindow {
	public void display(Stage primaryStage, Worker worker) {
		Stage getInfoStage = new Stage();
		getInfoStage.setResizable(false);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,200, 150);
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(10));
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		getInfoStage.initModality(Modality.WINDOW_MODAL);
        getInfoStage.initOwner(primaryStage);
        for(int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100/2.0);
			gridpane.getColumnConstraints().add(column);
		}
		Label label = new Label("Рабочий:");
		gridpane.add(label, 0, 0); 
		label = new Label(worker.work());
		gridpane.add(label, 1, 0);
		label = new Label("Имя: ");
		gridpane.add(label, 0, 1);
		Label workerFirstNameLabel = new Label(worker.getFirstName());
		gridpane.add(workerFirstNameLabel, 1, 1);
		label = new Label("Фамилия: ");
		gridpane.add(label, 0, 2);
		Label workerLastNameLabel = new Label(worker.getLastName());
		gridpane.add(workerLastNameLabel, 1, 2);
		if(worker.address!=null) {
			label = new Label("Улица: ");
			gridpane.add(label, 0, 3);
			Label streetLabel = new Label(worker.address.getStreet());
			gridpane.add(streetLabel, 1, 3);
			label = new Label("Дом: ");
			gridpane.add(label, 0, 4);
			Label houseLabel = new Label(worker.address.getHouse());
			gridpane.add(houseLabel, 1, 4);
			label = new Label("Квартира: ");
			gridpane.add(label, 0, 5);
			Label flatLabel = new Label(worker.address.getFlat());
			gridpane.add(flatLabel, 1, 5);
		} else {
			label = new Label("БОМЖ");
			gridpane.add(label, 0, 3);
		}
		root.setCenter(gridpane);
		getInfoStage.setScene(scene);
		getInfoStage.show();
	}
}
