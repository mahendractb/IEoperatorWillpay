package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class Bookingpage {
WebDriver driver;
	public Bookingpage(WebDriver driver){
		this.driver=driver;
	}
	
	public void bookTickets() throws Exception, Exception{
		Exceldata ex=new Exceldata(driver);
		String arr[]=ex.ExcelDataread(1);
		int s=Integer.parseInt(arr[3]);
		  int enddate=Integer.parseInt(arr[6]);
		  String city=arr[7];
		  selectDate(s,enddate,city);
	}
	
	
	public void selectDate(int s, int enddate, String city) throws Exception{
		
		 int f1=0,count=0,Excelcity=0,excelstorecount=0;
			
			List<WebElement> datecount = driver.findElements(By.xpath(".//input[starts-with(@id,'idCal')]"));//date list
			while(count<enddate)
			{
			for(WebElement d:datecount)	
					{
						driver.switchTo().defaultContent();
						driver.switchTo().frame("frameCalender");
						
						int t=Integer.parseInt(d.getAttribute("value"));
				
						if(t==s){
							f1=f1+1;
						}
					
					if(d.isEnabled()&&count<enddate&&f1>0){
					
					d.click();
	d.click();		
					count++;
	}
	else
	{
	continue;
	}
					
					int in=0;
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameInfo");
			String JoDate=driver.findElement(By.name("txtSDate")).getAttribute("value");
			Select counter =new Select(driver.findElement(By.className("InputBox")));
			List<WebElement> l =counter.getOptions();//Source cities names are storing in this list
			System.out.println("Source Size:"+l.size());
			String first=l.get(0).getText();
			String last=l.get(l.size()-1).getText();
			System.out.println("Testtttt===="+l.get(0).getText()+"opentext"+l.get(l.size()-1).getText());
			int m=0;
			for(WebElement sou:l)
			{
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("frameInfo");
				//Source city select from excel
				if(Excelcity==0){
				if(city.equalsIgnoreCase("no")){
					System.out.println("No source citry was enter in excel ");
				}
				else
				{
					for(int index=0;index<l.size();index++){
						if((l.get(index).getText().equalsIgnoreCase(city))&&Excelcity==0){
							in=index;
							Excelcity++;
							break;
						}
					}
				}
				}
				counter.selectByIndex(in);
				in++;
				
				String Source=sou.getText();
				//System.out.println("Source: "+sou.getText());
				driver.switchTo().defaultContent();
				driver.switchTo().frame("frameDestination");
			String tr=" ";
				try{
					tr=driver.findElement(By.xpath(".//form[@name='form1']/table/tbody/tr[1]/td")).getText();
			}
				catch(Exception e){
					Thread.sleep(10000);
					tr=driver.findElement(By.xpath(".//form[@name='form1']/table/tbody/tr[1]/td")).getText();
				}
				
				
				List<WebElement> dest=driver.findElements(By.className("DestButton"));//destination cities are storing in this list
				int destCount=dest.size();
				int destin=0;
				
				if(destCount==0){//Destination is zero 

					continue;
				}
				
				
				int i=0,j,b,subdest=15;
				while(i<destCount) //destination cities are available
				{
					
					do{
						//destin=0;
					for(WebElement ds:dest)
					{
						
						driver.switchTo().defaultContent();
						driver.switchTo().frame("frameDestination");
						
						System.out.println("===============================================");
						//fw.write("\n=================================================");
						String Destination= ds.getAttribute("value");
						//System.out.println("Destination name:"+ds.getAttribute("value"));
						//fw.write("\nDestination name:"+ds.getAttribute("value"));
						
						ds.click();
						driver.switchTo().defaultContent();
						driver.switchTo().frame("frameTime");
						List<WebElement> time=driver.findElements(By.className("DestButton2"));
						int timeCount=time.size();
						destin++;
						j=0;
						while(j<timeCount)//timings 
						{
							b=1;
							for(WebElement ti:time)
							{
								excelstorecount++;
								driver.switchTo().defaultContent();
								driver.switchTo().frame("frameTime");
								ti.click();
								String Route=driver.findElement(By.name("txtRnam")).getAttribute("value");
								int r7=b%2==0?5:2;
								int r8=b%2==0?b-1:b;
							
								
								String r1="//table[@class='blacktext']//table[@class='blacktext']//tr["+r8+"]/td["+r7+"]";
								String r5=driver.findElement(By.xpath(r1)).getText();
								r5=r5.split("\\[")[1].split("\\]")[0];
								//System.out.println("r5==="+r5);
								b++;
	String btime=ti.getAttribute("value");
								
	driver.switchTo().defaultContent();
	driver.switchTo().frame("frameInfo");

	String price=driver.findElement(By.name("txtTTtl")).getAttribute("value");
								driver.switchTo().defaultContent();
								driver.switchTo().frame("frameSeat");

								List<WebElement> seat=driver.findElements(By.className("Button2"));	
								int size=seat.size();
								
								driver.switchTo().defaultContent();
								driver.switchTo().frame("framePickup");
								//Select pickup=new Select(driver.findElement(By.name("txtPick")));
								String pickup=driver.findElement(By.xpath(".//select[@name='txtPick']/option")).getText();
								String drop=driver.findElement(By.xpath(".//select[@name='txtDrop']/option")).getText();
								String operator="kbEkspres";
								//db.insertBookingDetails(operator, Source, Destination, JoDate,r5,Route,price,btime, size);
								Thread.sleep(500);
								driver.switchTo().defaultContent();
								driver.switchTo().frame("frameSeat");
								driver.findElement(By.name("BtnWayBill")).click();
								Thread.sleep(500);
								String win0=driver.getWindowHandle();
								String win1="";
								System.out.println("window size-------"+driver.getWindowHandles().size());
								for(String window:driver.getWindowHandles())
								{
									driver.switchTo().window(window);
									
									try
									{
										driver.findElement(By.xpath("//input[@value='Preview Summary'and @name='BtnPre']"));
										win1=window;
										break;
									}
									catch(Exception e){
										
									}
									
								}
								driver.switchTo().defaultContent();
								driver.switchTo().window(win1);
								driver.findElement(By.xpath("//input[@value='Preview Summary']")).click();
								driver.close();
								Thread.sleep(500);
								String win2="";
								System.out.println("window sizert-------"+driver.getWindowHandles().size());
								for(String window:driver.getWindowHandles())
								{
									driver.switchTo().window(window);
									try
									{
										driver.findElement(By.xpath("//table[@class='SmallBlacktext']/tbody/tr"));
										win2=window;
										break;
									}
									catch(Exception e){
										
									}
									
								}
								driver.switchTo().defaultContent();
								driver.switchTo().window(win2);
								List<String> storedata=new ArrayList<String>();
								storedata.add(JoDate);
								storedata.add(Source);
								storedata.add(Destination);
								storedata.add(btime);
								List<WebElement> values=driver.findElements(By.xpath("//table[@class='SmallBlacktext']/tbody/tr"));
								for(int table=0;table<values.size();table++){
									List<WebElement> tabletd=values.get(table).findElements(By.tagName("td"));
									storedata.add(tabletd.get(4).getText());
									storedata.add(tabletd.get(5).getText());
									System.out.println(tabletd.get(4).getText());
									System.out.println(tabletd.get(5).getText());
								}
				
								Exceldata ex=new Exceldata(driver);
								ex.DataWrite(excelstorecount,storedata);
								driver.close();
								driver.switchTo().window(win0);
								j++;
								
							}

							if(j%8==0)//when we have more buses and pagination is available 
							{
								driver.switchTo().defaultContent();
								driver.switchTo().frame("frameTime");
								List<WebElement> nxt=driver.findElements(By.className("pageButton"));
								if(nxt.size()==0)//checking pagination available or not
								{
									break;
								}
								else
								{
									nxt.get(nxt.size()-1).click();
								}

								time=driver.findElements(By.xpath("//input[@value=' >> ']"));
								timeCount=timeCount+time.size();
								//System.out.println("Time Count: "+timeCount);
								//System.out.println("no.of Destinations(updated):"+destCount);
							//fw.write("\nno.of Destinations(updated):"+destCount);
							}
							
						}

						
						i++;
					}
	System.out.println("destin"+destin+"destCount"+destCount);
					}while(destCount!=destin);
						if(i%15==0)//when we have more destinations and pagination is available
						{
							driver.switchTo().defaultContent();
							driver.switchTo().frame("frameDestination");
							List<WebElement> nxt=driver.findElements(By.xpath("//input[@value=' >> ']"));
							System.out.println("testsize---"+nxt.size());
							if(nxt.size()==0)//checking pagination available or not
							{
								break;
							}
							else
							{
								nxt.get(0).click();
							}
		
							dest=driver.findElements(By.className("DestButton"));
							destCount=destCount+dest.size();
							
							//System.out.println("no.of Destinations(updated):"+destCount);
							//fw.write("\nno.of Destinations(updated):"+destCount);
							subdest=subdest+15;
						}
						
					}//dest
					
					}
					
					}
				
			
					
					}
			if(count<enddate)
			{
				driver.switchTo().defaultContent();
				driver.switchTo().frame("frameCalender");
				driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr/td[6]/img")).click();
				 datecount = driver.findElements(By.xpath(".//input[starts-with(@id,'idCal')]"));
			}

			}
		
	
}
