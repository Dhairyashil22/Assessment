package com.sdet;

	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;

	public class ForgetPassword {
	    public static void main(String[] args) throws InterruptedException {
	        // Set up WebDriver and URL
	        //String driverPath = "C:\\Users\\ROHAN\\source\\repos\\AssignmentProject\\AssignmentProject\\Driverfiles\\chromedriver.exe";
	        String url = "https://app-staging.nokodr.com/";

	        //System.setProperty("webdriver.chrome.driver", driverPath);
	        WebDriver driver = new ChromeDriver();
	        driver.get(url);
	        
	        // Verify page title
	        System.out.println(driver.getTitle());
	        String actualResult = driver.getTitle();
	        String expectedResult = "noKodr";
	        Assert.assertEquals(actualResult, expectedResult);

	        // Perform the forget password test
	        WebElement link = driver.findElement(By.xpath("(//a[normalize-space()='Forgot Password?'])[1]"));
	        link.click();
	        System.out.println(driver.getTitle());
	        System.out.println(link.getText());

	        WebElement emailButton = driver.findElement(By.xpath("(//a[@title='Email'])[2]"));
	        emailButton.click();
	        Thread.sleep(1000);
	        
	        WebElement insertEmail = driver.findElement(By.cssSelector("section[role='dialog'] div div div div input[name='username']"));
	        insertEmail.sendKeys("ross@mailinator.com");

	        WebElement proceedButton = driver.findElement(By.xpath("(//button[@name='undefined'])[4]"));
	        proceedButton.click();

	        WebElement verification = driver.findElement(By.xpath("(//div[@role='status'])[1]"));
	        String verificationText = verification.getText();
	        System.out.println(verificationText);

	        String expectedVerification = "Loading";
	        Assert.assertEquals(verificationText, expectedVerification);

	        // Refresh page and verify cancel button
	        Thread.sleep(1000);
	        driver.navigate().refresh();
	        Thread.sleep(1000);

	        WebElement link1 = driver.findElement(By.xpath("(//a[normalize-space()='Forgot Password?'])[1]"));
	        link1.click();
	        Thread.sleep(1000);
	        WebElement cancelButton = driver.findElement(By.xpath("//div[@title='Cancel']"));
	        cancelButton.click();

	        // Close the browser
	        driver.quit();
	    }
	}


