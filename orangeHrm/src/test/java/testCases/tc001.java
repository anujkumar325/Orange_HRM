package testCases;

import PageObjects.HomePage;
import PageObjects.clickModules;
import PageObjects.createEmployee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class tc001 extends  BaseClass {


    @Test(groups = {"Master"})
    public  void AddEmployee() throws InterruptedException, IOException {

        try {
            logger.info("***** Test case Start ******");
            HomePage hp = new HomePage(driver);
            Thread.sleep(5000);

            logger.info("enter username");
            hp.setUserName("Admin");
            logger.info("Enter password");
            hp.setUserPwd("admin123");
            logger.info("click on loginb");
            hp.clickSubmit();
            logger.info("Login sucessfully");
            clickModules cm = new clickModules(driver);
//chaintestreort.log(")
            logger.info("CLick on pim module");
            cm.pim();
            logger.info("***** Enter Employee details    ****");
            createEmployee ctEmp = new createEmployee(driver);
            ctEmp.clickAddBtn();
            ctEmp.setTxtFirstname("Anuj");
            ctEmp.setTxtLastName("kumar");
            ctEmp.setTxtEmpCode("1234");
            Thread.sleep(2000);
            ctEmp.clickSave();
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            logger.error("Test failed"+e.getMessage());
            Assert.fail(e.getMessage());
            captureScreen("error");

        }

        logger.debug("This is debug");





    }
}

