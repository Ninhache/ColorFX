package colorfix.app.controls.columns;

import colorfix.app.util.ColorUtil;
import colorfix.app.util.TableColumnUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

public class HexadecimalValueColumn extends TableColumn<Color, String> {
    public HexadecimalValueColumn() {
        super("Hexadécimal");
        setCellValueFactory(this::getCellValue);

        TableColumnUtil.setMinWidth(this, 100);
        TableColumnUtil.setCommonBehavior(this, true, true, true);

        setHeaderStyle();
    }

    private void setHeaderStyle() {
        StringBuilder css = new StringBuilder();

        //css.append(String.format("-fx-base: %s;", ColorUtil.tohexCode(COLOR_COMPONENT.getHeaderColor())));
        css.append("-fx-table-cell-border-color: lightgray;");

        setStyle(css.toString());
    }

    protected ObservableValue<String> getCellValue(CellDataFeatures<Color, String> cell) {
        Color color = cell.getValue();

        return new SimpleStringProperty(ColorUtil.tohexCode(color));
    }
}