package Utility;

import UI.Steps.UICommonStep;
import com.alibaba.fastjson.JSON;
import cucumber.api.DataTable;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taoyang on 14-9-25.
 */
public class Util {
    static WebDriver dr;
    static WebDriverWait wait;
    static int waitTime = 30;
    public static boolean flagUI = false;

    public static void WaitElementEnabled(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void WaitElementEnabled(WebElement element) {
        wait = new WebDriverWait(GetDr(), waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void WaitElementEnabled(WebDriver driver, By by) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement GetVisibleElement(WebDriver driver, By by) {
        List<WebElement> webElementList = driver.findElements(by);
        for (WebElement element : webElementList) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        try {
            throw new Exception("元素找不到");
        } catch (Exception e) {
            System.out.println("元素找不到");
        }
        return null;
    }

    public static WebDriver GetDr() {
        if (dr == null || ((ChromeDriver) dr).getSessionId() == null) {
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-web-security");
            dr = new ChromeDriver(options);
            dr.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
            flagUI = true;
        }
        return dr;
    }

    public static Properties GetProperties() {
        File pFile = new File("src/test/resources/Env.xml");
        try {
            FileInputStream inputStream = new FileInputStream(pFile);
            Properties p = new Properties();
            p.loadFromXML(inputStream);
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean switchToWindow(String windowTitle) {
        WebDriver driver = GetDr();
        boolean flag = false;
        try {
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                driver.switchTo().window(s);
                if (driver.getTitle().contains(windowTitle)) {
                    flag = true;
                    System.out.println("Switch to window: "
                            + windowTitle + " successfully!");
                    break;
                }
            }
        } catch (NoSuchWindowException e) {
            System.out.println("Window: " + windowTitle + " cound not found!");
            flag = false;
        }
        return flag;
    }

    public static String PostJsonData(String path, String content) {

        String result = null;
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        RequestConfig config = RequestConfig.DEFAULT;
//        RequestConfig config=RequestConfig.custom().setCookieSpec((CookieSpecs.STANDARD)).build();
        CookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
//        context.setAttribute(ClientContext.COOKIE_STORE,cookieStore);
        context.setCookieStore(cookieStore);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).setDefaultCookieStore(cookieStore).build();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpUriRequest request= RequestBuilder.get().setUri(path).setConfig(config).build();
        HttpPost httppost = new HttpPost("http://" + Util.GetProperties().getProperty("domain") + "/" + Util.GetProperties().getProperty("project") + path);
        httppost.setConfig(config);
        /*把Soap请求数据添加到PostMethod*/
        //byte[] b = soapRequestData.getBytes("utf-8");
        //InputStream is = new ByteArrayInputStream(b,0,b.length);
        try {
            //进行登录,进行初始化,记录cookie
            HttpPost postLogin = new HttpPost("http://" + Util.GetProperties().getProperty("domain") + "/" + Util.GetProperties().getProperty("project") + "/Admin/Login.aspx");
            postLogin.setConfig(config);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("txtUsername", Util.GetProperties().getProperty("username")));
            params.add(new BasicNameValuePair("btnLogin", "登录"));
            params.add(new BasicNameValuePair("txtPassword", Util.GetProperties().getProperty("password")));
            postLogin.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            HttpResponse response1 = httpClient.execute(postLogin,context);
//            System.out.println(context.getCookieStore().getCookies());
            HttpEntity entity1 = response1.getEntity();
            EntityUtils.consume(entity1);
//            String result1 = EntityUtils.toString(entity1);
//            httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

//            //触发登陆初始化
//            HttpGet get = new HttpGet("http://" + Util.GetProperties().getProperty("domain") + "/" + Util.GetProperties().getProperty("project") + "/Admin/Profile/MyWorktable.aspx");
//            get.setConfig(config);
//            HttpResponse response2 = httpClient.execute(get,context);
////            System.out.println(context.getCookieStore().getCookies());
//            HttpEntity entity2 = response2.getEntity();
//            EntityUtils.consume(entity2);

//            HttpEntity re = new StringEntity(content, HTTP.UTF_8);
            HttpEntity re = new StringEntity(content, Consts.UTF_8);
            httppost.setHeader("Content-Type", "application/json; charset=utf-8");
            //httppost.setHeader("Content-Length", String.valueOf(soapRequestData.length()));
            httppost.setEntity(re);
            HttpResponse response = httpClient.execute(httppost,context);
//            System.out.println(context.getCookieStore().getCookies());
//            EntityUtils.toString(httppost.getEntity());
            EntityUtils.consume(httppost.getEntity());
            response.getStatusLine();
            result = EntityUtils.toString(response.getEntity());
//            result=StringEscapeUtils.unescapeHtml4(result);
//            result=URLDecoder.decode(result, "UTF-8");
//            byte[] uft8 = result.getBytes("UTF-8");
//            result = new String(uft8, "UTF-8");
//            result = unicodeToUTF8(result);
            Object objct = JSON.parse(result);//去除类似\u003c这样的转义编码
            result = JSON.toJSONString(objct);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            httpClient.getConnectionManager().shutdown();
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            CloseableHttpClient httpClient2 = HttpClientBuilder.create().build();
//            httpClient.
        }
        return result;
    }
    public static String PostJsonDataCookie(String path, String content) throws Throwable {

        String result = null;
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        RequestConfig config = RequestConfig.DEFAULT;
//        RequestConfig config=RequestConfig.custom().setCookieSpec((CookieSpecs.STANDARD)).build();
//        CookieStore cookieStore = new BasicCookieStore();
        CookieStore cookieStore = GetCookieStore();
        HttpClientContext context = HttpClientContext.create();
//        context.setAttribute(ClientContext.COOKIE_STORE,cookieStore);
        context.setCookieStore(cookieStore);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).setDefaultCookieStore(cookieStore).build();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpUriRequest request= RequestBuilder.get().setUri(path).setConfig(config).build();
        HttpPost httppost = new HttpPost("http://" + Util.GetProperties().getProperty("domain") + "/" + Util.GetProperties().getProperty("project") + path);
        httppost.setConfig(config);
        /*把Soap请求数据添加到PostMethod*/
        //byte[] b = soapRequestData.getBytes("utf-8");
        //InputStream is = new ByteArrayInputStream(b,0,b.length);
        try {
            //进行登录,进行初始化,记录cookie
//            HttpPost postLogin = new HttpPost("http://" + Util.GetProperties().getProperty("domain") + "/" + Util.GetProperties().getProperty("project") + "/Admin/Login.aspx");
//            postLogin.setConfig(config);
//            List<NameValuePair> params = new ArrayList<>();
//            params.add(new BasicNameValuePair("txtUsername", Util.GetProperties().getProperty("username")));
//            params.add(new BasicNameValuePair("btnLogin", "登录"));
//            params.add(new BasicNameValuePair("txtPassword", Util.GetProperties().getProperty("password")));
//            postLogin.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
//            HttpResponse response1 = httpClient.execute(postLogin,context);
////            System.out.println(context.getCookieStore().getCookies());
//            HttpEntity entity1 = response1.getEntity();
//            EntityUtils.consume(entity1);
////            String result1 = EntityUtils.toString(entity1);
////            httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
//
//            //触发登陆初始化
//            HttpGet get = new HttpGet("http://" + Util.GetProperties().getProperty("domain") + "/" + Util.GetProperties().getProperty("project") + "/Admin/Profile/MyWorktable.aspx");
//            get.setConfig(config);
//            HttpResponse response2 = httpClient.execute(get,context);
////            System.out.println(context.getCookieStore().getCookies());
//            HttpEntity entity2 = response2.getEntity();
//            EntityUtils.consume(entity2);

//            HttpEntity re = new StringEntity(content, HTTP.UTF_8);
            HttpEntity re = new StringEntity(content, Consts.UTF_8);
            httppost.setHeader("Content-Type", "application/json; charset=utf-8");
            //httppost.setHeader("Content-Length", String.valueOf(soapRequestData.length()));
            httppost.setEntity(re);
            HttpResponse response = httpClient.execute(httppost,context);
            System.out.println(context.getCookieStore().getCookies());
//            EntityUtils.toString(httppost.getEntity());
            EntityUtils.consume(httppost.getEntity());
            response.getStatusLine();
            result = EntityUtils.toString(response.getEntity());
//            result=StringEscapeUtils.unescapeHtml4(result);
//            result=URLDecoder.decode(result, "UTF-8");
//            byte[] uft8 = result.getBytes("UTF-8");
//            result = new String(uft8, "UTF-8");
//            result = unicodeToUTF8(result);
            Object objct = JSON.parse(result);//去除类似\u003c这样的转义编码
            result = JSON.toJSONString(objct);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            httpClient.getConnectionManager().shutdown();
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            CloseableHttpClient httpClient2 = HttpClientBuilder.create().build();
//            httpClient.
        }
        return result;
    }


    public static CookieStore GetCookieStore() throws Throwable {
        new UICommonStep().默认账号登录();
//        Set<Cookie> abc = Util.GetDr().manage().getCookies();
        Cookie value = Util.GetDr().manage().getCookieNamed("ISHOPAUTH");
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie sdtCookie = new BasicClientCookie(value.getName(), value.getValue());
        sdtCookie.setVersion(1);
        sdtCookie.setDomain(value.getDomain());
        sdtCookie.setPath(value.getPath());
        sdtCookie.setSecure(value.isSecure());
        sdtCookie.setExpiryDate(value.getExpiry());
        sdtCookie.setAttribute(ClientCookie.VERSION_ATTR, "1");
        sdtCookie.setAttribute(ClientCookie.DOMAIN_ATTR, value.getDomain());
        cookieStore.addCookie(sdtCookie);
        return cookieStore;
    }
//    public static String unicodeToUTF8(String unicodeStr) throws UnsupportedEncodingException {
//        // Convert from Unicode to UTF-8
//        byte[] utf8 = unicodeStr.getBytes("UTF-8");
//        String UTF8Str = "";
//        UTF8Str = new String(utf8, "UTF-8");
//        return UTF8Str;
//    }
    //    public static boolean switchToWindow(String windowTitle){
    //        WebDriver driver=GetDr();
    //        boolean flag = false;
    //        try {
    //            String currentHandle = driver.getWindowHandle();
    //            Set<String> handles = driver.getWindowHandles();
    //            for (String s : handles) {
    //                if (s.equals(currentHandle))
    //                    continue;
    //                else {
    //                    driver.switchTo().window(s);
    //                    if (driver.getTitle().contains(windowTitle)) {
    //                        flag = true;
    //                        System.out.println("Switch to window: "
    //                                + windowTitle + " successfully!");
    //                        break;
    //                    } else
    //                        continue;
    //                }
    //            }
    //        } catch (NoSuchWindowException e) {
    //            System.out.println("Window: " + windowTitle+ " cound not found!");
    //            flag = false;
    //        }
    //        return flag;
    //    }

    /**
     * 过滤html标签
     *
     * @param value
     * @return
     */
    public static String DeleteHtmlTag(String value) {
        Pattern pattern = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.replaceAll("");
    }

    public static String GetMessage(String json) {
        return GetStringByRegexGroup(json, "message\\\\\":\\\\\"(.*)\\\\\"\\}\"");
    }

    public static String GetStringByRegexGroup(String str, String regex) {
        String result = null;
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(value);
//        if (matcher.find()) {
//            result = matcher.group(1).toString();
//        }else {
//            System.out.println("no matches!!");
//        }
//        return result;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
//        while (m.find()) {
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//        }
        if (m.find()) {
            result = m.group(1);
        }
        return result;
    }

    /**
     * 生成快递单号
     *
     * @return
     */
    public static String GetExpressNo() {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
        return sdf.format(time);
    }

    public static String TrimQuo(String value) {
        String result = null;
        if (value.startsWith("\"")) {
            value = value.substring(1);
        }
        if (value.endsWith("\"")) {
            result = value.substring(0, value.length() - 1);
        }
        return result;
    }

    public static Boolean BoolRegEX(String result, String regex) {
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(result).find();
    }

    /**
     * 把
     *
     * @param tb
     * @return
     */
    public static List<HashMap<String, String>> GetHasList(DataTable tb) {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 1; i < tb.raw().size(); i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            for (int j = 0; j < tb.raw().get(i).size(); j++) {
                hashMap.put(tb.raw().get(0).get(j), tb.raw().get(i).get(j));
            }
            list.add(hashMap);
        }
        return list;
    }

    public static String saveRelid() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmssSS");
        return sdf.format(date);
    }

    public static String GetEmptyStrIfNull(String name) {
        return name == null ? "" : name;
    }

    public static String GetStrIfNull(String s) {
        if (s.toUpperCase().equals("NULL")) {
            return null;
        } else {
            return s;
        }
    }

    public static boolean GetStrBool(String s) {
        if (s.toUpperCase().equals("TRUE")) {
            return true;
        } else if (s.toUpperCase().equals("FALSE")) {
            return false;
        } else {
            System.out.println("值有问题,无法校验成为 boolen:+" + s);
            return false;
        }
    }

    public static void assertContain(String message, String expect, String actual) {
        Assert.assertTrue(message + "{" + actual + "}应包含{" + expect + "}", actual.contains(expect));
    }

    public static void assertContain(String expect, String actual) {
        assertContain("", expect, actual);
    }

    public static void assertNotContain(String message, String expect, String actual) {
        Assert.assertTrue(message + "{" + actual + "}不应包含{" + expect + "}", !actual.contains(expect));
    }

    public static void assertNotContain(String expect, String actual) {
        assertNotContain("", expect, actual);
    }

    /**
     * 获取指定日期偏差和格式的时间
     *
     * @param day    指定日期偏差
     * @param format
     * @return
     */
    public static String GetDate(int day, String format) {
        if (format.equals("")) {
            format = "yyyy-M-dd HH:mm:ss";
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat(format);//
        gc.add(Calendar.DATE, day);
        return sdf.format(gc.getTime());
    }

    /**
     * 获取指定格式的时间
     *
     * @param format
     * @return
     */
    public static String GetDate(String format) {
        return GetDate(0, format);
    }

    /**
     * 把"True""False"转换成1,0
     *
     * @param result
     * @return
     */
    public static int GetIntByBoolStr(String result) {
        switch (result.toUpperCase()) {
            case "TRUE":
                return 1;
            case "FALSE":
                return 0;
            default:
                Assert.fail("没有该状态" + result);
        }
        return 0;
    }

    public static boolean GetBoolStr(String result) {
        switch (result.toUpperCase()) {
            case "TRUE":
                return true;
            case "FALSE":
                return false;
            default:
                Assert.fail("没有该状态" + result);
                return false;
        }
    }

    public static boolean GetBoolInt(int i) {
        switch (i) {
            case 1:
                return true;
            case 0:
                return false;
            default:
                Assert.fail("没有该状态" + i);
                return false;
        }
    }
}
