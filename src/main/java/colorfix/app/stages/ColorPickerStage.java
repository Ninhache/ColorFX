package colorfix.app.stages;

import colorfix.app.ExtendedColor;
import colorfix.app.controls.StyledScene;
import colorfix.app.controls.picker.ColorSquare;
import colorfix.app.controls.slider.ColorSliderTabs;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorPickerStage extends ExtendedStage {
    private ColorSquare picker;
    private ColorSliderTabs sliders;

    private ExtendedColor color;

    private HBox root;

    public ColorPickerStage() {
        this(Color.RED);
    }

    public ColorPickerStage(Color c) {
        root = new HBox();
        root.setSpacing(0);

        picker = new ColorSquare();
        sliders = new ColorSliderTabs();

        Tab pickerTab = new Tab("Couleur", picker);
        pickerTab.setClosable(false);
        TabPane pickerPane = new TabPane(pickerTab);


        root.getChildren().addAll(pickerPane, sliders);

        color = new ExtendedColor(c);

        picker.colorProperty().bindBidirectional(color.colorProperty());
        sliders.colorProperty().bindBidirectional(color.colorProperty());

        picker.prefHeightProperty().bind(root.heightProperty());
        picker.prefWidthProperty().bind(picker.prefHeightProperty());

        HBox.setHgrow(sliders, Priority.ALWAYS);
        VBox.setVgrow(sliders, Priority.ALWAYS);

        StyledScene scene = new StyledScene(root, 680, 320);
        setScene(scene);
    }
}