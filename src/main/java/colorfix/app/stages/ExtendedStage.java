package colorfix.app.stages;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/** Classe étendant les fonctionnalités d'un {@link javafx.stage.Stage Stage} classique **/
public abstract class ExtendedStage extends Stage {
    public Tooltip setTooltip(Node node, String text) {
        Tooltip tooltip = new Tooltip(text);
        Tooltip.install(node, tooltip);
        return tooltip;
    }
}
