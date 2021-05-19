package colorfix.app;


import java.io.File;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class MonRenduDeCellule extends ListCell<String> {

    @Override
    public void updateItem(String item, boolean empty) {

        super.updateItem(item, empty);
        Canvas c = new Canvas(10, 10);

        //Image img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
        if(!empty){
            c.getGraphicsContext2D().setFill(Color.RED); // J'sais pas comment foutre la couleur qu'on veut, sauf si on fout ça dans la classe principale avec la liste de couleur
                                                         // et comment ça on appelle listCouleur.indexOf(x) (c'est une ptite idée quoi)
            c.getGraphicsContext2D().fillRect(0,0,10,10);
        }
        //c.getGraphicsContext2D().fill();

        setText(item);
        setGraphic(c);
        //}
    }
}