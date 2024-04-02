package testng.upgrade.lib.ui.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.Duration;

public abstract class Page extends Component implements IPage {
	protected static Log log = LogFactory.getLog(Page.class);

	public Page() throws Exception {
		super();
		//get the current window that is in focus
		this.currentWindowHandle = this.driver.getWindowHandle();
	}
	
	private String currentWindowHandle;
	public void setCurrentWindowHandle(String currentWindowHandle) {
		this.currentWindowHandle = currentWindowHandle;
	}
	public String getCurrentWindowHandle() {
		return this.currentWindowHandle;
	}
	
	protected String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.getClass().getSimpleName();
	}
	public void printName() {
		log.info("Loading the page: '" + name + "'...");
	}
	
	public void goToUrl(String url){
		driver.navigate().to(url);
	}
	
	protected String expectedUrl;
	public String getExpectedUrl(){
		return this.expectedUrl;
	}
	public void setExpectedUrl(String expectedUrl){
		this.expectedUrl = expectedUrl;
	}
	
	protected Form form;
	public void setForm(Form form) throws Exception {
		this.form = form;
	}
	public Form getForm() throws Exception {
		return this.form;
	}
	
	protected Component component;
	public void setComponent(Component component) throws Exception {
		this.component = component;
	}
	public Component getComponent() throws Exception {
		return this.component;
	}
	
	/**
	 * wait for page to load
	 * @param timeout
	 * @throws Exception
	 */
	public void waitForPageToLoad(long timeout) throws Exception {
		String readyState = "";
		try {
			ExpectedCondition<String> expectation = new ExpectedCondition<String>() {
				public String apply(WebDriver webdriver) {
					Object state = true;
					try {
						state = ((JavascriptExecutor) webdriver).executeScript("return document.readyState;");
					} catch (NoSuchWindowException ex) {
						Object[] windowHandles = driver.getWindowHandles().toArray();
						String windowHandle = (String) windowHandles[windowHandles.length-1];
						driver.switchTo().window(windowHandle);
					}
					boolean complete = state.equals("complete");
					boolean loaded = state.equals("loaded");
					return (String) state;
				}
			};
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			readyState = wait.until(expectation);
		} catch (TimeoutException e) {
			if (!readyState.equals("interactive")) {
				throw new Exception("waitForPageToLoad failed.", e);
			}
		} catch (WebDriverException e) {
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
			}
			readyState = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState;");
			boolean complete = readyState.equals("complete");
			boolean loaded = readyState.equals("loaded");
			if (!(complete || loaded)) {
				throw new Exception("waitForPageToLoad failed.", e);
			}
		}
	}
	
	public void waitForPageToLoad() throws Exception {
		waitForPageToLoad(this.testConfiguration.getPageLoadTimeOut());
	}
	
	/**
	 * verify current URL
	 * @throws Exception
	 */
	public void verifyCurrentUrl() throws Exception {
		String actualUrl = "";
		int max = 20;
		for (int i = 0; i <= max; i++) {
			actualUrl = this.driver.getCurrentUrl();
			log.info("verifying the actual url " + actualUrl + " contains the expected url: " + this.expectedUrl);
			if (actualUrl.toLowerCase().contains(this.expectedUrl.toLowerCase())) {
				this.waitForPageToLoad(5000);
				i = max;
			} else {
				Thread.sleep(2000);
				this.waitForPageToLoad(2000);
			}
		}
		assertThat("the actual url " + actualUrl + " contains the expected url: " + this.expectedUrl,
				actualUrl.toLowerCase().contains(this.expectedUrl.toLowerCase()), is(true));
		log.info("verifyied the actual url " + actualUrl + " contains the expected url: " + this.expectedUrl);
	}
}
