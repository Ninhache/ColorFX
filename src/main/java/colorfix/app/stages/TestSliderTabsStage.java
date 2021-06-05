package colorfix.app.stages;

import colorfix.app.controls.StyledScene;
import colorfix.app.controls.slider.ColorSliderTabs;

public class TestSliderTabsStage extends ExtendedStage {
    public TestSliderTabsStage() {
        ColorSliderTabs sliders = new ColorSliderTabs();

        StyledScene scene = new StyledScene(sliders, 500, 250);
        setScene(scene);
    }
}
