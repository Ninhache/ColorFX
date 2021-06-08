package colorfix.app.util;

import java.net.URL;

public class Assets {
    public static URL getAssetUrl(String name) {
        return Assets.class.getResource(name);
    }

    public static String getAssetPath(String name) {
        return getAssetUrl(name).toExternalForm();
    }
}
