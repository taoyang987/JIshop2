package UI.Steps;

import UI.Pages.pgPlatOrderList;
import Utility.JS;
import Utility.Util;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

/**
 * Created by taoyang on 14-10-17.
 */
public class StepOrderOperate extends StepBase{
    WebDriver dr = Util.GetDr();
    @Then("^UI作废订单$")
    public void UI作废订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.lbl平台订单(relid));
        JS.Click(pgPlatOrderList.btn作废);

        JS.SetValue(pgPlatOrderList.clsAlert.txt作废原因, "自动化测试作废");
        JS.Click(pgPlatOrderList.clsAlert.btn确定);
    }

    @When("^UI撤销作废订单$")
    public void UI撤销作废订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.lbl平台订单(relid));
        JS.Click(pgPlatOrderList.btn撤销作废);
    }

    @When("^UI删除订单$")
    public void UI删除订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.lbl平台订单(relid));
        JS.Click(pgPlatOrderList.btn删除);
    }

    @When("^UI批量转单$")
    public void UI批量转单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.lbl平台订单(relid));
        JS.Click(pgPlatOrderList.btn批量转单);

    }
}
