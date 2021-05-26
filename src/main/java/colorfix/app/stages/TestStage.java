package colorfix.app.stages;

import colorfix.app.controls.picker.ColorSquare;
import colorfix.app.controls.StyledScene;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TestStage extends ExtendedStage {
    public TestStage() {
        ColorSquare root = new ColorSquare();

        HBox.setHgrow(root, Priority.ALWAYS);
        VBox.setVgrow(root, Priority.ALWAYS);

        Scene scene = new StyledScene(root, 500, 500);
        setScene(scene);

        titleProperty().bind(Bindings.concat("ColorFX - HUE: ", Bindings.selectInteger(root.hueProperty()), " SAT: ", Bindings.selectInteger(root.saturationProperty().multiply(100)), " LUM: ", Bindings.selectInteger(root.brightnessProperty().multiply(100))));
    }
}
