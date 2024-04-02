package testng.upgrade;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestConfiguration {
	/* many parameters are not included in this example:
	grid and cloud configuration, build, i18n, i10n, A11y, device form factors, etc.
	*/
	private String testName;
	private String platformName;
	private String platformVersion;
	private String browserName;
	private String browserVersion;
	private String environmentName;
	private String webDriverDirectory;
	private long implicitWait;
	private long pageLoadTimeOut;
	private long javaScriptTimeOut;
	private String appUrl;
	private String apiUrl;
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	private String firefoxBinary;
	private String chromeBinary;

	public TestConfiguration() {
		
	}
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	
	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	public String getWebDriverDirectory() {
		return webDriverDirectory;
	}
	public void setWebDriverDirectory(String webDriverDirectory) {
		this.webDriverDirectory = webDriverDirectory;
	}

	public long getPageLoadTimeOut() {
		return pageLoadTimeOut;
	}

	public void setPageLoadTimeOut(long pageLoadTimeOut) {
		this.pageLoadTimeOut = pageLoadTimeOut;
	}

	public long getImplicitWait() {
		return implicitWait;
	}

	public void setImplicitWait(long implicitWait) {
		this.implicitWait = implicitWait;
	}
	
	public long getJavaScriptTimeOut() {
		return javaScriptTimeOut;
	}

	public void setJavaScriptTimeOut(long javaScriptTimeOut) {
		this.javaScriptTimeOut = javaScriptTimeOut;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public ChromeOptions getChromeOptions() {
		return chromeOptions;
	}

	public void setChromeOptions(ChromeOptions chromeOptions) {
		this.chromeOptions = chromeOptions;
	}

	public FirefoxOptions getFirefoxOptions() {
		return firefoxOptions;
	}

	public void setFirefoxOptions(FirefoxOptions firefoxOptions) {
		this.firefoxOptions = firefoxOptions;
	}

	public String getFirefoxBinary() {
		return firefoxBinary;
	}

	public void setFirefoxBinary(String firefoxBinary) {
		this.firefoxBinary = firefoxBinary;
	}

	public String getChromeBinary() {
		return chromeBinary;
	}

	public void setChromeBinary(String chromeBinary) {
		this.chromeBinary = chromeBinary;
	}
}
