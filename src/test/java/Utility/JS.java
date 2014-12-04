package Utility;

import org.openqa.selenium.JavascriptExecutor;

/**
 * Created by taoyang on 14-10-9.
 */
public class JS {
    static String iframeName="$('#ifrContent').contents()";
    static String frame="ifrContent";

    public static Object ExecuteScript(String scripts) {
        Object result=new Object();
        try {
            result= ((JavascriptExecutor) Util.dr).executeScript(scripts);
        } catch (Exception e) {
            System.out.println("js执行错误:" + scripts);
            e.printStackTrace();
        }
        return result;
    }

    public static void Click(String Selector) throws InterruptedException {
        if (WaitLoop(Selector)) {
            JavaSciptsClick(Selector);
        }else{
            System.out.println("元素未找到:"+Selector);
        }
    }

    public static void ClickEach(String Selector) throws InterruptedException {
        if (WaitLoop(Selector)) {
            JavaSciptsClickEach(Selector);
        }else{
            System.out.println("元素未找到:"+Selector);
        }
    }

    public static void Click(String iframe, String Selector) throws InterruptedException {
        if (WaitLoop(iframe,Selector)) {
            JavaSciptsClick(iframe,Selector);
        }else{
            System.out.println("元素未找到:"+Selector);
        }
    }

    public static void SetValue(String selector, String value) throws InterruptedException {
        if (WaitLoop(selector)) {
            JavaScriptsSetVaule(selector,value);
        }else {
            System.out.println("元素未找到:"+selector);
        }
    }

    public static void SetValue(String frame, String selector, String value) throws InterruptedException {
        if (WaitLoop(frame,selector)) {
            JavaScriptsSetVaule(frame,selector,value);
        }else {
            System.out.println("元素未找到:"+selector);
        }
    }

    private static void JavaSciptsClick(String Selector) {
        String scripts = iframeName+".find(\"" + Selector + "\")[0].click();";
        try {
            ExecuteScript(scripts);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("js执行错误:" + scripts);
        }
    }
    private static void JavaSciptsClickEach(String Selector) {
        String scripts = iframeName+".find(\"" + Selector + "\").each(function(){$(this)[0].click()});";
        try {
            ExecuteScript(scripts);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("js执行错误:" + scripts);
        }
    }

    private static void JavaSciptsClick(String frame,String Selector) {
        String scripts = frame+".find(\"" + Selector + "\").click();";
        try {
            ExecuteScript(scripts);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("js执行错误:" + scripts);
        }
    }

    public static boolean WaitLoop(String Selector) throws InterruptedException {
        boolean result=false;
        for (int i = 0; i < Util.waitTime; i++) {
            if (jsWait(Selector)){
                result=true;
                break;
            }
            Thread.sleep(100);
        }
        return result;
    }

    /**
     *
     * @param Selector
     * @param time 最长等待时间
     * @return
     * @throws InterruptedException
     */
    public static boolean WaitLoop(String Selector, int time) throws InterruptedException {
        boolean result=false;
        for (int i = 0; i < time; i++) {
            if (jsWait(Selector)){
                result=true;
                break;
            }
            Thread.sleep(1000);
        }
        return result;
    }

    public static boolean WaitLoop(String frame,String Selector) throws InterruptedException {
        boolean result=false;
        for (int i = 0; i < Util.waitTime; i++) {
            if (jsWait(frame,Selector)){
                result=true;
                break;
            }
            Thread.sleep(100);
        }
        return result;
    }

    public static boolean jsWait(String Selector) {
        boolean result=false;
        String scripts = "return "+iframeName+".find(\"" + Selector + "\").size()>=1;";
        try {
            result=(Boolean)ExecuteScript(scripts);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("js执行错误:" + scripts);
        }
        return result;
    }

    public static boolean jsWait(String frame,String Selector) {
        boolean result=false;
        String scripts = "return "+frame+".find(\"" + Selector + "\").size()>=1;";
        try {
            result=(Boolean)ExecuteScript(scripts);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("js执行错误:" + scripts);
        }
        return result;
    }

    public static void JavaScriptsSetVaule(String frame,String selector, String value) {
        String scripts = frame+".find(\"" + selector + "\").val('" + value + "');";
        try {
            ExecuteScript(scripts);
        } catch (Exception e) {
            System.out.println("js执行错误:" + scripts);
            e.printStackTrace();
        }
    }

    public static void JavaScriptsSetVaule(String selector, String value) {
        String scripts = iframeName+".find(\"" + selector + "\").val('" + value + "');";
        try {
            ExecuteScript(scripts);
        } catch (Exception e) {
            System.out.println("js执行错误:" + scripts);
            e.printStackTrace();
        }
    }

    public static String GetValue(String selector) throws InterruptedException {
        return GetValue(iframeName, selector);
    }

    public static String GetValue(String frame, String selector) throws InterruptedException {
        String value=null;
        if (WaitLoop(frame,selector)) {
            String scripts = "return "+frame+".find(\"" + selector + "\").val();";
            try {
                value=(String)ExecuteScript(scripts);
            } catch (Exception e) {
                System.out.println("js执行错误:" + scripts);
                e.printStackTrace();
            }
        } else {
            System.out.println("元素未找到:"+selector);
        }
        return value;
    }
    public static String GetText(String selector) throws InterruptedException {
        return GetText(iframeName, selector);
    }

    public static String GetText(String frame, String selector) throws InterruptedException {
        String value=null;
        if (WaitLoop(frame,selector)) {
            String scripts = "return "+frame+".find(\"" + selector + "\").text();";
            try {
                value=(String)ExecuteScript(scripts);
            } catch (Exception e) {
                System.out.println("js执行错误:" + scripts);
                e.printStackTrace();
            }
        } else {
            System.out.println("元素未找到:"+selector);
        }
        return value;
    }

    public static  void MenuClk(String Text) throws InterruptedException {
        Click("$('#sidebar').contents()", "a:contains(" + Text + ")");
    }

    public static void Check(String frame, String selector) throws InterruptedException {
        if (WaitLoop(frame,selector)) {
            String scripts = frame+".find(\"" + selector + "\").attr('checked','checked');";
            try {
                ExecuteScript(scripts);
            } catch (Exception e) {
                System.out.println("js执行错误:" + scripts);
                e.printStackTrace();
            }
        } else {
            System.out.println("元素未找到:"+selector);
        }
    }

    public static void Check(String selector) throws InterruptedException {
        Check(iframeName,selector);
    }

    public static void ClickByID(String id) {
        ExecuteScript("document.getElementById(\""+frame+"\").contentWindow.document.getElementById(\"" + id + "\").click()");
    }


    /**
     * js点击下拉框触发
     * @param IDSelector #ID
     * @param option opt文本(使用contain,文本不能包含)
     * @throws InterruptedException
     */
    public static void optSelect(String IDSelector, String option) throws InterruptedException {
        String selector = IDSelector + " + span span span.combo-arrow";
        JS.Click(selector);
        Thread.sleep(500);
        String optselector = "div.combobox-item:visible:contains(" + option + ")";
        JS.Click(optselector);
    }

    /**
     * js点击下拉框触发
     *
     * @param frame
     * @param IDSelector #ID
     * @param option opt文本(使用contain,文本不能包含)
     * @throws InterruptedException
     */
    public static void optSelect(String frame, String IDSelector, String option) throws InterruptedException {
        String selector =  IDSelector + " + span span span.combo-arrow";
        JS.Click(frame,selector);
        Thread.sleep(500);
        String optselector = "div.combobox-item:visible:contains(" + option + ")";
        JS.Click(frame,optselector);
    }

    public static void DateChoose(String IDSelector, int date) throws InterruptedException {
        String selector = IDSelector + " + span span span.combo-arrow";
        JS.Click(selector);
        String day = Util.GetDate(date, "YYYY,MM,d");//使用d而不是dd,要求1,而不是01
        String daySelector = "td.calendar-day[abbr='" + day + "']";
        JS.Click(daySelector);
        Thread.sleep(500);
        String btn确定 = "a.datebox-ok";
        JS.Click(btn确定);
    }

    /**
     *
     * @param IDSelector
     * @param date
     * @param index确定 日期控件在页面上的索引顺序
     * @throws InterruptedException
     */
    public static void DateChoose(String IDSelector, int date,int index确定) throws InterruptedException {
        String selector = IDSelector + " + span span span.combo-arrow";
        JS.Click(selector);
        String day = Util.GetDate(date, "YYYY,MM,d");//使用d而不是dd,要求1,而不是01
        String daySelector = "td.calendar-day[abbr='" + day + "']";
//        JS.Click(daySelector);
        ExecuteScript(iframeName+".find(\"" + daySelector + "\")["+index确定+"].click();");
        Thread.sleep(500);
        String btn确定 = "a.datebox-ok";
        String scripts = iframeName+".find(\"" + btn确定 + "\")["+index确定+"].click();";
        ExecuteScript(scripts);
    }

}
