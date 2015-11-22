package testApp;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.junit.Test;

/**
 * Created by Serhiy on 22.11.2015.
 */
public class testApp {
    WebDriver driver = new FirefoxDriver();

    @Before
    public void setup()
    {
        driver.navigate().to("http://google.com");
    }

    @Test
    public void test()
    {
        String str = driver.getTitle();
        System.out.println(str);

    }

    @After
    public void teardown()
    {
        driver.close();
    }
}
