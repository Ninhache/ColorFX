package colorfix.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.paint.Color;

public class ColorUtil {
    public static String tohexCode(Color color) {
        return String.format("#%02X%02X%02X",
        (int)(color.getRed() * 255),
        (int)(color.getGreen() * 255),
        (int)(color.getBlue() * 255));
    }

    // TODO Fix la fonction ptdr
    public static ArrayList<Color> toGrayCours(Collection<Color> collection){
        int z;
        ArrayList<Color> grayList = new ArrayList<>();
        for (Color c: collection) {
            System.out.println((double)(c.getRed()*0.3));
            System.out.println((double)(c.getGreen()*0.59));
            System.out.println((double)(c.getBlue()*0.11));
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
}
