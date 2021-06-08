package colorfix.app.stages;

import colorfix.app.Constants;
import colorfix.app.controls.CopyTableView;
import colorfix.app.controls.StyledScene;
import colorfix.app.util.ColorAdjust;
import colorfix.app.util.ColorUtil;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToolBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CalibrateStage extends ExtendedStage{
    private Button  exportBtn;
    private CopyTableView colorTable;
    private final CheckBox showCmykColumn;
    private FileChooser fileChooser;
    
    private File file;
    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    public CalibrateStage(Collection<Color> collection, boolean isCmykVisible){
        super();

        BorderPane root = new BorderPane();
        ToolBar menu = new ToolBar();

        exportBtn = new Button("Exporter");
        exportBtn.setOnAction(this::onExportClicked);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(Constants.FILTERS);
        
        showCmykColumn = new CheckBox("Couleurs d'imprimante (CMJN)");

        menu.getItems().addAll(exportBtn, showCmykColumn);
        menu.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.setTop(menu);

        colorTable = new CopyTableView();

        root.setCenter(colorTable);

        //ArrayList<Color> newCollec = ColorUtil.toGrayNeo2(collection);
        List<Color> newCollec = ColorAdjust.autoAdjust(collection);

        colorTable.getItems().addAll(newCollec);
        
        if(isCmykVisible) {
        	showCmykColumn.setSelected(isCmykVisible);
        }
        
        colorTable.cmykVisibleProperty().bind(showCmykColumn.selectedProperty());
        colorTable.rgbVisibleProperty().bind(showCmykColumn.selectedProperty().not());

        Scene scene = new StyledScene(root);
        setScene(scene);
        setResizable(false);

        setTitle(Constants.APP_NAME);

        Runnable kcExport = ()-> {onExportClicked(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN), kcExport);

        Runnable kcCopy = ()-> {copy(new ActionEvent());};
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN), kcCopy);
    }

    private void onExportClicked(ActionEvent e){
        file = fileChooser.showSaveDialog(this);

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
            }
        }
    }

    private void copy(ActionEvent e){
        System.out.println("Copy");
        if(!colorTable.getSelectionModel().isEmpty()){
            System.out.println(ColorUtil.tohexCode( colorTable.getSelectionModel().getSelectedItem()));
            content.putString(ColorUtil.tohexCode( colorTable.getSelectionModel().getSelectedItem()));
            clipboard.setContent(content);
        }
    }
}
