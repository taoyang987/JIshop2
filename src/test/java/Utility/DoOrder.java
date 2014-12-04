package Utility;

import Utility.Entity.*;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;

/**
 * Created by taoyang on 14-10-10.
 */
public class DoOrder {


    public static String ChangeStatus(String shopID, boolean flag) {
        String result;
        int status;
        if (flag) {
            status = 1;
        } else {
            status = 0;
        }
        String content = JSON.toJSONString(new ShopStatus(shopID, status));
        result = Util.PostJsonData("/Admin/plat/PlatFormShopsList.aspx/ChangeStatus", content);
        return result;
    }

    /**
     * 批量支付
     *
     * @param reliid
     */
    public static String PayAndTransOrder(String reliid) {
        String mstid = Test.GetMstidByReliid(reliid);
        String result;
        String content = JSON.toJSONString(new IDS(mstid));
        result = Util.PostJsonData("/admin/plat/OrderList.aspx/PayAndTransOrder", content);
        Assert.assertEquals("\"\"", result);
        return Test.GetMstidByReliid(reliid);
    }

    /**
     * 扫描出库
     *
     * @param ids
     */
    public static void ScanOutWh(String ids) {
        String result;
        String traceno = Test.GetStrValue("SELECT traceno from dbo.ec_eordermst WHERE orderid='" + ids + "';");
        System.out.println("订单扫描出库,网上订单号:" + ids + "快递单号：" + traceno);
        //result=WSP.WebSite.Admin.Ord.ScanDelivery.ScanOutWh(traceno,ids);
        String content = JSON.toJSONString(new ScanOutOrder(traceno, ids));
        result = Util.PostJsonData("/Admin/Ord/ScanDelivery.aspx/ScanOutWh", content);
        if (!result.contains("发货成功")) {
            System.out.println("发货失败" + result);
        }
    }


    public static void PartlyScanOutWh(String ids){
        String result;
        String traceno = Test.GetStrValue("SELECT traceno from dbo.ec_eordermst WHERE orderid='" + ids + "';");
        System.out.println("订单部分扫描出库,网上订单号:" + ids + "快递单号：" + traceno);
        int dtlID = Test.GetIntValue("Select id from ec_eorderdtl where orderid='" + ids + "'");

        String content = JSON.toJSONString(new ScanOutOrderPartly(traceno, ids, dtlID));
        result = Util.PostJsonData("/Admin/Ord/ScanDelivery.aspx/PartlyScanOutWh", content);
        if (!result.contains("发货成功")) {
            System.out.println("发货失败" + result);
        }
    }

    /**
     * 作废订单
     * @param relid
     * @return
     */
    public static String DisableOrder(String relid) {
        String mstid = Test.GetMstidByReliid(relid);
        String result;
        String content = JSON.toJSONString(new DisableOrder(mstid));
        result = Util.PostJsonData("/Admin/plat/OrderList.aspx/DisableOrder", content);
//        MethodMessage message = JSON.parseObject(result, MethodMessage.class);
        return Util.GetMessage(result);
    }

    /**
     * 撤销作废订单
     * @param relid
     * @return
     */
    public static String EnableOrder(String relid) {
        String mstid = Test.GetMstidByReliid(relid);
        String result;
        result = EnableOrderPost(mstid);
//        MethodMessage message = JSON.parseObject(result, MethodMessage.class);
        return Util.GetMessage(result);
    }

    /**
     * 撤销作废订单请求
     * @param mstid
     * @return
     */
    public static String EnableOrderPost(String mstid) {
        String result;
        String content = JSON.toJSONString(new mstids(mstid));
        result = Util.PostJsonData("/Admin/plat/OrderList.aspx/EnableOrder", content);
        return result;
    }

    public static String DisableMergeOrSplitOrder(String orderid)
    {

        //alidate.AreEqual(result, Var提示结果);
//        String mstid = Test.GetMstidByReliid(orderid);
        String result;
        String content = JSON.toJSONString(new DisableOrder(orderid));
        result = Util.PostJsonData("/Admin/plat/OrderList.aspx/DisableMergeOrSplitOrder", content);
//        MethodMessage message = JSON.parseObject(result, MethodMessage.class);
        return Util.GetMessage(result);
    }

    /**
     * 单张订单打印
     *
     * @param ids
     */
    public static void CreateSinglePrintData(String ids) {
        String result;
        String content = JSON.toJSONString(new OrderID(ids));
        System.out.println("订单打印" + ids);
        //result=WSP.WebSite.Admin.Ord.EOrderPrint.CreateSinglePrintData(ids);
        result = Util.PostJsonData("/Admin/Ord/EOrderPrint.aspx/CreateSinglePrintData", content);
        Assert.assertTrue(result.contains(":false"));
//        if (!result.startsWith("{\"iserror\":false,\"message")) {
//            System.out.println("订单打印错误" + result);
//        }
    }
//
//    /// <summary>
//    /// 单张快递单打印
//    /// </summary>
//    /// <param name="ids">EO00113120600001,1234567890</param>

    /**
     * 单张快递单打印
     *
     * @param ids EO00113120600001,1234567890
     */
    public static void CreateSingleSenderPrintData(String ids) {
        String result;
        String traceno = Test.GetStrValue("SELECT traceno from dbo.ec_eordermst WHERE orderid='" + ids + "';");
        String content = JSON.toJSONString(new OrderID(ids + "," + traceno));
        System.out.println("快递单打印" + ids);
        //result=WSP.WebSite.Admin.Ord.EOrderPrint.CreateSingleSenderPrintData(ids);
        result = Util.PostJsonData("/Admin/Ord/EOrderPrint.aspx/CreateSingleSenderPrintData", content);
        result = Util.TrimQuo(result);
        Assert.assertTrue(result.contains(":false"));
//        if (!result.startsWith("{\"iserror\":false,\"message")) {
//            System.out.println("订单打印错误" + result);
//        }
    }
//
//    /// <summary>
//    /// 快递单绑定
//    /// </summary>
//    /// <param name="ids">EO00113121100006,1234567890|</param>

    /**
     * 快递单绑定
     *
     * @param ids EO00113121100006,1234567890|
     */
    public static String BindExpressNo(String ids) {
        String result;
        String content = JSON.toJSONString(new IDS(ids));
        System.out.println("快递单绑定" + ids);
        result = Util.PostJsonData("/Admin/Ord/EOrderPrint.aspx/BindExpressNo", content);
        //result=WSP.WebSite.Admin.Ord.EOrderPrint.BindExpressNo(ids);
//        Assert.assertTrue(result.contains("绑定成功"));
//
//        if (result != "{\"iserror\":false,\"message\":\"绑定成功!\"}") {
//            System.out.println("快递单绑定错误" + result);
//        }
        return result;
    }

    /**
    已有订单合并
     */
    public static String MergeOder(String orderids)
    {
        String content = JSON.toJSONString(new OrderidsSplit(orderids));
        String orderid = Util.DeleteHtmlTag(Util.PostJsonData("/Admin/Ord/POrderAutoMergeList.aspx/MergeOrder", content));
//        Assert.assertTrue("验证合并后返回的订单号", Util.BoolRegEX(orderid, "^\"EO\\d{14}\"$"));
        orderid = Util.GetStringByRegexGroup(orderid, "^\"(.*)\"$");
//        ContentFactory.getContent().setOrderId(orderid);
        System.out.println("合并结果:" + orderid);
        return orderid;
    }

    /**
     * 弃审
     *
     * @param orderid
     */
    public static String UnDoCheck(String orderid) {
        String result;
        String content = JSON.toJSONString(new OrderSplit(orderid));
        result = Util.PostJsonData("/Admin/Ord/POrderList.aspx/UnDoCheck", content);
        //result=WSP.WebSite.Admin.Ord.POrderList.UnDoCheck(orderid,';');
//        String tmp = " " + orderid + ":<span style='color:green'>弃审成功</span><br/><br/>";
//        if (result != tmp) {
//            System.out.println("弃审故障" + result);
//        }
//        System.out.println("弃审订单" + orderid);
        return Util.DeleteHtmlTag(result);
    }
//
//    /// <summary>
//    ///审核订单
//    /// </summary>
//    /// <param name="orderid">订单号(;分割的字符串)</param>

    /**
     * 审核订单
     *
     * @param orderid
     */
    public static String AppPOrder(String orderid) {
        //InitWeb();
        String result;
        String content = JSON.toJSONString(new OrderSplit(orderid));
        result = Util.PostJsonData("/Admin/Ord/POrderList.aspx/AppPOrder", content);
        System.out.println("审核订单" + orderid);
        //result=WSP.WebSite.Admin.Ord.POrderList.AppPOrder(orderid,';');
//        String expected = " " + orderid + ":<span style='color:green'>审核成功</span><br/><br/>";
//        Assert.assertTrue(result.contains("审核成功"));
//        Util.assertContain("审核成功",result);
        return Util.DeleteHtmlTag(result);
    }
//

    /**
     *
     * @param reliid
     * @return
     */
    public static String TransOrder(String reliid) {
        String errorMsg;
        String ids = Test.GetStrValue("SELECT mstid FROM dbo.wsp_ordermst WHERE relid='" + reliid + "';");
        String content = JSON.toJSONString(new IDS(ids));
        errorMsg = Util.PostJsonData("/Admin/Plat/OrderList.aspx/TransOrder", content);
        ContentFactory.getContent().Result=errorMsg;
//        System.out.println(errorMsg);
        String orderid = Test.GetOrderIDByreliid(reliid);
        System.out.println("生成网上订单:" + orderid);
        return orderid;
    }
//
//    /// <summary>
//    /// 批量转单
//    /// </summary>
//    /// <param name="reliid">平台订单号[,]</param>

    /**
     * 批量转单
     *
     * @param ids 平台订单号[,]
     * @return errorMsg
     */
    public static String TransOrderBatch(String ids) {
        StringBuilder builder = new StringBuilder();
        String[] arrPTDD = ids.split(",");
        for (int i = 0; i < arrPTDD.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(Test.GetStrValue("SELECT mstid FROM dbo.wsp_ordermst WHERE relid='" + arrPTDD[i] + "';"));
        }
        String errorMsg;
        System.out.println("转单,平台订单号:" + ids);
        //String ids = Test.GetStrValue("SELECT mstid FROM dbo.wsp_ordermst WHERE relid='" + reliid + "';");
        String content = JSON.toJSONString(new IDS(builder.toString()));
        errorMsg = Util.PostJsonData("/Admin/Plat/OrderList.aspx/TransOrder", content);
//        if (errorMsg != "") {
//            System.out.println("转单故障" + errorMsg);
//        }
        return errorMsg;
    }
//
//    /// <summary>
//    /// 一键转单
//    /// </summary>
//    /// <param name="shopid"></param>

    /**
     * 一键转单
     *
     * @param shopname
     */
    public static void TransOrderByShop(String shopname) {
        String errorMsg;
        System.out.println("一键转单，店铺为：" + shopname);
        int shopid = Test.GetIntValue("SELECT id FROM dbo.wsp_shops WHERE shopname='" + shopname + "';");

        String content = JSON.toJSONString(new BatchTransClass(shopid+""));
        content = JSON.toJSONString(new BatchTrans(content));
        errorMsg = Util.PostJsonData("/Admin/Plat/OrderList.aspx/BatchTransOrder", content);
        if (errorMsg.equals("")) {
            System.out.println("一键转单故障：" + errorMsg);
        }
    }
//
//    /// <summary>
//    /// 锁定订单
//    /// </summary>
//    /// <param name="orderid"></param>

    /**
     * 锁定订单
     *
     * @param orderid
     */
    public static String LockPOrder(String orderid) {
        String result;
        System.out.println("锁定订单" + orderid);
        String content = JSON.toJSONString(new OrderSplit(orderid));
        result = Util.PostJsonData("/Admin/Ord/POrderList.aspx/LockPOrder", content);
        //result=WSP.WebSite.Admin.Ord.POrderList.LockPOrder(orderid,',');
//        String expected = " " + orderid + ":<span style='color:green'>锁定成功</span><br/><br/>";
//        if (expected != result) {
//            System.out.println("锁定订单故障" + result);
//        }
        return Util.DeleteHtmlTag(result);
    }
//
//    /// <summary>
//    /// 解锁订单
//    /// </summary>
//    /// <param name="orderid"></param>

    /**
     * 解锁订单
     *
     * @param orderid
     */
    public static String UnLockPOrder(String orderid) {
        String result;
        System.out.println("解锁订单" + orderid);
        String content = JSON.toJSONString(new OrderSplit(orderid));
        result = Util.PostJsonData("/Admin/Ord/POrderList.aspx/UnLockPOrder", content);
        //result=WSP.WebSite.Admin.Ord.POrderList.UnLockPOrder(orderid,',');
//        String expected = " " + orderid + ":<span style='color:green'>解锁成功</span><br/><br/>";
//        if (expected != result) {
//            System.out.println("解锁订单故障" + result);
//        }
        return Util.DeleteHtmlTag(result);
    }
//
//    /// <summary>
//    /// 拆分订单
//    /// </summary>
//    /// <param name="orderid"></param>
//    /// <returns>拆分后的第一个订单</returns>

    /**
     * 拆分订单
     *返回结果直接赋值给result
     * @param orderid
     * @return 拆分后的第一个订单
     */
    public static String SplitOrder(String orderid) {
//        String result = "";
//        System.out.println("拆分订单" + orderid);
//        String content = JSON.toJSONString(new SplitOrder(orderid));
//        result = Util.TrimQuo(Util.PostJsonData("/Admin/Ord/POrderSplit.aspx/SplitOrder", content));
//        ContentFactory.getContent().setSplitOrderID(result);
//        ContentFactory.getContent().Result = result;
////        Assert.assertTrue("拆分订单结果验证", Util.BoolRegEX(result, "EO\\d{14};EO\\d{14}"));
//        return result.split(";")[0];
        return SplitOrder(orderid, "1");
    }

    /**
     * 拆分订单
     *返回结果直接赋值给result
     * @param orderid
     * @return 拆分后的第一个订单
     */
    public static String SplitOrder(String orderid,String splitstr) {
        String result;
        System.out.println("拆分订单" + orderid);
        String content = JSON.toJSONString(new SplitOrder(orderid,splitstr));
        result = Util.TrimQuo(Util.PostJsonData("/Admin/Ord/POrderSplit.aspx/SplitOrder", content));
        ContentFactory.getContent().setSplitOrderID(result);
        ContentFactory.getContent().Result = result;
//        Assert.assertTrue("拆分订单结果验证", Util.BoolRegEX(result, "EO\\d{14};EO\\d{14}"));
        return result.split(";")[0];
    }

    /**
     * 加入采购看板
     * @param orderid
     * @return
     */
    public static void CreatePurchaseOrder(String orderid) {
        String result;
        System.out.println("加入采购看板" + orderid);
        String content = JSON.toJSONString(new OrderSplit(orderid));
        result = Util.DeleteHtmlTag(Util.PostJsonData("/Admin/Ord/POrderList.aspx/CreatePurchaseOrder", content));
        System.out.println(result);
//        Assert.assertTrue(result.contains("加入采购看板成功"));
//        Assert.assertTrue("拆分订单结果验证", Util.BoolRegEX(result, "^EO\\d{14};EO\\d{14}$"));
    }

    /**
     * 取消采购看板
     * @param orderid
     */
    public static void SetPurchaseable(String orderid) {
        String result;
        System.out.println("取消采购看板" + orderid);
        String content = JSON.toJSONString(new OrderSplit(orderid));
        result = Util.PostJsonData("/Admin/Ord/POrderList.aspx/SetPurchaseable", content);
        Util.assertContain("取消成功", result);
//        Assert.assertTrue("拆分订单结果验证", Util.BoolRegEX(result, "^EO\\d{14};EO\\d{14}$"));
    }

    /**
     * 随机生成一个新订单与该订单合并
     * @param orderid
     * @return 合并后的订单号
     */
    public static String MergeNewOrder(String orderid){
        String result;
        String reliid= SaveOrders.SaveOrder();
        String orderid2=TransOrder(reliid);
        System.out.println("订单"+orderid+"与"+orderid2+"合并");
        String content=JSON.toJSONString(new OrderidsSplit(orderid+","+orderid2));
        result=Util.PostJsonData("/Admin/Ord/POrderAutoMergeList.aspx/MergeOrder",content);
//        Assert.assertTrue("验证合并后返回的订单号", BoolRegEX(result, "^EO\\d{14}$"));
        Assert.assertTrue("验证合并后返回的订单号", Util.BoolRegEX(result, "^\"EO\\d{14}\"$"));
        return result;
    }


    /// <summary>
    /// 使订单无效SET validflag='1
    /// </summary>
    /// <param name="orderId"></param>

    /**
     * 使订单无效
     * SET validflag='1
     *
     * @param orderId
     */
    public static void UnValidateOrder(String orderId) {
        Test.ExeSQL("UPDATE dbo.ec_eordermst SET validflag='1' WHERE orderid='" + orderId + "'");
    }


    public static String DelOrder(String ids) {
        String result;
        System.out.println("删除订单" + ids);
        String content = JSON.toJSONString(new  IDS(ids));
        result = Util.PostJsonData("/Admin/plat/OrderList.aspx/DelOrder", content);
//        Assert.assertTrue(result.contains("删除成功"));
        return result;
    }

    public static String DeletePOrder(String orders) {
        System.out.println("删除网上订单" + orders);
        String content = JSON.toJSONString( new OrderSplit(orders));
        String value = Util.PostJsonData("/Admin/ord/POrderList.aspx/DeletePOrder", content);
        return Util.DeleteHtmlTag(value);
    }

    /*
    获取订单列表
     */
    public static String GetOrderList(GetOrderList list) {
        String content = JSON.toJSONString(list);
        return Util.PostJsonData("/Admin/plat/OrderList.aspx/GetOrderList", content);
    }

    public static String GetPOrderList(GetPOrdmst getPOrdmst) throws Throwable {
        String content = JSON.toJSONString(getPOrdmst);
        return Util.PostJsonDataCookie("/Admin/ord/POrderList.aspx/GetPOrdmst", content);
    }

    public static String SetStockFlag(StockFlag stockFlag) {
        String content = JSON.toJSONString(stockFlag);
        return Util.PostJsonData("/admin/ord/DOrderList.aspx/SetStockFlag", content);
    }

    public static String SetGoodSendType(GoodSendType sendType) {
        String content = JSON.toJSONString(sendType);
        return Util.PostJsonData("/admin/ord/POrderList.aspx/SetGoodSendType", content);
    }

    /*
    修改仓库
     */
    public static String BatchSaveWarehouse(String orderId,String house) {
        String content = JSON.toJSONString(new WareHouse(orderId, house));
        return Util.PostJsonData("/admin/ord/POrderList.aspx/BatchSaveWarehouse", content);
    }

    public static String BatchSaveShelf(String orderId, String shelf) {
        String content = JSON.toJSONString(new Shelf(orderId, shelf));
        return Util.PostJsonData("/admin/ord/POrderList.aspx/BatchSaveShelf", content);
    }


//    public static void BatchTransOrder(String shop) {
//        String result = "";
//        System.out.println(shop+"执行一键转单" + ids);
//        String content = JSON.toJSONString(new BatchTrans(ids));
//        result = Util.PostJsonData("/Admin/plat/OrderList.aspx/DelOrder", content);
////        Assert.assertTrue(result.contains("删除成功"));
//    }

    public static String SaveInfo(OrderMstM orderMstM) {
        String content = JSON.toJSONString(orderMstM);
        return Util.PostJsonData("/admin/ord/POrderList.aspx/SaveInfo", content);

    }
    public static String SavePOrdDetaillInfo(POrdDetaill pOrdDetaill) {
        String content = JSON.toJSONString(pOrdDetaill);
        return Util.PostJsonData("/admin/ord/POrderList.aspx/SavePOrdDetaillInfo", content);

    }
}
