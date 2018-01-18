package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Exceldata {
WebDriver driver;
	public Exceldata(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}

	public String[] ExcelDataread(int y1) throws InterruptedException, Exception{
		System.out.println("1");
		 String[] dd=new String[10];
		 
		File f=new File("D:\\Seleniumworkspace\\CTBWillpay\\billpay.xlsx");//please provide the excel path here-------------->
		 FileInputStream objfile = new FileInputStream(f);
		 XSSFWorkbook wb= new XSSFWorkbook(objfile);
		 XSSFSheet xs =wb.getSheet("Sheet1");
		 System.out.println(xs.getLastRowNum());
		 for(int i=1;i<=xs.getLastRowNum();i=i+4){
			 Row r=xs.getRow(i);
			 Cell cp=r.getCell(3);
			 String ty=cp.getStringCellValue();
			 System.out.println("ty=="+ty);
			 if(ty.equalsIgnoreCase("Y")){
				 Cell cp1=r.getCell(0);
				 String ty1=cp1.getStringCellValue();
				 Cell cp2=r.getCell(1);
				 String ty2=cp2.getStringCellValue();
				 Cell cp3=r.getCell(2);
				 String ty3=cp3.getStringCellValue();
				 System.out.println("ty1=="+ty1+"ty2==="+ty2+"ty3=="+ty3);
				 dd[0]=ty1;
				 dd[1]=ty2;
				 dd[2]=ty3;
				if(y1!=5){
				 int yu=i+y1;
				 System.out.println("i="+yu);
				 Row r1=xs.getRow(yu);
				 Cell c1=r1.getCell(0);
				 Date s1=c1.getDateCellValue();
				 Cell c2=r1.getCell(2);
				 int s2=(int) c2.getNumericCellValue();
				 Cell c3=r1.getCell(3);
				 String sourcecity=c3.getStringCellValue();
				 SimpleDateFormat dt1 = new SimpleDateFormat("dd");
				 String D=dt1.format(s1);
				 System.out.println("day"+D);//starting day of the jounary 
				 SimpleDateFormat dt2 = new SimpleDateFormat("MM");
				 String M=dt2.format(s1);//starting month of the jounary
				 SimpleDateFormat dt3 = new SimpleDateFormat("YYYY");
				 String y=dt3.format(s1);
				 dd[3]=D;
				 dd[4]=M;
				 dd[5]=y;
				 dd[6]=String.valueOf(s2);
				 dd[7]=sourcecity;
				}
				else
				{
					dd[3]="";
					 dd[4]="";
					 dd[5]="";
					 dd[6]="";
					 dd[7]="";
				}
				 		 
			 }
		 }
		 
			
		 if(y1!=5){
			 Thread.sleep(5000);
			 	 driver.switchTo().defaultContent();
		 List<WebElement> l=driver.findElements(By.tagName("frame"));
		 System.out.println("size"+l.size());
			driver.switchTo().frame("frameCalender");
		 String year1=driver.findElement(By.name("txtYear")).getAttribute("value");
		 String Month1=driver.findElement(By.name("txtMonth")).getAttribute("value");
		
		if(Month1.length()<=1)
		{
			Month1="0"+Month1;
			
		}
		
		  for(;;){
			  
	year1=driver.findElement(By.name("txtYear")).getAttribute("value");
		 Month1=driver.findElement(By.name("txtMonth")).getAttribute("value");
		 if(Month1.length()<=1)
			{
			
				Month1="0"+Month1;
			}
		 if(year1.equals(dd[5])&&Month1.equals(dd[4]))
		 {
	System.out.println("DAter");

			 break;
		 }
		 else{
			
			 driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr/td[6]/img")).click();
		 }
		 }
		 }
		  return dd;
		 }
	
	public void DataWrite(int id,List storedata) throws Exception{
		File f=new File("D:\\Seleniumworkspace\\CTBWillpay\\ExceldataWrite.xlsx");
		FileInputStream fin=new FileInputStream(f);
		
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		
		XSSFSheet s=wb.getSheet("Sheet1");
		
		XSSFRow row=s.createRow(id);
		
		for(int i=0;i<storedata.size();i++){
			Cell c=row.createCell(i);
			
			String v=(String) storedata.get(i);
			c.setCellValue(v);
			
		
		}
		FileOutputStream fout=new FileOutputStream(f);
		wb.write(fout);
		
		fout.close();
		
	}
}
