package com.bejamas.tests.features;

import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends TestBase{
    /*
    Scenario: The product list should contain 6 artworks
      When the user hover overs the product list
      Then verify the number of the product list is 6
     */

    @Test
    public void numberOfProducts(){
        List<AndroidElement> products=driver.findElements(By.xpath("//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD']"));
        int expectedProductNumber=6;
        int actualProductNumber=products.size();
        System.out.println("actualProductNumber = " + actualProductNumber);
        Assert.assertEquals(actualProductNumber,expectedProductNumber,"Verify number of products"+actualProductNumber);
    }

    /*
    Scenario: Click to product picture and see add to chart bar
    When the user clicking the product pictures
    Then verify add to chart bars are displayed
     */

    @Test
    public void addToCartBars() throws InterruptedException {
        List<AndroidElement> products=driver.findElements(By.xpath("//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD']"));
        List<AndroidElement> addTocartButton=driver.findElements(By.xpath("//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD']/button"));

        for (int i = 0; i < products.size(); i++) {
            products.get(i).click();
            Thread.sleep(1000);
            Assert.assertTrue(addTocartButton.get(i).isDisplayed());
        }
    }
/*
Scenario: Best seller flag appears after clicking the product
      Then verify that Best Seller flag is disabled after hovering over
 */
    @Test
    public void bestSeller() throws InterruptedException {
        List<AndroidElement> products=driver.findElements(By.xpath("//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD']"));
        List<AndroidElement> bestSeller=driver.findElements(By.xpath("//p[@class='ProductCardstyle__BestSellerBadge-sc-5v39a6-6 eHAhyp']"));

        //Unless clicking to products picture Best Seller bar shouldn't be displayed
        //So size of bestSellerlist should be 0

        int expectedListSize=0;
        int actuallistSize=bestSeller.size();
        Assert.assertEquals(actuallistSize,expectedListSize,"Verify actual List Size");
    }


}
