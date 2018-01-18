package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Loginpage extends Elementsactions {
WebDriver driver; 
	
	
	public By username =By.name("txtUID");
	
	public By password=By.name("txtUPass");
	
	public By signin=By.xpath("//input[@type='button'and @value='login']");
			
	public Loginpage(WebDriver driver){
		super(driver);
		this.driver=driver;
		
	}
	
	public void Login() throws Exception{
		System.out.println(driver.getTitle());
		Exceldata ex=new Exceldata(driver);
		String arr[]=ex.ExcelDataread(5);
		 driver.get(arr[0]);
		Elementclick(username,arr[1]);
		Elementclick(password,arr[2]);
		Eleclick(signin);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt=driver.switchTo().alert();
		alt.accept();
		
		Thread.sleep(700);
		String win1="";
		for(String handle:driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			try{
			driver.switchTo().frame("frameCalender");
			win1=handle;
			}
			catch(Exception e)
			{
				driver.close();
			}
			
		}
		driver.switchTo().window(win1);
		System.out.println("title ="+driver.getTitle());
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frameCalender");
		System.out.println("testframe");
	}
}
