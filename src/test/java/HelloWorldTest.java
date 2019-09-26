import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class HelloWorldTest {

    AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @Before
    public void setUp() throws MalformedURLException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Nexus_5X");
        cap.setCapability("platformName", "Android");
//
        cap.setCapability("appPackage", "com.google.android.dialer");
        cap.setCapability("appActivity", "com.google.android.dialer.extensions.GoogleDialtactsActivity");
        // Real device
//         cap.setCapability("appPackage", "com.android.contacts");
//         cap.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

    }

    @Test
    public void checkHeaderExists() {
        driver.findElement(MobileBy.id("headerText"));
    }

    @Test
    public void checkEditTextWithClassName() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        String editTextValue = driver.findElementByClassName("android.widget.EditText").getText();
        assertEquals("Give me pizza!", editTextValue);
    }

    @Test
    public void checkEnteredText() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(MobileBy.id("textField")).sendKeys("Im hungry");
        assertEquals(driver.findElement(MobileBy.id("textRendered")).getText(), "\uD83C\uDF55 \uD83C\uDF55");
    }

    @Test
    public void openPhoneDialer(){
        driver.findElement(By.id("key pad")).click();
        driver.findElement(By.id("0,+")).click();
        driver.findElement(By.id("1,")).click();
        driver.findElement(By.id("0,+")).click();
        driver.findElement(By.id("9,WXYZ")).click();
        driver.findElement(By.id("0,+")).click();
        driver.findElement(By.id("6,MNO")).click();
        driver.findElement(By.id("1,")).click();
        driver.findElement(By.id("4,GHI")).click();
        driver.findElement(By.id("6,MNO")).click();
        driver.findElement(By.id("3,DEF")).click();
        driver.findElement(By.id("3,DEF")).click();
        driver.findElement(By.id("dial")).click();
//        driver.findElement(By.id("dial")).click();
//        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

//        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"0\"]/android.widget.RelativeLayout")).click();
//        driver.findElement(By.id("dial")).click();



    }
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}