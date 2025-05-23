package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.Builder;
import net.bytebuddy.asm.Advice;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.BaseClass;

import javax.sql.DataSource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public  void onStart(ITestContext textContext){
        /*
            SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            Date dt= new Date();
            String currentdatetimestamp= df.format(dt);
         */

        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
        repName = "Test-report-" + timeStamp + ".html";
        sparkReporter =  new ExtentSparkReporter("./reports/" +repName);

        sparkReporter.config().setDocumentTitle("Orange Automation report");
        sparkReporter.config().setReportName("OrangeHRM fuctional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","OrangeHrm");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customers");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Enviroment","QA");

        String os= textContext.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("Operating System",os);

        String browser = textContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = textContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups",includedGroups.toString());
        }
        }

        public  void onTestSuccess(ITestResult result){

        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+"got sucessfully executed");

        }

        public  void onTestFailure(ITestResult result){
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName()+"got Failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try{
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        }

        public  void onTestSkipped(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+"got skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());

        }

      /*  public void onFinish(ITestContext testContext){
            extent.flush();

            String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+repName;
            File extentReport = new File(pathOfExtentReport);

            try{
                Desktop.getDesktop().browse(extentReport.toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
      public void onFinish(ITestContext testContext) {
          extent.flush();

          // Use Paths to create a platform-independent path
          //Path pathOfExtentReport = Paths.get(System.getProperty("user.dir"), "reports", repName);
          Path reportPath = Paths.get(System.getProperty("user.dir"), "reports", repName);
          File extentReport = reportPath.toFile();

          try {
              if (extentReport.exists()) {
                  Desktop.getDesktop().browse(extentReport.toURI());
              } else {
                  System.err.println("Report file not found: " + extentReport.getAbsolutePath());
                  System.out.println("File exists? " + extentReport.exists());
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

          /*  try{
                URL url = new
                        URL("file://"+System.getProperty("user.dir")+"\\reports\\"+repName);

                //create the email message ImageHtmlEmail = new ImageHtmlEmail()
                email.setDataSourceResolver(new DataSourceUrlResolver(URL));
                email.setHostName("smtp.googlemail.com");
                email.setSmtpPort(465);
                email.setAuthenticator(new
                        DefaultAuthenticator("anuj@gmail.com","password"));
                email.setSSLOnConnect(true);
                email.setFrom("anuj@gmail.com");
                email.setMsg("Please find Attach report");
                email.addto("pavan@gmail.com");     //recevier email.attach(url,
                email.attach(url, "extent report", "please check report...");
                email.send();    //send the email
            } catch (Exception e) {
                e.printStackTrace();
            }*/

        }





