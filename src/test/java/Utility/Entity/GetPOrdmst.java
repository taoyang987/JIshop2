package Utility.Entity;

import com.alibaba.fastjson.JSON;

/**
 * Created by taoyang on 14/10/29.
 */
public class GetPOrdmst {
    public GetPOrdmst() {
        FlatSearchEorderCondition obj = new FlatSearchEorderCondition();
        obj.check = "0";
        obj.dealer = "0";
        obj.valid = "0";
        condStr = JSON.toJSONString(obj);
    }

    public String condStr;
    public int page=1;
    public int  rows=18;
}
