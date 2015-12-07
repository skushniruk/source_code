import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void shouldTestPage() throws InterruptedException {
        List<List<String>> data = excelFileHandler.readFromFile("PageResults.xls");

        List<String> results = new ArrayList<String>();
        results.add("Results");

        for (int i = 1; i < data.size(); i++)
        {
            int j = 0;
            driver.navigate().to("http://thetestroom.com/webapp");
            waitForPage(By.tagName("p"), 5);
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
        excelFileHandler.writeToFile("PageResults.xls", results);
    }

    public WebElement waitForPage(By locator, int maxSeconds)
    {
        return (new WebDriverWait(driver, maxSeconds*1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
