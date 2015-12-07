import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by forteadmin on 12/7/15.
 */
public class testContactForm {

    WebDriver driver;

    @Before
    public void before()
    {
        driver = new FirefoxDriver();
        driver.navigate().to("http://thetestroom.com/webapp");
    }

    @After
    public void after()
    {
        driver.quit();
    }
    
    @Test
    public void testSubmitForm()
    {
        driver.findElement(By.id("contact_link")).click();
        
        driver.findElement(By.name("name_field")).sendKeys(randomString(20));
        driver.findElement(By.name("address_field")).sendKeys(timeStamp());
        driver.findElement(By.name("postcode_field")).sendKeys(String.valueOf(randomNumber(10)));
        driver.findElement(By.name("email_field")).sendKeys(randomEmail());
        driver.findElement(By.id("submit_message")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("contact_confirm"));

    }

    public static int randomNumber(int maxValue) {
        Random rand = new Random();
        return rand.nextInt(maxValue);
    }

    public static String randomString(int length)
    {
        final String data = "0123456789qwertyuiopasdfghjklzxcvbnm";

        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
        {
            sb.append(data.charAt(rand.nextInt(data.length())));
        }
        return sb.toString();
    }
    public static String randomEmail()
    {
        String out = randomString(5) + "@" + randomString(4) + ".com";
        return out;
    }

    public static String timeStamp()
    {
        Date date = new Date();
        return new Timestamp(date.getTime()).toString();
    }
}
