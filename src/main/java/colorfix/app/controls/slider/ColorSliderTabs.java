package colorfix.app.controls.slider;

import colorfix.app.ExtendedColor;
import colorfix.app.enums.ColorComponent;
import colorfix.app.enums.ColorSpace;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSliderTabs extends TabPane {
    private ExtendedColor color;

    public ColorSliderTabs() {
        this(Color.RED);
    }

    public ColorSliderTabs(Color color) {
        this(new ExtendedColor(color));
    }

    public ColorSliderTabs(ExtendedColor color) {
        this.color = color;

        for (ColorSpace cSpace : ColorSpace.values()) {
            //if (cSpace == ColorSpace.HSB) continue;

            VBox page = new VBox();

            page.setPadding(new Insets(12));
            page.setSpacing(12);
            page.setAlignment(Pos.TOP_CENTER);

            for (ColorComponent cComponent : cSpace) {
                ColorSlider slider = new ColorSlider(cComponent);
                HBox.setHgrow(slider, Priority.ALWAYS);

                setBindings(slider);

                page.getChildren().add(slider);
            }

            ScrollPane scroll = new ScrollPane(page);

            page.prefWidthProperty().bind(scroll.widthProperty());
            scroll.prefHeightProperty().bind(page.prefHeightProperty());

            Tab tab = new Tab(cSpace.toString(), scroll);
            tab.setClosable(false);
            getTabs().add(tab);
        }

        color.refresh();
    }

    private void setBindings(ColorSlider slider) {
        ColorComponent c = slider.getComponent();

        var slideProp = slider.valueProperty();
        var colorProp = c.value01Property(color);

        colorProp.bindBidirectional(slideProp);
    }

    public SimpleObjectProperty<Color> colorProperty() {
        return color.colorProperty();
    }
}