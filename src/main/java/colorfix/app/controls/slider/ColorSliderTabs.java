package colorfix.app.controls.slider;

import colorfix.app.ExtendedColor;
import colorfix.app.enums.ColorSpace;
import colorfix.app.util.Assets;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorSliderTabs extends TabPane {
    private final ExtendedColor COLOR;

    public ColorSliderTabs() {
        this(Color.RED);
    }

    public ColorSliderTabs(Color color) {
        this(new ExtendedColor(color));
    }

    public ColorSliderTabs(ExtendedColor color) {
        getStylesheets().add(Assets.getAssetPath("/color-tabs.css"));
        this.COLOR = color;

        for (ColorSpace cSpace : ColorSpace.values()) {
            if (cSpace == ColorSpace.HSB) continue;

            ColorSpaceSliderTab tab = new ColorSpaceSliderTab(color, cSpace);

            getTabs().add(tab);
        }

        color.refresh();
    }

    public SimpleObjectProperty<Color> COLORProperty() {
        return COLOR.colorProperty();
    }
}