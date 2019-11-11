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
	public void login_to_chat() {
		this.driver.get("http://localhost:8080");
		WebElement inputElement = this.driver.findElement(By.id("username"));
		WebElement submitElement = this.driver.findElement(By.className("submit"));
		inputElement.sendKeys("foo");
		submitElement.click();
		Assert.assertNotNull(inputElement);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		inputElement = this.driver.findElement(By.id("username"));
		Assert.assertEquals(inputElement.getText(),"foo");
		Assert.assertEquals(this.driver.getTitle(), "Chat Room");

	}
}
