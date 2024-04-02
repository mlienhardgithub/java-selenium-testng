package testng.upgrade.e2e;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import testng.upgrade.TestNgRunner;
import testng.upgrade.TestThread;
import testng.upgrade.lib.ui.login.LoginPage;
import testng.upgrade.lib.ui.nondmfunnel.NonDmFunnelData;
import testng.upgrade.lib.ui.nondmfunnel.NonDmFunnelPage;
import testng.upgrade.lib.ui.offerpage.OfferContent;
import testng.upgrade.lib.ui.offerpage.OfferPage;
import testng.upgrade.lib.ui.personalinfobasic.PersonalInformationBasicData;
import testng.upgrade.lib.ui.personalinfobasic.PersonalInformationBasicPage;
import testng.upgrade.lib.ui.personalinfoincome.PersonalInformationIncomeData;
import testng.upgrade.lib.ui.personalinfoincome.PersonalInformationIncomePage;
import testng.upgrade.lib.ui.personalinfologin.PersonalInformationLoginData;
import testng.upgrade.lib.ui.personalinfologin.PersonalInformationLoginPage;
import testng.upgrade.utils.Utilities;

public class UpgradeUiTests extends TestNgRunner {	
	public UpgradeUiTests() throws IOException {
		super();
	}
	
	//normally I would place these in layers of workflow abstraction classes
	private NonDmFunnelPage nonDmFunnelPage = null;
	public NonDmFunnelPage getNonDmFunnelPage() throws Exception {
		if (nonDmFunnelPage == null) {
			nonDmFunnelPage = new NonDmFunnelPage();
		}
		return nonDmFunnelPage;
	}
	
	private PersonalInformationBasicPage personalInformationBasicPage = null;
	public PersonalInformationBasicPage getPersonalInformationBasicPage() throws Exception {
		if (personalInformationBasicPage == null) {
			personalInformationBasicPage = new PersonalInformationBasicPage();
		}
		return personalInformationBasicPage;
	}
	
	private PersonalInformationIncomePage personalInformationIncomePage = null;
	public PersonalInformationIncomePage getPersonalInformationIncomePage() throws Exception {
		if (personalInformationIncomePage == null) {
			personalInformationIncomePage = new PersonalInformationIncomePage();
		}
		return personalInformationIncomePage;
	}
	
	private PersonalInformationLoginPage personalInformationLoginPage = null;
	public PersonalInformationLoginPage getPersonalInformationLoginPage() throws Exception {
		if (personalInformationLoginPage == null) {
			personalInformationLoginPage = new PersonalInformationLoginPage();
		}
		return personalInformationLoginPage;
	}
	
	private OfferPage offerPage = null;
	public OfferPage getOfferPage() throws Exception {
		if (offerPage == null) {
			offerPage = new OfferPage();
		}
		return offerPage;
	}
	
	private LoginPage loginPage = null;
	public LoginPage getLoginPage() throws Exception {
		if (loginPage == null) {
			loginPage = new LoginPage();
		}
		return loginPage;
	}
	
	//normally I would make this test only a few lines long using a high-level workflow abstraction class
	@Test(description="As a borrower apply for a loan and should get a loan offer")
	public void testBorrowerAppliesAndReceivesLoanOffer( ) {
		try {
			//arrange
			
			//launch the browser and get the URL
			TestThread.getTestThread().loadWebPage("/phone/nonDMFunnel");
			
			//populate the non-DM funnel page
			String jsonFilePath = "src/test/resources/data/non-dm-funnel-data.json";
			getNonDmFunnelPage().setData(new Utilities().deserializeTheJsonFile(jsonFilePath, NonDmFunnelData.class));
			final PersonalInformationBasicPage personalInformationBasicPage = (PersonalInformationBasicPage) getNonDmFunnelPage().inputAndSubmitForm();
			
			//populate the personal information basic page
			jsonFilePath = "src/test/resources/data/personal-information-basic-data.json";
			personalInformationBasicPage.setData(new Utilities().deserializeTheJsonFile(jsonFilePath, PersonalInformationBasicData.class));
			final PersonalInformationIncomePage personalInformationIncomePage = (PersonalInformationIncomePage) personalInformationBasicPage.inputAndSubmitForm();
			
			//populate the personal information income page
			jsonFilePath = "src/test/resources/data/personal-information-income-data.json";
			personalInformationIncomePage.setData(new Utilities().deserializeTheJsonFile(jsonFilePath, PersonalInformationIncomeData.class));
			final PersonalInformationLoginPage personalInformationLoginPage = (PersonalInformationLoginPage) personalInformationIncomePage.inputAndSubmitForm();
			
			//populate the personal information login page
			jsonFilePath = "src/test/resources/data/personal-information-login-data.json";
			personalInformationLoginPage.setData(new Utilities().deserializeTheJsonFile(jsonFilePath, PersonalInformationLoginData.class));
			final Properties configProps = Utilities.loadConfigProperties();
			final String password = configProps.getProperty("app.password.test");
			personalInformationLoginPage.getData().setPassword(password);
			final OfferPage firstOfferPage = (OfferPage) personalInformationLoginPage.inputAndSubmitForm();
			
			final PersonalInformationLoginData loginData = personalInformationLoginPage.getData();
			
			//get the loan offer from the offer page
			firstOfferPage.loadLoanPayoffData();
			final Map<String, Object> orignalLoanPayoffData = firstOfferPage.getLoanPayoffData();
			final LoginPage loginPage = (LoginPage) firstOfferPage.signout();
			
			//login
			loginPage.setData(loginData);
			final OfferPage secondOfferPage = (OfferPage) loginPage.inputAndSubmitForm();
			
			//act
			
			//compare the loan payoff to the original offer page
			secondOfferPage.loadLoanPayoffData();
			final Map<String, Object> compareLoanPayoffData = secondOfferPage.getLoanPayoffData();
			
			//assert on the loan offer
			
			assertEquals(compareLoanPayoffData.get("Loan Amount"), orignalLoanPayoffData.get("Loan Amount"), "Loan amounts are different");
			
			Map<String, String> expectedPayment = (Map<String, String>) orignalLoanPayoffData.get(OfferContent.DEBT_PAYOFF_FASTER);
			Map<String, String> actualPayment = (Map<String, String>) compareLoanPayoffData.get(OfferContent.DEBT_PAYOFF_FASTER);
			assertEquals(actualPayment.get("Monthly Payment"), expectedPayment.get("Monthly Payment"), "Monthly Payments are different");
			assertEquals(actualPayment.get("Term"), expectedPayment.get("Term"), "Terms are different");
			assertEquals(actualPayment.get("Interest Rate"), expectedPayment.get("Interest Rate"), "Interest Rates are different");
			assertEquals(actualPayment.get("APR"), expectedPayment.get("APR"), "APRs are different");
			
			expectedPayment = (Map<String, String>) orignalLoanPayoffData.get(OfferContent.DEBT_PAYOFF_LOWER_PAYMENT);
			actualPayment = (Map<String, String>) compareLoanPayoffData.get(OfferContent.DEBT_PAYOFF_LOWER_PAYMENT);
			assertEquals(actualPayment.get("Monthly Payment"), expectedPayment.get("Monthly Payment"), "Monthly Payments are different");
			assertEquals(actualPayment.get("Term"), expectedPayment.get("Term"), "Terms are different");
			assertEquals(actualPayment.get("Interest Rate"), expectedPayment.get("Interest Rate"), "Interest Rates are different");
			assertEquals(actualPayment.get("APR"), expectedPayment.get("APR"), "APRs are different");
			
			//logout
			secondOfferPage.signout();
		} catch(Exception e) {
			fail("test exception failure: " + e);
		}
	}
}
