package UI.Pages;

import Utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by taoyang on 14-9-25.
 */
public class Index {
    WebDriver dr;

    public Index(WebDriver dr) {
        this.dr = dr;
        PageFactory.initElements(dr, this);
    }

    public Index() {
        this.dr= Util.GetDr();
        PageFactory.initElements(dr,this);
    }

    @FindBy(id = "hlkUser")
    public WebElement btn用户;

    @FindBy(xpath = "//span[text()='任务 ']")
    public WebElement btn任务;

    @FindBy(id = "hlkAbout")
    public WebElement btn关于;

    @FindBy(id = "hlkHelp")
    public WebElement btn帮助;

    @FindBy(id = "hlkGuide")
    public WebElement btn向导;

    @FindBy(id = "hlkLogout")
    public WebElement btn安全退出;

    @FindBy(xpath = "//span[text()='首页']")
    public WebElement btn首页;

    @FindBy(xpath = "//span[text()='商品管理']")
    public WebElement btn商品管理;

    @FindBy(xpath = "//span[text()='直销管理']")
    public WebElement btn直销管理;

    @FindBy(xpath = "//span[text()='分销管理']")
    public WebElement btn分销管理;

    @FindBy(xpath = "//span[text()='仓库管理']")
    public WebElement btn仓库管理;

    @FindBy(xpath = "//span[text()='采购管理']")
    public WebElement btn采购管理;

    @FindBy(xpath = "//span[text()='客户管理']")
    public WebElement btn客户管理;

    @FindBy(xpath = "//span[text()='帐表管理']")
    public WebElement btn帐表管理;

    @FindBy(xpath = "//span[text()='平台对接']")
    public WebElement btn平台对接;

    @FindBy(xpath = "//span[text()='系统管理']")
    public WebElement btn系统管理;

    public void ClkLnkAPI接口调用记录() {
        dr.switchTo().frame("ifrContent");
        dr.findElement(By.linkText("API接口调用记录")).click();
        dr.switchTo().defaultContent();
        btn关于.click();
    }
}
