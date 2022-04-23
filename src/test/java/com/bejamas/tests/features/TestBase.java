package com.bejamas.tests.features;

import com.bejamas.tests.utilities.ConfigurationReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected AndroidDriver<AndroidElement> driver;

    //Setup Android Driver and Navigate to homepage of webpage
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();

        //FOR EMULATOR
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "onuremulator");

        cap.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.get("url"));
    }


}
