package UI.Steps;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by taoyang on 14-9-19.
 */
public class StepBase {
    public static String relid;
    protected WebDriver dr;
    protected WebDriverWait wait;
    protected Scenario scenario;
    int waitTime=30;

//    static {
//        Utility.Utility=GetDriver.GetDr();
//    }

//    public  StepBase() {
//        dr= Util.GetDr();
//        wait = new WebDriverWait(dr,waitTime);
////        this.scenario=scenario;
//    }
    //    @Before
//    public void Setup(Scenario scenario){
//        dr= GetDriver.GetDr();
//        wait = new WebDriverWait(dr,30);
////        String scenarioName = scenario.getName();
////        scenario.write("abc");
//    }

//    @After
//    public void AfterScenario(Scenario scenario){
//        GetDriver.GetDr().quit();
////        System.out.print("end");
////        scenario.write(scenario.getStatus());
//    }
}
