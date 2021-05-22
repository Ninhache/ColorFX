package colorfix.app.util;

import javafx.scene.control.TableColumn;

public class TableColumnUtil {
    public static void setMinWidth(TableColumn column, double width) {
        column.setPrefWidth(width);
        column.setMinWidth(width);
    }

    public static void setCommonBehavior(TableColumn column, boolean reorderable, boolean resizable, boolean sortable) {
        column.setReorderable(reorderable);
        column.setResizable(resizable);
        column.setSortable(sortable);
    }
}
