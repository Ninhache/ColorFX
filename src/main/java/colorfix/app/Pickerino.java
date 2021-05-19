package colorfix.app;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Pickerino extends Application {
	ArrayList<String> listeCouleur;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
    	listeCouleur = new ArrayList<>();
    	ListView<String> lv = new ListView<>();
    	lv.getItems().addAll(listeCouleur);
        lv.setMinWidth(100);
        stage.setTitle("ColorPicker");
        Scene scene = new Scene(new HBox(20.0D), 750, 350);
        HBox box = (HBox)scene.getRoot();
        box.setPadding(new Insets(5.0D, 5.0D, 5.0D, 5.0D));
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);
        final Text text = new Text("Try the color picker!");
        text.setFont(Font.font("Verdana", 20.0D));
        text.setFill((Paint)colorPicker.getValue());



        Button boutonAjout = new Button("Add color");
        boutonAjout.setCursor(Cursor.CROSSHAIR); // https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/Cursor.html
        
        boutonAjout.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        	if(!listeCouleur.contains((Color)colorPicker.getValue())) {
        		if(lv.getItems().add(colorPicker.getValue().toString())) {
                    listeCouleur.add(colorPicker.getValue().toString());
                }
        	}
        	System.out.println(listeCouleur);
        });
        
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                text.setFill((Paint)colorPicker.getValue());
            }
        });

        stage.widthProperty().addListener(e ->{
            System.out.println("width =" + stage.getWidth());
        });

        stage.heightProperty().addListener(e ->{
            System.out.println("height =" + stage.getHeight());
        });

        lv.setCellFactory(new Callback<ListView<String>,
                                    ListCell<String>>() {
                                @Override
                                public ListCell<String> call(ListView<String> list) {
                                    return new MonRenduDeCellule();
                                }
                            }
        );

        box.getChildren().addAll(new Node[]{colorPicker, text, boutonAjout, lv});
        stage.setScene(scene);
        stage.show();
    }
    
    
}