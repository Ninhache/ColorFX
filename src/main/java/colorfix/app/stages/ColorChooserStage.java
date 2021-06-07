package colorfix.app.stages;

import colorfix.app.controls.ColorThumbnail;
import colorfix.app.controls.StyledScene;
import colorfix.app.controls.picker.ColorSquare;
import colorfix.app.controls.slider.ColorSliderTabs;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;

public class ColorChooserStage extends ExtendedStage {
    private ColorSquare square;
    private ColorThumbnail thumb;
    private ColorSliderTabs sliders;

    public ColorChooserStage() {
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);

        // INSTANTIATE: Color picker + preview
        square = new ColorSquare();
        square.setPrefSize(300, 300);

        thumb = new ColorThumbnail();
        thumb.setPrefSize(300, 64);

        sliders = new ColorSliderTabs();
        sliders.setPrefSize(300, 250);

        // LAYOUT: Color picker + preview

        TabPane colorTabPane = new TabPane();

        HBox thumbPane = new HBox(thumb);
        thumbPane.setPadding(new Insets(12));

        VBox colorRoot = new VBox(square, thumbPane);

        Tab colorTab = new Tab("Couleur", colorRoot);
        colorTab.setClosable(false);
        colorTabPane.getTabs().add(colorTab);

        VBox sliderRoot = new VBox();

        sliderRoot.getChildren().add(sliders);

        root.getChildren().addAll(colorTabPane, sliderRoot);

        setGrow(square, Priority.ALWAYS, Priority.ALWAYS);
        setGrow(thumb, Priority.ALWAYS, Priority.ALWAYS);
        setGrow(sliders, Priority.ALWAYS, Priority.ALWAYS);
        setGrow(thumbPane, Priority.ALWAYS, Priority.SOMETIMES);
        setGrow(colorTabPane, Priority.SOMETIMES, Priority.ALWAYS);

        thumb.colorProperty().bind(square.colorProperty());
        sliders.COLORProperty().bindBidirectional(square.colorProperty());

        StyledScene scene = new StyledScene(root);
        setScene(scene);
    }

    private void setGrow(Node node, Priority hgrow, Priority vgrow) {
        HBox.setHgrow(node, hgrow);
        VBox.setVgrow(node, vgrow);
    }
}
