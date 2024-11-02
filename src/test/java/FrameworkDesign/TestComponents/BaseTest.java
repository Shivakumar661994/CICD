package FrameworkDesign.TestComponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameworkDesign.PageObejectModel.LandingPage;
public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		//properties class for reading global data file
	
	Properties props = new Properties();
//	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\FrameworkDesign\\Resources");
	FileInputStream fis1 = new FileInputStream("C:\\Users\\Sankeerthana M\\OneDrive\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\FrameworkDesign\\Resources\\GlobalData.properties");
	props.load(fis1);
	//Ternory Operator 
//	String broswerName =System.getProperty("browser") !=null ? System.getProperty("browser") : props.getProperty("browser");;
//	normal way
	String broswerName = props.getProperty("browser");
	
	if(broswerName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		//read json to string use FileUtiles package
String jsonContent =		FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);

		
//convert string to HashMap use Jaskson databind dependency 
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data =	mapper.readValue(jsonContent, new TypeReference
	<List<HashMap<String,String>>>() {});
	return data;
	//{map,map} returning
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
	File source = 	ts.getScreenshotAs(OutputType.FILE);
//	setting file path
	File file =new File("C:\\Users\\Sankeerthana M\\OneDrive\\eclipse-workspace\\SeleniumFrameworkDesign" + ".png");
	FileUtils.copyFile(source, file);
	return "C:\\Users\\Sankeerthana M\\OneDrive\\eclipse-workspace\\SeleniumFrameworkDesign" + ".png";
	}
	
	
	@BeforeMethod	(alwaysRun =true) //this method will run regardless of grouping
	public LandingPage launchApplication() throws IOException {
		
		driver= initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod (alwaysRun =true)  //this method will run regardless of grouping
	public void tearDown() {
		driver.quit();
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}