package colorfix.app.controls;

import colorfix.app.controls.table.columns.ColorSpaceColumn;
import colorfix.app.controls.table.columns.ColorThumbnailColumn;
import colorfix.app.controls.table.columns.CopyActionsColumn;
import colorfix.app.controls.table.columns.HexadecimalValueColumn;
import colorfix.app.enums.ColorSpace;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class CopyTableView extends TableView<Color> {
    public CopyTableView() {
        ColorThumbnailColumn columnThumbnail = new ColorThumbnailColumn();

        //ColorComponentColumn columnRed = new ColorComponentColumn(ColorComponent.RED);
        //ColorComponentColumn columnGreen = new ColorComponentColumn(ColorComponent.GREEN);
        //ColorComponentColumn columnBlue = new ColorComponentColumn(ColorComponent.BLUE);

        HexadecimalValueColumn columnHex = new HexadecimalValueColumn();

        ColorSpaceColumn columnRGB = new ColorSpaceColumn(ColorSpace.RGB);
        ColorSpaceColumn columnHSB = new ColorSpaceColumn(ColorSpace.HSB);

        CopyActionsColumn columnAction = new CopyActionsColumn();

        //getColumns().addAll(columnThumbnail, columnHex, columnRed, columnGreen, columnBlue);

        getColumns().addAll(columnThumbnail, columnHex, columnRGB, columnHSB, columnAction);
    }
}
