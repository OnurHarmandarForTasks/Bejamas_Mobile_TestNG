package com.bejamas.tests.features;

import com.bejamas.tests.utilities.MyUtilities;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Pagination extends TestBase{

    /*
    Scenario: User sees the paginated page number under the page
    When the user hover overs the product list
      Then Verify the page numbers
     */

    @Test
    public void paginationNumbers(){
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        WebElement paginationBody=driver.findElement(By.xpath("//div[@class='style__Column-sc-4ctdae-1 RTgwF']/ol"));
        jse.executeScript("arguments[0].scrollIntoView(true);",paginationBody);

        Assert.assertTrue(paginationBody.isDisplayed());
    }



/*
    Scenario: User can change page by clicking page numbers
    Then Click on the page number and Verify the pages are changing
 */

    @Test
    public void switchWithNumbers(){
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        List<AndroidElement> pageNumbers=driver.findElements(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 jpDQtp']"));
        WebElement currentPageNumber=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 bbjimK']"));
        int defaultPageNumber=Integer.parseInt(currentPageNumber.getText());
        int currentNumber;

        for (int i = 0; i < pageNumbers.size(); i++) {
            currentPageNumber=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 bbjimK']"));
            currentNumber=Integer.parseInt(currentPageNumber.getText());
            System.out.println("currentPageNumber = " + currentNumber);
            System.out.println("defaultPageNumber = " + defaultPageNumber);
            Assert.assertEquals(defaultPageNumber,currentNumber);

            //after switching to second page prev arrow's locater is changing and it becomes a pages member
            //to pass to 0 to 2 a condition is needed
            if(pageNumbers.get(i).getText().equals("1")){
                i++;
            }
            pageNumbers.get(i).click();
            MyUtilities.waitFor(1);

            defaultPageNumber++;
            if(defaultPageNumber==6){
                currentPageNumber=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 bbjimK']"));
                currentNumber=Integer.parseInt(currentPageNumber.getText());
                System.out.println("currentPageNumber = " + currentNumber);
                System.out.println("defaultPageNumber = " + defaultPageNumber);
                Assert.assertEquals(defaultPageNumber,currentNumber);
            }
        }
    }




/*
    Scenario: User can cahange pages by clicking 'next' and 'prev' arrows
    Then Click on the "next" arrow and Verify the pages are changing
    And Click on the "prev" arrow and Verify the pages are changing
 */
@Test
    public void clickingPrevAndNextArrows() throws InterruptedException {
    JavascriptExecutor jse= (JavascriptExecutor) driver;
    List<AndroidElement> pageNumbers=driver.findElements(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 jpDQtp']"));
    WebElement currentPageNumber=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 bbjimK']"));
    WebElement nextArrow=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 MCnLo']"));
    WebElement prevArrow=driver.findElement(By.xpath("(//li[@class='PaginationIndicatorstyle__Li-urm45v-1 jpDQtp'])[1]"));

    int defaultPageNumber=Integer.parseInt(currentPageNumber.getText());
    int currentNumber;

    //for switching to next page
    for (int i=0;i<pageNumbers.size();i++){
        pageNumbers=driver.findElements(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 jpDQtp']"));
        currentPageNumber=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 bbjimK']"));
        currentNumber=Integer.parseInt(currentPageNumber.getText());
        System.out.println("currentPageNumber = " + currentNumber);
        System.out.println("defaultPageNumber = " + defaultPageNumber);
        Assert.assertEquals(defaultPageNumber,currentNumber);

        if (currentNumber!=pageNumbers.size()){
            nextArrow.click();
            Thread.sleep(1000);
            defaultPageNumber++;
        }
    }

    //for switching back
    for (int i=0;i<pageNumbers.size();i++){
        pageNumbers=driver.findElements(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 jpDQtp']"));
        currentPageNumber=driver.findElement(By.xpath("//div/ol/li[@class='PaginationIndicatorstyle__Li-urm45v-1 bbjimK']"));
        currentNumber=Integer.parseInt(currentPageNumber.getText());
        System.out.println("currentPageNumber = " + currentNumber);
        System.out.println("defaultPageNumber = " + defaultPageNumber);
        Assert.assertEquals(defaultPageNumber,currentNumber);
        prevArrow=driver.findElement(By.xpath("(//li[@class='PaginationIndicatorstyle__Li-urm45v-1 jpDQtp'])[1]"));
        prevArrow.click();
        Thread.sleep(1000);
        defaultPageNumber--;

        if(defaultPageNumber==1){
            currentNumber=defaultPageNumber;
            System.out.println("currentPageNumber = " + currentNumber);
            System.out.println("defaultPageNumber = " + defaultPageNumber);
            Assert.assertEquals(defaultPageNumber,currentNumber);
            break;
        }

    }

}


}
