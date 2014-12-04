package UI.Pages;

/**
 * Created by taoyang on 14-10-9.
 */
public class pgPlatOrderList {

    public static String btn新增 = "span:contains(新增)";

    public static String btn作废 = "span.l-btn-text.icon-no:contains(作废)";
    public static String btn撤销作废 = "span.l-btn-text.icon-ok:contains(撤销作废)";
    public static String btn批量转单 = "span.l-btn-text.icon-batchtrans:contains(批量转单)";
    public static String btn一键转单 = "span.l-btn-text.icon-trans:contains(一键转单)";
    public static String btn平台订单导出 = "span.l-btn-text.icon-excel-out:contains(平台订单导出)";
    public static String btn模糊订单修复 = "span.l-btn-text.icon-excel-in:contains(模糊订单修复)";
    public static String btn下载 = "span.l-btn-text.icon-import:contains(下载)";

    public static String btn删除 = "span.icon-remove:contains(删除)";


    public static String txt订单编号 = "#ctl00_cphHead_txtOrderid";

    public static String slt店铺 = "#ctl00_cphHead_ddlShops";
    public static String slt订单状态 = "#ctl00_cphHead_ddlIsTransfer";
    public static String slt类型 = "#ctl00_cphHead_ddlDealerflag";
    public static String slt作废 = "#ctl00_cphHead_ddlDelete";

    public static String slt货到付款 = "#ctl00_cphHead_ddlHdflag";
    public static String txt收货人 = "#ctl00_cphHead_txtRec";
    public static String btn显示全部选项 = "#aHanlder";




    



    public static String btn查询 = "span.l-btn-text.icon-search:contains(查询)";
    public static String btn清空 = "span.l-btn-text.icon-cancel:contains(清空)";
    public static java.lang.String btn下一页 = "span.pagination-next";
    public static String dt付款时间="#startdate";
    public static String dt付款结束时间="#enddate";

    public static String Getlnk订单编号(String text) {
        return "a:contains(" + text + ")";
    }

    public static String lbl支付状态(String text) {
        return "tr[datagrid-row-index=0] td[field=Payflag] div span:contains(" + text + ")";
    }

    public static String lbl平台订单(String name) {
        return "td[field=Relid] div:contains(" + name + ")";
    }

    public static class clsAlert {
        public static String txt作废原因 = "#txtReason";
        public static String btn确定 = "span.l-btn-text.icon-ok:contains(确定)";
        public static String btn取消 = "span.l-btn-text:contains(取消)";
    }
}
