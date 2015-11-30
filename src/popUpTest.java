import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Serhiy on 23.11.2015.
 */
public class popUpTest {
    WebDriver driver;

    @Before
    public void setup()
    {
        driver = new FirefoxDriver();
        driver.navigate().to("http://thetestroom.com/webapp");
    }

    @After
    public void teardown()
    {
        driver.close();
    }

    @Test
    public void testAlert()
    {
        driver.findElement(By.id("contact_link")).click();
        driver.findElement(By.id("submit_message")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains("Name"));
        alert.accept();
    }

    @Test
    public void testPopUp()
    {
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.id("footer_term")).click();

        for (String currentWindow : driver.getWindowHandles())
        {
            driver.switchTo().window(currentWindow);
        }
        System.out.println(driver.getCurrentUrl());
        driver.close();
        driver.switchTo().window(parentWindow);
        System.out.println(driver.getCurrentUrl());

    }
}
