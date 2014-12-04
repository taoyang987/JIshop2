package Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Content {
    /// <summary>
/// 网上订单号
/// </summary>
    public ArrayList<String> ListOrderId = new ArrayList<>();
    /// <summary>
/// 快递单号
/// </summary>
    public ArrayList<String> ListTransNum = new ArrayList<>();

    public ArrayList<String> Listrelid = new ArrayList<>();
    public ArrayList<String> ListMergeID = new ArrayList<>();

    /// <summary>
/// 当前订单号在ListOrderId中的Index
/// </summary>
    public int CurrentIndexOfOrderId = -1;
    public int CurrentIndexOfReId=-1;

    /// <summary>
/// OrderId，TransNum
/// </summary>
    public HashMap<String, String> DicTransNum = new HashMap<>();


    private String orderId;
/// <summary>
/// 当前网上订单号
/// 每次修改的时候会自动添加到ListOrderId
/// </summary>


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        if (!ListOrderId.contains(orderId)) {
            ListOrderId.add(orderId);
            CurrentIndexOfOrderId += 1;
        }
        this.orderId = orderId;
    }


    public String getReliid() {
        return Reliid;
    }

    public void setReliid(String reliid) {
        if (!Listrelid.contains(reliid)) {
            Listrelid.add(reliid);
            CurrentIndexOfReId+=1;
        }
        Reliid = reliid;
    }

    /// <summary>
/// 当前平台订单号
/// </summary>
    private String Reliid;
    /// <summary>
/// 当前接口返回结果
/// </summary>
    public String Result;

    /*
    清空原有数据
     */
    public void DeleteData() {
        this.Result=null;
        this.CurrentIndexOfOrderId=-1;
        this.CurrentIndexOfReId=-1;
        ListOrderId = new ArrayList<>();
        ListTransNum = new ArrayList<>();
        Listrelid = new ArrayList<>();
        DicTransNum = new HashMap<>();
        orderId = "";
        Reliid = "";
        SplitOrderID = new ArrayList<>();
    }

    public ArrayList<String>  getSplitOrderID() {
        return SplitOrderID;
    }

    public void setSplitOrderID(String SplitOrderID) {
        String[] arr=SplitOrderID.split(";");
        if(Util.BoolRegEX(arr[0],"^EO\\d{14}$")) {
            Collections.addAll(this.SplitOrderID, arr);
//            for (String id : arr) {
//                this.SplitOrderID.add(id);
//            }
//            this.SplitOrderID.addAll(arr);
        }
    }

    ArrayList<String> SplitOrderID=new ArrayList<>();

    public String getMergeOrderID() {
        return mergeOrderID;
    }

    public void setMergeOrderID(String mergeOrderID) {
        this.mergeOrderID = mergeOrderID;
        if (!ListMergeID.contains(mergeOrderID)) {
            ListMergeID.add(mergeOrderID);
        }
    }

    String mergeOrderID;
}
