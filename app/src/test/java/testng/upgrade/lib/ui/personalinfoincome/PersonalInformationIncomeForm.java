package testng.upgrade.lib.ui.personalinfoincome;

import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Form;
import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.personalinfobasic.PersonalInformationBasicPage;
import testng.upgrade.lib.ui.personalinfologin.PersonalInformationLoginPage;

public class PersonalInformationIncomeForm extends Form {
	protected static Log log = LogFactory.getLog(PersonalInformationIncomeForm.class);
	
	public PersonalInformationIncomeForm() throws Exception {
		this.bySubmitButton = By.cssSelector("[data-auto='continuePersonalInfo']");
	}
	
	@Override
	public PersonalInformationIncomeData getData(){
		return (PersonalInformationIncomeData) super.getData();
	}
	
	private By byIndividualIncomeText = By.cssSelector("[data-auto='borrowerIncome']");	
	protected WebElement individualIncomeText;
	
	private By byAdditionalIncomeText = By.cssSelector("[data-auto='borrowerAdditionalIncome']");	
	protected WebElement additionalIncomeText;
	
	@Override
	public void initElements() throws Exception {
		try {
			this.submitButton = waitUntilVisible(bySubmitButton);
			this.individualIncomeText = waitUntilVisible(this.byIndividualIncomeText);
			this.additionalIncomeText = waitUntilVisible(this.byAdditionalIncomeText);
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
			individualIncomeText.clear();
			additionalIncomeText.clear();
		} catch(Exception e) {
			log.info("inputFormFields() exception: " + e.getMessage());
		}
	}

	private String randomName(final String name) {
		long seed = System.currentTimeMillis();
		Random random = new Random(seed);
		// Obtain a number between [0 - 1000].
		int num = random.nextInt(1000);
		return name + String.valueOf(num);
	}
	
	@Override
	public void inputFormFields() throws Exception {
		try {
			initElements();
			log.info(String.format("individual income: %s; additional income %s \n", getData().getIndividualIncome(), getData().getAdditionalIncome()));
			individualIncomeText.sendKeys(getData().getIndividualIncome());
			additionalIncomeText.sendKeys(getData().getAdditionalIncome());
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
			returnPage = (PersonalInformationIncomePage) this.getParent();
		} catch(Exception e) {
			log.info("returnCancelPage() exception: " + e.getMessage());
		};
		return returnPage;
	}

	@Override
	public Page returnSubmitPage() throws Exception {
		Page returnPage = null;
		try {
			returnPage = new PersonalInformationLoginPage();
		} catch(Exception e) {
			log.info("returnSubmitPage() exception: " + e.getMessage());
		};
		return returnPage;
	}
}
