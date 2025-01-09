package com.sdet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginPage {

    public static void main(String[] args){
        // Set up WebDriver
        String url = "https://app-staging.nokodr.com/";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get(url);
            System.out.println(driver.getTitle());
            String actualResult = driver.getTitle();
            String expectedResult = "noKodr";
            Assert.assertEquals(expectedResult, actualResult);

            // Test 1: Login with valid password
            loginWithValidPassword(driver);

            // Test 2: Login with OTP
            //loginWithOtp(driver);

            // Test 3: Login with invalid data
            loginPageWithInvalidData(driver);
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    public static void loginWithValidPassword(WebDriver driver) {
        System.out.println("Executing: Login with valid password");
        WebElement passwordButton = driver.findElement(By.xpath("//a[@title='Password']"));
        passwordButton.click();
        Assert.assertTrue(passwordButton.isDisplayed());

        WebElement emailButton = driver.findElement(By.xpath("//a[@title='Email']"));
        emailButton.click();
        Assert.assertTrue(emailButton.isDisplayed());

        WebElement insertEmail = driver.findElement(By.xpath("(//input[@name='username'])[1]"));
        insertEmail.sendKeys("ross@mailinator.com");
        WebElement insertPassword = driver.findElement(By.xpath("(//input[@name='password'])[1]"));
        insertPassword.sendKeys("Ross@2025");

        WebElement loginButton = driver.findElement(By.cssSelector("div[title='Log In']"));
        loginButton.click();

        WebElement homepage = driver.findElement(By.xpath("(//div)[6]"));
        Assert.assertTrue(homepage.isDisplayed());
        System.out.println("Homepage displayed: " + homepage.getText());
    }

    public static void loginWithOtp(WebDriver driver) {
        System.out.println("Executing: Login with OTP");
        WebElement otpButton = driver.findElement(By.xpath("(//a[@title='OTP'])[1]"));
        otpButton.click();
        Assert.assertTrue(otpButton.isDisplayed());

        WebElement insertEmail = driver.findElement(By.xpath("(//input[@name='username'])[1]"));
        insertEmail.sendKeys("ross@mailinator.com");

        WebElement sendCode = driver.findElement(By.xpath("(//button[@name='undefined'])[1]"));
        sendCode.click();
        Assert.assertTrue(sendCode.isDisplayed());

        WebElement error = driver.findElement(By.xpath("//div[@role='status']"));
        System.out.println("Error message: " + error.getText());
    }

    public static void loginPageWithInvalidData(WebDriver driver) {
    	try {
        System.out.println("Executing: Login with invalid data");
        WebElement insertEmail = driver.findElement(By.xpath("(//input[@name='username'])[1]"));
        insertEmail.sendKeys("genny@mailinator.com");
        WebElement insertPassword = driver.findElement(By.xpath("(//input[@name='password'])[1]"));
        insertPassword.sendKeys("Ross@20");

        WebElement loginButton = driver.findElement(By.cssSelector("div[title='Log In']"));
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        loginButton.click();
        //driver.navigate().refresh();
    	}catch(Exception e){
    		
    	}
        

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // WebElement loginButton1 = driver.findElement(By.cssSelector("div[title='Log In']"));
        //loginButton1.click();

        WebElement error = driver.findElement(By.xpath("//div[@role='status']"));
        System.out.println("Error message: " + error.getText());
    }
}
