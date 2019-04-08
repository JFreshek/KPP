package gui;



import flat_management.Flat;
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

public class StartingWindow {
	
	public void display(Stage primaryStage) {
		try {
			Flat flat = new Flat();
			Stage startingStage = new Stage();
			startingStage.setResizable(false);
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,250, 170); 
			GridPane gridpane = new GridPane();
			gridpane.setPadding(new Insets(10));
			gridpane.setHgap(5);
			gridpane.setVgap(5);
			for(int i = 0; i < 2; i++) {
				ColumnConstraints column = new ColumnConstraints();
				column.setPercentWidth(100/2.0);
				gridpane.getColumnConstraints().add(column);
			}
			Label label = new Label("Welcome!");
			gridpane.add(label, 0, 0);
			
			label = new Label("Введите адрес вашей квартиры.");
			gridpane.add(label, 0, 1, 2, 1);
			
			label = new Label("Улица: ");
			gridpane.add(label, 0, 2);
			TextField streetField = new TextField();
			gridpane.add(streetField, 1, 2);
			
			label = new Label("Дом: ");
			gridpane.add(label, 0, 3);
			TextField houseField = new TextField();
			gridpane.add(houseField, 1, 3);
			
			label = new Label("Квартира: ");
			gridpane.add(label, 0, 4);
			TextField flatNumberField = new TextField();
			gridpane.add(flatNumberField, 1, 4);
			
	        Button continueButton = new Button("Продолжить");
	        gridpane.add(continueButton, 0, 5);
	        
	        continueButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
					flat.setAddress(streetField.getText(), Integer.parseInt(houseField.getText()),
							Integer.parseInt(flatNumberField.getText()));
					if(streetField.getText().equals("")) {
						throw new EmptyStringException();
					}
					if(!streetField.getText().matches("\\D*$")) {
						throw new NumberInStringException();
					}
					MainWindow mainWindow = new MainWindow();
					mainWindow.display(primaryStage);
					startingStage.close();
					} catch(NumberFormatException e) {
						callAlert("Error! NumberFormatException..");
					} catch(EmptyStringException e) {
						callAlert("Error! EmptyStringException..");
					} catch (NumberInStringException e) {
						callAlert("Error! NumberInStringException..");
					}
				}
	        }); 
	        
			root.setCenter(gridpane);
			startingStage.setScene(scene);
			startingStage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void callAlert(String exceptionMessage) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText(exceptionMessage);
		alert.showAndWait();
	}

}
