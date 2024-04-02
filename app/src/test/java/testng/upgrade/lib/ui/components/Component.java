package testng.upgrade.lib.ui.components;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import testng.upgrade.TestConfiguration;
import testng.upgrade.TestThread;

public abstract class Component extends Data implements IComponent {
	protected TestConfiguration testConfiguration;
	//protected DriverWrapper driver; not implementing for this example
	protected WebDriver driver;
	
	public Component() {
		this.testConfiguration = TestThread.getTestThread().getTestConfiguration();
		this.driver = TestThread.getTestThread().getWebDriver();
	}
	
	protected IComponent parent;
	public void setParent(IComponent parent) {
		this.parent = parent;
	}
	public IComponent getParent() {
		return this.parent;
	}
	
	/**
	 * wait until element is visible
	 * @param by
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public WebElement waitUntilVisible(By by, long timeout) throws Exception {
		WebElement element;
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (TimeoutException e) {
			throw new Exception("waitUntilVisible failed by " + by.toString(), e);
		} catch (WebDriverException e) {
			throw new Exception("waitUntilVisible failed by " + by.toString(), e);
		}
		return element;
	}
	
	public WebElement waitUntilVisible(By by) throws Exception {
		return waitUntilVisible(by, 5);
	}
	
	/**
	 * wait until element list is visible
	 * @param by
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public List<WebElement> waitUntilListVisible(By by, long timeout) throws Exception {
		List<WebElement> elements;
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (TimeoutException e) {
			throw new Exception("waitUntilListVisible failed by " + by.toString(), e);
		} catch (WebDriverException e) {
			throw new Exception("waitUntilListVisible failed by " + by.toString(), e);
		}
		return elements;
	}
	public List<WebElement> waitUntilListVisible(By by) throws Exception {
		return waitUntilListVisible(by, 5);
	}
}
