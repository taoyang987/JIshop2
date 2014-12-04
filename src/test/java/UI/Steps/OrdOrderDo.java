package UI.Steps;

import Utility.ContentFactory;
import Utility.DoOrder;
import Utility.Entity.GetPOrdmst;
import Utility.Entity.GoodSendType;
import Utility.Entity.StockFlag;
import Utility.Test;
import Utility.Util;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * Created by taoyang on 14/10/24.
 */
public class OrdOrderDo {
    @When("^审核订单$")
    public void 审核订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().Result=DoOrder.AppPOrder(ContentFactory.getContent().getOrderId());
    }

    @When("^批量审核订单$")
    public void 批量审核订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++)
        {
            if (i>0)
            {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListOrderId.get(i);
        }
        ContentFactory.getContent().Result=DoOrder.AppPOrder(ids);
    }

    @When("^弃审订单$")
    public void 弃审订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().Result=DoOrder.UnDoCheck(ContentFactory.getContent().getOrderId());
    }

    @And("^批量弃审订单$")
    public void 批量弃审订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++)
        {
            if (i>0)
            {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListOrderId.get(i);
        }
        ContentFactory.getContent().Result=DoOrder.UnDoCheck(ids);
    }

    @When("^批量锁定订单$")
    public void 批量锁定订单() throws Throwable {
        锁定订单();
    }
    @When("^锁定订单$")
    public void 锁定订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListOrderId.get(i);
        }
        ContentFactory.getContent().Result = DoOrder.LockPOrder(ids);
    }

    @And("^解锁订单$")
    public void 解锁订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        批量解锁订单();
    }

    @And("^批量解锁订单$")
    public void 批量解锁订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListOrderId.get(i);
        }
        ContentFactory.getContent().Result = DoOrder.UnLockPOrder(ids);
    }

    @And("^删除网上订单$")
    public void 删除网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListOrderId.get(i);
        }
        ContentFactory.getContent().Result = DoOrder.DeletePOrder(ids);
    }

    @When("^拆分订单$")
    public void 拆分订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        DoOrder.SplitOrder(ContentFactory.getContent().getOrderId());
    }

    @When("^指定拆分主订单 (.*)$")
    public void 指定拆分主订单(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        DoOrder.SplitOrder(ContentFactory.getContent().getOrderId(),arg1);
    }

    @And("^拆分已合并订单$")
    public void 拆分已合并订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        DoOrder.SplitOrder(ContentFactory.getContent().getMergeOrderID());
    }

    @When("^绑定快递单$")
    public void 绑定快递单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ContentFactory.getContent().ListOrderId.size(); i++)
        {
            if (i > 0)
            {
                builder.append("|");
            }
            String orderid = ContentFactory.getContent().ListOrderId.get(i);
            builder.append(orderid);
            builder.append(",");
            if (ContentFactory.getContent().DicTransNum.get(orderid)==null||ContentFactory.getContent().DicTransNum.get(orderid).equals(""))
            {
                ContentFactory.getContent().DicTransNum.put(orderid, Util.GetExpressNo());
            }
            builder.append(ContentFactory.getContent().DicTransNum.get(orderid));
        }
        ContentFactory.getContent().Result=DoOrder.BindExpressNo(builder.toString());
    }

    @When("^绑定指定快递单 (\\d+)$")
    public void 绑定指定快递单(int arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
//        ContentFactory.getContent().Result=DoOrder.BindExpressNo(ContentFactory.getContent().getOrderId()+","+arg1);
        ContentFactory.getContent().DicTransNum.put(ContentFactory.getContent().getOrderId(), arg1 + "");
        绑定快递单();
    }

    @And("^删除合并后的网上订单$")
    public void 删除合并后的网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().ListMergeID.size(); i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += ContentFactory.getContent().ListMergeID.get(i);
        }
        ContentFactory.getContent().Result = DoOrder.DeletePOrder(ids);
    }

    @And("^作废合并订单$")
    public void 作废合并订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().Result = DoOrder.DisableMergeOrSplitOrder(ContentFactory.getContent().getMergeOrderID());

    }

    @And("^作废拆分订单$")
    public void 作废拆分订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (int i = 0; i < ContentFactory.getContent().getSplitOrderID().size(); i++) {
            ContentFactory.getContent().Result = DoOrder.DisableMergeOrSplitOrder(ContentFactory.getContent().getSplitOrderID().get(i));


        }
    }

    @When("^用拆分后的一个订单做操作$")
    public void 用拆分后的一个订单做操作() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().setOrderId(ContentFactory.getContent().getSplitOrderID().get(0));
    }

    @And("^删除拆分后的网上订单$")
    public void 删除拆分后的网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String ids = "";
        for (int i = 0; i < ContentFactory.getContent().getSplitOrderID().size(); i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += ContentFactory.getContent().getSplitOrderID().get(i);
        }
        ContentFactory.getContent().Result = DoOrder.DeletePOrder(ids);
    }

    @And("^审核合并后的订单$")
    public void 审核合并后的订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().Result=DoOrder.AppPOrder(ContentFactory.getContent().getMergeOrderID());

    }

    @When("^查询网上订单$")
    public void 查询网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GetPOrdmst getPOrdmst = new GetPOrdmst();
        ContentFactory.getContent().Result = DoOrder.GetPOrderList(getPOrdmst);
    }

    @When("^修改为非采购缺货$")
    public void 修改为非采购缺货() throws Throwable {
        // Express the Regexp above with the code you wish you had
        StockFlag stockFlag = new StockFlag(ContentFactory.getContent().getOrderId(), "0");
        ContentFactory.getContent().Result = DoOrder.SetStockFlag(stockFlag);
    }

    @When("^修改为采购缺货$")
    public void 修改为采购缺货() throws Throwable {
        // Express the Regexp above with the code you wish you had
        StockFlag stockFlag = new StockFlag(ContentFactory.getContent().getOrderId(), "1");
        ContentFactory.getContent().Result = DoOrder.SetStockFlag(stockFlag);
    }

    @When("^修改设为自发订单$")
    public void 修改设为自发订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GoodSendType sendType = new GoodSendType(ContentFactory.getContent().getOrderId(), "1");
        ContentFactory.getContent().Result = DoOrder.SetGoodSendType(sendType);
    }

    @When("^修改设为代发订单$")
    public void 修改设为代发订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        GoodSendType sendType = new GoodSendType(ContentFactory.getContent().getOrderId(), "2");
        ContentFactory.getContent().Result = DoOrder.SetGoodSendType(sendType);
    }

    @When("^修改仓库 (.*)$")
    public void 修改仓库(String house) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().Result = DoOrder.BatchSaveWarehouse(ContentFactory.getContent().getOrderId(),house);
    }

    @When("^修改货架 (.*)$")
    public void 修改货架(String shelf) throws Throwable {
        // Express the Regexp above with the code you wish you had
        ContentFactory.getContent().Result = DoOrder.BatchSaveShelf(ContentFactory.getContent().getOrderId(),shelf);
    }

    @When("^修改第一个商品货架 (.*)$")
    public void 修改第一个商品货架(String shelf) throws Throwable {
        // Express the Regexp above with the code you wish you had
//        String id= Test.GetIntValue("")
    }
}
