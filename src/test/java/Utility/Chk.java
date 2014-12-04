package Utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by taoyang on 14-10-9.
 */
public class Chk {

    public static void ChkContainText(String text) throws InterruptedException {
        assertTrue(JS.WaitLoop(":contains(" + text + ")"));
    }

    public static void ChkText(String selector,String text) throws InterruptedException {
//        assertTrue(JS.GetText(selector).equals(text));
        assertEquals(JS.GetText(selector),text);
    }
    public static void ChkContainText(String selector,String text) throws InterruptedException {
//        assertTrue(JS.GetText(selector).equals(text));
        assertTrue(JS.GetText(selector).contains(text));
    }

}
