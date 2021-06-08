package colorfix.app.stages;

import colorfix.app.Constants;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/** Classe étendant les fonctionnalités d'un {@link javafx.stage.Stage Stage} classique **/
public abstract class ExtendedStage extends Stage {
    public ExtendedStage() {
        getIcons().add(Constants.APP_ICON);
    }

    public Tooltip setTooltip(Node node, String text) {
        Tooltip tooltip = new Tooltip(text);
        Tooltip.install(node, tooltip);
        return tooltip;
    }
}
