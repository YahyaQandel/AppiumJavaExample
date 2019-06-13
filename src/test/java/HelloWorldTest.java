import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

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
//     @TODO use aab in appium
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Nexus_5X");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", "apks/app-debug.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//        cap.setCapability("appPackage", "com.scores365");
//        cap.setCapability("appActivity", "com.scores365.ui.Splash");

//        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

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