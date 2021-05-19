package colorfix.app;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application{

	public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
    	
    	BorderPane root = new BorderPane();
    	VBox rootCenter = new VBox();
    	FlowPane fpMain = new FlowPane();
    	
    	Button b1 = new Button("test");
    	Button buttonAdd = new Button("Ajouter");
    	Button buttonClearListn = new Button("Tout supprimer");
    	Button buttonAPropos = new Button("A propos");
    	
    	fpMain.getChildren().addAll(buttonAdd, buttonClearListn, buttonAPropos);
    	
    	TableView tbView = new TableView<>();
    	
    	TableColumn<String, String> column1 = new TableColumn<>("Prénom");

        TableColumn<String, String> column2 = new TableColumn<>("Nom");
        
    	// http://tutorials.jenkov.com/javafx/tableview.html
        
    	tbView.getColumns().add(column1);
    	tbView.getColumns().add(column2);
    	
    	tbView.getItems().add("nom");
    	tbView.getItems().add("PPPP");
    	
    	root.setCenter(rootCenter);
    	root.setTop(fpMain);
    	
    	rootCenter.getChildren().add(tbView);
    	
    	ListView lv = new ListView();
    	
    	lv.getItems().clear();
    	
    	
    	
    	Scene mainScene = new Scene(root, 500,500);
    	
    	stage.setScene(mainScene);
    	stage.setTitle("ColorFX");
    	stage.show();
    }	
}

