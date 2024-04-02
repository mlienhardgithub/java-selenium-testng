package testng.upgrade.lib.ui.nondmfunnel;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testng.upgrade.lib.ui.components.Form;
import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.personalinfobasic.PersonalInformationBasicPage;

public class NonDmFunnelForm extends Form {
	protected static Log log = LogFactory.getLog(NonDmFunnelForm.class);
	
	public NonDmFunnelForm() throws Exception {
		this.bySubmitButton = By.cssSelector("[data-auto='CheckYourRate']");
	}
	
	@Override
	public NonDmFunnelData getData(){
		return (NonDmFunnelData) super.getData();
	}
	
	private By byGlobalAlertCloseButton = By.cssSelector("[data-auto='globalAlert'] [role=button][aria-label='Close']");
	protected WebElement globalAlertCloseButton;
	
	private By byLoanAmountInput = By.cssSelector("[id*='cYR-loan-amount-'][name='desiredAmount']");
	protected WebElement loanAmountInput;
	
	private By byLoanPurposeSelect = By.cssSelector("[data-auto='dropLoanPurpose']");	
	protected Select loanPurposeSelect;
	
	@Override
	public void initElements() throws Exception {
		try {
			this.globalAlertCloseButton = waitUntilVisible(this.byGlobalAlertCloseButton);
			this.submitButton = waitUntilVisible(bySubmitButton);
			this.loanAmountInput = waitUntilVisible(this.byLoanAmountInput);
			this.loanPurposeSelect = new Select(waitUntilVisible(this.byLoanPurposeSelect));
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
			loanAmountInput.clear();
			loanPurposeSelect.selectByVisibleText("");
		} catch(Exception e) {
			log.info("clearForm() exception: " + e.getMessage());
		}
	}

	@Override
	public void inputFormFields() throws Exception {
		try {
			initElements();
			globalAlertCloseButton.click();
			log.info(String.format("loan amount: %s; loan purpose %s  \n", getData().getLoanAmount(), getData().getLoanPurposeText()));
			loanAmountInput.sendKeys(getData().getLoanAmount());
			loanPurposeSelect.selectByValue(getData().getLoanPurposeValue());
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
			returnPage = (NonDmFunnelPage) this.getParent();
		} catch(Exception e) {
			log.info("returnCancelPage() exception: " + e.getMessage());
		};
		return returnPage;
	}

	@Override
	public Page returnSubmitPage() throws Exception {
		Page returnPage = null;
		try {
			returnPage = new PersonalInformationBasicPage();
		} catch(Exception e) {
			log.info("returnSubmitPage() exception: " + e.getMessage());
		};
		return returnPage;
	}
}
