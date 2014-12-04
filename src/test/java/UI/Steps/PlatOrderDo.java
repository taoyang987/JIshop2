package UI.Steps;

import Utility.*;
import Utility.Entity.GetOrderList;
import Utility.Entity.OrderMst;
import Utility.SaveOrders;
import com.alibaba.fastjson.JSON;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;

/**
 * Created by taoyang on 14/10/22.
 */
public class PlatOrderDo {

    @And("^转单$")
    public void 转单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(ContentFactory.getContent().getReliid()));
    }

    @When("^作废订单$")
    public void 作废订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = ContentFactory.getContent().getReliid();
//        for (int i = 0; i < ContentFactory.getContent().Listrelid.size(); i++) {
//            if (i > 0) {
//                ids += ",";
//                ids += ContentFactory.getContent().Listrelid.get(i);
//            }
//        }
        ContentFactory.getContent().Result = DoOrder.DisableOrder(ids);
    }

    @When("^撤销作废订单$")
    public void 撤销作废订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = ContentFactory.getContent().getReliid();
//        for (int i = 0; i < ContentFactory.getContent().Listrelid.size(); i++) {
//            if (i > 0) {
//                ids += ",";
//                ids += ContentFactory.getContent().Listrelid.get(i);
//            }
//        }
        ContentFactory.getContent().Result = DoOrder.EnableOrder(ids);
    }

    @When("^批量撤销作废订单$")
    public void 批量撤销作废订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (String relid : ContentFactory.getContent().Listrelid) {
            String mstid = Test.GetMstidByReliid(relid);
            ids += mstid + "|";
        }
        ids = ids.substring(0, ids.length() - 1);
        String result = DoOrder.EnableOrderPost(ids);
        ContentFactory.getContent().Result = Util.GetMessage(result);
    }

    @When("^删除订单$")
    public void 删除订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (String relid : ContentFactory.getContent().Listrelid) {
            String mstid = Test.GetMstidByReliid(relid);
            ids = ids + mstid + ",";
        }
        ids = ids.substring(0, ids.length() - 1);
        ContentFactory.getContent().Result = DoOrder.DelOrder(ids);
    }

    @When("^批量转单$")
    public void 批量转单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (String relid : ContentFactory.getContent().Listrelid) {
            ids = ids + relid + ",";
        }
        ids = ids.substring(0, ids.length() - 1);
        ContentFactory.getContent().Result = DoOrder.TransOrderBatch(ids);
        for (String relid : ContentFactory.getContent().Listrelid) {
            String id = Test.GetOrderIDByreliid(relid);
            ContentFactory.getContent().setOrderId(id);
        }
    }

    @Given("^生成一键转单店铺平台订单 (.*)$")
    public void 生成一键转单店铺平台订单_auto_(String order) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setReliid(SaveOrders.SaveOrder(order, "AutoData", "一键转单店铺"));
    }

    @When("^一键转单$")
    public void 一键转单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        DoOrder.TransOrderByShop("一键转单店铺");
    }

    @And("^一键转单结果$")
    public void 一键转单结果(DataTable table) throws Throwable {
        // Express the Regexp above with the code you wish you had
        List<HashMap<String, String>> list = Util.GetHasList(table);
        for (HashMap<String, String> hashMap : list) {
            if (Util.GetStrBool(hashMap.get("Results"))) {
                new ChkOrder().转单成功();
            } else {
                new ChkOrder().转单失败();
            }
        }
    }

    @Given("^清空原有订单信息$")
    public void 清空原有订单信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        ContentFactory.getContent().DeleteData();
        ContentFactory.setContent(new Content());
    }

    @When("^查询平台订单$")
    public void 查询平台订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetOrderList list=new GetOrderList();
        ContentFactory.getContent().Result = DoOrder.GetOrderList(list);
    }

    @When("^清空查询平台订单$")
    public void 清空查询平台订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetOrderList list=new GetOrderList();
        list.istransfer = "";
        list.deleteflag = "";

        ContentFactory.getContent().Result = DoOrder.GetOrderList(list);
    }

    @When("^录入精确查询数据$")
    public void 录入精确查询数据() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetOrderList list=new GetOrderList();
        list.deleteflag = "0";
        list.dealerflag = "0";
        list.startime= Util.GetDate(-1,"");
        list.istransfer = "0";
        list.endtime = Util.GetDate("");
        list.shopid = "1";
        list.orderid = Test.GetMstidByReliid(ContentFactory.getContent().getReliid());
        list.recman = "AutoData";
        list.hdflag = "0";
        ContentFactory.getContent().Result = DoOrder.GetOrderList(list);
    }

    @When("^录入模糊查询数据$")
    public void 录入模糊查询数据() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetOrderList list=new GetOrderList();
        list.orderid = ContentFactory.getContent().getReliid();
        list.recman = "utoDa";

        ContentFactory.getContent().Result = DoOrder.GetOrderList(list);
    }

    @When("^录入错误查询数据$")
    public void 录入错误查询数据() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetOrderList list=new GetOrderList();
        list.orderid = ContentFactory.getContent().getReliid() + "1";
        list.recman = "AutoData"+"1";
        list.deleteflag = "1";
        list.dealerflag = "1";
        list.startime= Util.GetDate(+1,"");
        list.istransfer = "1";
        list.endtime = Util.GetDate("");
        list.shopid = "3";
        list.hdflag = "1";
        ContentFactory.getContent().Result = DoOrder.GetOrderList(list);
    }

    @When("^查询平台订单第二页$")
    public void 查询平台订单第二页() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetOrderList list=new GetOrderList();
        list.page=2;
        ContentFactory.getContent().Result = DoOrder.GetOrderList(list);
    }

    @And("^生成不同信息订单 (.*)")
    public void 生成不同信息订单(String info) throws Throwable {
        // Express the Regexp above with the code you wish you had
        OrderMst orderMst=new OrderMst();
        String orderdtl = null;
        switch (info)
        {
            case "不同联系人":
                orderMst.Linkman = "合并订单";
                break;
            case "不同联系电话":
                orderMst.Linktel = "0571-99999999";
                break;
            case "不同手机":
                orderMst.Handset = "13499999999";
                break;
            case "不同发货地址":
                orderMst.ReceiveAddr = "新收获地址";
                break;
            case "不同付款方式":
                orderMst.Hdflag = "1";//货到付款
                break;
            case "不同仓库":
                orderdtl = "auto006600000001,1";
                break;
        }
        String json = JSON.toJSONString(orderMst);
        ContentFactory.getContent().setReliid(SaveOrders.SaveOrder(orderdtl, "", "", json));
        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(ContentFactory.getContent().getReliid()));


    }

    @Given("^生成指定店铺网上订单 (.*)$")
    public void 生成指定店铺网上订单_无快递店铺(String shop) throws Throwable {
        // Express the Regexp above with the code you wish you had
        String rellid = SaveOrders.SaveOrder("", "", shop);
        ContentFactory.getContent().setOrderId(DoOrder.TransOrder(rellid));
    }
}
