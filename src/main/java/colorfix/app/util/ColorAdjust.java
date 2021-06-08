package colorfix.app.util;

import colorfix.app.ColorGrayscaleComparator;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Fonction d'ajustement de couleur **/
public class ColorAdjust {
    public static List<Color> autoAdjust(Collection<Color> colors){
        var comparator = new ColorGrayscaleComparator();

        // Couleurs en entrée
        List<Color> sourceColors = new ArrayList<>(colors);
        sourceColors.sort(comparator);

        // Couleurs en sortie
        List<Color> newColors = new ArrayList<>();

        // Incrément de niveau de gris
        final double delta = 1.0 / (double)(colors.size() - 1);

        var minColor = sourceColors.stream().min(comparator);
        var maxColor = sourceColors.stream().max(comparator);
        final double minBrightness = minColor.isPresent() ? minColor.get().getBrightness() : 0;
        final double maxBrightness = maxColor.isPresent() ? maxColor.get().getBrightness() : 1;

        for (int i = 0; i < colors.size(); ++i) {
            Color oldCol = sourceColors.get(i);

            // Valeurs de la nouvelle couleur
            final double hue = oldCol.getHue();
            //final double sat = oldCol.getSaturation();
            final double sat = Math.pow(oldCol.getSaturation(), 0.8);
            final double bri = Maths.map(Math.pow(i * delta, 0.8), 0, 1, minBrightness, maxBrightness);
            //final double bri = i * brightnessDelta;

            Color newCol = Color.hsb(hue, sat, bri);

            newColors.add(newCol);
        }

        newColors.sort(new ColorGrayscaleComparator());

        return newColors;
    }
}
