package testng.upgrade.lib.ui.personalinfobasic;

import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Form;
import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.personalinfoincome.PersonalInformationIncomePage;

public class PersonalInformationBasicForm extends Form {
	protected static Log log = LogFactory.getLog(PersonalInformationBasicForm.class);
	
	public PersonalInformationBasicForm() throws Exception {
		this.bySubmitButton = By.cssSelector("[data-auto='continuePersonalInfo']");
	}
	
	@Override
	public PersonalInformationBasicData getData(){
		return (PersonalInformationBasicData) super.getData();
	}
	
	private By byIndividualRadio = By.cssSelector("label[data-checked]:nth-of-type(1) > input[name='jointApplicationToggle']");
	protected WebElement individualRadio;
	
	private By byJointApplicationRadio = By.cssSelector("label[data-checked]:nth-of-type(1) > input[name='jointApplicationToggle']");
	protected WebElement jointApplicationRadio;
	
	private By byFirstNameText = By.cssSelector("[data-auto='borrowerFirstName']");	
	protected WebElement firstNameText;
	
	private By byLastNameText = By.cssSelector("[data-auto='borrowerLastName']");	
	protected WebElement lastNameText;
	
	private By byStreetText = By.cssSelector("[data-auto='borrowerStreet']");	
	protected WebElement streetText;
	
	//id="geosuggest__list--borrowerStreet"
	private By byStreetList = By.id("geosuggest__list--borrowerStreet");	
	protected WebElement streetList;
	
	//role="option"
	private By byStreetItems = By.cssSelector("[role='option']");	
	
	private By byCityText = By.cssSelector("[data-auto='borrowerCity']");	
	protected WebElement cityText;
	
	private By byStateText = By.cssSelector("[data-auto='borrowerState']");	
	protected WebElement stateText;
	
	private By byZipCodeText = By.cssSelector("[data-auto='borrowerZipCode']");	
	protected WebElement zipCodeText;
	
	private By byDateOfBirthText = By.cssSelector("[data-auto='borrowerDateOfBirth']");	
	protected WebElement dateOfBirthText;
	
	@Override
	public void initElements() throws Exception {
		try {
			this.submitButton = waitUntilVisible(bySubmitButton);
			this.firstNameText = waitUntilVisible(this.byFirstNameText);
			this.lastNameText = waitUntilVisible(this.byLastNameText);
			this.streetText = waitUntilVisible(this.byStreetText);
			this.cityText = waitUntilVisible(this.byCityText);
			this.stateText = waitUntilVisible(this.byStateText);
			this.zipCodeText = waitUntilVisible(this.byZipCodeText);
			this.dateOfBirthText = waitUntilVisible(this.byDateOfBirthText);
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
			firstNameText.clear();
			lastNameText.clear();
			streetText.clear();
			cityText.clear();
			stateText.clear();
			zipCodeText.clear();
			dateOfBirthText.clear();
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
			String firstName = randomName(getData().getFirstName());
			String lastName = randomName(getData().getLastName());
			log.info(String.format("first Name: %s; last Name %s; date of birth: %s \n", firstName, lastName, getData().getDateOfBirth()));
			firstNameText.sendKeys(firstName);
			lastNameText.sendKeys(lastName);
			streetText.sendKeys(getData().getStreetNumber() + " " + getData().getStreetName());
			
			streetList = waitUntilVisible(byStreetList);
			List<WebElement> streetItems = streetList.findElements(byStreetItems);
			for (WebElement element: streetItems) {
				if (
						(element.getText().contains(getData().getStreetNumber()))
						&& (element.getText().contains(getData().getStreetName()))
						&& (element.getText().contains(getData().getCity()))
						&& (element.getText().contains(getData().getState()))
				) {
					element.click();
				}
			}
			
			cityText.sendKeys(getData().getCity());
			stateText.sendKeys(getData().getState());
			zipCodeText.sendKeys(getData().getZipCode());
			dateOfBirthText.sendKeys(getData().getDateOfBirth());
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
			returnPage = (PersonalInformationBasicPage) this.getParent();
		} catch(Exception e) {
			log.info("returnCancelPage() exception: " + e.getMessage());
		};
		return returnPage;
	}

	@Override
	public Page returnSubmitPage() throws Exception {
		Page returnPage = null;
		try {
			returnPage = new PersonalInformationIncomePage();
		} catch(Exception e) {
			log.info("returnSubmitPage() exception: " + e.getMessage());
		};
		return returnPage;
	}
}
