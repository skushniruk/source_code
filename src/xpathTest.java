import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Serhiy on 22.11.2015.
 */
public class xpathTest {

    @Test
    public void testXpath()
    {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://thetestroom.com/webapp");

        driver.findElement(By.xpath("//a[4]")).click();

        driver.findElement(By.xpath("//input[contains(@name, 'name_field')]")).sendKeys("my name is Serhiy");
        driver.findElement(By.xpath("//input[2]")).click();
        driver.findElement(By.xpath("//input[contains[@id, 'cdona']")).click();

    }
}
