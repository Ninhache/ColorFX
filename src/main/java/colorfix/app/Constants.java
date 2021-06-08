package colorfix.app;

import colorfix.app.util.Assets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public static final Image ADD_ICON = loadImageRes("/addIcon.png");
    public static final Image COPY_ICON = loadImageRes("/copyIcon.png");
    public static final Image DEL_ICON = loadImageRes("/delIcon.png");
    public static final Image EXP_ICON = loadImageRes("/exportIcon.png");
    public static final Image LOAD_ICON = loadImageRes("/loadIcon.png");
    public static final Image QUES_ICON = loadImageRes("/questionMarkIcon.png");
    public static final Image REC_ICON = loadImageRes("/recycleIcon.png");


    public static final FileChooser.ExtensionFilter[] FILTERS = new FileChooser.ExtensionFilter[]{
    	new FileChooser.ExtensionFilter("Fichier couleurs (*.color)", "*.color"),
		new FileChooser.ExtensionFilter("Fichier texte (*.txt)", "*.txt"),
		new FileChooser.ExtensionFilter("Tous les fichiers (*)", "*"),
    };
    
    public static Image loadImageRes(String path) {
    	return new Image(Assets.getAssetPath(path));
    }

    public static final ImageView loadImage(Image src){
        ImageView img = new ImageView(src);

        img.setFitWidth(20);
        img.setFitHeight(20);
        img.setPreserveRatio(false);

        return img;
    }
}
