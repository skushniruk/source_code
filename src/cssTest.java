import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Serhiy on 23.11.2015.
 */
public class cssTest {

    @Test
    public void testCss()
    {
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("http://thetestroom.com/webapp");
        //id
        driver.findElement(By.cssSelector("#contact_link")).click();
        //tag
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        //class
        driver.findElement(By.cssSelector(".home")).click();
        //any attribute
        driver.findElement(By.cssSelector("[href='about.html']")).click();
    }
}
