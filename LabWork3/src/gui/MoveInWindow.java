package gui;


import java.util.ListIterator;

import flat_management.Flat;
import flat_management.Human;
import javafx.collections.ObservableList;
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

	public void display(Stage mainStage, Human person, Flat flat, ObservableList<String> langs) {
		Stage moveInStage = new Stage();
		moveInStage.setResizable(false);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,180,100); 
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(10));
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		moveInStage.initModality(Modality.WINDOW_MODAL);
	    moveInStage.initOwner(mainStage);
		for(int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100/2.0);
			gridpane.getColumnConstraints().add(column);
		}
		
		Label label = new Label("Имя:");
		gridpane.add(label, 0, 0);
		TextField firstNameField = new TextField();
		gridpane.add(firstNameField, 1, 0);
		
		label = new Label("Фамилия:");
		gridpane.add(label, 0, 1);
		TextField lastNameField = new TextField();
		gridpane.add(lastNameField, 1, 1);
		
		Button moveInButton = new Button("Заселить");
		gridpane.add(moveInButton, 0, 2, 2, 1);
		moveInButton.setMaxWidth(Double.MAX_VALUE);
		moveInButton.setMaxHeight(Double.MAX_VALUE);
			
		moveInButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
					person.setFirstName(firstNameField.getText());
					if(firstNameField.getText().equals("")) {
						throw new EmptyStringException();
					}
					if(!firstNameField.getText().matches("\\D*$")) {
						throw new NumberInStringException();
					}
					person.setLastName(lastNameField.getText());
					if(lastNameField.getText().equals("")) {
						throw new EmptyStringException();
					}
					if(!lastNameField.getText().matches("\\D*$")) {
						throw new NumberInStringException();
					}
					person.setAddress(flat.getAddress());
					person.setFlat(flat);
					flat.acceptTenant(person);
					refreshTenantsList(flat, langs);
					} catch(EmptyStringException e) {
						callAlert("Error! EmptyStringException..");
					} catch (NumberInStringException e) {
						callAlert("Error! NumberInStringException..");
					}
					moveInStage.close();
				}
		});
			
		root.setCenter(gridpane);
		moveInStage.setScene(scene);
		moveInStage.show();
	}
	
	public void callAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	public void refreshTenantsList(Flat flat, ObservableList<String> langs) { 
		langs.clear();
		ListIterator<Human> it = flat.getTenants().listIterator();  
      	while(it.hasNext()) {
  			langs.add(it.next().getInfo());
  		}
      }
	

}
