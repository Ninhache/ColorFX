package colorfix.app.controls.table.columns;

import colorfix.app.stages.dialogs.ColorChooserDialog;
import colorfix.app.util.Assets;
import colorfix.app.util.TableColumnUtil;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class EditActionsColumn extends TableColumn<Color, Void> {
    public EditActionsColumn(){
        super("Actions");

        TableColumnUtil.setMinWidth(this, 200);
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
        private final Button DELETE_BUTTON;
        private final Button EDIT_BUTTON;

        private final HBox PANE;

        public ActionCell() {
            PANE = new HBox();
            PANE.setAlignment(Pos.CENTER);
            PANE.setSpacing(0);
            PANE.setPadding(new Insets(0, 2, 0, 2));
            PANE.prefWidthProperty().bind(widthProperty());

            var padding = PANE.getPadding();
            var halfWidthProp = PANE.widthProperty().subtract(padding.getLeft() + padding.getRight()).divide(2);

            EDIT_BUTTON = new Button("Modifier");
            EDIT_BUTTON.setOnAction(this::onEditButtonClicked);
            EDIT_BUTTON.prefWidthProperty().bind(halfWidthProp);
            EDIT_BUTTON.getStyleClass().addAll("pill-button", "left-pill");

            DELETE_BUTTON = new Button("Supprimer");
            DELETE_BUTTON.setOnAction(this::onDeleteButtonClicked);
            DELETE_BUTTON.prefWidthProperty().bind(halfWidthProp);
            DELETE_BUTTON.getStyleClass().addAll("pill-button", "right-pill");
            DELETE_BUTTON.setStyle("-fx-base: firebrick;");

            PANE.getStylesheets().add(Assets.getAssetPath("/pill-button.css"));
            PANE.getChildren().addAll(EDIT_BUTTON, DELETE_BUTTON);
        }

        private void onDeleteButtonClicked(ActionEvent e) {
            getTableView().getItems().remove(getIndex());
        }

        private void onEditButtonClicked(ActionEvent e) {
            Color currentCol = getTableView().getItems().get(getIndex());

            Color newCol = ColorChooserDialog.open(currentCol);

            if (newCol != null) {
                getTableView().getItems().set(getIndex(), newCol);
            }
        }

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(PANE);
            }
        }
    }
}
