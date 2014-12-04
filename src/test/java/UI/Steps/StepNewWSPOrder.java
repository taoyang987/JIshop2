package UI.Steps;

import UI.Pages.*;
import Utility.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by taoyang on 14-10-8.
 */
public class StepNewWSPOrder extends StepBase {
    String str业务员;
    String str业务员电话;
    Date mytime;
    String orderid;
    String str开票单位;
    String strSellerMark;
    String strBuyerMark;
    Goods mygood;
    String mstid;

    @Given("^跳转到B2B订单管理页面$")
    public void 跳转到B2B订单管理页面() throws Throwable {
        // Express the Regexp above with the code you wish you had
        new UICommonStep().默认账号登录();
        Index index = new Index();
        index.btn平台对接.click();
        JS.MenuClk("B2B订单管理");
        UIUtil.SyncList();
    }

    @When("^点新增按钮$")
    public void 点新增按钮() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.btn新增);
        relid = JS.GetValue(pgPlatOrderCreate.txt平台订单);
        System.out.println("平台订单号:" + relid);
    }

    @And("^在基本信息中录入所有必输项数据$")
    public void 在基本信息中录入所有必输项数据(DataTable table) throws Throwable {
        // Express the Regexp above with the code you wish you had

        String shop = table.getGherkinRows().get(1).getCells().get(0);
        JS.Click(pgPlatOrderCreate.slt店铺);
        JS.Click(pgPlatOrderCreate.opt店铺(shop));

        String Linkman = table.getGherkinRows().get(1).getCells().get(1);
        JS.SetValue(pgPlatOrderCreate.txt联系人, Linkman);

        String phone = table.getGherkinRows().get(1).getCells().get(2);
        JS.SetValue(pgPlatOrderCreate.txt联系电话, phone);

        String handSet = table.getGherkinRows().get(1).getCells().get(3);
        JS.SetValue(pgPlatOrderCreate.txt手机, handSet);

        String province = table.getGherkinRows().get(1).getCells().get(4);
        JS.Click(pgPlatOrderCreate.slt省);
        JS.Click(pgPlatOrderCreate.opt省(province));

        String city = table.getGherkinRows().get(1).getCells().get(5);
        JS.Click(pgPlatOrderCreate.slt市);
        JS.Click(pgPlatOrderCreate.opt市(city));

        String qu = table.getGherkinRows().get(1).getCells().get(6);
        JS.Click(pgPlatOrderCreate.slt区);
        JS.Click(pgPlatOrderCreate.opt区(qu));

        String address = table.getGherkinRows().get(1).getCells().get(7);
        JS.SetValue(pgPlatOrderCreate.txt详细地址, address);

//        JS.Click(pgPlatOrderCreate.btn保存并返回);
    }

    @And("^在明细信息选择默认商品$")
    public void 在明细信息选择默认商品() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.btn明细信息);
        JS.Click(pgPlatOrderCreate.btn新增明细);

        //选择商品弹窗
        JS.SetValue(fmChooseItem.frame, fmChooseItem.txt编号, VarDefault.商品编号);
        Thread.sleep(1000);
        JS.Click(fmChooseItem.frame, fmChooseItem.btn查询);
        JS.WaitLoop(fmChooseItem.frame, fmChooseItem.lbl第一行sku(VarDefault.商品编号));
        JS.Click(fmChooseItem.frame, fmChooseItem.chk);
        JS.Click(fmChooseItem.frame, fmChooseItem.btn提交);
    }

    @Then("^点保存并返回按钮$")
    public void 点保存并返回按钮() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.btn保存并返回);
    }

    @And("^校验新增成功$")
    public void 校验新增成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
        assertTrue(JS.WaitLoop(":contains(添加成功)"));
    }

    @And("^跳转到平台订单列表并查询$")
    public void 跳转到平台订单列表并查询() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        throw new PendingException();
        if (relid == null) {
            relid = ContentFactory.getContent().getReliid();
        }
        JS.Click(pgPlatOrderList.btn清空);
        JS.SetValue(pgPlatOrderList.txt订单编号, relid);
        JS.Click(pgPlatOrderList.btn查询);
        UIUtil.SyncQueryList();
    }

    @And("^校验支付状态为未支付$")
    public void 校验支付状态为未支付() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.WaitLoop(pgPlatOrderList.lbl支付状态("未支付"));
    }

    @And("^跳转到该订单详细页面校验$")
    public void 跳转到该订单详细页面校验() throws Throwable {
        // Express the Regexp above with the code you wish you had
        Thread.sleep(2000);
        JS.Click(pgPlatOrderList.Getlnk订单编号(relid));
        Util.switchToWindow("平台订单");
//        String url = Util.GetDr().getCurrentUrl().replace("Admin", "admin/plat");
//        Util.GetDr().get(url);
        Thread.sleep(1000);
        Util.GetDr().close();
        Util.switchToWindow("ishop网络商店软件管理");

    }

    @And("^新增平台订单失败$")
    public void 新增平台订单失败() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        int num=SQL.GetIntValue("select count(1) as num from wsp_ordermst where relid='" + relid + "'", "num");
        int num = (Integer) Test.GetVaule("select count(1) as num from wsp_ordermst where relid='" + relid + "'", "num");
        Assert.assertEquals(0, num);
    }


    @And("^校验提示各必输项不能为空$")
    public void 校验提示各必输项不能为空() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        throw new PendingException();
        Chk.ChkText(pgPlatOrderCreate.lbl联系人提示, "联系人不能为空");
        Chk.ChkContainText(pgPlatOrderCreate.lbl联系电话提示, "联系电话和手机两项中必填一项");
        Chk.ChkContainText(pgPlatOrderCreate.lbl手机提示, "联系电话和手机两项中必填一项");
        Chk.ChkText(pgPlatOrderCreate.lbl省市区提示, "省份城市不能为空");
        Chk.ChkText(pgPlatOrderCreate.lbl详细地址提示, "详细地址不能为空");
    }

    @And("^禁用B2B店铺$")
    public void 禁用B_B店铺() throws Throwable {
        // Express the Regexp above with the code you wish you had
        DoOrder.ChangeStatus("7", false);//B2B自动化北京店铺id为7
    }

    @And("^启用B2B店铺$")
    public void 启用B_B店铺() throws Throwable {
        // Express the Regexp above with the code you wish you had
        DoOrder.ChangeStatus("7", true);//B2B自动化北京店铺id为7
    }

    @And("^选择默认客户$")
    public void 选择默认客户() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.btn客户编号);
        JS.Click(fmPicker.frame, fmPicker.lbl自动化客户1);


    }

    @And("^选择默认业务员$")
    public void 选择默认业务员() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.btn业务员);
        JS.Click(fmPicker.frame, fmPicker.lbl陶阳);
        str业务员 = "陶阳";
        str业务员电话 = "13488888888";

    }

    @And("^批量支付该订单$")
    public void 批量支付该订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String orderid = DoOrder.PayAndTransOrder(relid);
        System.out.println("网上订单编号:" + orderid);
    }

    @And("^数据库校验业务员和业务员联系方式$")
    public void 数据库校验业务员和业务员联系方式() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String man = Test.GetStrValue("select s_name NUM from emp WHERE emp_no=(SELECT runemp from ec_eordermst WHERE relid='" + relid + "');");
        Assert.assertEquals("陶阳", man);
    }

    @And("^修改自动显示的平台订单号为已存在的平台订单号$")
    public void 修改自动显示的平台订单号为已存在的平台订单号() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String oldrelid = Test.GetStrValue("SELECT TOP(1) relid FROM dbo.wsp_ordermst ORDER BY recorddate DESC;");
        JS.SetValue(pgPlatOrderCreate.txt平台订单, oldrelid);
    }

    @And("^数据库校验业务员和业务员联系方式未录入$")
    public void 数据库校验业务员和业务员联系方式未录入() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String man = Test.GetStrValue("SELECT runemp from ec_eordermst WHERE relid='" + relid + "'");
        assertEquals(man, "001");//没有时为001
    }

    @And("^删除自动显示的平台订单号$")
    public void 删除自动显示的平台订单号() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.SetValue(pgPlatOrderCreate.txt平台订单, "");
    }

    @And("^到货时间选择 (.*)$")
    public void 到货时间选择(String day) throws Throwable {
        // Express the Regexp above with the code you wish you had
        Date time = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        String strTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        if (day.equals("昨天")) {
            gc.add(5, -1);
            mytime = gc.getTime();
            strTime = sdf.format(gc.getTime());
        } else if (day.equals("今天")) {
            mytime = time;
            strTime = sdf.format(time);
        } else {
            System.out.println("选择的日期错误");
        }
        JS.SetValue(pgPlatOrderCreate.txt到货时间val, strTime);
    }

    @And("^校验到货时间$")
    public void 校验到货时间() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        Timestamp ts = (Timestamp) Test.GetVaule("select storearrivaltime from wsp_ordermst t where relid='" + relid + "'");
        Date dbTime = new Date(ts.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        assertEquals(sdf.format(mytime), sdf.format(dbTime));
    }

    @And("^设为分销代销订单$")
    public void 设为分销代销订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.chk分销是);
        JS.Click(pgPlatOrderCreate.chk代销);
//        JS.Click(pgPlatOrderCreate.btn选择分销商);//原本方式无法生效
        JS.ClickByID("A1");
        String str分销商 = "自动化分销商1";
        JS.SetValue(fmPicker.frame, fmPicker.cls选择分销商.txt呢称, str分销商);
        JS.Click(fmPicker.frame, fmPicker.cls选择分销商.btn查询);
        JS.Click(fmPicker.frame, fmPicker.cls选择分销商.lbl分销商(str分销商));
        JS.Click(fmPicker.frame, fmPicker.cls选择分销商.btn提交);
    }

    @And("^开具默认发票$")
    public void 开具默认发票() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.chk开发票是);
        str开票单位 = "个人";
        JS.SetValue(pgPlatOrderCreate.txt开票单位, str开票单位);
    }

    @And("^校验平台订单发票信息$")
    public void 校验平台订单发票信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String billcompany = Test.GetStrValue("SELECT billcompany FROM dbo.wsp_ordermst where relid='" + relid + "'");
        assertEquals(str开票单位, billcompany);
    }


    @And("^校验网上订单发票信息$")
    public void 校验网上订单发票信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String billcompany = Test.GetStrValue("SELECT billcompany FROM ec_eordermst where relid='" + relid + "'");
        assertEquals(str开票单位, billcompany);
    }

    @And("^输入默认卖家客服备注$")
    public void 输入默认卖家客服备注() throws Throwable {
        // Express the Regexp above with the code you wish you had
        strBuyerMark = "卖家默认备注信息";
        JS.SetValue(pgPlatOrderCreate.txt卖家备注, strBuyerMark);
    }

    @And("^校验平台订单卖家备注信息$")
    public void 校验平台订单卖家备注信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String tmp = Test.GetStrValue("SELECT sellerremarks  FROM dbo.wsp_ordermst where relid='" + relid + "'");
        assertEquals(strBuyerMark, tmp);
    }

    @And("^校验网上订单卖家备注信息$")
    public void 校验网上订单卖家备注信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String tmp = Test.GetStrValue("SELECT sellerremarks FROM ec_eordermst where relid='" + relid + "'");
        assertEquals(strBuyerMark, tmp);
    }

    @And("^输入买家备注 (.*)$")
    public void 输入买家备注(String str) throws Throwable {
        // Express the Regexp above with the code you wish you had
        strBuyerMark = str;
        JS.SetValue(pgPlatOrderCreate.txt买家备注, strBuyerMark);
    }

    @And("^校验平台订单买家备注信息$")
    public void 校验平台订单买家备注信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String tmp = Test.GetStrValue("SELECT buyerremarks FROM dbo.wsp_ordermst where relid='" + relid + "'");
        assertEquals(strBuyerMark, tmp);
        ;
    }

    @And("^校验网上订单买家备注信息$")
    public void 校验网上订单买家备注信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String tmp = Test.GetStrValue("SELECT buyerremarks FROM dbo.ec_eordermst where relid='" + relid + "'");
        assertEquals(strBuyerMark, tmp);
        ;
    }

    @And("^删除已添加的商品$")
    public void 删除已添加的商品() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.lbl明细列表商品(VarDefault.商品编号));
        JS.Click(pgPlatOrderCreate.clsDetail.btn删除);
    }

    @And("^修改已添加商品信息$")
    public void 修改已添加商品信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click("tr[datagrid-row-index=0] td[field=Saleprice] div:contains(0)");//单击第一行成交单价
        mygood = new Goods("800", "2", "90%", "100.00", "200.00", "300.00", "备注内容");
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt成交单价, mygood.price);
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt数量, mygood.qty);
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt折扣率, mygood.discount);
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt体积, mygood.volume);
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt标准工时, mygood.standardtime);
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt物流成本, mygood.freight);
        JS.SetValue(pgPlatOrderCreate.clsDetail.txt备注, mygood.remarks);
        JS.Click(pgPlatOrderCreate.clsDetail.btn结束修改);
    }

    @And("^校验已修改商品信息平台订单$")
    public void 校验已修改商品信息平台订单() throws Throwable {
        mstid = Test.GetMstidByReliid(relid);
        // Express the Regexp above with the code you wish you had
        String price = Test.GetVaule("SELECT price FROM dbo.wsp_orderdtl WHERE mstid='" + mstid + "'").toString();
        assertEquals(mygood.price + ".00000000", price);
        String qty = Test.GetVaule("SELECT qty FROM dbo.wsp_orderdtl WHERE mstid='" + mstid + "'").toString();
        assertEquals(mygood.qty + ".00000000", qty);
        String remarks = Test.GetVaule("SELECT remarks FROM dbo.wsp_orderdtl WHERE mstid='" + mstid + "'").toString();
        assertEquals(mygood.remarks, remarks);
    }

    @And("^校验已修改商品信息网上订单$")
    public void 校验已修改商品信息网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had

    }

    @And("^校验平台订单代销信息$")
    public void 校验平台订单代销信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String distributortype = Test.GetStrValue("SELECT distributortype FROM dbo.wsp_ordermst WHERE relid='" + relid + "'");
        assertEquals("AGENT", distributortype);
    }

    @And("^校验平台订单经销信息$")
    public void 校验平台订单经销信息() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String distributortype = Test.GetStrValue("SELECT distributortype FROM dbo.wsp_ordermst WHERE relid='" + relid + "'");
        assertEquals("DEALER", distributortype);
    }

    @And("^设为分销经销订单$")
    public void 设为分销经销订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderCreate.chk分销是);
        JS.Click(pgPlatOrderCreate.chk经销);
        JS.ClickByID("A1");
        String str分销商 = "自动化分销商1";
        JS.SetValue(fmPicker.frame, fmPicker.cls选择分销商.txt呢称, str分销商);
        JS.Click(fmPicker.frame, fmPicker.cls选择分销商.btn查询);
        JS.Click(fmPicker.frame, fmPicker.cls选择分销商.lbl分销商(str分销商));
        JS.Click(fmPicker.frame, fmPicker.cls选择分销商.btn提交);
    }

    @Given("^跳转到淘宝订单管理页面$")
    public void 跳转到淘宝订单管理页面() throws Throwable {
        // Express the Regexp above with the code you wish you had
        new UICommonStep().默认账号登录();
        Index index = new Index();
        index.btn平台对接.click();
        JS.MenuClk("淘宝订单管理");
        UIUtil.SyncList();
    }

    @Then("^列表中不存在该订单$")
    public void 列表中不存在该订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        new UICommonStep().UI不存在(pgPlatOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
    }

    @Then("^列表中存在该订单$")
    public void 列表中存在该订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        new UICommonStep().UI存在(pgPlatOrderList.lbl平台订单(ContentFactory.getContent().getReliid()));
    }

    @And("^点击清空按钮查询$")
    public void 点击清空按钮查询() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.btn清空);
        JS.Click(pgPlatOrderList.btn查询);
    }

    @And("^点击跳转到下一页$")
    public void 点击跳转到下一页() throws Throwable {
        // Express the Regexp above with the code you wish you had
        Thread.sleep(3000);
        JS.Click(pgPlatOrderList.btn下一页);
        UIUtil.SyncList2();
    }

    @And("^UI录入精确查询数据$")
    public void UI录入精确查询数据() throws Throwable {
        // Express the Regexp above with the code you wish you had
        JS.Click(pgPlatOrderList.btn显示全部选项);
        JS.SetValue(pgPlatOrderList.txt订单编号, ContentFactory.getContent().getReliid());
        JS.optSelect(pgPlatOrderList.slt店铺, "自动化店铺北京");
        JS.optSelect(pgPlatOrderList.slt订单状态, "已转单");
        JS.optSelect(pgPlatOrderList.slt类型, "直销");
        JS.SetValue(pgPlatOrderList.txt收货人, "Data");
        JS.optSelect(pgPlatOrderList.slt作废, "未作废");
        JS.optSelect(pgPlatOrderList.slt货到付款, "非货到付款");

        JS.DateChoose(pgPlatOrderList.dt付款时间, -1,0);
        JS.DateChoose(pgPlatOrderList.dt付款结束时间, 0,1);

        JS.Click(pgPlatOrderList.btn查询);
        UIUtil.SyncList2();
    }

    public class Goods {
        public Goods(String price, String qty, String discount, String volume, String standardtime, String freight, String remarks) {
            this.price = price;
            this.qty = qty;
            this.discount = discount;
            this.volume = volume;
            this.freight = freight;
            this.standardtime = standardtime;
            this.remarks = remarks;
        }

        public String price;
        public String qty;
        public String discount;
        public String volume;
        public String freight;
        public String standardtime;
        public String remarks;
    }
}
