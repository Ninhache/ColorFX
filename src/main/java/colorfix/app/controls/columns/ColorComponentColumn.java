package colorfix.app.controls.columns;

import colorfix.app.enums.ColorComponent;
import colorfix.app.util.ColorUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

public class ColorComponentColumn extends TableColumn<Color, String> {
    private final ColorComponent COLOR_COMPONENT;

    public ColorComponentColumn(ColorComponent component) {
        super(component.toString());
        COLOR_COMPONENT = component;
        setCellValueFactory(this::getCellValue);
        setReorderable(false);
        setResizable(false);
        setSortable(false);
        setHeaderStyle();
    }

    private void setHeaderStyle() {
        StringBuilder css = new StringBuilder();

        css.append(String.format("-fx-base: %s;", ColorUtil.tohexCode(COLOR_COMPONENT.getHeaderColor())));
        css.append("-fx-table-cell-border-color: transparent;");

        setStyle(css.toString());
    }

    protected ObservableValue<String> getCellValue(CellDataFeatures<Color, String> cell) {
        Color color = cell.getValue();

        StringBuilder content = new StringBuilder();
        content.append(COLOR_COMPONENT.getValueInt(color));

        return new SimpleStringProperty(content.toString());
    }
}