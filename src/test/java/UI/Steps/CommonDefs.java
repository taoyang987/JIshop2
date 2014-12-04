package UI.Steps;

import Utility.ContentFactory;
import Utility.DoOrder;
import Utility.ParseStep;
import Utility.Util;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by taoyang on 14-9-25.
 */
public class CommonDefs {

//    private final SaveOrders saveOrder = new SaveOrders();

//    @And("^转单$")
//    public void 转单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(ContentFactory.getContent().getReliid()));
//    }

//    @When("^作废订单$")
//    public void 作废订单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        String ids = ContentFactory.getContent().getReliid();
////        for (int i = 0; i < ContentFactory.getContent().Listrelid.size(); i++) {
////            if (i > 0) {
////                ids += ",";
////                ids += ContentFactory.getContent().Listrelid.get(i);
////            }
////        }
//        ContentFactory.getContent().Result = DoOrder.DisableOrder(ids);
//    }

    @Then("^检验数据提示 (.*)$")
    public void 检验数据提示(String tip) throws Throwable {
        // Express the Regexp above with the code you wish you had
//        assertEquals(tip, ContentFactory.getContent().Result);
        Util.assertContain(tip,ContentFactory.getContent().Result);
    }

    @And("^操作订单 (.*)")
    public void 操作订单(String step) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ParseStep.ParseOrderStep(step, ContentFactory.getContent().getReliid(),ContentFactory.getContent().getOrderId());
    }

    @When("^合并订单$")
    public void 合并订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListOrderId.get(i);
        }
        String result = DoOrder.MergeOder(ids);
//        if (Util.BoolRegEX(result, "^\"EO\\d{14}\"$")) {
//            ContentFactory.getContent().setMergeOrderID(result);
//        }else {
//            ContentFactory.getContent().Result = result;
//        }
        ContentFactory.getContent().Result = result;
        ContentFactory.getContent().setMergeOrderID(result);
    }
//    @Given("^生成指定网上订单 (.*)$")
//    public void 生成指定网上订单(String order) throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成指定网上订单(order);
//    }

    @And("^作废合并或拆分订单$")
    public void 作废合并或拆分订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = ContentFactory.getContent().getReliid();
        ContentFactory.getContent().Result = DoOrder.DisableMergeOrSplitOrder(ids);
    }

//    @Given("^生成B2B平台订单$")
//    public void 生成B_B平台订单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成B_B平台订单();
//    }
//
//    @Given("^生成B2C平台订单$")
//    public void 生成B_C平台订单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成B_C平台订单();
//    }
//
//    @Given("^生成指定B2B平台订单 (.*)$")
//    public void 生成指定B_B平台订单_auto_(String order) throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成指定B_B平台订单_auto_(order);
//    }
//
//    @Given("^生成指定平台订单 (.*)$")
//    public void 生成指定平台订单_auto_(String orderdtl) throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成指定平台订单_auto_(orderdtl);
//    }
//
//    @When("^拆分订单$")
//    public void 拆分订单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        ContentFactory.getContent().setOrderId(DoOrder.SplitOrder(ContentFactory.getContent().getOrderId()));
//    }
//
//    @Given("^生成货到付款订单$")
//    public void 生成货到付款订单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成货到付款订单();
//    }
//
//    @Given("^生成未设置默认快递平台订单$")
//    public void 生成未设置默认快递平台订单() throws Throwable {
//        // Express the Regexp above with the code you wish you had
//        saveOrder.生成未设置默认快递平台订单();
//    }


}
