package com.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest {
    @Test
    public void testGoogle(){
       //create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver=new ChromeDriver();

        //open page
        String url="https://www.google.com/";
        driver.get(url);
        System.out.println("Opening Google page");

       //maximize page
        driver.manage().window().maximize();
        System.out.println("Page is maximized");
        Assert.assertEquals("https://www.google.com/", url);

        //close page
        driver.quit();
        System.out.println("Closing page");


    }
}
