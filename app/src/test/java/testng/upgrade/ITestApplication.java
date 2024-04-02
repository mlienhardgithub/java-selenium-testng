package testng.upgrade;

import org.openqa.selenium.WebDriver;

public interface ITestApplication {
	void setTestConfiguration(TestConfiguration testConfiguration);
	TestConfiguration getTestConfiguration();
	WebDriver getWebDriver();
	void setUp() throws Exception;
	void tearDown() throws Exception;
	void setTestName(String testName);
	String getTestName();
	void loadWebPage(String appPath) throws Exception;
	void loadWebPage() throws Exception;
}
