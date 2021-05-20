package colorfix.app;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class MainWindow extends Application{

	public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {

    	BorderPane root = new BorderPane();
    	VBox rootCenter = new VBox();


    	FlowPane fpTopMenu = new FlowPane();
    	Button buttonAdd = new Button("Ajouter");
    	Button buttonClearListn = new Button("Tout supprimer");
    	Button buttonAPropos = new Button("A propos");
    	fpTopMenu.getChildren().addAll(buttonAdd, buttonClearListn, buttonAPropos);

		FlowPane fpBotMenu = new FlowPane();
		fpBotMenu.setAlignment(Pos.CENTER_RIGHT);
		Button buttonCalibrate = new Button("Calibrer les couleurs");
		fpBotMenu.getChildren().add(buttonCalibrate);

    	// TABLE VIEW
		// Color column
		TableColumn<cellValue, Color> columnCanvas = new TableColumn<>("Color");
		columnCanvas.setMinWidth(100);
		columnCanvas.setCellValueFactory(new PropertyValueFactory<>("canvas"));

		// Hexa column
		TableColumn<cellValue, String> columnHexa = new TableColumn<>("Code hexadécimal");
		columnHexa.setMinWidth(150);
		columnHexa.setCellValueFactory(new PropertyValueFactory<>("hexCode"));

		// Red column
		TableColumn<cellValue, Integer> columnRed = new TableColumn<>("Red");
		columnRed.setMinWidth(70);
		columnRed.setCellValueFactory(new PropertyValueFactory<>("red"));

		// Blue column
		TableColumn<cellValue, Integer> columnBlue = new TableColumn<>("Blue");
		columnBlue.setMinWidth(70);
		columnBlue.setCellValueFactory(new PropertyValueFactory<>("blue"));

		// Green column
		TableColumn<cellValue, Integer> columnGreen = new TableColumn<>("Green");
		columnGreen.setMinWidth(70);
		columnGreen.setCellValueFactory(new PropertyValueFactory<>("green"));

		// Buttons column
		TableColumn<cellValue, Integer> columnButtons = new TableColumn<>("Actions supplémentaires");
		columnButtons.setMinWidth(150);
		System.out.println(columnButtons.getWidth());
		columnButtons.setCellValueFactory(new PropertyValueFactory<>("buttons"));



		TableView mainTableView = new TableView<>();
		// Warning:(79, 3) Raw use of parameterized class 'TableView'
		mainTableView.setItems(getCellValue());
		// Warning:(81, 3) Unchecked call to 'setItems(ObservableList<S>)' as a member of raw type 'javafx.scene.control.TableView'
		mainTableView.getColumns().addAll(columnCanvas, columnHexa, columnRed, columnBlue, columnGreen, columnButtons);
		// Warning:(83, 3) Unchecked call to 'addAll(E...)' as a member of raw type 'javafx.collections.ObservableList'
    	




    	
    	root.setCenter(rootCenter);
    	root.setTop(fpTopMenu);
    	root.setBottom(fpBotMenu);
    	
    	rootCenter.getChildren().add(mainTableView);

		// 611 = toutes les tailles min des colonnes, + 2, sinon y'a un truc moche en bas
    	Scene mainScene = new Scene(root,612,400);
    	stage.setResizable(false);
    	stage.setScene(mainScene);
    	stage.setTitle("ColorFX");
    	stage.show();
    }

	public ObservableList<cellValue> getCellValue(){
		ObservableList<cellValue> cellValues = FXCollections.observableArrayList();
		cellValues.add(new cellValue(Color.ORANGE));
		cellValues.add(new cellValue(Color.PALEGOLDENROD));
		cellValues.add(new cellValue(Color.DARKBLUE));
		return cellValues;
	}

	public ObservableList<Color> getCellValueONLYCOLOR(){
		ObservableList<Color> colorValues = FXCollections.observableArrayList();
		colorValues.add(Color.ORANGE);
		colorValues.add(Color.PALEGOLDENROD);

		return colorValues;
	}

}

