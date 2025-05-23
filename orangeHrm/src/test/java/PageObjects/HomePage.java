package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends  BasePage {


        public HomePage(WebDriver driver){
            super(driver);
        }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement userName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement userPwd;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement submit;

    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
    WebElement openDropDown;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement ClickLogout;



    public  void setUserName(String username){
        userName.sendKeys(username);
    }
    public  void setUserPwd(String password){
        userPwd.sendKeys(password);
    }

    public  void clickSubmit()
    {
        submit.click();
    }

    public void clickdrpdwn(){
        openDropDown.click();
    }

    public void logout(){

        ClickLogout.click();
    }




}
