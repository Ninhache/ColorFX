package colorfix.app;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class cellValue {
    private Canvas canvas;
    private Color hexCode;
    private double red;
    private double blue;
    private double green;
    private HBox buttons;
    private Button buttonModify;
    private Button buttonDelete;

    public cellValue(Color hexCode) {
        this.hexCode = hexCode;
        this.red = hexCode.getRed();
        this.blue = hexCode.getBlue();
        this.green = hexCode.getGreen();

        this.buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        this.buttonDelete = new Button("Modifier");
        this.buttonModify = new Button("Supprimer");

        this.buttons.getChildren().addAll(buttonDelete, buttonModify);

        this.canvas = new Canvas(95,25);

        this.canvas.getGraphicsContext2D().setFill(this.hexCode);
        this.canvas.getGraphicsContext2D().fillRect(0,0,this.canvas.getWidth(),this.canvas.getHeight());
        this.canvas.getGraphicsContext2D().setStroke(Color.BLACK);
        this.canvas.getGraphicsContext2D().strokeRect(0,0,this.canvas.getWidth(),this.canvas.getHeight());
    }

    public HBox getButtons() {
        return buttons;
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    public Button getButtonModify() {
        return buttonModify;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public String getHexCode() {
        return hexCode.toString();
    }

    public int getRed() {
        return (int)(red*255);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getBlue() {
        return (int)(blue*255);
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return (int)(green*255);
    }

    public void setGreen(int green) {
        this.green = green;
    }
}
