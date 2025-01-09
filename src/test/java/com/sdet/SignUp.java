package com.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignUp{

    private WebDriver driver;

    public static void main(String[] args) {
    	SignUp test = new SignUp();
        
        // Setup the browser and open the URL
        test.setup();
        
        // Run the tests
        test.signUpPage();
        test.invalidSignUpData();
        
        // Teardown the browser
        test.tearDown();
    }

    public void setup() {
      
        String url = "https://app-staging.nokodr.com/";

       // System.setProperty("webdriver.chrome.driver");
        driver = new ChromeDriver();
        driver.get(url);
    }

    public void signUpPage() {
        driver.findElement(By.xpath("//a[text()='Sign up']")).click();
        
        // Use WebDriverWait instead of Thread.sleep
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signupHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("slds-text-heading_small")));
        System.out.println(signupHeader.getText());
        
        // Check for the social media signup button visibility
        boolean signupButton = driver.findElement(By.name("loginWithFacebook")).isDisplayed();
        Assert.assertTrue(signupButton, "Signup button not visible");

        WebElement emailButton = driver.findElement(By.xpath("(//li[@role='presentation'])[5]"));
        emailButton.click();

        WebElement emailTab = driver.findElement(By.xpath("(//input[@name='username'])[2]"));
        Assert.assertTrue(emailTab.isDisplayed(), "Email tab not visible");

        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='username'])[2]")));
        proceedButton.sendKeys("Rachel@mailinator.com");

        WebElement termsAndCondition = driver.findElement(By.xpath("//span[@class='slds-checkbox_faux']"));
        termsAndCondition.click();
        

        WebElement proceedButton2 = driver.findElement(By.cssSelector("abx-button[class='btn'] button[name='undefined']"));
        proceedButton2.click();

        WebElement verification = driver.findElement(By.xpath("(//div[@role='status'])[1]"));
        String successMessage = verification.getText();
        System.out.println(successMessage);

        String expectedResult1 = "Loading";
        Assert.assertEquals(successMessage, expectedResult1, "Success message did not match");
        
        driver.navigate().refresh();
    }

    public void invalidSignUpData() {
        driver.findElement(By.xpath("//a[text()='Sign up']")).click();
        
        WebElement email = driver.findElement(By.xpath("(//input[@name='username'])[2]"));
        email.sendKeys("invalid-email");

        WebElement termsAndCondition1 = driver.findElement(By.xpath("//span[@class='slds-checkbox_faux']"));
        termsAndCondition1.click();
        

        WebElement proceedButton1 = driver.findElement(By.cssSelector("abx-button[class='btn'] button[name='undefined']"));
        proceedButton1.click();

      
    }

    public void tearDown() {
       driver.quit();
    }
}
