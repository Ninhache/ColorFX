package colorfix.app.types;

import colorfix.app.util.Maths;
import javafx.beans.property.SimpleDoubleProperty;

public class SimpleNormalizedProperty extends SimpleDoubleProperty {
    public SimpleNormalizedProperty() {
        super();
    }

    public SimpleNormalizedProperty(double initialValue)
    {
        this();
        set(initialValue);
    }

    @Override
    public double get() {
        return Maths.clamp01(super.get());
    }

    @Override
    public void set(double newValue) {
        super.set(Maths.clamp01(newValue));
    }

    @Override
    public Double getValue() {
        return get();
    }

    @Override
    public void setValue(Number v) {
        set(v.doubleValue());
    }
}
