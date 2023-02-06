package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void login(){
        //1 Deschidem pagina
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();

        //2 Introducem username si password
        WebElement username=driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        WebElement password=driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        //3 Apasam Login
        WebElement loginButton= driver.findElement(By.className("radius"));
        loginButton.click();

        //4 Verificam
        String landingUrl="https://the-internet.herokuapp.com/secure";
        Assert.assertEquals(landingUrl,"https://the-internet.herokuapp.com/secure");

        WebElement logoutButton=driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutButton.isDisplayed());

        WebElement alert= driver.findElement(By.id("flash"));
        String alertText="You logged into a secure area!";
        Assert.assertTrue(alert.isDisplayed());
        Assert.assertTrue(alert.getText().contains(alertText));

        //5 Inchidem pagina
        driver.quit();








    }


}
