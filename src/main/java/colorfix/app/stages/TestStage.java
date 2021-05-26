package colorfix.app.stages;

import colorfix.app.controls.picker.ColorSquare;
import colorfix.app.controls.StyledScene;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TestStage extends ExtendedStage {
    public TestStage() {
        ColorSquare root = new ColorSquare();

        root.colorProperty().set(Color.YELLOWGREEN);

        HBox.setHgrow(root, Priority.ALWAYS);
        VBox.setVgrow(root, Priority.ALWAYS);

        ColorSquare picker = new ColorSquare();

        //root.getChildren().setAll(picker);

        Scene scene = new StyledScene(root, 500, 500);
        setScene(scene);

        //titleProperty().bind(Bindings.concat("ColorFX - HUE: ", Bindings.selectInteger(picker.hueProperty()), " SAT: ", Bindings.selectInteger(picker.saturationProperty().multiply(100)), " LUM: ", Bindings.selectInteger(picker.brightnessProperty().multiply(100))));
    }
}
