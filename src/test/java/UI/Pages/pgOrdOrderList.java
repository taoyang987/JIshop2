package UI.Pages;

/**
 * Created by taoyang on 14/10/31.
 */
public class pgOrdOrderList {
    public static String btn审核 = "span.l-btn-text.icon-check";
    public static String btn弃审 = "span.l-btn-text.icon-uncheck";

    public static String btn锁定 = "span.l-btn-text.icon-lock";
    public static String btn解锁 = "span.l-btn-text.icon-unlock";
    public static String btn拆分 = "span.l-btn-text.icon-split";
    public static String btn删除 = "span.l-btn-text.icon-remove";
    public static String btn网上订单导出 = "span.l-btn-text.icon-excel-out";
    public static String btn修改 = "span.l-btn-text.icon-edit:contains(修改)";
    public static String btn修改下拉 = "span.icon-edit:contains(修改) span.s-btn-downarrow";
    public static String btn批量修改为非采购缺货 = "#ctl00_ToolBar_tbb_def11";
    public static String btn批量修改为采购缺货 = "#ctl00_ToolBar_tbb_def12";
    public static String btn批量修改仓库= "#ctl00_ToolBar_tbb_def3";
    public static String btn批量修改快递公司= "#ctl00_ToolBar_tbb_print";
    public static String btn修改货架= "#ctl00_ToolBar_tbb_def6";
    public static String btn设为代发订单= "#ctl00_ToolBar_tbb_def4";
    public static String btn设为自发订单= "#ctl00_ToolBar_tbb_def5";
    public static String btn其他 = "span.l-btn-text.icon-edit:contains(其他)";

    public static String btn其他下拉 = "span.icon-edit:contains(其他) span.s-btn-downarrow";
    public static String btn加入采购看板= "#ctl00_ToolBar_tbb_def7";
    public static String btn取消采购看板= "#ctl00_ToolBar_tbb_def8";
    public static String btn生成售后申请单= "#ctl00_ToolBar_tbb_def10";

    public static String txt平台订单 = "input[p=Relid]";
    public static String txt网上订单 = "input[p=Orderid]";
    public static String slt所在平台 = "#ctl00_cphHead_ddlSysid";

    public static String slt店铺 = "#ctl00_cphHead_ddlShopid";

    public static String slt锁定 = "#ctl00_cphHead_ddlLock";

    public static String slt审核 = "#ctl00_cphHead_ddlCheck";

    public static String slt缺货 = "#ctl00_cphHead_EasyUIDropDown1";

    public static String slt解析 = "#ctl00_cphHead_ddlAnaylsis";

    public static String slt合并拆分 = "#ctl00_cphHead_ddlValidflag";

    public static String slt来源 = "#ctl00_cphHead_ddlSourceopt";

    public static String slt订单来源 = "#ctl00_cphHead_ddlOrderfrom";
    public static String slt打印 = "#ctl00_cphHead_ddlPrint";
    public static String slt退货状态 = "#ctl00_cphHead_ddlReturnflag";
    public static String slt代销 = "#ctl00_cphHead_ddlDealer";
    public static String slt货到付款 = "#ctl00_cphHead_ddlHD";
    public static String txt分销商 = "input[title=分销商]";
    public static String txt采购单号 = "input[title=采购单号]";
    public static String slt生成采购 = "#ctl00_cphHead_ddlPurchaseOrderflag";
    public static String slt采购缺货 = "#ctl00_cphHead_ddlOutofstockflag";
    public static String dt付款时间从 = "input[title=付款时间从]";

    public static String dt付款时间到= "input[title=付款时间到]";
    public static String dt下载时间从= "input[title=下载时间从]";
    public static String dt下载时间到= "input[p=RecordTo]";
    public static String dt发货时间从= "input[title=发货时间从]";
    public static String dt发货时间到= "input[p=SendTo]";
    public static String txt订单金额从 = "#txtsumfrom";
    public static String txt订单金额到 = "#txtsumto";
    public static String txt商品数量从 = "#txtqtyfrom";
    public static String txt商品数量到 = "#txtqtyto";
    public static String txt商品编号 = "input[title=商品编码]";
    public static String txt条形码 = "input[title=商品条码]";

    public static String txt买家= "input[title=买家]";
    public static String txt收货人= "input[title=收货人]";
    public static String txt联系方式= "input[title=联系方式]";
    public static String txt地址= "input[title=地址]";
    public static String slt快递公司 = "#ctl00_cphHead_ddlShipcomp";
    public static String slt配送 = "#ctl00_cphHead_ddlSend";
    public static String slt开具发票 = "#ctl00_cphHead_ddlBill";
    public static String slt卖家备注 = "#ctl00_cphHead_ddlSellerremarks";
    public static String slt买家备注 = "#ctl00_cphHead_ddlBuyerremarks";
    public static String slt仓库 = "#ctl00_cphHead_ddlWarehouse";

    public static String txt配送站点= "input[title=配送站点]";
    public static String slt所在货架 = "#cbgShelf";
    public static String slt发货方式 = "#ctl00_cphHead_ddlGoodSendType";

    public static String btn查询 = "span.l-btn-text.icon-search:contains(查询)";
    public static String btn清空 = "span.l-btn-text.icon-cancel:contains(清空)";
    public static String btn显示全部选项 = "#aHanlder";

    public static String lbl平台订单(String name) {
        return "td[field=Relid] div:contains(" + name + ")";
    }

}
