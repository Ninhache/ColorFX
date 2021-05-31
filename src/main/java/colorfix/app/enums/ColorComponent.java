package colorfix.app.enums;

import colorfix.app.ExtendedColor;
import javafx.scene.paint.Color;

import java.util.function.Function;

public enum ColorComponent {
    RED        ("Rouge",      Color.web("de6060"), ExtendedColor::getRed,        255),
    GREEN      ("Vert",       Color.web("a3cf4a"), ExtendedColor::getGreen,      255),
    BLUE       ("Bleu",       Color.web("60a7de"), ExtendedColor::getBlue,       255),
    HUE        ("Teinte",     Color.web("ffffff"), ExtendedColor::getHue,          1),
    SATURATION ("Saturation", Color.web("ffffff"), ExtendedColor::getSaturation, 100),
    BRIGHTNESS ("Luminosité", Color.web("ffffff"), ExtendedColor::getBrightness, 100),
    CYAN       ("Cyan",       Color.web("14d2d9"), ExtendedColor::getCyan,       100),
    MAGENTA    ("Magenta",    Color.web("d94acd"), ExtendedColor::getMagenta,    100),
    YELLOW     ("Jaune",      Color.web("d9c345"), ExtendedColor::getYellow,     100),
    BLACK      ("Noir",       Color.web("424242"), ExtendedColor::getBlack,      100);

    private final Color HEADER_COLOR;
    private final String HEADER_NAME;
    private final Function<ExtendedColor, Double> GETTER;
    private final int INTEGER_VALUE_MAXIMUM;

    private ColorComponent(String headerName, Color headerColor, Function<ExtendedColor, Double> componentGetter, int max) {
        HEADER_NAME = headerName;
        HEADER_COLOR = headerColor;
        GETTER = componentGetter;
        INTEGER_VALUE_MAXIMUM = max;
    }

    private ColorComponent(String headerName, Color headerColor, Function<ExtendedColor, Double> componentGetter) {
        this(headerName, headerColor, componentGetter, 255);
    }

    public double getValue01(ExtendedColor color) {
        return GETTER.apply(color);
    }

    public double getValue01(Color color) {
        return getValue01(new ExtendedColor(color));
    }

    public int getValueInt(ExtendedColor color) {
        return (int)Math.round(getValue01(color) * INTEGER_VALUE_MAXIMUM);
    }

    public int getValueInt(Color color) {
        return getValueInt(new ExtendedColor(color));
    }

    public Color getHeaderColor() {
        return HEADER_COLOR;
    }

    @Override
    public String toString() {
        return HEADER_NAME;
    }
}
