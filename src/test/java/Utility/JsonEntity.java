package Utility;

/**
 * Created by taoyang on 14-10-9.
 */
public class JsonEntity {
}

class Order
{
    public Order(String ordmst, String orddtl)
    {
        ordermst = ordmst;
        orderdtl = orddtl;
    }

    //public OrderMst ordermst;
    public String ordermst;
    public String orderdtl;
}

class ScanOutOrder{
    public ScanOutOrder(String tran,String idss){
        tranceno=tran;
        orderid=idss;
        boxused="";
    }

    public String tranceno;
    public String orderid;
    public String boxused;
}

class ScanOutOrderPartly{
    public ScanOutOrderPartly(String tran,String idss,int dtlID){
        traceno =tran;
        orderid=idss;
        boxused="";
        sendingdetail = "[{\"Id\":" + String.valueOf(dtlID) + ",\"Sendqty\":1}]";
    }

    public String traceno;
    public String orderid;
    public String sendingdetail;
    public String boxused;
}

class OrderID{
    public OrderID(String idss){
        orderid=idss;
    }
    public String orderid;
}

class IDS{
    public IDS(String idss){
        ids=idss;
    }
    public String ids;
}

class DisableOrder
{
    public DisableOrder(String mstid)
    {
        this.mstid = mstid;
        reason = "自动化测试作废";
    }

    public String mstid;
    public String reason;
}

class OrderSplit{
    public OrderSplit(String id,char split){
        arr_pordid=id;
        split_char=split;
    }
    public OrderSplit(String id){
        arr_pordid=id;
        split_char=',';
    }
    public String arr_pordid;
    public char split_char;
}

class mstids{
    public String mstids;

    mstids(String mstids) {
        this.mstids = mstids;
    }
}

class OrderidsSplit{
    public OrderidsSplit(String id,char split){
        arr_ids=id;
        split_char=split;
    }
    public OrderidsSplit(String id){
        arr_ids=id;
        split_char=',';
    }
    public String arr_ids;
    public char split_char;
}

class BatchTrans{
    public BatchTrans(String Str){
        otcStr=Str;
    }
    public String otcStr;
}

class BatchTransClass{
    public BatchTransClass(String shop){
        Sysid="3";
        Shopid=shop;
        Delaymins="0";
    }
    public String Sysid;
    public String Shopid;
    public String Delaymins;

}

class ShopStatus{
    public String ids;
    public int status;

    ShopStatus(String ids, int status) {
        this.ids = ids;
        this.status = status;
    }
}

//class MethodMessage
//{
//    public boolean iserror = false;
//    public String message = "";
//}
class MethodMessage
{
    public boolean iserror ;
    public String message ;
}

class SplitOrder{
    public SplitOrder(String order,String splitchars){
        orderid=order;
        splitStr=splitchars;
    }

    public SplitOrder(String order) {
        orderid = order;
        splitStr = "1";
    }
    public String splitStr;
    public String orderid;
}

class BatchTransOrder{
    BatchTransOrder(String Sysid, String Shopid, String Delaymins) {
        otcStr.Delaymins = Delaymins;
        otcStr.Shopid = Shopid;
        otcStr.Sysid = Sysid;
    }
    public otcStr otcStr;
}
 class otcStr
{
    public String Sysid;
    public String Shopid;
    public String Delaymins;
}

class WareHouse{
    WareHouse(String arr_pordid, String warehouse) {
        this.arr_pordid = arr_pordid;
        this.warehouse = warehouse;
    }

    public String arr_pordid;
    public String warehouse;
}

class Shelf{
    Shelf(String arr_pordid, String shelf) {
        this.arr_pordid = arr_pordid;
        this.shelf = shelf;
        this.split_char = ",";
    }

    public String arr_pordid;
    public String shelf;
    public String split_char;

}

class POrdDetaill{
    POrdDetaill(String distr) {
        this.distr = distr;
    }

    public String distr;
}

class Distr{
    public String Attrvalid;
    public String Defshelf;
    public String Id;
    public String Itemno;
    public String Price;
    public String Qty;
    public String Supplierno;
}

