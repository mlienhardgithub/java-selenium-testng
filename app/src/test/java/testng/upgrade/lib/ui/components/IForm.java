package testng.upgrade.lib.ui.components;

import java.util.List;

import org.openqa.selenium.By;

public interface IForm extends IComponent {
	By getByCancelButton();
	By getBySubmitButton();
	Page cancelForm() throws Exception;
	void clearForm() throws Exception;
	void inputFormFields() throws Exception;
	Page returnCancelPage() throws Exception;
	Page returnSubmitPage() throws Exception;
	Page submitForm() throws Exception;
	void submitFormAndVerifyErrors() throws Exception;
	Page inputAndSubmitForm() throws Exception;
	void inputAndSubmitFormAndVerifyErrors() throws Exception;
}
