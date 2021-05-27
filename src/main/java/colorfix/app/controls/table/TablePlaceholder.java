package colorfix.app.controls.table;

import colorfix.app.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.Collection;

public class TablePlaceholder extends VBox {
    private final Label TITLE, DESCRIPTION;

    private Hyperlink[] actions;

    public TablePlaceholder(String title, String description) {
       this(title, description, new Node[0]);
    }

    public TablePlaceholder(String title, String description, Node... widgets) {
        this(title, description, Arrays.asList(widgets));
    }

    public TablePlaceholder(String title, String description, Collection<Node> widgets) {
        this(title, null, description, widgets);
    }

    public TablePlaceholder(String title, Image graphics, String description, Node... widgets) {
        this(title, graphics, description, Arrays.asList(widgets));
    }

    public TablePlaceholder(String title, Image graphics, String description, Collection<Node> widgets) {
        setSpacing(8);
        setPadding(new Insets(4));
        setAlignment(Pos.CENTER);

        HBox titleBox = new HBox();
        titleBox.setSpacing(8);
        titleBox.setPadding(Insets.EMPTY);
        titleBox.setAlignment(Pos.CENTER);

        TITLE = new Label(title);
        TITLE.setFont(new Font("Arial", 32));
        TITLE.setTextFill(Color.LIGHTGRAY);

        if (graphics != null) {
            ImageView icon = new ImageView(graphics);
            icon.setPreserveRatio(true);
            icon.setSmooth(true);
            icon.setCache(true);

            icon.setFitHeight(TITLE.getFont().getSize());
            titleBox.getChildren().add(icon);
        }

        titleBox.getChildren().add(TITLE);

        DESCRIPTION = new Label(description);
        DESCRIPTION.setTextFill(Color.GRAY);

        getChildren().addAll(titleBox, DESCRIPTION);

        if (widgets != null && widgets.size() > 0) {

            Region spacer = new Region();
            spacer.setMinHeight(0);
            spacer.setPrefHeight(4);
            spacer.maxHeightProperty().bind(spacer.prefHeightProperty());
            HBox.setHgrow(spacer, Priority.ALWAYS);

            FlowPane container = new FlowPane(Orientation.HORIZONTAL);

            container.setPadding(Insets.EMPTY);
            container.setAlignment(Pos.CENTER);
            container.setVgap(2);
            container.setHgap(4);
            container.setMaxWidth(500);

            HBox.setHgrow(container, Priority.ALWAYS);

            container.getChildren().addAll(widgets);

            getChildren().addAll(spacer, container);
        }
    }
}
