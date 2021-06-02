package colorfix.app.stages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import colorfix.app.Constants;
import colorfix.app.controls.StyledScene;
import colorfix.app.controls.table.ActionLink;
import colorfix.app.controls.table.ColorTableView;
import colorfix.app.controls.table.TablePlaceholder;
import colorfix.app.util.ColorUtil;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/** Fenêtre principale du logiciel **/
public class MainStage extends ExtendedStage {
    private final Button addBtn, importBtn, removeAllBtn, aboutBtn, calibrateBtn;
    private final Region menuSpacer;
    private final ColorTableView colorTable;

    private final CheckBox showCmykColumn;

    private final TablePlaceholder tablePlaceholder;
    private final ActionLink addColorLink, openFileLink;

    private AboutStage aboutWindow;
    private CalibrateStage calibrateWindow;
    private ErrorStage errorWindow;

    private final FileChooser fileChooser;
    private File file;

    public MainStage() {
        super();
        // Initialiser les widgets
        BorderPane root = new BorderPane();
        ToolBar menu = new ToolBar();

        addBtn = new Button("Ajouter");
        removeAllBtn = new Button("Tout supprimer");
        aboutBtn = new Button("À propos");

        addBtn.setAlignment(Pos.BASELINE_RIGHT);
        importBtn = new Button("Importer");
        importBtn.setOnAction(this::onImportClicked);


        //addBtn.setStyle("-fx-base: yellowgreen;");
        //removeAllBtn.setStyle("-fx-base: #de5454;");
        aboutBtn.setStyle("-fx-base: lightgray;");

        addBtn.setId("toolbarButton");
        removeAllBtn.setId("toolbarButton");
        aboutBtn.setId("toolbarButton");


        menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);

        showCmykColumn = new CheckBox("Couleurs d'imprimante (CMJN)");

        menu.getItems().addAll(addBtn, importBtn, removeAllBtn, showCmykColumn, menuSpacer, aboutBtn);
        menu.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.setTop(menu);

        calibrateBtn = new Button("Calibrer les couleurs");
        calibrateBtn.setDefaultButton(true);

        HBox bottomButtons = new HBox(calibrateBtn);
        bottomButtons.setAlignment(Pos.BASELINE_RIGHT);
        bottomButtons.setPadding(new Insets(8));
        bottomButtons.setSpacing(8);
        bottomButtons.setId("dark");

        root.setBottom(bottomButtons);

        colorTable = new ColorTableView();

        //colorTable.itemsProperty().addListener(x -> {

        colorTable.getItems().addListener((this::onTableModified));


        colorTable.cmykVisibleProperty().bind(showCmykColumn.selectedProperty());

        //calibrateBtn.setDisable(true);


        addColorLink = new ActionLink("Ajouter une couleur", this::onAddClicked);
        openFileLink = new ActionLink("Charger des couleurs depuis un fichier");
        openFileLink.setOnAction(this::onImportClicked);

        tablePlaceholder = new TablePlaceholder(Constants.APP_NAME, Constants.APP_ICON, "C'est un peu vide par ici...", addColorLink, openFileLink);

        colorTable.setPlaceholder(tablePlaceholder);

        root.setCenter(colorTable);

        colorTable.getItems().addAll(Color.WHITE, Color.BLACK, Color.SALMON, Color.OLIVEDRAB, Color.rgb(127, 0, 65), Color.WHITESMOKE, Color.FIREBRICK);

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
        Scene scene = new StyledScene(root);
        setScene(scene);
        setTitle(Constants.APP_NAME);

        // Déclaration du gestionnaire de fichier
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(Constants.FILTERS);
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
        System.out.println((colorTable.getItems().size()));

        if (calibrateWindow == null || !calibrateWindow.isShowing()) {
            calibrateWindow = new CalibrateStage(colorTable.getItems());
            calibrateWindow.initOwner(this);
            calibrateWindow.show();
        } else {
            calibrateWindow.close();
            calibrateWindow = null;
        }
    }

    private void onImportClicked(ActionEvent e){
    	Boolean importSucces = false;
        file = fileChooser.showOpenDialog(this);
        ArrayList<Color> list = new ArrayList<>();
        if(file != null){
            try {
                Scanner sc = new Scanner(file);
                while(sc.hasNext()){
                	String nextLine = sc.nextLine();
                	
                	if(ColorUtil.isAnHexcode(nextLine)) {
                		list.add(Color.web(nextLine));
                	}else {
                		throw new Exception();
                	}
                }
                importSucces = true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exc) {
            	if (errorWindow == null || !errorWindow.isShowing()) {
            		errorWindow = new ErrorStage("Essayez avec un autre fichier");
            		errorWindow.initOwner(this);
            		errorWindow.show();
                } else {
                	errorWindow.close();
                	errorWindow = null;
                }
            } 
        }
        if(importSucces) {
        	 colorTable.getItems().removeAll(colorTable.getItems());
             colorTable.getItems().addAll(list);
             importSucces = false;
        }
    }

    private void onTableModified(ListChangeListener.Change<? extends Color> c) {
    	
    	System.out.println(colorTable.getItems().size());
    	calibrateBtn.setDisable(colorTable.getItems().size() == 0);
    	
    }
}
