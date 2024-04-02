package testng.upgrade.lib.ui.components;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class Form extends Component implements IForm {
	protected static Log log = LogFactory.getLog(Form.class);
	
	public Form() {
		
	}
	
	protected By byCancelButton = By.id("cancel");
	public By getByCancelButton(){
		return byCancelButton;
	}
	protected WebElement cancelButton;
	
	protected By bySubmitButton = By.id("submit");
	public By getBySubmitButton(){
		return bySubmitButton;
	}
	protected WebElement submitButton;
	
	public Page cancelForm() throws Exception{
		cancelButton = waitUntilVisible(byCancelButton);
		cancelButton.click();
		return returnCancelPage();
	}
	
	public abstract void clearForm() throws Exception;
	public abstract void inputFormFields() throws Exception;
	public abstract Page returnCancelPage() throws Exception;
	public abstract Page returnSubmitPage() throws Exception;
	
	public Page submitForm() throws Exception {
		submitButton = waitUntilVisible(bySubmitButton);
		submitButton.click();
		return returnSubmitPage();
	}
	
	public void submitFormAndVerifyErrors() throws Exception {
		submitButton = waitUntilVisible(bySubmitButton);
		submitButton.click();
		Thread.sleep(1000);
	}
	
	public Page inputAndSubmitForm() throws Exception {
		inputFormFields();
		return submitForm();
	}
	
	public void inputAndSubmitFormAndVerifyErrors() throws Exception {
		inputFormFields();
		submitFormAndVerifyErrors();
	}
}
