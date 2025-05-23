package PageObjects;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class clickModules extends BasePage {

    public clickModules(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='PIM']")
    WebElement clickonPim;

    @FindBy(xpath = "//img[@class='oxd-userdropdown-img']")
    WebElement msgHeading;

    public  void pim(){
        clickonPim.click();
    }

    public boolean myactpageExisr()
    {   try {
        return (msgHeading.isDisplayed());
    }
    catch (Exception e)
    {
        return  false;

    }

    }


}
