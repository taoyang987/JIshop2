package Utility.Entity;

/**
 * Created by taoyang on 14/10/31.
 */

/**
 * 发货方式
 */
public class GoodSendType {
    public String arr_pordid;

    public GoodSendType(String arr_pordid, String goodsendtype) {
        this.arr_pordid = arr_pordid;
        this.goodsendtype = goodsendtype;
    }

    public String goodsendtype;
    public String split_char=",";

}
