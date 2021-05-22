package colorfix.app.stages;

import colorfix.app.Constants;
import colorfix.app.controls.ColorTableView;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/** Fenêtre principale du logiciel **/
public class MainStage extends ExtendedStage {
    private Button addBtn, removeAllBtn, aboutBtn, calibrateBtn;
    private Region menuSpacer;
    private ColorTableView colorTable;

    private AboutStage aboutWindow;

    public MainStage() {
        // Initialiser les widgets
        BorderPane root = new BorderPane();
        ToolBar menu = new ToolBar();
        menu.getStylesheets().add(getClass().getResource("/rename.css").toExternalForm());
        addBtn = new Button("Ajouter");
        removeAllBtn = new Button("Tout supprimer");
        aboutBtn = new Button("À propos");

        addBtn.setAlignment(Pos.BASELINE_RIGHT);

        //addBtn.setStyle("-fx-base: yellowgreen;");
        //removeAllBtn.setStyle("-fx-base: #de5454;");
        addBtn.setId("toolbarButton");
        removeAllBtn.setId("toolbarButton");
        aboutBtn.setId("toolbarButton");


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

        colorTable = new ColorTableView();

        root.setCenter(colorTable);

        colorTable.getItems().addAll(Color.WHITE, Color.BLACK, Color.SALMON, Color.OLIVEDRAB, Color.rgb(127, 0, 65));

        // Info-bulles (tooltip)

        setTooltip(addBtn, Constants.TOOLTIP_ADDBTN);
        setTooltip(removeAllBtn, Constants.TOOLTIP_REMOVALLEBTN);
        setTooltip(aboutBtn, Constants.TOOLTIP_ABOUTBTN);
        setTooltip(calibrateBtn, Constants.TOOLTIP_CALIBRATEBTN);

        // Gestion des events
        addBtn.setOnAction(this::onAddClicked);
        removeAllBtn.setOnAction(this::onRemoveAllClicked);
        aboutBtn.setOnAction(this::onAboutClicked);
        calibrateBtn.setOnAction(this::onCalibrateClicked);

        // Déclaration de la scène

        //Scene scene = new Scene(root, 640, 480);
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle(Constants.APP_NAME);
    }

    private void onAddClicked(ActionEvent e) {
        System.out.println("ADD");
    }

    private void onRemoveAllClicked(ActionEvent e) {
        System.out.println("REMOVE");
        //colorTable.getItems().removeAll(colorTable.getSelectionModel().getSelectedItem());
        colorTable.getItems().removeAll(colorTable.getItems());
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
