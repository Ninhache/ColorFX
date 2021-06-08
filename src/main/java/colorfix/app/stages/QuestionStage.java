package colorfix.app.stages;

import colorfix.app.Constants;
import colorfix.app.controls.StyledScene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class QuestionStage extends ExtendedStage{

    private Button closeBtn;

    public QuestionStage() {
        super();

        VBox root = new VBox();
        root.setPadding(new Insets(18));
        root.setSpacing(10);

        Separator separator = new Separator(Orientation.HORIZONTAL);

        closeBtn = new Button("Fermer");
        closeBtn.setDefaultButton(true);
        closeBtn.setId("toolbarButton");

        closeBtn.setOnAction(this::onCloseBtnClicked);


        Scene scene = new StyledScene(root, 300,150);
        setScene(scene);
        setResizable(false);

        setTitle("Tips");
        closeBtn.requestFocus();

        getIcons().add(Constants.APP_ICON);

        Label kcImport = new Label("Ctrl+O");
        Label kcSave = new Label("Ctrl+S");
        Label kcNew = new Label("Ctrl+N");
        Label kcCpy = new Label("Ctrl+C");

        Label labelImport = new Label("Ouvrir");
        Label labelSave = new Label("Sauvegarder");
        Label labelNew = new Label("Nouveau");
        Label labelCpy = new Label("Copier");

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        Region spacer4 = new Region();

        HBox kcOne = new HBox();
        kcOne.getChildren().addAll(kcImport, spacer1,labelImport);
        kcOne.setHgrow(spacer1, Priority.ALWAYS);
        kcOne.setAlignment(Pos.CENTER);
        HBox kcTwo = new HBox();
        kcTwo.getChildren().addAll(kcSave,spacer2,labelSave);
        kcTwo.setHgrow(spacer2, Priority.ALWAYS);
        kcTwo.setAlignment(Pos.CENTER);
        HBox kcThree = new HBox();
        kcThree.getChildren().addAll(kcNew,spacer3,labelNew);
        kcThree.setHgrow(spacer3, Priority.ALWAYS);
        kcThree.setAlignment(Pos.CENTER);
        HBox kcFour = new HBox();
        kcFour.getChildren().addAll(kcCpy,spacer4,labelCpy);
        kcFour.setHgrow(spacer4, Priority.ALWAYS);
        kcFour.setAlignment(Pos.CENTER);

        root.getChildren().addAll(kcOne, kcTwo, kcThree, kcFour, separator, closeBtn);
        root.setAlignment(Pos.CENTER);
    }

    private void onCloseBtnClicked(ActionEvent e) {
        this.close();
    }

}
