package colorfix.app.controls.slider;

import colorfix.app.ExtendedColor;
import colorfix.app.enums.ColorComponent;
import colorfix.app.enums.ColorSpace;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSliders extends TabPane {
    private ExtendedColor color;

    public ColorSliders() {
        this(Color.RED);
    }

    public ColorSliders(Color color) {
        this(new ExtendedColor(color));
    }

    public ColorSliders(ExtendedColor color) {
        this.color = color;

        VBox page = new VBox();

        for (ColorSpace cSpace : ColorSpace.values()) {
            if (cSpace == ColorSpace.HSB) continue;


            page.setPadding(new Insets(8));
            page.setSpacing(12);
            page.setAlignment(Pos.TOP_CENTER);

            for (ColorComponent cComponent : cSpace) {
                ColorSlider slider = new ColorSlider(cComponent);
                HBox.setHgrow(slider, Priority.ALWAYS);

                setBindgs(slider);

                page.getChildren().add(slider);
            }

            ScrollPane scroll = new ScrollPane(page);

            page.prefWidthProperty().bind(scroll.widthProperty());
            page.prefHeightProperty().bind(scroll.heightProperty());

            //Tab tab = new Tab(cSpace.toString(), scroll);
            //tab.setClosable(false);
            //getTabs().add(tab);
        }

        ScrollPane scroll = new ScrollPane(page);

        page.prefWidthProperty().bind(scroll.widthProperty());
        page.prefHeightProperty().bind(scroll.heightProperty());

        Tab tab = new Tab("Sliders", scroll);
        tab.setClosable(false);

        getTabs().add(tab);
    }

    private void setBindgs(ColorSlider slider) {
        var sliderProp = slider.valueProperty();

        ColorComponent component = slider.getComponent();
        var colorProp = component.value01Property(color);

        sliderProp.bindBidirectional(colorProp);
    }
}