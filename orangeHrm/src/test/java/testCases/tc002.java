package testCases;

import PageObjects.HomePage;
import PageObjects.clickModules;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.DataProviders;

public class tc002 extends BaseClass{

    @Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class)
    public void userLogIn(String username, String pwd, String exp) throws InterruptedException {
        // logger.info("***** Test case Start ******");
        HomePage hm = new HomePage(driver);
            Thread.sleep(5000);


            hm.setUserName(username);
            logger.info("Enter password");
            hm.setUserPwd(pwd);
            logger.info("click on loginb");
            hm.clickSubmit();
            hm.clickdrpdwn();


            clickModules cm = new clickModules(driver);
            boolean target=cm.myactpageExisr();



            if(exp.equalsIgnoreCase("Valid"))
            {
                if(target==true)
                {
                    hm.logout();
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
            }


    }



}
