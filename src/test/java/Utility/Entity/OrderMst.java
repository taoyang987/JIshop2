package Utility.Entity;

import Utility.Util;

/**
 * Created by taoyang on 14/10/28.
 */
public class OrderMst{
    public OrderMst(){
        Linkman="AutoData";
        Linktel="0571-88888888";
        Handset="13488888888";
        Recaddr="默认收获地址";
        Shopid="1";
        Province="北京";
        City="北京市";
        Region="东城区";
        Hdflag="0";
        Ordershouldpay="0";
        Billcompany="个人";
        Sellerremarks="卖家备注内容";
        Buyerremarks="买家备注内容";
//        Relid= System.DateTime.Now.ToString("ddhhmmss")+System.DateTime.Now.Millisecond.ToString();
        Relid = Util.saveRelid();
//        long time=System.currentTimeMillis();
        Zipcode="";
        Prono="";
        ReceiveAddr = "";
    }

    public String Linkman;
    public String Linktel;
    public String Handset;
    public String Recaddr;
    public String Zipcode;
    public String Shopid;
    public String Prono;
    public String City;
    public String Region;
    public String Relid;
    public String Buyerremarks;
    public String Sellerremarks;
    public String ReceiveAddr;
    public String Province;
    public String Billcompany;
    public String Hdflag;
    public String Ordershouldpay;
    public String Distributorid;
    public String Distributorname;
    public String Distributortype;

}
