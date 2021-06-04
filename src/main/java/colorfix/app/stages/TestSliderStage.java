package colorfix.app.stages;

import colorfix.app.ExtendedColor;
import colorfix.app.controls.StyledScene;
import colorfix.app.controls.slider.ColorSlider;
import colorfix.app.enums.ColorComponent;
import colorfix.app.enums.ColorSpace;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TestSliderStage extends ExtendedStage {
    private VBox root;

    private SimpleObjectProperty<ExtendedColor> color = new SimpleObjectProperty<ExtendedColor>();

    public TestSliderStage() {
        root = new VBox();
        root.setPadding(new Insets(4));
        root.setSpacing(8);
        root.setAlignment(Pos.CENTER);

        List<ColorSlider> sliders = new ArrayList<ColorSlider>();

        for (ColorComponent c : ColorComponent.values()) {
            if (ColorSpace.HSB.contains(c)) continue;

            var slider = new ColorSlider(c);
            HBox.setHgrow(slider, Priority.ALWAYS);
            sliders.add(slider);
        }

        root.getChildren().addAll(sliders);

        var scene = new StyledScene(root, 500, 500);
        setScene(scene);
    }
}
