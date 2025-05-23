package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class createEmployee extends BasePage{

    public createEmployee(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement clickAdd;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement txtFirstname;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement txtLastName;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement txtEmpCode;

    @FindBy(xpath = "//button[contains(.,\"Save\")]")
    WebElement saveEmp;

    public  void clickAddBtn(){
        clickAdd.click();
    }

    public void setTxtFirstname(String fname){
        txtFirstname.sendKeys(fname);
    }

    public  void setTxtLastName(String lname){
        txtLastName.sendKeys(lname);
    }

    public void setTxtEmpCode(String employeecode) throws InterruptedException {
       // txtEmpCode.click();
        Thread.sleep(5000);
        txtEmpCode.clear();
        txtEmpCode.sendKeys(employeecode);
    }

    public void clickSave(){
        saveEmp.click();
    }

}
