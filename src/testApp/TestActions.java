package testApp;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Serhiy on 30.11.2015.
 */
public class TestActions {
    WebDriver driver;

    @Test
    public void TestSlider()
    {
        driver = new FirefoxDriver();
        driver.navigate().to("http://thetestroom.com/webapp");

        driver.findElement(By.id("contact_link")).click();

        Actions slider = new Actions(driver)
                .dragAndDropBy(driver.findElement(By.id("slider-1")), -100, 0);
        slider.build().perform();
    }
}
