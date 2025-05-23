package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;



public class BaseClass {
    public Properties p;
  public static   WebDriver   driver;
    public Logger logger;
    @BeforeClass(groups= {"Sanity","Regression","Master"})
    @Parameters({"os","browser"})
    public void setup(String os,String br) throws InterruptedException, IOException {
        logger= (Logger) LogManager.getLogger(this.getClass());

        FileReader file= new FileReader("/home/rst/IdeaProjects/orangeHrm/src/config.properties");
        p= new Properties();
        p.load(file);

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WINDOWS);
            } else if ((os.equalsIgnoreCase("linux"))) {
                capabilities.setPlatform(Platform.LINUX);
            }


           else{
                System.out.println("No matching platform");
                return;
            }
            switch (br.toLowerCase())
            {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("Invalid browser");
                    return;
            }
           driver=new RemoteWebDriver(new URL("http://192.168.60.104:4444/wd/hub"),capabilities);
        }

        if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("invalid browser");
                    return;
            }

        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("url"));
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @AfterClass(groups= {"Sanity","Regression","Master"})
    public void tearDown(){
        driver.quit();
    }


    public  String captureScreen (String tname) throws IOException{

        String timeStamp= new SimpleDateFormat("yyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath= System.getProperty("user.dir")+"/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return  targetFilePath;
    }


}
