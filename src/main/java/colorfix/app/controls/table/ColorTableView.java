package colorfix.app.controls.table;

import colorfix.app.controls.table.columns.*;
import colorfix.app.enums.ColorSpace;
import colorfix.app.stages.dialogs.ColorChooserDialog;
import colorfix.app.util.ColorUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ColorTableView extends TableView<Color> {
    private SimpleBooleanProperty hexVisible = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty rgbVisible = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty cmykVisible = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty hsbVisible = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isEmpty = new SimpleBooleanProperty();

    private ContextMenu contextMenu;
    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

	private final ColorSpaceColumn columnRGB, columnHSB, columnCMYK;


    public ColorTableView() {
        ColorThumbnailColumn columnThumbnail = new ColorThumbnailColumn();

        HexadecimalValueColumn columnHex = new HexadecimalValueColumn();

        columnRGB = new ColorSpaceColumn(ColorSpace.RGB);
        columnHSB = new ColorSpaceColumn(ColorSpace.HSB);
        columnCMYK = new ColorSpaceColumn(ColorSpace.CMYK);
        
        setOnMouseClicked(this::onMouseClicked);

        columnHex.visibleProperty().bind(hexVisible);
        columnRGB.visibleProperty().bind(rgbVisible);
        columnCMYK.visibleProperty().bind(cmykVisible);
        columnHSB.visibleProperty().bind(hsbVisible);
        

        EditActionsColumn columnAction = new EditActionsColumn();

        getColumns().addAll(columnThumbnail, columnHex, columnRGB, columnCMYK, columnHSB, columnAction);
        
        /// Menu contextuel
        contextMenu = new ContextMenu();

        MenuItem menuItemAdd = new MenuItem("Ajouter");
        menuItemAdd.setOnAction(this::onItemAdd);

        MenuItem menuItemMod = new MenuItem("Modifier");
        menuItemMod.setOnAction(this::onItemModify);

        MenuItem menuItemDel = new MenuItem("Supprimer");
        menuItemDel.setOnAction(this::onItemDelete);
        
        MenuItem menuItemCpy = new MenuItem("Copier code hexadécimal");
        menuItemCpy.setOnAction(this::onItemCopy);
        
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem3 = new SeparatorMenuItem();
        
        contextMenu.getItems().addAll(menuItemAdd,separatorMenuItem, menuItemMod, separatorMenuItem2, menuItemDel, separatorMenuItem3, menuItemCpy);
        
        itemsProperty().addListener(e -> {
        	getItems().addListener(this::onTableModified);
        });
        
        setItems(FXCollections.observableArrayList());
    }
    
    private void onMouseClicked(MouseEvent e) {
    	if(e.getButton() == MouseButton.SECONDARY) {
    		contextMenu.show(this, e.getScreenX(), e.getScreenY());
    	}else {
    		contextMenu.hide();
    	}
    }
    
    private void onItemAdd(ActionEvent e) {
        Color c = ColorChooserDialog.open();

        if (c != null) {
            this.getItems().add(c);
        }
    }
    
    private void onTableModified(ListChangeListener.Change<? extends Color> c) {
    	System.out.println("ALORS C VIDE ?" + isEmpty.get());
    	isEmpty.set(getItems().isEmpty());
    }
    
    private void onItemModify(ActionEvent e) {
        int index = getSelectionModel().getFocusedIndex();

        Color oldCol = getItems().get(index);
        Color newCol = ColorChooserDialog.open(oldCol);

        if (newCol != null) {
            getItems().set(index, newCol);
        }

    	//int index = getFocusModel().getFocusedCell().getRow();
    	
    	System.out.println(getItems().get(index).toString());
    }
    
    private void onItemDelete(ActionEvent e) {
    	int index = getFocusModel().getFocusedCell().getRow();
    	getItems().remove(index);
    }
    
    private void onItemCopy(ActionEvent e) {
    	int index = getFocusModel().getFocusedCell().getRow();
    	content.putString(ColorUtil.tohexCode(getItems().get(index)));
    	clipboard.setContent(content);	
    }
    
    // === PROPERTY ===
    
    public ReadOnlyBooleanProperty isEmptyProperty() {
    	return ReadOnlyBooleanProperty.readOnlyBooleanProperty(isEmpty);
    }

    // === COLUMNS ===
    public ColorSpaceColumn getRGBColumn() {
        return columnRGB;
    }

    public ColorSpaceColumn getHSBColumn() {
        return columnHSB;
    }

    public ColorSpaceColumn getCMYKColumn() {
        return columnCMYK;
    }

    // === PROPRIETES ===

    public SimpleBooleanProperty hexVisibleProperty() {
        return hexVisible;
    }

    public SimpleBooleanProperty rgbVisibleProperty() {
        return rgbVisible;
    }

    public SimpleBooleanProperty hsbVisibleProperty() {
        return hsbVisible;
    }

    public SimpleBooleanProperty cmykVisibleProperty() {
        return cmykVisible;
    }

    // === GETTERS & SETTERS ===
    public boolean isHexVisible() {
        return hexVisible.get();
    }

    public void setHexVisible(boolean value) {
        hexVisible.set(value);
    }

    public boolean isRgbVisible() {
        return rgbVisible.get();
    }

    public void setRgbVisible(boolean value) {
        rgbVisible.set(value);
    }

    public boolean isHsbVisible() {
        return hsbVisible.get();
    }

    public void setHsbVisible(boolean value) {
        hsbVisible.set(value);
    }

    public boolean isCmykVisible() {
        return cmykVisible.get();
    }

    public void setCmykVisible(boolean value) {
        cmykVisible.set(value);
    }
}
