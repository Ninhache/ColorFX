package colorfix.app.stages;

import colorfix.app.Constants;
import colorfix.app.controls.CopyTableView;
import colorfix.app.controls.StyledScene;
import colorfix.app.util.ColorUtil;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class CalibrateStage extends ExtendedStage{
    private Button  exportBtn, removeAllBtn;
    private CopyTableView colorTable;

    private FileChooser fileChooser;
    
    private File file;

    public CalibrateStage(Collection<Color> collection){
        super();

        BorderPane root = new BorderPane();
        ToolBar menu = new ToolBar();
        
        exportBtn = new Button("Exporter");
        exportBtn.setOnAction(this::onExportClicked);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(Constants.FILTERS);

        removeAllBtn = new Button("Tout supprimer");
        removeAllBtn.setId("toolbarButton");
        removeAllBtn.setOnAction(this::onRemoveAllClicked);


        menu.getItems().addAll(exportBtn, removeAllBtn);
        menu.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.setTop(menu);

        colorTable = new CopyTableView();

        root.setCenter(colorTable);

        ArrayList<Color> newCollec = ColorUtil.toGrayNeo2(collection);
        colorTable.getItems().addAll(newCollec);

        Scene scene = new StyledScene(root);
        setScene(scene);
        setResizable(false);

        setTitle(Constants.APP_NAME);

    }


    private void onRemoveAllClicked(ActionEvent e) {
        System.out.println("REMOVE");
        colorTable.getItems().removeAll(colorTable.getItems());
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
                pw.println(res);
                pw.close();
            }catch(IOException ioex){
                System.out.println(ioex);
            }
        }
    }


}
