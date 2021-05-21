package colorfix.app.controls.columns;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

public class ColorThumbnailColumn extends TableColumn<Color, Canvas> {
    public ColorThumbnailColumn() {
        super("Couleur");
        setCellValueFactory(this::getCellValue);

        setPrefWidth(90);
        setReorderable(false);
        setResizable(false);
        setSortable(false);
        setHeaderStyle();
    }

    private void setHeaderStyle() {
        setStyle("-fx-table-cell-border-color: transparent;");
    }

    protected ObservableValue<Canvas> getCellValue(CellDataFeatures<Color, Canvas> cell) {
        Color color = cell.getValue();
        double size = 20;

        Canvas canvas = new Canvas(getPrefWidth(), size);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        return new SimpleObjectProperty<Canvas>(canvas);
    }
}
