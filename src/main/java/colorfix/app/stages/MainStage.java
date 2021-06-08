package colorfix.app.stages;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import colorfix.app.Constants;
import colorfix.app.controls.StyledScene;
import colorfix.app.controls.table.ActionLink;
import colorfix.app.controls.table.ColorTableView;
import colorfix.app.controls.table.TablePlaceholder;
import colorfix.app.stages.dialogs.ColorChooserDialog;
import colorfix.app.util.ColorUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/** Fenêtre principale du logiciel **/
public class MainStage extends ExtendedStage {
    private final Button addBtn, importBtn, exportBtn, removeAllBtn, questionMark ,aboutBtn, calibrateBtn;
    private final Region menuSpacer, menuSpacer2;
    private final ColorTableView colorTable;

    private final CheckBox showCmykColumn;

    private final TablePlaceholder tablePlaceholder;
    private final ActionLink addColorLink, openFileLink;

    private AboutStage aboutWindow;
    private CalibrateStage calibrateWindow;
    private ErrorStage errorWindow;
    private QuestionStage questionWindow;

    private final FileChooser fileChooser;
    private File file;

    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    private SimpleBooleanProperty changed = new SimpleBooleanProperty(true);

    public MainStage() {
        super();
        // Initialiser les widgets
        BorderPane root = new BorderPane();
        ToolBar menu = new ToolBar();

        addBtn = new Button("Ajouter", Constants.loadImage(Constants.ADD_ICON));
        removeAllBtn = new Button("Tout supprimer", Constants.loadImage(Constants.DEL_ICON));
        aboutBtn = new Button("À propos");

        addBtn.setAlignment(Pos.BASELINE_RIGHT);
        importBtn = new Button("Importer", Constants.loadImage(Constants.LOAD_ICON));
        importBtn.setOnAction(this::onImportClicked);

        aboutBtn.setStyle("-fx-base: lightgray;");

        addBtn.setId("toolbarButton");
        removeAllBtn.setId("toolbarButton");
        aboutBtn.setId("toolbarButton");

        exportBtn = new Button("Exporter", Constants.loadImage(Constants.EXP_ICON));
        exportBtn.setOnAction(this::onExportClicked);

        questionMark = new Button("",Constants.loadImage(Constants.QUES_ICON));
        questionMark.setOnAction(this::onQuestion);

        menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);

        showCmykColumn = new CheckBox("Couleurs d'imprimante (CMJN)");

        menu.getItems().addAll(addBtn, removeAllBtn, showCmykColumn, menuSpacer, questionMark,aboutBtn);
        menu.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.setTop(menu);

        calibrateBtn = new Button("Calibrer les couleurs", Constants.loadImage(Constants.REC_ICON));
        calibrateBtn.setDefaultButton(true);

        menuSpacer2 = new Region();
        HBox.setHgrow(menuSpacer2, Priority.ALWAYS);

        HBox bottomButtons = new HBox(importBtn, exportBtn, menuSpacer2, calibrateBtn);

        bottomButtons.setPadding(new Insets(8));
        bottomButtons.setSpacing(8);
        bottomButtons.setId("dark");

        root.setBottom(bottomButtons);

        colorTable = new ColorTableView();

        colorTable.cmykVisibleProperty().bind(showCmykColumn.selectedProperty());
        colorTable.rgbVisibleProperty().bind(showCmykColumn.selectedProperty().not());

        colorTable
            .getRGBColumn()
            .prefWidthProperty()
            .bindBidirectional(colorTable.getCMYKColumn().prefWidthProperty());

        changed.setValue(true);

        exportBtn.disableProperty().bind(colorTable.isEmptyProperty());
        calibrateBtn.disableProperty().bind(colorTable.isEmptyProperty());
        removeAllBtn.disableProperty().bind(colorTable.isEmptyProperty());

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


        // KEYCODE COMBINATION
        Runnable kcImport = ()-> {onImportClicked(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN), kcImport);

        Runnable kcExport = ()-> {onExportClicked(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN), kcExport);

        Runnable kcNew = ()-> {onRemoveAllClicked(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCodeCombination.CONTROL_DOWN), kcNew);

        Runnable kcCalibrate = () -> {onCalibrateClicked(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER, KeyCodeCombination.CONTROL_DOWN), kcCalibrate);

        Runnable kcCopy = ()-> {copy(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN), kcCopy);

        colorTable.getItems().addListener(new ListChangeListener<Color>(){
            @Override
            public void onChanged(Change<? extends Color> c) {
                changed.setValue(true);
                System.out.println(changed.get());
            }
        });
    }

    private void onQuestion(ActionEvent actionEvent) {
        if (questionWindow == null || !questionWindow.isShowing()) {
            questionWindow = new QuestionStage();
            questionWindow.initOwner(this);
            questionWindow.show();
        } else {
            questionWindow.close();
            questionWindow = null;
        }
    }

    private void onExportClicked(ActionEvent e){
        if(changed.getValue()){
            this.file = fileChooser.showSaveDialog(this);
        }
        if(file != null){
            String res = "";
            for(Color c : colorTable.getItems()){
                res = res + ColorUtil.tohexCode(c) + "\n";
            }
            try{
                PrintWriter pw = new PrintWriter(file);
                pw.print(res);
                pw.close();
            }catch(IOException ioex){
                System.out.println(ioex);
            }finally {
                changed.set(false);
            }
        }
    }

    private void onAddClicked(ActionEvent e) {
        Color c = ColorChooserDialog.open();

        if (c != null) {
            colorTable.getItems().add(c);
        }
    }

    private void onRemoveAllClicked(ActionEvent e) {
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
            calibrateWindow = new CalibrateStage(colorTable.getItems(), colorTable.cmykVisibleProperty().get() );
            calibrateWindow.initOwner(this);
            calibrateWindow.show();
        } else {
            calibrateWindow.close();
            calibrateWindow = null;
        }
    }

    private void onImportClicked(ActionEvent e) {
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

    private void copy(ActionEvent e){
        if(!colorTable.getSelectionModel().isEmpty()){
           // System.out.println(ColorUtil.tohexCode( colorTable.getSelectionModel().getSelectedItem()));
            content.putString(ColorUtil.tohexCode( colorTable.getSelectionModel().getSelectedItem()));
            clipboard.setContent(content);
        }
    }
}
