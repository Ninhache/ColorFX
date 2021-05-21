package colorfix.app.enums;

import java.util.Arrays;
import java.util.Iterator;

public enum ColorSpace implements Iterable<ColorComponent> {
    RGB("RGB", ColorComponent.RED, ColorComponent.GREEN, ColorComponent.BLUE),
    HSB("HSB", ColorComponent.HUE, ColorComponent.SATURATION, ColorComponent.BRIGHTNESS);

    private final String NAME;
    private final ColorComponent[] COMPONENTS;

    private ColorSpace(String name, ColorComponent... components) {
        NAME = name;
        COMPONENTS = Arrays.copyOf(components, components.length);
    }

    @Override
    public Iterator<ColorComponent> iterator() {
        return Arrays.stream(COMPONENTS).iterator();
    }

    @Override
    public String toString() {
        return NAME;
    }
}
