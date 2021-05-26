package colorfix.app;

import colorfix.app.stages.MainStage;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

public class ColorFX extends Application {
    private static HostServices services;

    @Override
    public void start(Stage stage) {
        services = getHostServices();
        // new MainStage(); || new TestStage();
        var mainStage = new MainStage();

        mainStage.getIcons().add(Constants.APP_ICON);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static HostServices hostServices() {
        return services;
    }
}
