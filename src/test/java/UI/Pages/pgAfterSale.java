package UI.Pages;

/**
 * Created by taoyang on 14/10/31.
 */
public class pgAfterSale {
    public static String btn保存并返回 = "span.icon-save:contains(保存并返回)";
    public static String btn保存并查看= "span.icon-save:contains(保存并查看)";
    public static String btn处理= "span.icon-redo:contains(处理)";
    public static String btn退款确认= "span.icon-push:contains(退款确认)";

    public static String txt网上订单 = "#ctl00_cphMain_txtOrderid";
    public static String slt售后类型 = "#ctl00_cphMain_ddlType";
    public static String slt售后原因 = "#ctl00_cphMain_ddlReason";
    public static String txt处理金额 = "#ctl00_cphMain_txtAmount";
    public static String slt金额承担方 = "#ctl00_cphMain_ddlAssume";
    public static String txt客服处理结果 = "#ctl00_cphMain_txtAsresult";

    public static String chk默认 = "td[field=ck] div.datagrid-cell-check input[type=checkbox]";

    public static class pgAfterSaleList{
        public static String getbtn查看(String relid) {
            return "td[field=Relid]:contains("+relid+") ~ td[field=del] div a";
        }
    }
}
