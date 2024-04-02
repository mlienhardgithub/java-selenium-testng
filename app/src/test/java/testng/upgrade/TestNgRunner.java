package testng.upgrade;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;

import testng.upgrade.TestThread;
import testng.upgrade.utils.Utilities;


public class TestNgRunner {
	protected WebDriver driver;
	
	public TestNgRunner() {
		System.out.println("TestNgRunner");
	}
	
    @BeforeClass
    public void beforeClass() {
    	System.out.println("beforeClass");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, Method method) throws Exception {
		System.out.println("setting up before method...: " + getClass().getName() + "." + method.getName());
		
		XmlTest xmlTest = context.getCurrentXmlTest();
		Map<String, String> map = xmlTest.getAllParameters();
		System.out.printf("map size: %d \n", map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
		}
		
		System.out.println("platformName: " + context.getCurrentXmlTest().getParameter("platformName"));

		final TestConfiguration testConfiguration = new TestConfiguration();
		testConfiguration.setTestName(getClass().getName() + "." + method.getName());
		testConfiguration.setPlatformName(context.getCurrentXmlTest().getParameter("platformName"));
		testConfiguration.setPlatformVersion(context.getCurrentXmlTest().getParameter("platformVersion"));
		testConfiguration.setBrowserName(context.getCurrentXmlTest().getParameter("browserName"));
		testConfiguration.setBrowserVersion(context.getCurrentXmlTest().getParameter("browserVersion"));
		testConfiguration.setEnvironmentName(context.getCurrentXmlTest().getParameter("environmentName"));
		
		final Properties configProps = Utilities.loadConfigProperties();
		String appUrl = configProps.getProperty("app.url.test");
		testConfiguration.setAppUrl(appUrl);
		String apiUrl = configProps.getProperty("api.url.test");
		testConfiguration.setApiUrl(apiUrl);
		String javaScriptTimeOut = configProps.getProperty("javascript.time.out");
		testConfiguration.setJavaScriptTimeOut(Long.parseLong(javaScriptTimeOut));
		String pageLoadTimeOut = configProps.getProperty("page.load.time.out");
		testConfiguration.setPageLoadTimeOut(Long.parseLong(pageLoadTimeOut));
		String implicitWait = configProps.getProperty("implicit.wait");
		testConfiguration.setImplicitWait(Long.parseLong(implicitWait));
		String webDriverDirectory = configProps.getProperty("webdriver.directory");
		testConfiguration.setWebDriverDirectory(webDriverDirectory);
		String chromeBinary = configProps.getProperty("chrome.binary");
		testConfiguration.setChromeBinary(chromeBinary);
		String firefoxBinary = configProps.getProperty("firefox.binary");
		testConfiguration.setFirefoxBinary(firefoxBinary);
		
		new TestThread().setUpThread(testConfiguration);
		System.out.println("set up before method.");
    }
    
    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method) throws Exception {
    	System.out.println("tearing down after method...: " + getClass().getName() + "." + method.getName());		
		try{
			TestThread.getTestThread().getWebDriver().manage().deleteAllCookies();
		} catch (Exception e){
			//do nothing
		}
		TestThread.tearDownThread();
		System.out.println("tore down after method.");
    }

    @AfterClass
    public void afterClass() {
    	System.out.println("tearing down after class...");
		TestThread.removeTestThread();
		System.out.println("tore down after class.");
    }
}
