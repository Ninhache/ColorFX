package colorfix.app;

import colorfix.app.util.ColorUtil;
import javafx.scene.paint.Color;

public class ColorEncaps {
    private final int CYAN, MAGENTA, YELLOW, BLACK;

    public ColorEncaps(int CYAN, int MAGENTA, int YELLOW, int BLACK) {
        this.CYAN = CYAN;
        this.MAGENTA = MAGENTA;
        this.YELLOW = YELLOW;
        this.BLACK = BLACK;
    }

    public ColorEncaps(int r, int g, int b){

        this.CYAN = ColorUtil.rgbToCMYK(r,g,b).getCYAN();
        this.MAGENTA = ColorUtil.rgbToCMYK(r,g,b).getMAGENTA();
        this.YELLOW = ColorUtil.rgbToCMYK(r,g,b).getYELLOW();
        this.BLACK = ColorUtil.rgbToCMYK(r,g,b).getBLACK();

    }

    public ColorEncaps(Color c){
        this.CYAN = ColorUtil.colorToCMYK(c).getCYAN();
        this.MAGENTA = ColorUtil.colorToCMYK(c).getMAGENTA();
        this.YELLOW = ColorUtil.colorToCMYK(c).getYELLOW();
        this.BLACK = ColorUtil.colorToCMYK(c).getBLACK();
    }

    public int getCYAN() {
        return CYAN;
    }

    public int getMAGENTA() {
        return MAGENTA;
    }

    public int getYELLOW() {
        return YELLOW;
    }

    public int getBLACK() {
        return BLACK;
    }
}
