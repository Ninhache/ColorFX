package colorfix.app.controls.table;

import colorfix.app.controls.table.columns.*;
import colorfix.app.enums.ColorSpace;
import colorfix.app.stages.TestStage;
import colorfix.app.util.ColorUtil;
import javafx.beans.property.SimpleBooleanProperty;
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
    ContextMenu contextMenu;
    final Clipboard clipboard = Clipboard.getSystemClipboard();
	final ClipboardContent content = new ClipboardContent();


    public ColorTableView() {
        ColorThumbnailColumn columnThumbnail = new ColorThumbnailColumn();

        //ColorComponentColumn columnRed = new ColorComponentColumn(ColorComponent.RED);
        //ColorComponentColumn columnGreen = new ColorComponentColumn(ColorComponent.GREEN);
        //ColorComponentColumn columnBlue = new ColorComponentColumn(ColorComponent.BLUE);

        HexadecimalValueColumn columnHex = new HexadecimalValueColumn();

        ColorSpaceColumn columnRGB = new ColorSpaceColumn(ColorSpace.RGB);
        ColorSpaceColumn columnHSB = new ColorSpaceColumn(ColorSpace.HSB);
        ColorSpaceColumn columnCMYK = new ColorSpaceColumn(ColorSpace.CMYK);
        
        setOnMouseClicked(this::onMouseClicked);

        columnHex.visibleProperty().bind(hexVisible);
        columnRGB.visibleProperty().bind(rgbVisible);
        columnCMYK.visibleProperty().bind(cmykVisible);
        columnHSB.visibleProperty().bind(hsbVisible);

        EditActionsColumn columnAction = new EditActionsColumn();

        getColumns().addAll(columnThumbnail, columnHex, columnRGB, columnCMYK, columnHSB, columnAction);
        
        /// Menu contextuel
        contextMenu = new ContextMenu();
        
        MenuItem menuItemMod = new MenuItem("Modify");
        menuItemMod.setOnAction(this::onItemModify);
        
        MenuItem menuItemAdd = new MenuItem("Add");
        menuItemAdd.setOnAction(this::onItemAdd);
        
        MenuItem menuItemDel = new MenuItem("Delete");
        menuItemDel.setOnAction(this::onItemDelete);
        
        MenuItem menuItemCpy = new MenuItem("Copier");
        menuItemCpy.setOnAction(this::onItemCopy);
        
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem3 = new SeparatorMenuItem();
        
        contextMenu.getItems().addAll(menuItemMod,separatorMenuItem, menuItemAdd, separatorMenuItem2,menuItemDel,separatorMenuItem3,menuItemCpy);


        
    }
    
    private void onMouseClicked(MouseEvent e) {
    	if(e.getButton() == MouseButton.SECONDARY) {
    		contextMenu.show(this, e.getScreenX(), e.getScreenY());
    	}else {
    		contextMenu.hide();
    	}
    }
    
    private void onItemAdd(ActionEvent e) {
    	TestStage ok = new TestStage();
    	ok.show();
    	System.out.println("add");
    }
    
    private void onItemModify(ActionEvent e) {
    	int index = getFocusModel().getFocusedCell().getRow();
    	
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
