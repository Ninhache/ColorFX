package colorfix.app.controls;

import colorfix.app.ExtendedColor;
import colorfix.app.util.ColorUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColorThumbnail extends Pane {
    protected final Canvas canvas;
    protected final GraphicsContext graphics;

    private ExtendedColor color;

    public ColorThumbnail() {
        this(Color.RED);
    }

    public ColorThumbnail(Color color) {
        this(new ExtendedColor(color));
    }

    public ColorThumbnail(ExtendedColor color) {
        this.color = color;

        canvas = new Canvas();
        graphics = canvas.getGraphicsContext2D();

        // Liaison de la taille du canvas à celle du conteneur parent
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
        colorProperty().addListener(e -> draw());

        getChildren().add(canvas);
    }

    public void draw() {
        final double halfWidth = getWidth() / 2;

        Color c = colorProperty().get();

        graphics.setFill(c);
        graphics.fillRect(0,0,halfWidth, getHeight());

        graphics.setFill(ColorUtil.grayScale(c));
        graphics.fillRect(halfWidth,0,halfWidth, getHeight());
    }

    @Override
    /** Autorise le redimentionnement de ce widget **/
    public boolean isResizable() {
        return true;
    }

    public SimpleObjectProperty<Color> colorProperty() {
        return color.colorProperty();
    }
}
