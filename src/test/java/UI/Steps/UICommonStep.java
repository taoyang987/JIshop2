package UI.Steps;

import UI.Pages.*;
import Utility.ContentFactory;
import Utility.JS;
import Utility.UIUtil;
import Utility.Util;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by taoyang on 14/10/23.
 */
public class UICommonStep {

    @Given("^默认账号登录$")
    public void 默认账号登录() throws Throwable {
        WebDriver dr = Util.GetDr();
        // Express the Regexp above with the code you wish you had
        String domain = Util.GetProperties().getProperty("domain");
        String user = Util.GetProperties().getProperty("username");
        String password = Util.GetProperties().getProperty("password");
        dr.get("http://" + domain + "/" + Util.GetProperties().getProperty("project") + "/Admin/Login.aspx");
        Login login = new Login(dr);
        Util.WaitElementEnabled(dr, login.txt用户名);
        login.LoginWith(user, password);
        Index index = new Index(dr);
        Util.WaitElementEnabled(dr, index.btn用户);
    }

    @And("^UI提示 (.*)$")
    public void UI提示(String tip) throws Throwable {
        // Express the Regexp above with the code you wish you had
        assertTrue(JS.WaitLoop(":contains(" + tip + ")"));
    }

    public void UI不存在(String selector) throws Throwable {
        // Express the Regexp above with the code you wish you had
        assertTrue(!JS.WaitLoop(selector));
    }

    public void UI存在(String selector) throws Throwable {
        // Express the Regexp above with the code you wish you had
        assertTrue(JS.WaitLoop(selector));
    }


    @When("^跳转到网上订单管理页面$")
    public void 跳转到网上订单管理页面() throws Throwable {
        // Express the Regexp above with the code you wish you had
       默认账号登录();
        Index index = new Index();
        index.btn直销管理.click();
        JS.MenuClk("网上订单");
        UIUtil.SyncList2();
    }

    @And("^网上订单UI录入精确查询数据$")
    public void 网上订单UI录入精确查询数据() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgOrdOrderList.btn显示全部选项);
        JS.SetValue(pgOrdOrderList.txt平台订单, ContentFactory.getContent().getReliid());
        JS.SetValue(pgOrdOrderList.txt网上订单, ContentFactory.getContent().getOrderId());
        JS.optSelect(pgOrdOrderList.slt所在平台, "淘宝平台");
        JS.optSelect(pgOrdOrderList.slt店铺, "自动化店铺北京");
        JS.optSelect(pgOrdOrderList.slt锁定, "未锁定");
        JS.optSelect(pgOrdOrderList.slt审核, "未审核");
        JS.optSelect(pgOrdOrderList.slt缺货, "不缺货");
        JS.optSelect(pgOrdOrderList.slt解析, "正常解析");
        JS.optSelect(pgOrdOrderList.slt合并拆分, "未合并拆分");
        JS.optSelect(pgOrdOrderList.slt来源, "转单");
        JS.optSelect(pgOrdOrderList.slt订单来源, "自营");
        JS.optSelect(pgOrdOrderList.slt打印, "未打印");
        JS.optSelect(pgOrdOrderList.slt退货状态, "未退货");
        JS.optSelect(pgOrdOrderList.slt代销, "自营");
//        JS.optSelect(pgOrdOrderList.slt货到付款, "非货到付款");
        JS.optSelect(pgOrdOrderList.slt生成采购, "未生成");
        JS.optSelect(pgOrdOrderList.slt采购缺货, "非采购缺货");

//
//        JS.DateChoose(pgOrdOrderList.dt付款时间从, -1, 0);
//        JS.DateChoose(pgOrdOrderList.dt付款时间到, 0, 1);
//        JS.DateChoose(pgOrdOrderList.dt下载时间从, -1, 2);
//        JS.DateChoose(pgOrdOrderList.dt下载时间到, 0, 3);
//        JS.DateChoose(pgOrdOrderList.dt发货时间从, -1, 4);
//        JS.DateChoose(pgOrdOrderList.dt发货时间到, 0, 5);
//        JS.SetValue(pgOrdOrderList.txt订单金额从, "0.00");
//        JS.SetValue(pgOrdOrderList.txt订单金额到, "999.00");
//        JS.SetValue(pgOrdOrderList.txt商品数量从, "0");
//        JS.SetValue(pgOrdOrderList.txt商品数量到, "1");
//        JS.SetValue(pgOrdOrderList.txt商品编号, "auto0001");
//        JS.SetValue(pgOrdOrderList.txt条形码, "auto00010001");

        JS.SetValue(pgOrdOrderList.txt收货人, "AutoData");
        JS.SetValue(pgOrdOrderList.txt联系方式, "13488888888");
        JS.optSelect(pgOrdOrderList.slt快递公司, "自动化北京");
        JS.optSelect(pgOrdOrderList.slt配送, "未配送");
        JS.optSelect(pgOrdOrderList.slt开具发票, "不开");
        JS.optSelect(pgOrdOrderList.slt买家备注, "有备注");
        JS.optSelect(pgOrdOrderList.slt卖家备注, "有备注");
        JS.optSelect(pgOrdOrderList.slt仓库, "自动化仓库北京");
        JS.optSelect(pgOrdOrderList.slt发货方式, "自发");

        JS.Click(pgOrdOrderList.btn查询);
        UIUtil.SyncList2();
    }

    @When("^UI修改为采购缺货$")
    public void UI修改为采购缺货() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        JS.Click(pgCommon.btn确定);
        JS.Click(pgOrdOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
        JS.Click(pgOrdOrderList.btn修改下拉);
        JS.Click(pgOrdOrderList.btn批量修改为采购缺货);
        JS.Click(pgCommon.btn确定);
    }


    @And("^跳转到网上订单管理页面并查询$")
    public void 跳转到网上订单管理页面并查询() throws Throwable {
        // Express the Regexp above with the code you wish you had
        跳转到网上订单管理页面();
        JS.Click(pgOrdOrderList.btn清空);
        JS.SetValue(pgOrdOrderList.txt网上订单, ContentFactory.getContent().getOrderId());
        JS.Click(pgOrdOrderList.btn查询);
        UIUtil.SyncList2();
    }

    @When("^UI修改为非采购缺货$")
    public void UI修改为非采购缺货() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgCommon.btn确定);
        Thread.sleep(2000);
        JS.Click(pgOrdOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
        JS.Click(pgOrdOrderList.btn修改下拉);
        JS.Click(pgOrdOrderList.btn批量修改为非采购缺货);
        JS.Click(pgCommon.btn确定);
    }

    @When("^生成售后申请单$")
    public void 生成售后申请单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgOrdOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
        JS.Click(pgOrdOrderList.btn其他下拉);
        JS.Click(pgOrdOrderList.btn生成售后申请单);
    }

    @Then("^售后申请单处理$")
    public void 售后申请单处理() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgAfterSale.btn处理);
    }

    @When("^填写售后申请单$")
    public void 填写售后申请单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String orderid=JS.GetValue(pgAfterSale.txt网上订单);
        assertEquals("填写售后申请单,网上订单号:", ContentFactory.getContent().getOrderId(), orderid);
        JS.optSelect(pgAfterSale.slt售后类型, "差评");
        JS.optSelect(pgAfterSale.slt售后原因, "客户要求");
        JS.SetValue(pgAfterSale.txt处理金额, "990.00");
        JS.optSelect(pgAfterSale.slt金额承担方, "公司");
        JS.SetValue(pgAfterSale.txt客服处理结果, "同意");
        JS.Click(pgAfterSale.chk默认);
        JS.Click(pgAfterSale.btn保存并查看);
    }

    @Then("^退款确认$")
    public void 退款确认() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgAfterSale.pgAfterSaleList.getbtn查看(ContentFactory.getContent().getReliid()));
//        String orderid=JS.GetValue(pgAfterSale.txt网上订单);
//        assertEquals("填写售后申请单,网上订单号:", ContentFactory.getContent().getOrderId(), orderid);
        JS.Click(pgAfterSale.btn退款确认);
    }

    @When("^UI修改为代发订单$")
    public void UI修改为代发订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgOrdOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
        JS.Click(pgOrdOrderList.btn修改下拉);
        JS.Click(pgOrdOrderList.btn设为代发订单);
    }

    @When("^UI修改为自发订单$")
    public void UI修改为自发订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgCommon.btn确定);
        JS.Click(pgOrdOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
        JS.Click(pgOrdOrderList.btn修改下拉);
        JS.Click(pgOrdOrderList.btn设为自发订单);
    }


    @When("^UI修改仓库 (.*)$")
    public void UI修改仓库(String house) throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgOrdOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
        JS.Click(pgOrdOrderList.btn修改下拉);
        JS.Click(pgOrdOrderList.btn批量修改仓库);
        JS.optSelect(fmDialog.frame,  fmDialog.clsHouseHelper.slt仓库,house);
        JS.Click(fmDialog.frame,fmDialog.clsHouseHelper.btn选择);
    }
}
