package Utility;

/**
 * Created by taoyang on 14-10-15.
 */
public class ParseStep {
    /*
    不记录订单操作的返回值和结果
     */
    public static String ParseOrderStep(String step,String relid,String orderid)
    {
        switch (step) {
            case "取消采购看板":
                DoOrder.SetPurchaseable(orderid);
                break;
            case "加入采购看板":
                DoOrder.CreatePurchaseOrder(orderid);
                break;
            case "批量支付":
            case "批量支付订单":
                DoOrder.PayAndTransOrder(relid);
                break;
            case "支付":
                DoOrder.PayAndTransOrder(relid);
                break;
            case "删除订单":
                DoOrder.DelOrder(relid);
                break;
            case "审核":
                DoOrder.AppPOrder(orderid);
                break;
            case "弃审":
                DoOrder.UnDoCheck(orderid);
                break;
            case "锁定":
                DoOrder.LockPOrder(orderid);
                break;
            case "解锁":
                DoOrder.UnLockPOrder(orderid);
                break;
            case "绑定快递单":
            case "绑定快递单号":
                DoOrder.BindExpressNo(orderid+","+Util.GetExpressNo());
                break;
            case "打印快递单":
                DoOrder.CreateSingleSenderPrintData(orderid);
                break;
            case "打印订单":
            case "打印发货单":
                DoOrder.CreateSinglePrintData(orderid);
                break;
            case "扫描出库":
                DoOrder.ScanOutWh(orderid);
                break;
            case "部分扫描出库":
                DoOrder.PartlyScanOutWh(orderid);
                break;
            case "无效":
                DoOrder.UnValidateOrder(orderid);
                break;
            //case "拆分":
            //    orderid= DoOrder.SplitOrder(orderid);
            //    break;
            //case "合并":
            //    orderid= DoOrder.MergeOrder(orderid);
            //    break;
            case "转单":
                ContentFactory.getContent().setOrderId(DoOrder.TransOrder(relid));
                break;
            case "作废订单":
            case "作废":
                DoOrder.DisableOrder(relid);
                break;
            case "撤销作废订单":
                DoOrder.EnableOrder(relid);
                break;
            case "":
                break;
            default:
                System.out.println("不包含该步骤:" + step);
                break;
        }
        return orderid;
    }
}
