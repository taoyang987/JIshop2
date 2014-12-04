package Utility;

/**
 * Created by taoyang on 14-10-17.
 */
public class UIUtil {
    public static void SyncList() throws InterruptedException {
        Thread.sleep(1000);
        JS.WaitLoop("div.pagination-info:!contains(共1记录)");
    }

    /**
     * 利用"请稍等"
     * @throws InterruptedException
     */
    public static void SyncList2() throws InterruptedException {
        String selector = ":contains(请稍等)";
        JS.WaitLoop(selector);
        for (int i = 0; i < Util.waitTime; i++) {
            Thread.sleep(1000);
            if(!JS.jsWait(selector)){
                break;
            }
        }
    }

    public static void SyncQueryList() throws InterruptedException {
        JS.WaitLoop("div.pagination-info:!contains(显示1到1,共1记录)");
    }
}
