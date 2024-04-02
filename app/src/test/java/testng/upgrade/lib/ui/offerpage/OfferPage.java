package testng.upgrade.lib.ui.offerpage;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.login.LoginPage;

public class OfferPage extends Page {
	protected static Log log = LogFactory.getLog(OfferPage.class);

	public OfferPage() throws Exception {
		super();
		this.name = this.getClass().getSimpleName();
		this.printName();
		this.expectedUrl = "/offer-page";
		this.verifyCurrentUrl();
		initElements();
	}
	
	@Override
	public void initElements() throws Exception {
		this.component = new OfferContent();
		this.component.setParent(this);
	}
	
	public Page signout() throws Exception {
		driver.get(testConfiguration.getAppUrl() + "/portal/logout");
		
		Page returnPage = null;
		try {
			returnPage = new LoginPage();
		} catch(Exception e) {
			log.info("signout() exception: " + e.getMessage());
			throw new Exception(e);
		};
		return returnPage;
	}
	
	public void loadLoanPayoffData() throws Exception {
		((OfferContent) this.component).loadLoanPayoffData();
	}
	
	public Map<String, Object> getLoanPayoffData() {
		return ((OfferContent) this.component).getLoanPayoffData();
	}
}
