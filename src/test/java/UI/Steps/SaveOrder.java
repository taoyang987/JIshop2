package UI.Steps;

import Utility.ContentFactory;
import Utility.DoOrder;
import Utility.SaveOrders;
import cucumber.api.java.en.Given;

public class SaveOrder {
    public SaveOrder() {
    }

    @Given("^生成平台订单$")
    public void 生成平台订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String relid = SaveOrders.SaveOrder();
        ContentFactory.getContent().setReliid(relid);
    }

    @Given("^生成网上订单$")
    public void 生成网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        throw new Exception();
        生成平台订单();
        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(ContentFactory.getContent().getReliid()));
    }

    @Given("^生成指定网上订单 (.*)$")
    public void 生成指定网上订单(String order) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SaveOrder(order));
        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(ContentFactory.getContent().getReliid()));
    }

    @Given("^生成B2B平台订单$")
    public void 生成B_B平台订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String relid = SaveOrders.SaveB2BOrder();
        ContentFactory.getContent().setReliid(relid);
    }

    @Given("^生成B2C平台订单$")
    public void 生成B_C平台订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String relid = SaveOrders.SaveB2COrder();
        ContentFactory.getContent().setReliid(relid);
    }

    @Given("^生成指定B2B平台订单 (.*)$")
    public void 生成指定B_B平台订单_auto_(String order) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SaveOrder(order));
    }

    @Given("^生成指定平台订单 (.*)$")
    public void 生成指定平台订单_auto_(String orderdtl) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SaveOrder(orderdtl));
    }

    @Given("^生成货到付款订单$")
    public void 生成货到付款订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SavePayOrder());
    }

    @Given("^生成未设置默认快递平台订单$")
    public void 生成未设置默认快递平台订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SaveOrder("", "", "未设默认快递店铺"));
    }

    @Given("^生成代销网上订单$")
    public void 生成代销网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SaveAgentOrder());
        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(ContentFactory.getContent().getReliid()));
    }
}