package colorfix.app.controls.picker;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public abstract class ColorSquareAbstract extends HBox {
    // Propriétés
    private boolean localChange = false; // Sert à ne pas créer de boucle de déclenchement d'event infinie
    private SimpleObjectProperty<Color> colorProp = new SimpleObjectProperty<Color>();

    private SimpleDoubleProperty hueProp = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty satProp = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty brightProp = new SimpleDoubleProperty(0);

    protected ColorSquareAbstract() {
        colorProperty().addListener(this::onColorChanged);
        hueProperty().addListener(this::onHueChanged);
        saturationProperty().addListener(this::onSaturationChanged);
        brightnessProperty().addListener(this::onBrightnessChanged);

        colorProperty().set(Color.RED);
    }

    private void onColorChanged(ObservableValue<? extends Color> observable, Color before, Color after) {
        if (!localChange) {
            localChange = true;

            hueProperty().set(after.getHue());
            saturationProperty().set(after.getSaturation());
            brightnessProperty().set(after.getBrightness());

            localChange = false;
        }

        redraw();
    }

    private void onHueChanged(ObservableValue<? extends Number> observable, Number before, Number after) {
        if (!localChange) {
            localChange = true;

            colorProperty().set(Color.hsb(after.doubleValue(), saturationProperty().get(), brightnessProperty().get()));

            localChange = false;
        }

        redraw();
    }

    private void onSaturationChanged(ObservableValue<? extends Number> observable, Number before, Number after) {
        if (!localChange) {
            localChange = true;

            colorProperty().set(Color.hsb(hueProperty().get(), after.doubleValue(), brightnessProperty().get()));

            localChange = false;
        }

        redraw();
    }

    private void onBrightnessChanged(ObservableValue<? extends Number> observable, Number before, Number after) {
        if (!localChange) {
            localChange = true;

            colorProperty().set(Color.hsb(hueProperty().get(), saturationProperty().get(), after.doubleValue()));

            localChange = false;
        }

        redraw();
    }

    public SimpleObjectProperty<Color> colorProperty() {
        return colorProp;
    }

    public SimpleDoubleProperty hueProperty() {
        return hueProp;
    }

    public SimpleDoubleProperty saturationProperty() {
        return satProp;
    }

    public SimpleDoubleProperty brightnessProperty() {
        return brightProp;
    }

    protected abstract void redraw();
}
