package UI.Pages;

/**
 * Created by taoyang on 14-10-9.
 */
public class pgPlatOrderCreate {
    public static String slt店铺 = "#ctl00_cphMain_tabs_ddlShop ~ span span span.combo-arrow";
    public static String txt店铺 = "#ctl00_cphMain_tabs_ddlShop ~ span input.combo-text.validatebox-text";

    public static String opt店铺(String name) {
        return "div.combobox-item:contains(" + name + ")";
    }

    public static String txt客户 = "#ctl00_cphMain_tabs_txtCustomna";

    public static String btn客户编号 = "#ctl00_cphMain_tabs_Label1";

    public static String btn业务员 = "#ctl00_cphMain_tabs_Label8";

    public static String txt业务员 = "#ctl00_cphMain_tabs_txtI6user";



    public static String txt平台订单 = "#ctl00_cphMain_tabs_txtRelid";
    public static String txt联系人 = "#ctl00_cphMain_tabs_txtLinkman";
    public static String txt手机 = "#ctl00_cphMain_tabs_txtHandset";
    public static String txt联系电话 = "#ctl00_cphMain_tabs_txtLinktel";
    public static String slt省 = "#ctl00_cphMain_tabs_ddlProvince + span span span.combo-arrow";
    public static String slt市 = "#ctl00_cphMain_tabs_ddlCity + span span span.combo-arrow";
    public static String slt区 = "#ctl00_cphMain_tabs_ddlRegion + span span span.combo-arrow";

    public static String opt省(String name) {
        return "body div:nth-child(4) div div.combobox-item:contains(" + name + ")";
    }

    public static String opt市(String name) {
        return "body div:nth-child(5) div div.combobox-item:contains(" + name + ")";
    }

    public static String opt区(String name) {
        return "body div:nth-child(6) div div.combobox-item:contains(" + name + ")";
    }

    public static String txt到货时间val = "input[name='ctl00$cphMain$tabs$txtStoreArrivalTime']";

    public static String txt详细地址 = "#ctl00_cphMain_tabs_txtAddress";

    public static String lbl联系人提示 = "#ctl00_cphMain_tabs_rfvLinkman";

    public static String lbl联系电话提示 = "#spanLinkphone";

    public static String lbl联系电话检验 = "#checkLinktel";

    public static String lbl手机提示 = "#spanCellphone";

    public static String lbl手机校验 = "#checkHandset";

    public static String lbl省市区提示 = "#spanAddress";

    public static String lbl详细地址提示 = "#ctl00_cphMain_tabs_rfvAddress";

    public static String chk分销是 = "#ctl00_cphMain_tabs_rblisdealerorder_1";
    public static String chk分销否 = "#ctl00_cphMain_tabs_rblisdealerorder_0";

    public static String chk代销 = "#ctl00_cphMain_tabs_rbldistributortype_0";

    public static String chk经销 = "#ctl00_cphMain_tabs_rbldistributortype_1";

    public static String chk开发票是 = "#ctl00_cphMain_tabs_rblMakebill_1";
    public static String chk开发票否 = "#ctl00_cphMain_tabs_rblMakebill_0";

    public static String chk发票个人 = "#ctl00_cphMain_tabs_rblBillType_0";
    public static String chk发票单位 = "#ctl00_cphMain_tabs_rblBillType_1";

    public static String txt开票单位 = "#txtbillcompany";

    public static String lbl开票单位提示 = "#checkBill";


    public static String txt卖家备注 = "#txtSellerRemarks";

    public static String txt买家备注 = "#txtBuyerRemarks";







    


    //region 菜单按钮
    public static String btn保存并返回 = "span.l-btn-text:contains(保存并返回)";

    public static String btn基本信息 = "span.tabs-title:contains(基本信息)";

    public static String btn明细信息 = "span.tabs-title:contains(明细信息)";
    //endregion

    //region 明细
    public static String btn新增明细 = "#btnAdd";
    public static class clsDetail {
        public static String btn删除 = "span.icon-remove";
        public static String btn结束修改 = "span.icon-edit";

        
        public static String txt成交单价 = "td[field=Saleprice] div table tbody td input";

        public static String txt数量 = "td[field=Qty] div table tbody td input";

        public static String txt折扣率 = "td[field=Count] div table tbody td input";

        public static String txt体积 = "td[field=VolumeTotal] div table tbody td input";


        public static String txt标准工时 = "td[field=StandardtimeTotal] div table tbody td input";


        public static String txt物流成本 = "td[field=FreightTotal] div table tbody td input";



        public static String txt备注 = "td[field=Remarks] div table tbody td input";


    }
    public static String lbl明细列表商品(String goods) {
        return "div.datagrid-cell:contains("+goods+")";
    }

    //endregion


}
