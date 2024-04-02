package testng.upgrade.lib.ui.personalinfologin;

import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Form;
import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.offerpage.OfferPage;

public class PersonalInformationLoginForm extends Form {
	protected static Log log = LogFactory.getLog(PersonalInformationLoginForm.class);
	
	public PersonalInformationLoginForm() throws Exception {
		this.bySubmitButton = By.cssSelector("button[type='submit']");
	}
	
	@Override
	public PersonalInformationLoginData getData(){
		return (PersonalInformationLoginData) super.getData();
	}
	
	private By byEmailText = By.cssSelector("[name='username']");
	protected WebElement emailText;
	
	private By byPasswordText = By.cssSelector("[name='password']");
	protected WebElement passwordText;
	
	private By byTermsOfUseCheckbox = By.cssSelector("input[name='agreements']+div");
	protected WebElement termsOfUseCheckbox;
	
	@Override
	public void initElements() throws Exception {
		try {
			this.submitButton = waitUntilVisible(bySubmitButton);
			this.emailText = waitUntilVisible(this.byEmailText);
			this.passwordText = waitUntilVisible(this.byPasswordText);
			this.termsOfUseCheckbox = driver.findElement(this.byTermsOfUseCheckbox);
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
			if (termsOfUseCheckbox.getAttribute("aria-checked").equalsIgnoreCase("true")) {
				termsOfUseCheckbox.click();
			}
		} catch(Exception e) {
			log.info("inputFormFields() exception: " + e.getMessage());
		}
	}

	private String randomEmail(final String prefix, final String suffix) {
		long seed = System.currentTimeMillis();
		Random random = new Random(seed);
		// Obtain a number between [0 - 1000].
		int num = random.nextInt(1000);
		return prefix + String.valueOf(num) + suffix;
	}
	
	@Override
	public void inputFormFields() throws Exception {
		try {
			initElements();
			final String email = randomEmail(getData().getEmailPrefix(), getData().getEmailSuffix());
			log.info(String.format("email: %s; password: %s \n", email, getData().getPassword()));
			emailText.sendKeys(email);
			this.getData().setEmail(email); //local data object
			((PersonalInformationLoginPage) this.getParent()).getData().setEmail(email); //parent data object
			passwordText.sendKeys(getData().getPassword());
			termsOfUseCheckbox.click();
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
			returnPage = (PersonalInformationLoginPage) this.getParent();
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
