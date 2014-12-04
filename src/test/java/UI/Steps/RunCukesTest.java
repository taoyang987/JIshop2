package UI.Steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features="src/test/resources/UI/Steps/DelOrder.feature",
//        tags = "~@UI",
        monochrome = true,
        format = {"pretty", "html:target/cucumber", "rerun:target/rerun.txt"})
public class RunCukesTest {
}
