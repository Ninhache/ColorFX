package colorfix.app.controls.columns;

import colorfix.app.enums.ColorComponent;
import colorfix.app.enums.ColorSpace;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorSpaceColumn extends TableColumn<Color, String> {
    private final ColorSpace COLOR_SPACE;

    public ColorSpaceColumn(ColorSpace colorSpace) {
        super(colorSpace.toString());
        COLOR_SPACE = colorSpace;

        setReorderable(false);
        setResizable(false);
        setSortable(false);
        setHeaderStyle();

        for (ColorComponent component : colorSpace) {
            getColumns().add(new ColorComponentColumn(component));
        }
    }

    private void setHeaderStyle() {
        setStyle("-fx-table-cell-border-color: transparent;");
    }
}
