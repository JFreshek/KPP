package gui;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import flat_management.Flat;
import flat_management.Human;
import flat_management.Student;
import flat_management.Worker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class MainWindow {
	
	public void display(Stage primaryStage) {
		Flat flat = new Flat();
		Stage mainStage = new Stage();
		mainStage.setResizable(false);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,300,305);
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
        for(int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100/2.0);
			gridPane.getColumnConstraints().add(column);
		}
		Label roomStatusLabel = new Label("������ �������: " + refreshStatus(flat));
		gridPane.add(roomStatusLabel, 0, 0, 2, 1);
		
		Button allocateRoomButton = new Button("�������� �������");
		gridPane.add(allocateRoomButton, 0, 1);
		allocateRoomButton.setMaxWidth(Double.MAX_VALUE);
		allocateRoomButton.setMaxHeight(Double.MAX_VALUE);
		
		Button vacateRoomButton = new Button("���������� �������");
		gridPane.add(vacateRoomButton, 1, 1);
		vacateRoomButton.setMaxWidth(Double.MAX_VALUE);
		vacateRoomButton.setMaxHeight(Double.MAX_VALUE);
		
		Label label = new Label("�������� ���� ��������:");
		gridPane.add(label, 0, 2, 2, 1);
		
		RadioButton moveInStudentRadioButton = new RadioButton("��������");
		RadioButton moveInWorkerRadioButton = new RadioButton("��������");
		ToggleGroup moveInToggleGroup = new ToggleGroup();
		moveInStudentRadioButton.setToggleGroup(moveInToggleGroup);
		moveInWorkerRadioButton.setToggleGroup(moveInToggleGroup);
		gridPane.add(moveInStudentRadioButton, 0, 3);
		gridPane.add(moveInWorkerRadioButton, 0, 4);

		Button moveInButton = new Button("��������");
		gridPane.add(moveInButton, 0, 5);
		moveInButton.setMaxWidth(Double.MAX_VALUE);
		moveInButton.setMaxHeight(Double.MAX_VALUE);
		
		Button moveOutButton = new Button("��������");
		gridPane.add(moveOutButton, 1, 5);
		moveOutButton.setMaxWidth(Double.MAX_VALUE);
		moveOutButton.setMaxHeight(Double.MAX_VALUE);

		ObservableList<String> langs = FXCollections.observableArrayList();
		ListView<String> langsListView = new ListView<String>(langs);
        langsListView.setPrefSize(290, 150);
        langsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        langsListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				moveOutButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {	
						try {
								ListIterator<Human> it = flat.getTenants().listIterator(); 
								for(int i = (int) newValue; i > 0; i--) {
									it.next();
								}
								it.next().moveOut();
								flat.evictTenant(it);
								refreshTenantsList(flat, langs);
						} catch (NoSuchElementException e) {
							callAlert("Error! NoSuchElementException..");
						}
					}
				});
						
				
			}
        });
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10, langsListView);
        gridPane.add(flowPane, 0, 6, 2, 1);
        
		allocateRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				flat.allocateRoom();
				roomStatusLabel.setText("������ �������: " + refreshStatus(flat));
			}
		});
		
		vacateRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				flat.vacateRoom();
				refreshTenantsList(flat, langs);
				roomStatusLabel.setText("������ �������: " + refreshStatus(flat));
			}
		});
		
		moveInButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {			
				if(flat.getRoom() != null) {
					MoveInWindow moveInWindow = new MoveInWindow();
					try {
						RadioButton selection = (RadioButton) moveInToggleGroup.getSelectedToggle();
						if (selection.getText().equals("��������")) {
							Student student = new Student();
							moveInWindow.display(mainStage, student, flat, langs);
						}
						if (selection.getText().equals("��������")) {
							Worker worker = new Worker();
							moveInWindow.display(mainStage, worker, flat, langs);
						}
					} catch(NullPointerException e) {
						callAlert("Error! NullPointerException..");
					}
				} else {
					callAlert("������� �� ������!");
				}
				
			}
		});
	
		root.setCenter(gridPane);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	public void refreshTenantsList(Flat flat, ObservableList<String> langs) { 
		langs.clear();
		ListIterator<Human> it = flat.getTenants().listIterator();  
      	while(it.hasNext()) {
  			langs.add(it.next().getInfo());
  		}
      }
	
	public String refreshStatus(Flat flat) {
			if(flat.getRoom() != null)
				return "������";
			return "�� ������";
	}
	
	public void callAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
}
