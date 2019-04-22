package ui;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.Matrix;
import logic.Ñalculator;

public class MatrixWindow {

	public void display() {
		try {
			Matrix mtrx = new Matrix();
			ExecutorService executor = Executors.newFixedThreadPool(3);
			Stage matrixStage = new Stage();
			matrixStage.setResizable(false);
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 250, 220);
			GridPane gridpane = new GridPane();
			gridpane.setPadding(new Insets(10));
			gridpane.setHgap(5);
			gridpane.setVgap(5);
			for (int i = 0; i < 4; i++) {
				ColumnConstraints column = new ColumnConstraints();
				column.setPercentWidth(100 / 4.0);
				gridpane.getColumnConstraints().add(column);
			}

			Label label = new Label("A = ");
			gridpane.add(label, 0, 1);
			GridPane.setHalignment(label, HPos.RIGHT);
			GridPane.setValignment(label, VPos.CENTER);

			Label labelA11 = new Label("A11 = ");
			gridpane.add(labelA11, 0, 4, 2, 1);

			Label labelA12 = new Label("A12 = ");
			gridpane.add(labelA12, 0, 5, 4, 1);

			Label labelA13 = new Label("A13 = ");
			gridpane.add(labelA13, 0, 6, 4, 1);

			Label labelDet = new Label("det = ");
			gridpane.add(labelDet, 0, 7, 4, 1);

			TextField a11 = new TextField();
			gridpane.add(a11, 1, 0);
			TextField a12 = new TextField();
			gridpane.add(a12, 2, 0);
			TextField a13 = new TextField();
			gridpane.add(a13, 3, 0);
			TextField a21 = new TextField();
			gridpane.add(a21, 1, 1);
			TextField a22 = new TextField();
			gridpane.add(a22, 2, 1);
			TextField a23 = new TextField();
			gridpane.add(a23, 3, 1);
			TextField a31 = new TextField();
			gridpane.add(a31, 1, 2);
			TextField a32 = new TextField();
			gridpane.add(a32, 2, 2);
			TextField a33 = new TextField();
			gridpane.add(a33, 3, 2);

			Button calculateButton = new Button("Calculate");
			gridpane.add(calculateButton, 1, 3, 3, 1);
			calculateButton.setMaxWidth(Double.MAX_VALUE);
			calculateButton.setMaxHeight(Double.MAX_VALUE);

			calculateButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					mtrxInitialization(mtrx, a11, a12, a13, a21, a22, a23, a31, a32, a33);
					try {
						Future<Integer> calculatorAlgAdd11 = executor.submit(new Ñalculator(mtrx, 1, 1));
						Future<Integer> calculatorAlgAdd12 = executor.submit(new Ñalculator(mtrx, 1, 2));
						Future<Integer> calculatorAlgAdd13 = executor.submit(new Ñalculator(mtrx, 1, 3));
						int algAddition11 = calculatorAlgAdd11.get();
						int algAddition12 = calculatorAlgAdd12.get();
						int algAddition13 = calculatorAlgAdd13.get();
						labelDet.setText("det = " + mtrx.getDeterminant(algAddition11, algAddition12, algAddition13));
						labelA11.setText("A11 = " + algAddition11);
						labelA12.setText("A12 = " + algAddition12);
						labelA13.setText("A13 = " + algAddition13);
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
			});
			
			root.setCenter(gridpane);
			matrixStage.setTitle("Calculator");
			matrixStage.setScene(scene);
			matrixStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mtrxInitialization(Matrix mtrx, TextField a11, TextField a12, TextField a13, TextField a21,
			TextField a22, TextField a23, TextField a31, TextField a32, TextField a33) {
		try {
			mtrx.setElement(0, 0, Integer.parseInt(a11.getText()));
			mtrx.setElement(0, 1, Integer.parseInt(a12.getText()));
			mtrx.setElement(0, 2, Integer.parseInt(a13.getText()));
			mtrx.setElement(1, 0, Integer.parseInt(a21.getText()));
			mtrx.setElement(1, 1, Integer.parseInt(a22.getText()));
			mtrx.setElement(1, 2, Integer.parseInt(a23.getText()));
			mtrx.setElement(2, 0, Integer.parseInt(a31.getText()));
			mtrx.setElement(2, 1, Integer.parseInt(a32.getText()));
			mtrx.setElement(2, 2, Integer.parseInt(a33.getText()));
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Values entered incorrectly!");
			alert.showAndWait();
		}
	}
}
