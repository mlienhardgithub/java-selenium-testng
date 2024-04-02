package testng.upgrade;

import org.openqa.selenium.WebDriver;

public class TestThreadOld {
	public TestThreadOld() {
		System.out.println("TestThread constructor");
	}
	
	private static final ThreadLocal<WebDriver> WEBDRIVER_THREAD = new ThreadLocal<WebDriver>();
	
	public static void setInstance(WebDriver webDriver) throws Exception {
		WEBDRIVER_THREAD.set(webDriver);
	}
	
	public static WebDriver getInstance() {
		return WEBDRIVER_THREAD.get();
	}
	
	public static void removeInstance() {
		WebDriver webDriver = getInstance();
		webDriver = null;
		WEBDRIVER_THREAD.set(null);
		WEBDRIVER_THREAD.remove();
	}

}
