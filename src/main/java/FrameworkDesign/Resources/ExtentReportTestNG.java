package FrameworkDesign.Resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	
	public static ExtentReports getReportObject() {
		// ExtentReports , ExtentSparkReporter
	String path = 	"C:\\Users\\Sankeerthana M\\OneDrive\\eclipse-workspace\\SeleniumFrameworkDesign\\reports" + "index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	
	ExtentReports extent= new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Shiva Kumar");
	
	return extent;
	}
	
	
	
	
	
}
