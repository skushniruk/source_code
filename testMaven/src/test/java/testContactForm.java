import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.logging.*;


/**
 * Created by forteadmin on 12/7/15.
 */
public class testContactForm {

    WebDriver driver;

    private static final Logger logger = Logger.getLogger(testContactForm.class.getName());
    Handler filehandler = null;
    Formatter formatter = null;

    @Before
    public void before() throws IOException {
        filehandler = new FileHandler("zootest.log");
        formatter = new SimpleFormatter();
        filehandler.setFormatter(formatter);
        filehandler.setLevel(Level.ALL);
        logger.addHandler(filehandler);
        driver = new FirefoxDriver();
        driver.navigate().to("http://thetestroom.com/webapp");

        logger.info("About to start the test");
    }

    @After
    public void after()
    {
        logger.info("About to quit the driver");
        driver.quit();

    }
    
    @Test
    public void testSubmitForm()
    {
        logger.info("About to run the test");
        driver.findElement(By.id("contact_link")).click();
        
        driver.findElement(By.name("name_field")).sendKeys(randomString(20));
        driver.findElement(By.name("address_field")).sendKeys(timeStamp());
        driver.findElement(By.name("postcode_field")).sendKeys(String.valueOf(randomNumber(10)));
        driver.findElement(By.name("email_field")).sendKeys(randomEmail());
        driver.findElement(By.id("submit_message")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("contact_confirm"));

        try
        {
            driver.findElement(By.id("dfgd")).click();
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Some severe issue occurred", e);
        }

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
