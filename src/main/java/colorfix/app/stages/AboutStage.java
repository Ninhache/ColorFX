package colorfix.app.stages;

import colorfix.app.ColorFX;
import colorfix.app.Constants;
import colorfix.app.controls.StyledScene;
import colorfix.app.util.Assets;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

/** Fenêtre "À propos" **/
public class AboutStage extends ExtendedStage {
    private Label applicationTitle, applicationDescription;
    private HBox descriptionContainer;
    private Separator separatorA, separatorB;
    private TextArea textZone;
    private Hyperlink gitlabUrl;
    private Button closeBtn;

    public AboutStage() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(18));
        root.setSpacing(6);

        applicationTitle = new Label(Constants.APP_NAME);
        applicationTitle.setFont(new Font("Arial", 32));

        descriptionContainer = new HBox();
        descriptionContainer.setAlignment(Pos.CENTER);
        descriptionContainer.setPadding(Insets.EMPTY);
        descriptionContainer.setSpacing(4);

        HBox.setHgrow(descriptionContainer, Priority.ALWAYS);

        applicationDescription = new Label(Constants.APP_DESCRIPTION);

        gitlabUrl = new Hyperlink("Lien GitLab");
        gitlabUrl.setOnAction(this::onGitLabLinkClicked);

        descriptionContainer.getChildren().addAll(applicationDescription, gitlabUrl);

        separatorA = new Separator(Orientation.HORIZONTAL);
        HBox.setHgrow(separatorA, Priority.ALWAYS);

        //textZone = new TextArea("Description détaillée du projet ici");
        textZone = new TextArea(Assets.readtextFile("about.txt"));
        textZone.setWrapText(true);
        textZone.setEditable(false);

        textZone.setPrefWidth(300);

        separatorB = new Separator(Orientation.HORIZONTAL);
        HBox.setHgrow(separatorB, Priority.ALWAYS);

        closeBtn = new Button("Fermer");
        closeBtn.setDefaultButton(true);
        closeBtn.setOnAction(this::onCloseClicked);

        root.getChildren().addAll(applicationTitle, descriptionContainer, separatorA, textZone, separatorB, closeBtn);

        Scene scene = new StyledScene(root);
        setScene(scene);
        setResizable(false);
        initStyle(StageStyle.UTILITY); // N'avoir que le bouton "fermer" de la fenêtre
        setTitle("À propos");

        closeBtn.requestFocus();
    }

    private void onCloseClicked(ActionEvent e) {
        close();
    }

    private void onGitLabLinkClicked(ActionEvent e) {
        ColorFX.hostServices().showDocument(Constants.GITLAB_URL);
    }
}
