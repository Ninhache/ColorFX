package colorfix.app.controls.table;

import colorfix.app.controls.table.columns.*;
import colorfix.app.enums.ColorSpace;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class ColorTableView extends TableView<Color> {
    public ColorTableView() {
        ColorThumbnailColumn columnThumbnail = new ColorThumbnailColumn();

        //ColorComponentColumn columnRed = new ColorComponentColumn(ColorComponent.RED);
        //ColorComponentColumn columnGreen = new ColorComponentColumn(ColorComponent.GREEN);
        //ColorComponentColumn columnBlue = new ColorComponentColumn(ColorComponent.BLUE);

        HexadecimalValueColumn columnHex = new HexadecimalValueColumn();

        ColorSpaceColumn columnRGB = new ColorSpaceColumn(ColorSpace.RGB);
        ColorSpaceColumn columnHSB = new ColorSpaceColumn(ColorSpace.HSB);

        ActionsColumn columnAction = new ActionsColumn();

        //getColumns().addAll(columnThumbnail, columnHex, columnRed, columnGreen, columnBlue);

        getColumns().addAll(columnThumbnail, columnHex, columnRGB, columnHSB, columnAction);
    }
}
