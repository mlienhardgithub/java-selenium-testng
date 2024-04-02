package testng.upgrade.lib.ui.login;

import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Form;
import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.offerpage.OfferPage;
import testng.upgrade.lib.ui.personalinfologin.PersonalInformationLoginData;

public class LoginForm extends Form {
	protected static Log log = LogFactory.getLog(LoginForm.class);
	
	public LoginForm() throws Exception {
		this.bySubmitButton = By.cssSelector("button[data-auto='login'][type='submit']");
	}
	
	@Override
	public PersonalInformationLoginData getData(){
		return (PersonalInformationLoginData) super.getData();
	}
	
	private By byEmailText = By.cssSelector("[name='username']");
	protected WebElement emailText;
	
	private By byPasswordText = By.cssSelector("[name='password']");
	protected WebElement passwordText;
	
	@Override
	public void initElements() throws Exception {
		try {
			this.submitButton = waitUntilVisible(bySubmitButton);
			this.emailText = waitUntilVisible(this.byEmailText);
			this.passwordText = waitUntilVisible(this.byPasswordText);
		} catch(Exception e) {
			log.info("initElements() exception: " + e.getMessage());
			System.out.println(this.driver.getPageSource());
			throw new Exception(e);
		}
	}

	@Override
	public void clearForm() throws Exception {
		try {
			initElements();
			emailText.clear();
			passwordText.clear();
		} catch(Exception e) {
			log.info("inputFormFields() exception: " + e.getMessage());
		}
	}
	
	@Override
	public void inputFormFields() throws Exception {
		try {
			initElements();
			emailText.sendKeys(getData().getEmail());
			passwordText.sendKeys(getData().getPassword());
		} catch(Exception e) {
			log.info("inputFormFields() exception: " + e.getMessage());
			System.out.println(this.driver.getPageSource());
			throw new Exception(e);
		}
	}

	@Override
	public Page returnCancelPage() throws Exception {
		Page returnPage = null;
		try {
			returnPage = (LoginPage) this.getParent();
		} catch(Exception e) {
			log.info("returnCancelPage() exception: " + e.getMessage());
		};
		return returnPage;
	}

	@Override
	public Page returnSubmitPage() throws Exception {
		Page returnPage = null;
		try {
			returnPage = new OfferPage();
		} catch(Exception e) {
			log.info("returnSubmitPage() exception: " + e.getMessage());
		};
		return returnPage;
	}
}
