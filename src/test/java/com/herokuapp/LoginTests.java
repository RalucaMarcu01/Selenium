package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
    private  WebDriver driver;
    @BeforeMethod
    private void setUp(){
        //Deschidem pagina
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }
    @Parameters({ "user","password1" })
    @Test//(priority = 1, groups = {"smoke","regression"})
    public void login(String user, String password1){


        //2 Introducem username si password
        WebElement username=driver.findElement(By.id("username"));
        username.sendKeys(user);

        WebElement password=driver.findElement(By.name("password"));
        password.sendKeys(password1);

       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/

        //3 Apasam Login
       //
        //WebElement loginButton= driver.findElement(By.className("radius"));
        // WebElement loginButton= driver.findElement(By.xpath("//*[@id=\"login\"]/button/i"));
        //by css
        WebElement loginButton= driver.findElement(By.cssSelector("#login > button > i"));
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

    }
    @Parameters({"userNeg","passNeg"})
    @Test(priority = 2, groups = {"regression"})
    public void negative(String userNeg, String passNeg)

    {


        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(userNeg);

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(passNeg);

        WebElement loginButton= driver.findElement(By.className("radius"));
        loginButton.click();

        WebElement alertNegative= driver.findElement(By.id("flash"));
        String alertTextNeg="Your username is invalid!";
        Assert.assertTrue(alertNegative.isDisplayed());
        Assert.assertTrue(alertNegative.getText().contains(alertTextNeg));



    }
    @AfterMethod
    private void tearDown(){
        //5 Inchidem pagina
        driver.quit();


    }


}
