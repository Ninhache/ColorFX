package colorfix.app.controls.columns;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

public class ActionsColumn extends TableColumn<Color, Button> {

    public Color color;
    public ActionsColumn(){
        super("Actions");
        setCellValueFactory(this::getCellValue);

        setPrefWidth(100);
        setReorderable(false);
        setResizable(false);
        setSortable(false);

    }

    protected ObservableValue<Button> getCellValue(CellDataFeatures<Color, Button> cell){
        this. color = cell.getValue();
        Button button = new Button("Supprimer");

        button.setOnAction(this::removeTheSelectedOne);

        return new SimpleObjectProperty<Button>(button);
    }

    private void removeTheSelectedOne(ActionEvent e) {
        System.out.println("REMOVE");
        super.getTableView().getSelectionModel().getSelectedItem();
        //System.out.println(super.getTableView().getSelectionModel().getSelectedItem());

        for(Color c : super.getTableView().getItems()){
            if(c == super.getTableView().getSelectionModel().getSelectedItem()){
                System.out.println("trouvé ptdr");
                // Bon j'sais pas du tt comment supprimer la ligne donc j'ai juste.. fais en sorte de pouvoir la trouver :^)
            }
        }
    }

    // removeAllBtn.setOnAction(this::onRemoveAllClicked);
    //
}
