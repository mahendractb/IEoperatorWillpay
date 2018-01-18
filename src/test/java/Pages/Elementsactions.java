package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elementsactions {
WebDriver driver;


public Elementsactions(WebDriver driver){
	this.driver=driver;
}
	public void Elementclick(By elename,String value){
		driver.findElement(elename).sendKeys(value);
	}
	
	public void Eleclick(By elename){
		driver.findElement(elename).click();
	}
}
