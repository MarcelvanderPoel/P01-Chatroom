package edu.udacity.java.nano.chatroomstarter;

import edu.udacity.java.nano.chatroomstarter.BaseSeleniumTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebSocketChatApplicationTests extends BaseSeleniumTests {
	@Test
	public void login() {
		this.driver.get("http://localhost:8080");
		WebElement inputElement = this.driver.findElement(By.id("username"));
		WebElement submitElement = this.driver.findElement(By.className("submit"));
		inputElement.sendKeys("foo");
		submitElement.click();
		Assert.assertNotNull(inputElement);

		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		WebElement webElement = this.driver.findElement(By.id("username"));
		Assert.assertEquals(webElement.getText(),"foo");
		Assert.assertEquals(this.driver.getTitle(), "Chat Room");
	}

	@Test
	public void user_join() throws InterruptedException {
		this.driver.get("http://localhost:8080");
		WebElement inputElement = this.driver.findElement(By.id("username"));
		WebElement submitElement = this.driver.findElement(By.className("submit"));
		inputElement.sendKeys("foo");
		submitElement.click();
		Assert.assertNotNull(inputElement);

		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		Thread.sleep(1000);

		WebElement webElement = this.driver.findElement(By.className("message-container"));
		Assert.assertEquals(webElement.getText(),"New person entering");
		Assert.assertEquals(this.driver.getTitle(), "Chat Room");
	}

	@Test
	public void chat() throws InterruptedException {
		this.driver.get("http://localhost:8080");
		WebElement inputElement = this.driver.findElement(By.id("username"));
		WebElement submitElement = this.driver.findElement(By.className("submit"));
		inputElement.sendKeys("foo");
		submitElement.click();

		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		inputElement = this.driver.findElement(By.id("msg"));
		submitElement = this.driver.findElement(By.className("send-button"));
		inputElement.sendKeys("New message");
		submitElement.click();

		Thread.sleep(1000);

		WebElement webElement = this.driver.findElement(By.className("message-container"));
		Assert.assertEquals(webElement.getText().substring(24),"New message");
	}

	@Test
	public void leave() throws InterruptedException {
		this.driver.get("http://localhost:8080");
		WebElement inputElement = this.driver.findElement(By.id("username"));
		WebElement submitElement = this.driver.findElement(By.className("submit"));
		inputElement.sendKeys("foo");
		submitElement.click();

		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		submitElement = this.driver.findElement(By.className("leave-button"));
		submitElement.click();

		Thread.sleep(1000);

		winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		Assert.assertEquals(this.driver.getTitle(), "Chat Room Login");
	}

}