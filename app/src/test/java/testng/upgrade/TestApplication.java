package testng.upgrade;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestApplication implements ITestApplication {
	private TestConfiguration testConfiguration;
	protected WebDriver driver;
	
	public TestApplication(TestConfiguration testConfiguration){
		System.out.println("TestApplication");
		this.testConfiguration = testConfiguration;
	}

	@Override
	public void setTestConfiguration(TestConfiguration testConfiguration) {
		this.testConfiguration = testConfiguration;
	}

	@Override
	public TestConfiguration getTestConfiguration() {
		return testConfiguration;
	}

	@Override
	public WebDriver getWebDriver() {
		return this.driver;
	}

	@Override
	public void setUp() throws Exception {
		//if local workstation, used for this example
    	if (testConfiguration.getBrowserName().equalsIgnoreCase("chrome")) {
    		System.setProperty("webdriver.chrome.driver", testConfiguration.getWebDriverDirectory());
			String[] switches = {"--ignore-certificate-errors"};
			ChromeOptions options = new ChromeOptions();
		    options.addArguments(switches);
    	    options.setBinary(testConfiguration.getChromeBinary());
		    testConfiguration.setChromeOptions(options);
    	} else if (testConfiguration.getBrowserName().equalsIgnoreCase("firefox")) {
    	    FirefoxProfile profile = new FirefoxProfile();
    	    profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);
            
    	    FirefoxOptions options = new FirefoxOptions();
    	    options.setProfile(profile);
    	    options.setBinary(testConfiguration.getFirefoxBinary());
    	    testConfiguration.setFirefoxOptions(options);
    	}
		/*
		else if internal grid or farm
		...
		else if cloud based grid
		...
		not included for this example...
		*/
		//intialize WebDriver when starting the test...
	}

	@Override
	public void tearDown() throws Exception {
		System.out.println("tearing down app...");
		if (this.driver != null) {
	    	try {
	    		this.driver.close();
	    	} catch(Exception e) {}
	    	try {
				this.driver.quit();
	    	} catch(Exception e) {}
		}
    	System.out.println("tore down app.");
	}

	@Override
	public void setTestName(String testName) {
		testConfiguration.setTestName(testName);
	}
	@Override
	public String getTestName() {
		return testConfiguration.getTestName();
	}

	@Override
	public void loadWebPage(final String appPath) throws Exception {
		//if local workstation, used for this example
		if (testConfiguration.getBrowserName().equalsIgnoreCase("chrome")) {
		    this.driver = (WebDriver) new ChromeDriver(testConfiguration.getChromeOptions());
    	} else if (testConfiguration.getBrowserName().equalsIgnoreCase("firefox")) {
		    this.driver = (WebDriver) new FirefoxDriver(testConfiguration.getFirefoxOptions());
    	}
		
		//configure the driver
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testConfiguration.getImplicitWait()));
		this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(testConfiguration.getPageLoadTimeOut()));
		this.driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(testConfiguration.getJavaScriptTimeOut()));
		this.driver.manage().window().maximize();
		
		//launch the browser and get the URL
		this.driver.get(testConfiguration.getAppUrl() + appPath);
	}
	
	@Override
	public void loadWebPage() throws Exception {
		loadWebPage("");
	}
}
