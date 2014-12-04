package UI.Steps;

import Utility.ContentFactory;
import Utility.Test;
import Utility.Util;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by taoyang on 14-10-15.
 */
public class ChkOrder{
    @And("^订单已作废$")
    public void 订单已作废() throws Throwable {
        for (String relid : ContentFactory.getContent().Listrelid) {
            // Express the Regexp above with the code you wish you had
            String Deleteflag = Test.GetStrValue("SELECT Deleteflag FROM dbo.wsp_ordermst WHERE relid='" + relid + "'");
            Assert.assertEquals("1", Deleteflag);
        }
    }

    @And("^订单未作废$")
    public void 订单未作废() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (String relid : ContentFactory.getContent().Listrelid) {
            String Deleteflag = Test.GetStrValue("SELECT Deleteflag FROM dbo.wsp_ordermst WHERE relid='" + relid + "'");
//            if (! Deleteflag.equals("0")) {
            if ((! "0".equals(Deleteflag))&&(Deleteflag!=null)) {
                System.out.println("与预期不一致Deleteflag:" + Deleteflag);
            }
        }
    }

    @And("^合并订单已作废$")
    public void 合并订单已作废() throws Throwable {
        // Express the Regexp above with the code you wish you had
        // Express the Regexp above with the code you wish you had
        String Deleteflag = Test.GetStrValue("SELECT Deleteflag FROM dbo.wsp_ordermst WHERE relid='" + ContentFactory.getContent().getReliid()+ "'");
        Assert.assertEquals("1", Deleteflag);
    }

    @And("^平台订单已删除$")
    public void 平台订单已删除() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (String relid : ContentFactory.getContent().Listrelid) {
            int flag = Test.GetIntValue("SELECT count(1) as num FROM dbo.wsp_ordermst WHERE relid='" + relid + "'");
            assertEquals("平台订单应已删除:",0,flag);
//            if (flag == 0) {
//            } else {
//                System.out.println("未被删除:" + flag);
//            }
        }
    }

    @And("^平台订单未删除$")
    public void 平台订单未删除() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (String relid : ContentFactory.getContent().Listrelid) {
            int flag = Test.GetIntValue("SELECT count(1) as num FROM dbo.wsp_ordermst WHERE relid='" + relid + "'");
            assertEquals("平台订单应未删除:",1,flag);
//            if (flag == 1) {
//            } else {
//                System.out.println("已被删除:" + flag);
//            }
        }
    }


    @And("^转单失败$")
    public void 转单失败() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (String relid : ContentFactory.getContent().Listrelid) {
            int flag = Test.GetIntValue("SELECT count(1) as num FROM dbo.ec_eordermst WHERE relid='" + relid + "'");
            assertEquals("应转单失败:",0,flag);
//            if (flag == 0) {
//            } else {
//                System.out.println("转单成功:" + relid);
//            }
        }
    }

    @And("^转单成功$")
    public void 转单成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
//        for (String relid : ContentFactory.getContent().Listrelid) {
        String relid= ContentFactory.getContent().getReliid();
            int flag = Test.GetIntValue("SELECT count(1) as num FROM dbo.ec_eordermst WHERE relid='" + relid + "'");
        assertEquals("应转单成功:",1,flag);
//            if (flag == 1) {
//            } else {
//                System.out.println("转单失败:" + relid);
//            }
//        }
    }

    @And("^一键转单成功$")
    public void 一键转单成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
        //一键转单容易出现一部分数据转单失败
        for (String relid : ContentFactory.getContent().Listrelid) {
            for (int i = 0; i < 30; i++) {
                int flag = Test.GetIntValue("SELECT count(1) as num FROM dbo.ec_eordermst WHERE relid='" + relid + "'");
                if (flag != 1) {
                    Thread.sleep(1000);
                }
            }
            int flag = Test.GetIntValue("SELECT count(1) as num FROM dbo.ec_eordermst WHERE relid='" + relid + "'");
            if (flag!=1) {
                System.out.println("转单失败:" + relid);
            }
        }
    }

    @And("^检验收货信息中显示店铺设置的货到付款的默认快递公司$")
    public void 检验收货信息中显示店铺设置的货到付款的默认快递公司() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String shipComp = Test.GetStrValue("select shipcomp from ec_eordermst where relid='" + ContentFactory.getContent().getReliid() + "';");
        Assert.assertEquals("auto02", shipComp);//auto02为货到付款快递
    }

    @And("^检验收货信息中显示店铺设置的默认快递公司$")
    public void 检验收货信息中显示店铺设置的默认快递公司() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String shipComp = Test.GetStrValue("select shipcomp from ec_eordermst where relid='" + ContentFactory.getContent().getReliid() + "';");
        Assert.assertEquals("auto", shipComp);//auto02为货到付款快递
    }

    @And("^检验收货信息中显示的快递公司 (.*)")
    public void 检验收货信息中显示的快递公司(String shipComp) throws Throwable {
        // Express the Regexp above with the code you wish you had
        String compname =Test.GetStrValue("SELECT compname FROM dbo.supplyfile WHERE compno=(SELECT shipcomp FROM dbo.ec_eordermst WHERE relid='" + ContentFactory.getContent().getReliid() + "');");
        Assert.assertEquals(Util.GetStrIfNull(shipComp), compname);
    }

    @And("^检验仓储信息$")
    public void 检验仓储信息(DataTable tb) throws Throwable {
        // Express the Regexp above with the code you wish you had
        List<HashMap<String,String>> lst = Util.GetHasList(tb);
        HashMap<String, String> has = lst.get(0);
//        |仓库名称|仓库编码|货架|供应商|

//        String house = has.get("仓库名称");
//        Assert.assertEquals(house, Test.GetStrValue(""));

        String houseCode = has.get("仓库编码");
        Assert.assertEquals(houseCode, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT defwhouse FROM dbo.ec_eorderdtl WHERE orderid='"+ ContentFactory.getContent().getOrderId()+"';")));
        String shelf = has.get("货架");
        Assert.assertEquals(shelf, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT defshelf FROM dbo.ec_eorderdtl WHERE orderid='" + ContentFactory.getContent().getOrderId() + "';")));
        String supply = has.get("供应商");
        Assert.assertEquals(supply, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT supplierno FROM dbo.ec_eorderdtl WHERE orderid='" + ContentFactory.getContent().getOrderId() + "';")));

    }


    @And("^合并订单未作废$")
    public void 合并订单未作废() throws Throwable {
        // Express the Regexp above with the code you wish you had
            // Express the Regexp above with the code you wish you had
            String Deleteflag = Test.GetStrValue("SELECT Deleteflag FROM dbo.wsp_ordermst WHERE relid='" + ContentFactory.getContent().getMergeOrderID() + "'");
//            Assert.assertEquals("0", Deleteflag);
        if ((! "0".equals(Deleteflag))&&(Deleteflag!=null)) {
            System.out.println("与预期不一致Deleteflag:" + Deleteflag);
        }
    }

    @Then("^校验查询结果中包含新建的订单$")
    public void 校验查询结果中包含新建的订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        Util.assertContain(ContentFactory.getContent().getReliid(), ContentFactory.getContent().Result);
    }

    @Then("^校验查询结果中不包含新建的订单$")
    public void 校验查询结果中不包含新建的订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        Util.assertNotContain(ContentFactory.getContent().getReliid(), ContentFactory.getContent().Result);
    }

    @And("^订单已审核$")
    public void 订单已审核() throws Throwable {
        // Express the Regexp above with the code you wish you had
        int i = getApprStat(ContentFactory.getContent().getOrderId());
        assertEquals("订单应已审核", 1, i);
    }

    public int getApprStat(String orderId) {
        return Test.GetshtValue("SELECT appflg FROM dbo.ec_eordermst WHERE orderid='" + orderId + "';").intValue();
    }


    @And("^订单未审核$")
    public void 订单未审核() throws Throwable {
        // Express the Regexp above with the code you wish you had
        int i = getApprStat(ContentFactory.getContent().getOrderId());
        assertEquals("订单应未审核", 0, i);
    }

    @Then("^批量提示$")
    public void 批量提示(List<Map<String,String>> tips) throws Throwable {
        // Express the Regexp above with the code you wish you had
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tips.size(); i++) {
            builder.append(ContentFactory.getContent().ListOrderId.get(i)).append(":").append(tips.get(i).get("Tips"));
        }
        Util.assertContain("批量提示", builder.toString(), ContentFactory.getContent().Result.replace(" ",""));

    }

    @And("^批量订单审核结果$")
    public void 批量订单审核结果(List<Map<String,String>> lmData) throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (int i = 0; i <lmData.size() ; i++) {
            String result = lmData.get(i).get("Results");
            int j = Util.GetIntByBoolStr(result);
            String orderid = ContentFactory.getContent().ListOrderId.get(i);
            assertEquals("校验" + orderid + "审核状态",j,getApprStat(orderid));
        }
    }

    @And("^订单已锁定$")
    public void 订单已锁定() throws Throwable {
        // Express the Regexp above with the code you wish you had
        assertEquals("订单应已锁定",1,getHangStat(ContentFactory.getContent().getOrderId()));
    }

    public int getHangStat(String orderId) {
        return Integer.parseInt(Test.GetStrValue("SELECT hangflag FROM dbo.ec_eordermst WHERE orderid='" + orderId + "';"));
    }

    @And("^订单未锁定$")
    public void 订单未锁定() throws Throwable {
        // Express the Regexp above with the code you wish you had
        assertEquals("订单应未锁定",0,getHangStat(ContentFactory.getContent().getOrderId()));
    }

    @And("^批量订单锁定结果$")
    public void 批量订单锁定结果(List<Map<String,String>> lmData) throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (int i = 0; i <lmData.size() ; i++) {
            String result = lmData.get(i).get("Results");
            boolean b = Util.GetBoolStr(result);
            String orderid = ContentFactory.getContent().ListOrderId.get(i);
            assertEquals("校验" + orderid + "审核状态",b,Util.GetBoolInt(getHangStat(orderid)));
        }
    }

    @And("^删除网上订单成功$")
    public void 删除网上订单成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for ( int i=0;i< ContentFactory.getContent().ListOrderId.size();i++) {
            int j=Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='"+ ContentFactory.getContent().ListOrderId.get(i)+"'");
            assertEquals("订单应已删除", 0, j);
        }
    }

    @And("^删除网上订单失败$")
    public void 删除网上订单失败() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for ( int i=0;i< ContentFactory.getContent().ListOrderId.size();i++) {
            int j=Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='"+ ContentFactory.getContent().ListOrderId.get(i)+"'");
            assertEquals("订单应已未删除", 1, j);
        }
    }

    @Then("^提示订单号$")
    public void 提示订单号() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String orderid = ContentFactory.getContent().getMergeOrderID();
        System.out.println("合并生成的订单号是:" + orderid);
        assertTrue("合并订单的结果:", Util.BoolRegEX(orderid, "^EO\\d{14}$"));
    }

    @And("^订单已合并$")
    public void 订单已合并() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for (String orderid : ContentFactory.getContent().ListOrderId)
        {
            String mergeFlag = Test.GetStrValue("SELECT mergeflag FROM dbo.ec_eordermst WHERE orderid='" + orderid + "';");
            Assert.assertEquals("订单" + orderid + "的mergeflag字段值", "1", mergeFlag);
            String validFlag = Test.GetStrValue("SELECT validflag FROM dbo.ec_eordermst WHERE orderid='" + orderid + "';");
            Assert.assertEquals("订单" + orderid + "的validflag字段值", "1", validFlag);
        }
    }

    @Then("^订单合并结果$")
    public void 订单合并结果(List<Map<String,String>> lstmap) throws Throwable {
        // Express the Regexp above with the code you wish you had
        String orderid = null;
        String tip = null;
        for (int i = 0; i < lstmap.size(); i++)
        {
            if(!lstmap.get(i).get("Results").equals("")){
                    orderid = ContentFactory.getContent().ListOrderId.get(i);
                    tip = lstmap.get(i).get("Tip");
                    break;
            }
        }
        String givenwords = "error:订单[" + orderid + "]" + tip;
        Assert.assertEquals("订单合并结果", givenwords, ContentFactory.getContent().Result);
    }

    @Then("^拆分成功$")
    public void 拆分成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String oldFlag = Test.GetStrValue("SELECT splitflag FROM dbo.ec_eordermst WHERE orderid='" + ContentFactory.getContent().getOrderId() + "'");
        assertEquals("拆分订单","1",oldFlag);
        for (String id : ContentFactory.getContent().getSplitOrderID()) {
            int res = Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='" + id + "'");
            assertEquals("拆分订单",1,res);
        }

    }

    @Then("^快递单绑定结果$")
    public void 快递单绑定结果(List<Map<String,String>> lstmap) throws Throwable {
        // Express the Regexp above with the code you wish you had
        String givenwords=null;
        for (int i=0;i<lstmap.size();i++) {
            String orderID = ContentFactory.getContent().ListOrderId.get(i);
            String results = lstmap.get(i).get("Results");
            if (!results.equals("")) {
                if (Util.GetBoolStr(results)) {
                     givenwords = "绑定成功!";
                    int tmp = Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='" + orderID + "' AND traceno='" + ContentFactory.getContent().DicTransNum.get(orderID) + "';");
                    assertEquals("绑定快递单结果",1,tmp);
                }else {
                     givenwords = "订单号" + orderID + lstmap.get(i).get("Tip");
                }
            }
        }
        Util.assertContain("绑定快递提示", givenwords, ContentFactory.getContent().Result);
    }

    @And("^删除合并后的网上订单成功$")
    public void 删除合并后的网上订单成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for ( int i=0;i< ContentFactory.getContent().ListMergeID.size();i++) {
            int j=Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='"+ ContentFactory.getContent().ListMergeID.get(i)+"'");
            assertEquals("订单应已删除", 0, j);
        }
    }

    @And("^校验合并前的原订单未删除$")
    public void 校验合并前的原订单未删除() throws Throwable {
        // Express the Regexp above with the code you wish you had
       删除网上订单失败();
    }

    @And("^删除合并后的网上订单失败$")
    public void 删除合并后的网上订单失败() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for ( int i=0;i< ContentFactory.getContent().ListMergeID.size();i++) {
            int j=Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='"+ ContentFactory.getContent().ListMergeID.get(i)+"'");
            assertEquals("订单应已删除", 1, j);
        }
    }

    @And("^删除拆分后的网上订单成功$")
    public void 删除拆分后的网上订单成功() throws Throwable {
        // Express the Regexp above with the code you wish you had
        for ( int i=0;i< ContentFactory.getContent().getSplitOrderID().size();i++) {
            int j=Test.GetIntValue("SELECT count(1) FROM dbo.ec_eordermst WHERE orderid='"+ ContentFactory.getContent().getSplitOrderID().get(i)+"'");
            assertEquals("订单应已删除", 0, j);
        }
    }

    @Then("^校验查询结果中包含新建的网上订单$")
    public void 校验查询结果中包含新建的网上订单() throws Throwable {
        // Express the Regexp above with the code you wish you had
        Util.assertContain(ContentFactory.getContent().getOrderId(), ContentFactory.getContent().Result);

    }

    @And("^校验为非采购缺货$")
    public void 校验为非采购缺货() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String flag = Test.GetStrValue("SELECT outofstockflag FROM dbo.ec_eordermst WHERE orderid='" + ContentFactory.getContent().getOrderId() + "'");
        assertEquals("校验为非采购缺货","0",flag);
    }

    @And("^校验为采购缺货$")
    public void 校验为采购缺货() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String flag = Test.GetStrValue("SELECT outofstockflag FROM dbo.ec_eordermst WHERE orderid='" + ContentFactory.getContent().getOrderId() + "'");
        assertEquals("校验为采购缺货","1",flag);
    }

    @And("^校验已被设为自发$")
    public void 校验已被设为自发() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String flag = Test.GetStrValue("SELECT goodsendtype FROM dbo.ec_eordermst WHERE orderid='" + ContentFactory.getContent().getOrderId() + "'");
        assertEquals("校验已被设为自发","1",flag);
    }

    @And("^校验已被设为代发$")
    public void 校验已被设为代发() throws Throwable {
        // Express the Regexp above with the code you wish you had
        String flag = Test.GetStrValue("SELECT goodsendtype FROM dbo.ec_eordermst WHERE orderid='" + ContentFactory.getContent().getOrderId() + "'");
        assertEquals("校验已被设为代发","2",flag);    }

    @And("^检验仓库信息$")
    public void 检验仓库信息(List<Map<String,String>> lstmap) throws Throwable {
        // Express the Regexp above with the code you wish you had
        Map<String, String> has = lstmap.get(0);
        String houseCode = has.get("仓库编码");
        Assert.assertEquals("仓库编码",houseCode, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT defwhouse FROM dbo.ec_eorderdtl WHERE orderid='" + ContentFactory.getContent().getOrderId() + "';")));
        String shelf = has.get("货架");
        Assert.assertEquals("货架",shelf, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT defshelf FROM dbo.ec_eorderdtl WHERE orderid='" + ContentFactory.getContent().getOrderId() + "';")));
        String supply = has.get("供应商");
        Assert.assertEquals("供应商",supply, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT supplierno FROM dbo.ec_eorderdtl WHERE orderid='" + ContentFactory.getContent().getOrderId() + "';")));
        String location = has.get("库位");
        Assert.assertEquals("库位",location, Util.GetEmptyStrIfNull(Test.GetStrValue("SELECT defwhloc  FROM dbo.ec_eorderdtl WHERE orderid='" + ContentFactory.getContent().getOrderId() + "';")));

    }
}
