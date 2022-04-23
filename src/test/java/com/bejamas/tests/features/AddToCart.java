package com.bejamas.tests.features;

import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCart extends TestBase{


    /*
    Scenario:User can not see the counter badge if it is empty
    Then Verify that counter badge is NOT appear
     */

    @Test
    public void emptyCounterBadge(){
        WebElement counterBadge=driver.findElement(By.cssSelector("button>span"));
        //Unless adding new product counter badge shouldn't be displayed
        Assert.assertFalse(counterBadge.isDisplayed());
    }



    /*
    Scenario: User can see the number of added items on the counter badge
      When the user adds the new item
      Then Verify the number of counter badge is the same with added products
     */
    @Test
    public void addedCounterBadge(){
        //Add featured artwork to cart
        WebElement featuredArtworkAddToCart=driver.findElement(By.cssSelector("button[class='Buttonstyle__ButtonWrapper-sc-1d6yy9q-0 fQMgOi']"));
        featuredArtworkAddToCart.click();

        //get the number of dropdown
        List<AndroidElement> dropdownItems=driver.findElements(By.xpath("//div[@class='style__Column-sc-4ctdae-1 EwxsO']/div/div"));
        int numberOfAddedItems=dropdownItems.size();
        int numberOfCounter=Integer.parseInt(driver.findElement(By.cssSelector("button>span")).getText());

        System.out.println("numberOfAddedItems = " + numberOfAddedItems);
        System.out.println("numberOfCounter = " + numberOfCounter);

        Assert.assertEquals(numberOfAddedItems,numberOfCounter,"Verify Number Of Counter");

    }




    /*
    Scenario: User can see the dropdow for each Add to cart action
    When the user adds the new item
    Then verify the dropdown appears and close the dropdown
    And Add items from product list and verify dropdown appears for each action
     */

    @Test
    public void verifyDropdownForEveryAddToCartAction() throws InterruptedException {
        JavascriptExecutor jse= (JavascriptExecutor) driver;

        //add featrued artwork
        WebElement featuredArtworkAddToCart=driver.findElement(By.cssSelector("button[class='Buttonstyle__ButtonWrapper-sc-1d6yy9q-0 fQMgOi']"));
        featuredArtworkAddToCart.click();

        //verify dropdown is displayed
       WebElement dropdown=driver.findElement(By.xpath("//div[@class='Navbarstyle__CartWrapper-sc-1b7zefh-1 kcaWdL']"));
       Assert.assertTrue(dropdown.isDisplayed());

        //close the dropdown
        WebElement closeButton=driver.findElement(By.xpath("//button[@class='ShoppingCartstyle__CloseButton-sc-1xombtx-2 dZcQeL']"));
        closeButton.click();
       Thread.sleep(1000);

        //Add each product and verify the dropdown appears for each time
        List<AndroidElement> products=driver.findElements(By.xpath("//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD']"));
        List<AndroidElement> addToCartButtons=driver.findElements(By.xpath("//div[@class='ProductCardstyle__ProductImageWrapper-sc-5v39a6-1 OdCKD']/button"));



        for (int i = 0; i < products.size(); i++) {
            jse.executeScript("arguments[0].scrollIntoView(true);",products.get(i));
            products.get(i).click();
            Thread.sleep(1000);
            addToCartButtons.get(i).click();
            Thread.sleep(1000);
            dropdown=driver.findElement(By.xpath("//div[@class='Navbarstyle__CartWrapper-sc-1b7zefh-1 kcaWdL']"));
            Assert.assertTrue(dropdown.isDisplayed());
                Thread.sleep(1000);
            closeButton=driver.findElement(By.xpath("//button[@class='ShoppingCartstyle__CloseButton-sc-1xombtx-2 dZcQeL']"));
                closeButton.click();
        }
    }





       /*
         Scenario: User can see dropdown by clicking busket icon
    When the user adds the new item
    Then verify the dropdown appears and close the dropdown
    And click the basket icon
    Then verify the dropdown appears and close the dropdown
         */


    @Test
    public void basketIcon() throws InterruptedException {
        //add featrued artwork
        WebElement featuredArtworkAddToCart=driver.findElement(By.cssSelector("button[class='Buttonstyle__ButtonWrapper-sc-1d6yy9q-0 fQMgOi']"));
        featuredArtworkAddToCart.click();

        //verify dropdown is displayed
        WebElement dropdown=driver.findElement(By.xpath("//div[@class='Navbarstyle__CartWrapper-sc-1b7zefh-1 kcaWdL']"));
        Assert.assertTrue(dropdown.isDisplayed());

        //close the dropdown
        WebElement closeButton=driver.findElement(By.xpath("//button[@class='ShoppingCartstyle__CloseButton-sc-1xombtx-2 dZcQeL']"));
        closeButton.click();
        Thread.sleep(1000);

        //click basket icon
        WebElement basketIcon=driver.findElement(By.xpath("(//button)[1]"));
        basketIcon.click();
        dropdown=driver.findElement(By.xpath("//div[@class='Navbarstyle__CartWrapper-sc-1b7zefh-1 kcaWdL']"));

        Assert.assertTrue(dropdown.isDisplayed());
    }



/*
    Scenario: User can see the dropdow empty after clicking clear button
    When the user adds the new item
    Then verify the dropdown appears and close the dropdown
    And click the basket icon
    And click the clear button
    And click the basket icon
    Then verify the "Your Shopping Cart is Empty" message
 */

    @Test
    public void verifyEmptyCartMessage() throws InterruptedException {
        //add featrued artwork
        WebElement featuredArtworkAddToCart=driver.findElement(By.cssSelector("button[class='Buttonstyle__ButtonWrapper-sc-1d6yy9q-0 fQMgOi']"));
        featuredArtworkAddToCart.click();

        //verify dropdown is displayed
        WebElement dropdown=driver.findElement(By.xpath("//div[@class='Navbarstyle__CartWrapper-sc-1b7zefh-1 kcaWdL']"));
        Assert.assertTrue(dropdown.isDisplayed());

        //close the dropdown
        WebElement closeButton=driver.findElement(By.xpath("//button[@class='ShoppingCartstyle__CloseButton-sc-1xombtx-2 dZcQeL']"));
        closeButton.click();
        Thread.sleep(1000);

        //click basket icon
        WebElement basketIcon=driver.findElement(By.xpath("(//button)[1]"));
        basketIcon.click();

        WebElement clearButton=driver.findElement(By.xpath("//button[@class='Buttonstyle__ButtonWrapper-sc-1d6yy9q-0 kkjxIW']"));
        clearButton.click();

        basketIcon.click();

        WebElement emptyMessage=driver.findElement(By.xpath("//*[@class='empty-text']"));
        String expectedMessage="Your Shopping Cart is Empty";
        String actualMessage=emptyMessage.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertEquals(actualMessage,expectedMessage,"Verify actual Message");

    }

}
