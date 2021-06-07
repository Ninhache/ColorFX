package colorfix.app.controls.table.columns;

import colorfix.app.util.TableColumnUtil;
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

        TableColumnUtil.setMinWidth(this, 75);
        TableColumnUtil.setCommonBehavior(this, true, false, true);

        setHeaderStyle();
    }

    private void setHeaderStyle() {
        setStyle("-fx-table-cell-border-color: lightgray;");
    }

    protected ObservableValue<Canvas> getCellValue(CellDataFeatures<Color, Canvas> cell) {
        Color color = cell.getValue();
        double sizeW = cell.getTableColumn().getWidth();

        Canvas canvas = new Canvas(sizeW, 25);

        canvas.widthProperty().bind(widthProperty());
        canvas.widthProperty().addListener(x -> drawCanvas(canvas, color));

        drawCanvas(canvas, color);

        return new SimpleObjectProperty<Canvas>(canvas);
    }

    private void drawCanvas(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        double width = getWidth();
        //System.out.println(width);

        gc.setFill(color);
        gc.fillRect(0, 0, width-10, canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0, width-10, canvas.getHeight());
    }
}
