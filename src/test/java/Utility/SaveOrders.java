package Utility;

import Utility.Entity.OrderMst;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * Created by taoyang on 14/10/31.
 */
public class SaveOrders {

    /**
     * 代销订单
     * @return
     */
    public static String SaveAgentOrder() {
        OrderMst mst = new OrderMst();
        mst.Distributorid = "自动化分销商1";
        mst.Distributorname = "自动化分销商1";
        mst.Distributortype = "AGENT";
        return SaveOrder("", "", "", JSON.toJSONString(mst));
    }
    public static String SaveOrder(String details, String name, String shop, String ordermst) {
//        String details="auto000100000001,1",String name="AutoData",String shop="自动化店铺北京",String ordermst=""
        if (shop == null || shop.equals("")) {
            shop="自动化店铺北京";
        }
        if (name == null || name.equals("")) {
            name = "AutoData";
        }
        if (details == null || details.equals("")) {
            details="auto000100000001,1";
        }
        int shopid = Test.GetIntValue("SELECT id FROM dbo.wsp_shops WHERE shopname='" + shop + "';");
        OrderMst objOrderMst;
        //String reliid=System.DateTime.Now.ToString("ddhhmmss")+System.DateTime.Now.Millisecond;

        if (ordermst==null|| ordermst.equals("")) {
            objOrderMst = new OrderMst();
            //objOrderMst = JsonConvert.DeserializeObject(ordermst);//"{\"Linkman\":\""+name+"\",\"Linktel\":\"\",\"Handset\":\"13488888888\",\"Recaddr\":\"\",\"Zipcode\":\"\",\"Shopid\":\""+shopid+"\",\"Prono\":\"\",\"City\":\"\\u5317\\u4eac\\u5e02\",\"Region\":\"\\u4e1c\\u57ce\\u533a\",\"Relid\":\"" + reliid + "\",\"Buyerremarks\":\"\",\"Sellerremarks\":\"\",\"ReceiveAddr\":\"\\u5317\\u4eac\\u8be6\\u7ec6\\u5730\\u5740\",\"Province\":\"\\u5317\\u4eac\",\"Billcompany\":\"\\u4e2a\\u4eba\",\"Hdflag\":\"0\",\"Ordershouldpay\":\"0\"}";
            objOrderMst.Linkman = name;
            objOrderMst.Shopid = String.valueOf(shopid);
            ordermst = JSON.toJSONString(objOrderMst);
        } else {
//            ordermst=ordermst+"\"Relid\":\""+Util.saveRelid()+"\"";//Relid 自动生成
            objOrderMst = JSON.parseObject(ordermst, OrderMst.class);
        }
//        details = ((details == null || details.equals("")) ? "auto000100000001,1" : details);
        String[] arrDetail = details.split("\\|"); //特殊字符要加\\转义
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < arrDetail.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            String[] product = arrDetail[i].split(",");
            HashMap<String, String> dr = Test.GetHashVaule("SELECT itemno,skuname,strdisplay,skuid,barcode,marketprice,saleprice FROM wsp_itemspecification s INNER JOIN wsp_product p ON s.itemno = p.productid WHERE p.ishistory=0 and s.skuid='" + product[0] + "'");
            builder.append("{");
            builder.append("\"Itemno\":\"").append(dr.get("itemno")).append("\"");
            builder.append(",\"Itemname\":\"").append(dr.get("skuname")).append("\"");
            builder.append(",\"Strdisplay\":\"").append(dr.get("strdisplay")).append("\"");
            builder.append(",\"Skuid\":\"").append(dr.get("skuid")).append("\"");
            builder.append(",\"Barcode\":\"").append(dr.get("barcode")).append("\"");
            builder.append(",\"Marketprice\":").append(dr.get("marketprice"));
            builder.append(",\"Saleprice\":").append(dr.get("saleprice"));
            builder.append(",\"Qty\":").append(product[1]);
            builder.append(",\"Count\":100");
            builder.append(",\"Sum\":1");
            builder.append("}");
        }
        builder.append("]");
        String orderDtl = builder.toString();
        //String content="{\"ordermst\":"+ordermst+",\"orderdtl\":"+"1"+"}";
        String content = JSON.toJSONString(new Order(ordermst, orderDtl));
        //content = "{\"ordermst\": \"1\",\"orderdtl\": \"2\"}";
        String result=Util.PostJsonData("/admin/plat/ordercreate.aspx/SaveOrder", content);
        //return Test.GetStrValue("SELECT mstid FROM dbo.wsp_ordermst WHERE relid='"+reliid+"';");
        if (result.equals("\"\"")|| result.equals("null")) {
            System.out.println("生成平台订单:" + objOrderMst.Relid);
        } else {
            System.out.println("生成平台订单返回结果不是\"\":可能出错了"+result );
        }
        return objOrderMst.Relid;
    }

    /*
        货到付款订单
         */
    public static String SavePayOrder(){
        return SavePayOrder("","","","");
    }

    public static String SavePayOrder(String details, String name, String shop, String ordermst) {
//        String details="auto000100000001,1",String name="AutoData",String shop="自动化店铺北京",String ordermst=""
//        ordermst={"Billcompany":"个人","Buyerremarks":"买家备注内容","City":"北京市","Handset":"13488888888","Hdflag":"1","Linkman":"AutoData","Linktel":"0571-88888888","Ordershouldpay":"123","Prono":"","Province":"北京","Recaddr":"默认收获地址","ReceiveAddr":"","Region":"东城区","Relid":"21100434724","Sellerremarks":"卖家备注内容","Shopid":"1","Zipcode":""}
        if (shop == null || shop.equals("")) {
            shop="自动化店铺北京";
        }
        if (name == null || name.equals("")) {
            name = "AutoData";
        }
        int shopid = Test.GetIntValue("SELECT id FROM dbo.wsp_shops WHERE shopname='" + shop + "';");
        OrderMst objOrderMst;
        //String reliid=System.DateTime.Now.ToString("ddhhmmss")+System.DateTime.Now.Millisecond;

        if (ordermst == null || ordermst.equals("")) {
            objOrderMst = new OrderMst();
            //objOrderMst = JsonConvert.DeserializeObject(ordermst);//"{\"Linkman\":\""+name+"\",\"Linktel\":\"\",\"Handset\":\"13488888888\",\"Recaddr\":\"\",\"Zipcode\":\"\",\"Shopid\":\""+shopid+"\",\"Prono\":\"\",\"City\":\"\\u5317\\u4eac\\u5e02\",\"Region\":\"\\u4e1c\\u57ce\\u533a\",\"Relid\":\"" + reliid + "\",\"Buyerremarks\":\"\",\"Sellerremarks\":\"\",\"ReceiveAddr\":\"\\u5317\\u4eac\\u8be6\\u7ec6\\u5730\\u5740\",\"Province\":\"\\u5317\\u4eac\",\"Billcompany\":\"\\u4e2a\\u4eba\",\"Hdflag\":\"0\",\"Ordershouldpay\":\"0\"}";
            objOrderMst.Linkman = name;
            objOrderMst.Shopid = String.valueOf(shopid);
            objOrderMst.Hdflag = "1";
            objOrderMst.Ordershouldpay = "123";

            ordermst = JSON.toJSONString(objOrderMst);
        } else {
            ordermst=ordermst+"\"Relid\":\""+Util.saveRelid()+"\"";//Relid 自动生成
            objOrderMst = JSON.parseObject(ordermst, OrderMst.class);
        }
        details = ((details == null || details.equals("")) ? "auto000100000001,1" : details);
        String[] arrDetail = details.split("\\|"); //特殊字符要加\\转义
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < arrDetail.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            String[] product = arrDetail[i].split(",");
            HashMap<String, String> dr = Test.GetHashVaule("SELECT itemno,skuname,strdisplay,skuid,barcode,marketprice,saleprice FROM wsp_itemspecification s INNER JOIN wsp_product p ON s.itemno = p.productid WHERE p.ishistory=0 and s.skuid='" + product[0] + "'");
            builder.append("{");
            builder.append("\"Itemno\":\"").append(dr.get("itemno")).append("\"");
            builder.append(",\"Itemname\":\"").append(dr.get("skuname")).append("\"");
            builder.append(",\"Strdisplay\":\"").append(dr.get("strdisplay")).append("\"");
            builder.append(",\"Skuid\":\"").append(dr.get("skuid")).append("\"");
            builder.append(",\"Barcode\":\"").append(dr.get("barcode")).append("\"");
            builder.append(",\"Marketprice\":").append(dr.get("marketprice"));
            builder.append(",\"Saleprice\":").append(dr.get("saleprice"));
            builder.append(",\"Qty\":").append(product[1]);
            builder.append(",\"Count\":100");
            builder.append(",\"Sum\":1");
            builder.append("}");
        }
        builder.append("]");
        String orderDtl = builder.toString();
        //String content="{\"ordermst\":"+ordermst+",\"orderdtl\":"+"1"+"}";
        String content = JSON.toJSONString(new Order(ordermst, orderDtl));
        //content = "{\"ordermst\": \"1\",\"orderdtl\": \"2\"}";
        String result = Util.PostJsonData("/admin/plat/ordercreate.aspx/SaveOrder", content);
        //return Test.GetStrValue("SELECT mstid FROM dbo.wsp_ordermst WHERE relid='"+reliid+"';");
        if (result.equals("\"\"")) {
            System.out.println("生成平台订单:" + objOrderMst.Relid);
        } else {
            System.out.println("生成平台订单返回结果不是\"\":可能出错了"+result );
        }
        return objOrderMst.Relid;
    }

    /**
     * 新建平台订单
     * details = "auto000100000001,1"
     * @return 平台订单号
     */
    public static String SaveOrder() {
        String details = "auto000100000001,1";

        return SaveOrder(details);
    }

    public static String SaveB2BOrder() {
        String details = "auto000100000001,1";

        return SaveOrder(details,"AutoData","B2B自动化北京店铺");
    }

    public static String SaveB2BOrder(String details) {

        return SaveOrder(details,"AutoData","B2B自动化北京店铺");
    }

    public static String SaveB2COrder() {
        String details = "auto000100000001,1";

        return SaveOrder(details,"AutoData","B2C自动化北京店铺");
    }

    public static String SaveOrder(String details) {
        String name = "AutoData";

        return SaveOrder(details, name);
    }

    public static String SaveOrder(String details, String name) {
        String shop = "自动化店铺北京";
        return SaveOrder(details, name, shop);
    }

    public static String SaveOrder(String details, String name, String shop) {
        String ordermst = "";
        return SaveOrder(details, name, shop,ordermst);
    }
}
