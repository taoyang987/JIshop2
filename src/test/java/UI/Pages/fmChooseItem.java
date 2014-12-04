package UI.Pages;

/**
 * Created by taoyang on 14-10-9.
 *  嵌套在iframe中
 */
 public class fmChooseItem {
    public static String frame="$('#ifrContent').contents().find('iframe').contents()";
    public static String txt编号 = "#txtno";

    public static String btn查询 = "span.icon-search";
    public static String chk = "div.datagrid-cell-check input[type=checkbox]";

    public static String lbl第一行sku(String sku){
        return  "tbody > tr:nth-child(1) > td[field=Skuid] > div:contains("+sku+")";
    }
    public static String btn提交 = "span.l-btn-text.icon-ok:contains(提交)";



}
