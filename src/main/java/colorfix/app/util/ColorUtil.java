package colorfix.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import colorfix.app.ColorEncaps;
import javafx.scene.paint.Color;

public class ColorUtil {
    public static String tohexCode(Color color) {
        return String.format("#%02X%02X%02X",
        (int)(color.getRed() * 255),
        (int)(color.getGreen() * 255),
        (int)(color.getBlue() * 255));
    }

    public static Color grayScale(Color color) {
        final double r = 0.30 * color.getRed();
        final double g = 0.59 * color.getGreen();
        final double b = 0.11 * color.getBlue();

        final int gray = (int)Math.round(255 * (r + g + b));

        return Color.grayRgb(gray);
    }


    // TODO Fix la fonction ptdr
    public static ArrayList<Color> toGrayCours(Collection<Color> collection){
        int z;
        ArrayList<Color> grayList = new ArrayList<>();
        for (Color c: collection) {
            z = (int)((c.getRed()*0.3 + c.getGreen()*0.59 + c.getBlue()*0.11)*255);
            grayList.add(Color.rgb(z,z,z));
        }
        return grayList;
    }

    // Fonction de fou mais pas utilisée :pensive:
    public static ArrayList<Color> toGrayNeo1(Collection<Color> collection){
        ArrayList<Color> grayList = new ArrayList<>();
        for (Color c : collection) {
            grayList.add(Color.hsb(c.getHue(), 0, c.getBrightness()));
        }

        return grayList;
    }

    // J'ai placé tous mes bitcoins dessus
    public static ArrayList<Color> toGrayNeo2(Collection<Color> collection){
        ArrayList<Color> grayList = new ArrayList<>();
        double size = collection.size()+1;
        for (int i = 0; i < collection.size() ; i++) {
            grayList.add(Color.hsb(0,0, (i+1)/size));
        }
        return grayList;
    }
    
    public static boolean isAnHexcode(String hex) {
        String res = hex.replaceAll("\\\\n", "");
    	Pattern perfectHex = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
    	Matcher matcher =  perfectHex.matcher(res);
    	return matcher.find();
    }

    public static ColorEncaps rgbToCMYK(int red, int green, int blue){
        int r,g,b,c,m,j,k;;

        r = (int)(red/255);
        g = (green/255);
        b = (int)(blue/255);

        k = 1 - max(r,g,b);
        c = (1 - r - k) / (1-k);
        m = (1 - g - k) / (1-k);
        j = (1 - b - k) / (1-k);

        return new ColorEncaps(c,m,j,k);
    }

    public static ColorEncaps colorToCMYK(Color color){
        return rgbToCMYK((int)color.getRed(), (int)color.getGreen(),(int)color.getBlue());
    }

    public static Color cmykToColor(int c, int m, int y, int k){
        int r,g,b;

        r = 255 * (1-c) * (1-k);
        g = 255 * (1-m) * (1-k);
        b = 255 * (1-y) * (1-k);

        return Color.rgb(r,g,b);
    }

    public static Color colorEncapsToColor(ColorEncaps c){
        return cmykToColor(c.getCYAN(), c.getMAGENTA(), c.getYELLOW(),c.getBLACK());
    }

    public static int max (int first, int second, int third){
        return Math.max(Math.max(first, second), third);
    }

}
