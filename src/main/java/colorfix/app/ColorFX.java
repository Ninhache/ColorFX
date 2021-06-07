package colorfix.app;

import colorfix.app.stages.ColorChooserStage;
import colorfix.app.stages.ColorPickerStage;
import colorfix.app.stages.TestSliderTabsStage;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

public class ColorFX extends Application {
    private static HostServices services;

    @Override
    public void start(Stage stage) {
        services = getHostServices();
        var mainStage = new ColorChooserStage();

        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static HostServices hostServices() {
        return services;
    }
}
