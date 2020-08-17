import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HelloWorldVodafone {

    AndroidDriver driver;
    String messagingAppPackageName = "com.google.android.apps.messaging";
    String messagingAppActivityName = "com.google.android.apps.messaging.ui.ConversationListActivity";
    String vodafoneAppPackageName = "com.emeint.android.myservices";
    String vodafoneAppActivityName = "vodafone.vis.engezly.ui.screens.splash.SplashRevampActivity";

    DesiredCapabilities messagingCap ;
    DesiredCapabilities vodafoneCap;
    private static AppiumDriverLocalService service;

    @Before
    public void setUp() throws MalformedURLException {
//        service = AppiumDriverLocalService.buildDefaultService();
//        service.start();
//
//        if (service == null || !service.isRunning()) {
//            throw new AppiumServerHasNotBeenStartedLocallyException(
//                    "An appium server node is not started!");
//        }
        registerVodafoneCaps();
        registerMessagingCaps();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), vodafoneCap);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void changeUserSettingThroughSms() throws MalformedURLException {
        loginToMyAccount();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        openUserInfoPage();
        openSmsApplication();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        sendMessage();
        switchToVodafoneApp();
    }

    private void openUserInfoPage() {
        driver.findElement(MobileBy.id("desc")).click();
    }

    @After
    public void tearDown()  {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
    private DesiredCapabilities registerCaps(String appPackage,String appActivity){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel_2_API_26");
//      fvodafoneCap.setCapability("deviceName", "HUAWEI_P10_lite-65be85067");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);
        return caps;
    }
    private void registerVodafoneCaps() {
        vodafoneCap = registerCaps(vodafoneAppPackageName,vodafoneAppActivityName);
    }
    private void registerMessagingCaps() {
        messagingCap =  registerCaps(messagingAppPackageName,messagingAppActivityName);
    }
    private void sendMessage() {
        driver.findElement(MobileBy.id("start_new_conversation_button")).click();
        driver.findElement(MobileBy.id("recipient_text_view")).sendKeys("888");
        driver.findElement(MobileBy.id("contact_picker_create_group")).click();
        driver.findElement(MobileBy.id("compose_message_text")).sendKeys("16555");
        driver.findElement(MobileBy.id("send_message_button_icon")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    private void openSmsApplication() throws MalformedURLException {
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), messagingCap);
    }

    private void switchToVodafoneApp()  {
        driver.activateApp(vodafoneAppPackageName);
    }

    private void loginToMyAccount() {
        driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.EditText\n")).sendKeys("01090614633");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(MobileBy.id("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.EditText")).sendKeys("oNe_status_1");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(MobileBy.id("btnLogin")).click();
    }
}