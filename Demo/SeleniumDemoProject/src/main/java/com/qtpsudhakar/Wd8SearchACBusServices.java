package com.qtpsudhakar;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import com.wd.util.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Wd8SearchACBusServices {
    public static void main(String[] args) throws InterruptedException  {
        WebDriver driver = DriverFactory.getDriver("chrome");
        //page load timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));

        //find element timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //maximize window
        driver.manage().window().maximize();

		//navigate to application
		driver.get("https://www.tsrtconline.in/");

        String fromPlace = "HYDERABAD";
        String toPlace = "BANGALORE";

        //select from place
        driver.findElement(By.id("fromPlaceName")).sendKeys(fromPlace);
        driver.findElement(By.linkText(fromPlace)).click();

		//select to place
        driver.findElement(By.id("toPlaceName")).sendKeys(toPlace);
        driver.findElement(By.linkText(toPlace)).click();

        //select date
        driver.findElement(By.id("txtJourneyDate")).click();
        driver.findElement(By.linkText("24")).click();

        //click on search button
        driver.findElement(By.id("searchBtn")).click();
        Thread.sleep(10000);
        //get number of services         
        String srvCount = driver.findElement(By.id("fwTotalServicesId")).getText();
        System.out.println(srvCount);
        List<WebElement>lstServices = driver.findElements(By.cssSelector("div.srvceNO"));
        System.out.println(lstServices.size());
        if (lstServices.size()==Integer.parseInt(srvCount)){
            
            System.out.println("Correct number of srvices displayed");
        }else{
            System.out.println("Wrong number of srvices displayed");
        }

        //select A/C Class
        driver.findElement(By.id("BtFid")).click();

        if (!driver.findElement(By.name("A/C CLASS")).isSelected()) {
            driver.findElement(By.name("A/C CLASS")).click();    
        }
        
        String acSrvCount = driver.findElement(By.id("fwTotalServicesId")).getText();

        List<WebElement>lstACServices = driver.findElements(By.cssSelector("div.srvceNO"));
        System.out.println(lstServices.size());
        int acCounter = 0;
        for(int i=0;i<lstACServices.size();i++){
            if (lstACServices.get(i).isDisplayed()){
                acCounter++;
            }
        }


        // lstACServices.forEach(elm->{            
        //     if (elm.isDisplayed()){
                
        //     }});

        List<WebElement> lstFliterACServices = lstACServices.stream().filter(elm->elm.isDisplayed()).collect(Collectors.toList());

        if (lstFliterACServices.size()==Integer.parseInt(acSrvCount)){            
            System.out.println("Correct number of AC services displayed");
        }else{
            System.out.println("Wrong number of AC services displayed");
        }

        driver.quit();
    }
}
