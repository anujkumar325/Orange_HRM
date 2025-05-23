package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

        @DataProvider(name="LoginData")
    public String [][] getData() throws IOException {
        String path= "/home/rst/IdeaProjects/automation_Learn/login_data1.xlsx";

        ExcelUtility xlUtil= new ExcelUtility(path);

        int totalrows = xlUtil.getRowCount("sheet1");
        int totalcols = xlUtil.getCellCount("sheet1",1);

        String logindata[][]= new String[totalrows][totalcols];  //created for two dimensional array which can store

        for (int i=1; i<=totalrows;i++)
        {
            for(int j=0; j<totalcols;j++)
            {
                logindata[i-1][j]= xlUtil.getCellData("sheet1",i,j);      //1,0
            }
        }
        return logindata;    //returning two dimension array


    }
}
