import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HelloWorldBundle {

    AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @BeforeClass
    public static void createAPKS() throws IOException, InterruptedException {
//      RUN ONLY FIRST TIME TO CREATE THE APKS FOR THE BUNDLE
        AndroidHelper.generateApksFromAAB();
        AndroidHelper.installAPKSonDevice();
    }
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
        cap.setCapability("appPackage", "com.helloworld");
        cap.setCapability("appActivity", "MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void checkHeaderExists() {
        driver.findElement(MobileBy.id("headerText"));
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
//      @TODO until you find a way to wait for the apk to be installed this line should be commented
//      driver.removeApp("com.helloworld");
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}