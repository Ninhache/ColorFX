package colorfix.app.controls.table;

import colorfix.app.controls.table.columns.*;
import colorfix.app.enums.ColorSpace;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class ColorTableView extends TableView<Color> {
    private SimpleBooleanProperty hexVisible = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty rgbVisible = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty hsbVisible = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty cmykVisible = new SimpleBooleanProperty(false);


    public ColorTableView() {
        ColorThumbnailColumn columnThumbnail = new ColorThumbnailColumn();

        //ColorComponentColumn columnRed = new ColorComponentColumn(ColorComponent.RED);
        //ColorComponentColumn columnGreen = new ColorComponentColumn(ColorComponent.GREEN);
        //ColorComponentColumn columnBlue = new ColorComponentColumn(ColorComponent.BLUE);

        HexadecimalValueColumn columnHex = new HexadecimalValueColumn();

        ColorSpaceColumn columnRGB = new ColorSpaceColumn(ColorSpace.RGB);
        ColorSpaceColumn columnHSB = new ColorSpaceColumn(ColorSpace.HSB);
        ColorSpaceColumn columnCMYK = new ColorSpaceColumn(ColorSpace.CMYK);

        columnHex.visibleProperty().bind(hexVisible);
        columnRGB.visibleProperty().bind(rgbVisible);
        columnHSB.visibleProperty().bind(hsbVisible);
        columnCMYK.visibleProperty().bind(cmykVisible);

        ActionsColumn columnAction = new ActionsColumn();

        getColumns().addAll(columnThumbnail, columnHex, columnRGB, columnHSB, columnCMYK, columnAction);
    }

    // === PROPRIETES ===

    public SimpleBooleanProperty hexVisibleProperty() {
        return hexVisible;
    }

    public SimpleBooleanProperty rgbVisibleProperty() {
        return rgbVisible;
    }

    public SimpleBooleanProperty hsbVisibleProperty() {
        return hsbVisible;
    }

    public SimpleBooleanProperty cmykVisibleProperty() {
        return cmykVisible;
    }

    // === GETTERS & SETTERS ===
    public boolean isHexVisible() {
        return hexVisible.get();
    }

    public void setHexVisible(boolean value) {
        hexVisible.set(value);
    }

    public boolean isRgbVisible() {
        return rgbVisible.get();
    }

    public void setRgbVisible(boolean value) {
        rgbVisible.set(value);
    }

    public boolean isHsbVisible() {
        return hsbVisible.get();
    }

    public void setHsbVisible(boolean value) {
        hsbVisible.set(value);
    }

    public boolean isCmykVisible() {
        return cmykVisible.get();
    }

    public void setCmykVisible(boolean value) {
        cmykVisible.set(value);
    }
}
