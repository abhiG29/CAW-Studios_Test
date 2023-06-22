package StepDefinition;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class Generic_test_steps {

	WebDriver driver = null;
	
	
	@Given("user lands on the url of the provided website")
	public void user_lands_on_the_url_of_the_provided_website() throws InterruptedException {
	    
		System.getProperty("webdriver.chrome.driver","C:\\Users\\kumar\\eclipse-workspace\\Abhishek_Selenium\\Selenium_BDD\\src\\test\\resources\\Drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		
		
		ArrayList<Person>  input= new ArrayList<Person>();
		input.add(new Person("Bob",20,"Male"));
		input.add(new Person("George",42,"Male"));
		input.add(new Person("Sarah",30,"Female"));
		input.add(new Person("James",40,"Male"));
		input.add(new Person("Bill",50,"Male"));
		  
	 
		  String ans="[";
		  for(int i=0;i<input.size();i++) {
			  String temp=send(input.get(i));
			  ans+=temp;
			  if(i!=input.size()-1)
			  ans+=" , ";
		  }
		  ans+=']';
		
		
		
		
		driver.manage().window().maximize();
		

		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()=\"Table Data\"]")).click();
		Thread.sleep(2000);
		
		
		WebElement  textbox= driver.findElement((By.id("jsondata")));
		textbox.clear();
		
		textbox.sendKeys(ans);
		
		Thread.sleep(5000);
		driver.findElement(By.id("refreshtable")).click();
		
		
		
	  ArrayList<Person> actual= new ArrayList<Person>();
		  for(int i=2;i<=input.size()+1;i++) {
			  
			  Person temp=new Person();
			  String[] t_string= driver.findElement(By.xpath("//*[@id=\"dynamictable\"]/tr["+i+"]")).getText().trim().split(" ");
			  temp.setGender(t_string[0]);
			  temp.setName(t_string[1]);
			  temp.setAge(Integer.parseInt(t_string[2]));
			 actual.add(temp);
		  }
	    
		 
		  

	
		for(int i=0;i<input.size();i++)	{
			assertEquals(input.get(i).getName(),actual.get(i).getName());
			assertEquals(input.get(i).getAge(),actual.get(i).getAge());
			assertEquals(input.get(i).getGender(),actual.get(i).getGender());
		}
		
		System.out.println("COMPLETE");
		Thread.sleep(6000);
		driver.close();
		
	}
	
	private static String send(Person person) {
		
		  JSONObject file = new JSONObject();
		  String ans="";
		  
		  file.put("name", person.getName());
	        file.put("age", person.getAge());
	        file.put("gender", person.getGender());
	        return (file.toString());
	 
	  
}
	
	
	@When("user performs the actions required")
	public void user_performs_the_actions_required() {
	    System.out.println("step 2nd");
	}
	@Then("the application should function as expected")
	public void the_application_should_function_as_expected() {
	    System.out.println("step 3rd");
	}

	
}
