package colorfix.app.controls.table.columns;

import colorfix.app.util.TableColumnUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ActionsColumn extends TableColumn<Color, Void> {
    public ActionsColumn(){
        super("Actions");

        TableColumnUtil.setMinWidth(this, 100);
        TableColumnUtil.setCommonBehavior(this, true, true, false);

        setCellFactory(new ActionCellFactory());
    }

    private class ActionCellFactory implements Callback<TableColumn<Color, Void>, TableCell<Color, Void>> {
        @Override
        public TableCell<Color, Void> call(TableColumn<Color, Void> param) {
            return new ActionCell();
        }
    }

    private class ActionCell extends TableCell<Color, Void> {
        private final Button BUTTON;

        public ActionCell() {
            BUTTON = new Button("Supprimer");
            BUTTON.setOnAction(this::onButtonClicked);
            BUTTON.prefWidthProperty().bind(widthProperty());
        }

        private void onButtonClicked(ActionEvent e) {
            getTableView().getItems().remove(getIndex());
        }

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(BUTTON);
            }
        }
    }
}
