package colorfix.app.stages;

import colorfix.app.controls.StyledScene;
import colorfix.app.controls.slider.ColorSliders;

public class TestSliderTabsStage extends ExtendedStage {
    public TestSliderTabsStage() {
        ColorSliders sliders = new ColorSliders();

        StyledScene scene = new StyledScene(sliders, 500, 250);
        setScene(scene);
    }
}
