package colorfix.app.enums;

import javafx.scene.paint.Color;

import java.util.function.Function;

public enum ColorComponent {
    RED("Rouge", Color.web("de6060"), Color::getRed),
    GREEN("Vert", Color.web("a3cf4a"), Color::getGreen),
    BLUE("Bleu", Color.web("60a7de"), Color::getBlue),
    HUE("Teinte", Color.WHITE, Color::getHue, 1),
    SATURATION("Saturation", Color.WHITE, Color::getSaturation, 100),
    BRIGHTNESS("Luminosité", Color.WHITE, Color::getBrightness, 100);

    private final Color HEADER_COLOR;
    private final String HEADER_NAME;
    private final Function<Color, Double> GETTER;
    private final int INTEGER_VALUE_MAXIMUM;

    private ColorComponent(String headerName, Color headerColor, Function<Color, Double> componentGetter, int max) {
        HEADER_NAME = headerName;
        HEADER_COLOR = headerColor;
        GETTER = componentGetter;
        INTEGER_VALUE_MAXIMUM = max;
    }

    private ColorComponent(String headerName, Color headerColor, Function<Color, Double> componentGetter) {
        this(headerName, headerColor, componentGetter, 255);
    }

    public double getValue01(Color color) {
        return GETTER.apply(color);
    }

    public int getValueInt(Color color) {
        return (int)Math.round(getValue01(color) * INTEGER_VALUE_MAXIMUM);
    }

    public Color getHeaderColor() {
        return HEADER_COLOR;
    }

    @Override
    public String toString() {
        return HEADER_NAME;
    }
}
