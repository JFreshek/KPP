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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MoveInWindow {
	
	public void display(Stage primaryStage, Human person) {
		Stage moveInStage = new Stage();
		moveInStage.setResizable(false);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,200,150);
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(10));
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		moveInStage.initModality(Modality.WINDOW_MODAL);
        moveInStage.initOwner(primaryStage);
        for(int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100/2.0);
			gridpane.getColumnConstraints().add(column);
		}
		Label label = new Label("Адрес:");
		gridpane.add(label, 0, 0);
		label = new Label("Улица: ");
		gridpane.add(label, 0, 1);
		TextField streetField = new TextField();
		gridpane.add(streetField, 1, 1);
		label = new Label("Дом: ");
		gridpane.add(label, 0, 2);
		TextField houseField = new TextField();
		gridpane.add(houseField, 1, 2);
		label = new Label("Квартира: ");
		gridpane.add(label, 0, 3);
		TextField flatField = new TextField();
		gridpane.add(flatField, 1, 3);
        Button moveInButton = new Button("Заселить");
        gridpane.add(moveInButton, 0, 4);
        moveInButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				person.moveIn();
				person.address.setStreet(streetField.getText());
				try {
				person.address.setHouse(Integer.parseInt(houseField.getText()));
				person.address.setFlat(Integer.parseInt(flatField.getText()));
				}
				catch(NumberFormatException e){
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Input ERROR");
					alert.showAndWait();
					person.moveOut();
				}
				moveInStage.close();
			}
        });
		root.setCenter(gridpane);
		moveInStage.setScene(scene);
		moveInStage.show();
	}
}
