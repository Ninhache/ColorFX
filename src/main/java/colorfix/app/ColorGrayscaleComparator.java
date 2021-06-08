package colorfix.app;

import colorfix.app.util.ColorUtil;
import javafx.scene.paint.Color;

import java.util.Comparator;

public class ColorGrayscaleComparator implements Comparator<Color> {
    @Override
    public int compare(Color c1, Color c2) {
        final double grayScale1 = ColorUtil.grayScaleValue(c1);
        final double grayScale2 = ColorUtil.grayScaleValue(c2);

        return Double.compare(grayScale1, grayScale2);
    }
}