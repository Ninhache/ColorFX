package colorfix.app.controls.columns;

import colorfix.app.util.ColorUtil;
import colorfix.app.util.TableColumnUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class CopyActionsColumn extends TableColumn<Color, Void> {

    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    public CopyActionsColumn(){
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
            BUTTON = new Button("Copier");
            BUTTON.setOnAction(this::onButtonClicked);
            BUTTON.prefWidthProperty().bind(widthProperty());
        }

        private void onButtonClicked(ActionEvent e) {
            content.putString(ColorUtil.tohexCode(getTableView().getItems().get(getIndex())));
            clipboard.setContent(content);
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
