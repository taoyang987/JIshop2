package Utility.Entity;

import java.util.List;

/**
 * Created by taoyang on 14/10/29.
 */
public class FlatSearchEorderCondition {

        public String orderid="";
        public String relid="";
        public String sysid="";
        public String shopid="";
        public String status="";
        public String traceno="";
        public String linkman="";
        public String shipcomp="";
        public String buyerremarks="";
        public String sellerremarks="";
        public String lock="";
        public String check="";
        public String send="";
        public String anaylsis="";
        public String lack="";
        public String orderFrom="";
        public String orderTo="";
        public String recordFrom="";
        public String recordTo="";
        public String buyer="";
        public String linknumber="";
        public String address="";
        public String warehouse="";
        public String sourceopt="";
        public String valid="";
        public String sumto="";
        public String sumfrom="";
        public String qtyfrom="";
        public String qtyto="";
        public String dealer="";
        public String print="";
        public String hd="";
        public String bill="";
        public String orderfrom="";
        public String distributor="";
        public String sendsite="";
        public String returnflag="";
//        public String orderconfirm="";
//        public String logisticconfirm="";
//        public String isdistribution="";
//        public String stockflag="";
        public String sendfrom="";
        public String sendto="";
        public String defshelf="";
        public String purchaseflag="";
        public String goodsendtype="";
        public String itemno="";
        public String barcode="";
        public String purchaseorderid="";
        public String distributeflag ="";
        public String outofstockflag="";
        public List<Integer> adminID;

    public String Ismerged = "";
    public String Issplited = "";


//
//        /// <summary>
//        /// 订单分销标志,1 是 0 正常
//        /// </summary>
//        public String Distributeflag
//        {
//            get { return _distributeflag; }
//            set { _distributeflag = value; }
//        }
//
//
//        /// <summary>
//        /// 相关采购单(明细)
//        /// </summary>
//        public String Purchaseorderid
//        {
//            get { return _purchaseorderid; }
//            set { _purchaseorderid = value; }
//        }
//
//        /// <summary>
//        /// 商品编号(明细)
//        /// </summary>
//        public String Itemno
//        {
//            get { return _itemno; }
//            set { _itemno = value; }
//        }
//
//        /// <summary>
//        /// 条形码(明细)
//        /// </summary>
//        public String Barcode
//        {
//            get { return _barcode; }
//            set { _barcode = value; }
//        }
//
//
//
//        /// <summary>
//        /// 发货方式 1自发 2 代发
//        /// </summary>
//        public String Goodsendtype
//        {
//            get { return _goodsendtype; }
//            set { _goodsendtype = value; }
//        }
//
//        /// <summary>
//        /// 出库标识
//        /// </summary>
//        public String Stockflag
//        {
//            get { return _stockflag; }
//            set { _stockflag = value; }
//        }
//
//        /// <summary>
//        /// 是否分销
//        /// </summary>
//        public String Isdistribution
//        {
//            get { return _isdistribution; }
//            set { _isdistribution = value; }
//        }
//
//        /// <summary>
//        /// 订单财务确认
//        /// </summary>
//        public String Orderconfirm
//        {
//            get { return _orderconfirm; }
//            set { _orderconfirm = value; }
//        }
//
//        /// <summary>
//        /// 物流财务确认
//        /// </summary>
//        public String Logisticconfirm
//        {
//            get { return _logisticconfirm; }
//            set { _logisticconfirm = value; }
//        }
//
//        /// <summary>
//        /// 退货状态 0未退 1 部分,2全部 
//        /// </summary>
//        public String Returnflag
//        {
//            get { return _returnflag; }
//            set { _returnflag = value; }
//        }
//        /// <summary>
//        /// 开具发票 0 否 1是
//        /// </summary>
//        public String Bill
//        {
//            get { return _bill; }
//            set { _bill = value; }
//        }
//
//        /// <summary>
//        /// 货到付款 1是货到付款 
//        /// </summary>
//        public String Hd
//        {
//            get { return _hd; }
//            set { _hd = value; }
//        }
//
//
//        /// <summary>
//        /// 打印
//        /// </summary>
//        public String Print
//        {
//            get { return _print; }
//            set { _print = value; }
//        }
//
//
//        /// <summary>
//        /// 代销
//        /// </summary>
//        public String Dealer
//        {
//            get { return _dealer; }
//            set { _dealer = value; }
//        }
//
//
//        /// <summary>
//        /// 网上订单号
//        /// </summary>
//        public String Orderid
//        {
//            get { return _orderid; }
//            set { _orderid = value; }
//        }
//        /// <summary>
//        /// 平台订单号(淘宝,京东)
//        /// </summary>
//        public String Relid
//        {
//            get { return _relid; }
//            set { _relid = value; }
//        }
//        /// <summary>
//        /// 平台编号(淘宝:3 京东:5...)
//        /// </summary>
//        public String Sysid
//        {
//            get { return _sysid; }
//            set { _sysid = value; }
//        }
//        /// <summary>
//        /// 店铺编号
//        /// </summary>
//        public String Shopid
//        {
//            get { return _shopid; }
//            set { _shopid = value; }
//        }
//        /// <summary>
//        /// 状态
//        /// </summary>
//        public String Status
//        {
//            get { return _status; }
//            set { _status = value; }
//        }
//        /// <summary>
//        /// 快递单号
//        /// </summary>
//        public String Traceno
//        {
//            get { return _traceno; }
//            set { _traceno = value; }
//        }
//        /// <summary>
//        /// 联系人
//        /// </summary>
//        public String Linkman
//        {
//            get { return _linkman; }
//            set { _linkman = value; }
//        }
//        /// <summary>
//        /// 快递公司
//        /// </summary>
//        public String Shipcomp
//        {
//            get { return _shipcomp; }
//            set { _shipcomp = value; }
//        }
//        /// <summary>
//        /// 买家备注
//        /// </summary>
//        public String Buyerremarks
//        {
//            get { return _buyerremarks; }
//            set { _buyerremarks = value; }
//        }
//        /// <summary>
//        /// 卖家备注
//        /// </summary>
//        public String Sellerremarks
//        {
//            get { return _sellerremarks; }
//            set { _sellerremarks = value; }
//        }
//        /// <summary>
//        /// 锁定(挂起)
//        /// </summary>
//        public String Lock
//        {
//            get { return _lock; }
//            set { _lock = value; }
//        }
//        /// <summary>
//        /// 审核
//        /// </summary>
//        public String Check
//        {
//            get { return _check; }
//            set { _check = value; }
//        }
//        /// <summary>
//        /// 发货
//        /// </summary>
//        public String Send
//        {
//            get { return _send; }
//            set { _send = value; }
//        }
//        /// <summary>
//        /// 解析
//        /// </summary>
//        public String Anaylsis
//        {
//            get { return _anaylsis; }
//            set { _anaylsis = value; }
//        }
//        /// <summary>
//        /// 缺货
//        /// </summary>
//        public String Lack
//        {
//            get { return _lack; }
//            set { _lack = value; }
//        }
//        /// <summary>
//        /// 下单时间从
//        /// </summary>
//        public String OrderFrom
//        {
//            get { return _orderFrom; }
//            set { _orderFrom = value; }
//        }
//        /// <summary>
//        /// 下单时间到
//        /// </summary>
//        public String OrderTo
//        {
//            get { return _orderTo; }
//            set { _orderTo = value; }
//        }
//        /// <summary>
//        /// 下载时间起
//        /// </summary>
//        public String RecordFrom
//        {
//            get { return _recordFrom; }
//            set { _recordFrom = value; }
//        }
//        /// <summary>
//        /// 下载时间到
//        /// </summary>
//        public String RecordTo
//        {
//            get { return _recordTo; }
//            set { _recordTo = value; }
//        }
//
//
//        /// <summary>
//        /// 买家信息(包括昵称和ID)
//        /// </summary>
//        public String Buyer
//        {
//            get { return _buyer; }
//            set { _buyer = value; }
//        }
//
//        /// <summary>
//        /// 联系号码(包括手机和电话)
//        /// </summary>
//        public String Linknumber
//        {
//            get { return _linknumber; }
//            set { _linknumber = value; }
//        }
//
//        /// <summary>
//        /// 收货地址
//        /// </summary>
//        public String Address
//        {
//            get { return _address; }
//            set { _address = value; }
//        }
//
//        /// <summary>
//        /// 仓库
//        /// </summary>
//        public String Warehouse
//        {
//            get { return _warehouse; }
//            set { _warehouse = value; }
//        }
//
//
//        /// <summary>
//        /// 来源操作 1拆分 2合并 3转单 4拆分和合并
//        /// </summary>
//        public String Sourceopt
//        {
//            get { return _sourceopt; }
//            set { _sourceopt = value; }
//        }
//        /// <summary>
//        /// 1不可操作
//        /// </summary>
//        public String Valid
//        {
//            get { return _valid; }
//            set { _valid = value; }
//        }
//        /// <summary>
//        /// 金额从
//        /// </summary>
//        public String SumTo
//        {
//            get { return _sumto; }
//            set { _sumto = value; }
//        }
//
//        /// <summary>
//        /// 金额到
//        /// </summary>
//        public String SumFrom
//        {
//            get { return _sumfrom; }
//            set { _sumfrom = value; }
//        }
//
//        /// <summary>
//        /// 数量从
//        /// </summary>
//        public String QtyFrom
//        {
//            get { return _qtyfrom; }
//            set { _qtyfrom = value; }
//        }
//
//        /// <summary>
//        /// 数量到
//        /// </summary>
//        public String QtyTo
//        {
//            get { return _qtyto; }
//            set { _qtyto = value; }
//        }
//
//        /// <summary>
//        /// 订单来源
//        /// </summary>
//        public String Orderfrom
//        {
//            get { return _orderfrom; }
//            set { _orderfrom = value; }
//        }
//        /// <summary>
//        /// 分销商
//        /// </summary>
//        public String Distributor
//        {
//            get { return _distributor; }
//            set { _distributor = value; }
//        }
//        /// <summary>
//        /// 配送站点
//        /// </summary>
//        public String Sendsite
//        {
//            get { return _sendsite; }
//            set { _sendsite = value; }
//        }
//
//
//
//        /// <summary>
//        /// 发货时间从
//        /// </summary>
//        public String SendFrom
//        {
//            get { return _sendfrom; }
//            set { _sendfrom = value; }
//        }
//
//        /// <summary>
//        /// 发货时间到
//        /// </summary>
//        public String SendTo
//        {
//            get { return _sendto; }
//            set { _sendto = value; }
//        }
//
//
//        /// <summary>
//        /// 所在货架
//        /// </summary>
//        public String Defshelf
//        {
//            get { return _defshelf; }
//            set { _defshelf = value; }
//        }
//
//        /// <summary>
//        /// 生成采购单 2全部生成,1部分生成,0 生成失败 3未生成
//        /// </summary>
//        public String Purchaseflag
//        {
//            get { return _purchaseflag; }
//            set { _purchaseflag = value; }
//        }
//
//        /// <summary>
//        /// 采购缺货标志或代销缺货标志，1：缺货；其他：不缺货
//        /// </summary>
//        public String Outofstockflag
//        {
//            get { return _outofstockflag; }
//            set { _outofstockflag = value; }
//        }
//
//        /// <summary>
//        /// 一级分类长度
//        /// </summary>
//        public int Firsteditkey
//        {
//            get { return _firsteditkey; }
//            set { _firsteditkey = value; }
//        }
//
//        /// <summary>
//        /// 管理员编号及下属管理员编号
//        /// </summary>
//        public List<int> AdminID
//        {
//            get { return _adminID; }
//            set { _adminID = value; }
//        }
}
