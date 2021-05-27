package colorfix.app;

import java.util.ArrayList;

import colorfix.app.util.Assets;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class Constants {
    public static final String GITLAB_URL = "https://gitlab.univ-lille.fr/neo.almeida.etu/ColorFX";
    public static final String APP_NAME = "ColorFX";
    public static final String APP_DESCRIPTION = "Correcteur de teinte pour impression noir et blanc.";
    public static final String TOOLTIP_ADDBTN = "Ouvrir une boite de dialogue pour sélectionner une couleur à ajouter";
    public static final String TOOLTIP_REMOVALLEBTN = "Vider la liste des couleurs";
    public static final String TOOLTIP_ABOUTBTN = "Afficher des informations sur ce logiciel";
    public static final String TOOLTIP_CALIBRATEBTN = "Recalibrer les couleurs et les afficher dans une nouvelle fenêtre";

    public static final Image APP_ICON = new Image("/handidoge.png");
    public static final Image APP_ICON_ERROR = new Image("/handidogeError.png");
    
//    ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<>();
    
    
    public static final FileChooser.ExtensionFilter[] FILTERS = new FileChooser.ExtensionFilter[]{
    		new FileChooser.ExtensionFilter("Fichier couleurs (*.color)", "*.color"),
			new FileChooser.ExtensionFilter("Fichier texte (*.txt)", "*.txt"),
			new FileChooser.ExtensionFilter("Tous les fichiers (*)", "*"),
    };
}
