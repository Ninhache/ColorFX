package colorfix.app.stages;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/** Fenêtre principale du logiciel **/
public class MainStage extends BaseStage {
    private Button addBtn, removeAllBtn, aboutBtn, calibrateBtn;
    private Region menuSpacer;
    private TableView colorTable;

    private AboutStage aboutWindow;

    public MainStage() {
        // Initialiser les widgets
        BorderPane root = new BorderPane();
        ToolBar menu = new ToolBar();
        addBtn = new Button("Ajouter");
        removeAllBtn = new Button("Tout supprimer");
        aboutBtn = new Button("À propos");

        addBtn.setAlignment(Pos.BASELINE_RIGHT);

        addBtn.setStyle("-fx-base: yellowgreen;");
        removeAllBtn.setStyle("-fx-base: #de5454;");

        menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);

        menu.getItems().addAll(addBtn, removeAllBtn, menuSpacer, aboutBtn);

        root.setTop(menu);

        calibrateBtn = new Button("Calibrer les couleurs");
        calibrateBtn.setDefaultButton(true);

        HBox bottomButtons = new HBox(calibrateBtn);
        bottomButtons.setAlignment(Pos.BASELINE_RIGHT);
        bottomButtons.setPadding(new Insets(8));

        root.setBottom(bottomButtons);

        colorTable = new TableView();

        root.setCenter(colorTable);

        // Info-bulles (tooltip)

        setTooltip(addBtn, "Ouvrir une boite de dialogue pour sélectionner une couleur à ajouter");
        setTooltip(removeAllBtn, "Vider la liste des couleurs");
        setTooltip(aboutBtn, "Afficher des informations sur ce logiciel");
        setTooltip(calibrateBtn, "Recalibrer les couleurs et les afficher dans une nouvelle fenêtre");

        // Gestion des events
        addBtn.setOnAction(this::onAddClicked);
        removeAllBtn.setOnAction(this::onRemoveAllClicked);
        aboutBtn.setOnAction(this::onAboutClicked);
        calibrateBtn.setOnAction(this::onCalibrateClicked);

        // Déclaration de la scène

        Scene scene = new Scene(root, 640, 480);
        setScene(scene);
        setTitle("ColorFX");
    }

    private void onAddClicked(ActionEvent e) {
        System.out.println("ADD");
    }

    private void onRemoveAllClicked(ActionEvent e) {
        System.out.println("REMOVE");
    }

    private void onAboutClicked(ActionEvent e) {
        if (aboutWindow == null || !aboutWindow.isShowing()) {
            aboutWindow = new AboutStage();
            aboutWindow.initOwner(this);
            aboutWindow.show();
        } else {
            aboutWindow.close();
            aboutWindow = null;
        }
    }

    private void onCalibrateClicked(ActionEvent e) {
        System.out.println("CALIBRATE");
    }
}
