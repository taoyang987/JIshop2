package UI.Pages;

/**
 * Created by taoyang on 14-10-11.
 */
public class fmPicker {
    public static String frame="$('#ifrContent').contents().find('iframe').contents()";
    //客户编号选择
    public static String lbl自动化客户1 = "tr[v=自动化客户1] td:eq(0)";

    //业务员
    public static String lbl陶阳 = "tr[v=陶阳] td:eq(0)";

    public static class cls选择分销商{
        public static String txt呢称 = "#ctl00_chpCnt_txtdistributorname";
        public static String btn查询 = "span.icon-search";

        public static String btn提交 = "#hypSubmit";

        public static String lbl分销商(String name) {
            return "tr[datagrid-row-index=0] td[field=Distributornick] div:contains(" + name + ")";
        }
    }
}
