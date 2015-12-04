import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forteadmin on 12/4/15.
 */
public class ZooPageTest {

    WebDriver driver;
    ExcelFileHandler excelFileHandler;

    @Before
    public void setUp()
    {
        driver = new FirefoxDriver();
        excelFileHandler = new ExcelFileHandler();
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void shouldTestPage()
    {
        List<List<String>> data = excelFileHandler.readFromFile("PageResults.xls");

        List<String> results = new ArrayList<String>();
        results.add("Results");

        for (int i = 1; i < data.size(); i++)
        {
            int j = 0;
            driver.navigate().to("http://thetestroom.com/webapp");
            driver.findElement(By.id(data.get(i).get(j).toLowerCase() + "_link")).click();
            if (driver.getTitle().contains(data.get(i).get(++j)))
            {
                results.add("PASS");
            }
            else
            {
                results.add("FAIL");
            }
        }
        System.out.println(results);
    }

}
