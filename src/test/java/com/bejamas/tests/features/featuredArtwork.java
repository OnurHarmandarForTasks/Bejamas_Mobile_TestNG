package com.bejamas.tests.features;

import com.bejamas.tests.utilities.ConfigurationReader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class featuredArtwork extends TestBase{

    /*
    Scenario: the product displays on the other products
    When the product should have special message under the picture
    Then the product should be displayed on the productlist
     */
    @Test
    public void featuredProduct() {

        WebElement featuredArtwork=driver.findElement(By.cssSelector("div[class='FeaturedProductstyle__ProductImageWrapper-b7938p-0 epCavU']"));
        String expectedMessage="Photo of the day";
        String artMessage= featuredArtwork.getText();
        System.out.println("artMessage = " + artMessage);

        Assert.assertEquals(artMessage,expectedMessage,"Verify actual message");

        WebElement firstProcduct=driver.findElement(By.xpath("(//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD'])[1]"));
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);",firstProcduct);
        Assert.assertTrue(firstProcduct.isDisplayed());
    }

}
