package UI.Pages;

import Utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by taoyang on 14-9-25.
 */
public class Login {
    WebDriver dr;

    public Login(WebDriver dr) {
        this.dr = dr;
        PageFactory.initElements(dr,this);
    }

    @FindBy(id="txtUsername")
    public WebElement txt用户名;

    @FindBy(id = "txtPassword")
    public WebElement txt密码;

    @FindBy(name = "btnLogin")
    public WebElement btn登录;


    public Index LoginWith(String name, String password) {
        txt用户名.sendKeys(name);
        txt密码.sendKeys(password);
        btn登录.click();
        Index index=new Index(dr);
        Util.WaitElementEnabled(dr, index.btn用户);
        return index;
    }


}
