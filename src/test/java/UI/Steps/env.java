package UI.Steps;

import Utility.Util;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * Created by taoyang on 14-9-17.
 */
public class env {
//    WebDriver dr;

    @Before
    public void BeforeScenario() {
//        Utility.GetDriver.GetDr();
    }

//    @Before
//    public void setup(Scenario scenario){
//
//    }

    @After
    public void AfterScenario(Scenario scenario) {
        if (Util.flagUI) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((ChromeDriver) Util.GetDr()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            Util.GetDr().quit();
            Util.flagUI=false;
        }
//        scenario.write("steps");
    }
}


