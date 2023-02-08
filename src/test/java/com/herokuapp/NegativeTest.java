package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {

        @Test(priority = 2, groups = {"regression"})
        public void negative()

    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith1");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        WebElement loginButton= driver.findElement(By.className("radius"));
        loginButton.click();

        WebElement alertNegative= driver.findElement(By.id("flash"));
        String alertTextNeg="Your username is invalid!";
        Assert.assertTrue(alertNegative.isDisplayed());
        Assert.assertTrue(alertNegative.getText().contains(alertTextNeg));

        driver.quit();

    }
}
