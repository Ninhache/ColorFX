package colorfix.app.controls.table.columns;

import colorfix.app.controls.ColorThumbnail;
import colorfix.app.stages.dialogs.ColorChooserDialog;
import colorfix.app.util.TableColumnUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

public class ColorThumbnailColumn extends TableColumn<Color, ColorThumbnail> {
    public ColorThumbnailColumn() {
        super("Couleur");
        setCellValueFactory(this::getCellValue);

        TableColumnUtil.setMinWidth(this, 85);
        TableColumnUtil.setCommonBehavior(this, true, true, true);

        setHeaderStyle();
    }

    private void setHeaderStyle() {
        setStyle("-fx-table-cell-border-color: lightgray;");
    }

    protected ObservableValue<ColorThumbnail> getCellValue(CellDataFeatures<Color, ColorThumbnail> cell) {
        Color color = cell.getValue();

        ColorThumbnail thumb = new ColorThumbnail(color);

        thumb.prefWidthProperty().bind(widthProperty());

        return new SimpleObjectProperty<ColorThumbnail>(thumb);
    }

    private void drawCanvas(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double width = canvas.getWidth() - 5;

        gc.setFill(color);
        gc.fillRect(0, 0, width, canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0, width, canvas.getHeight());
    }
}
