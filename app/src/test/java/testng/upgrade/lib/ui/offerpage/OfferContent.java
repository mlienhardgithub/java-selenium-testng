package testng.upgrade.lib.ui.offerpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Component;


public class OfferContent extends Component {
	public static final String DEBT_PAYOFF_FASTER = "Debt Payoff Discount Offers Faster Payoff";
	public static final String DEBT_PAYOFF_LOWER_PAYMENT = "Debt Payoff Discount Offers Lower Payment";
	
	protected static Log log = LogFactory.getLog(OfferContent.class);
	private Map<String, Object> loanPayoffData;
	
	public OfferContent() throws Exception {
		super();
		loanPayoffData = new HashMap<>();
	}
	
	public Map<String, Object> getLoanPayoffData() {
		return loanPayoffData;
	}
	
	private By byUserLoanAmountLabel = By.cssSelector("[data-auto='userLoanAmount']");
	protected WebElement userLoanAmountLabel;
	
	public By getByMonthlyPaymentLabel(int index) {
		return By.cssSelector("[data-auto='HeroOfferBox" + index + "'] [data-auto='defaultMonthlyPayment']");
	}
	
	public By getByTermLabel(int index) {
		return By.cssSelector("[data-auto='HeroOfferBox" + index + "'] [data-auto='defaultLoanTerm'] div:nth-of-type(2) p");
	}
	
	public By getByInterestRateLabel(int index) {
		return By.cssSelector("[data-auto='HeroOfferBox" + index + "'] [data-auto='defaultLoanInterestRate'] div:nth-of-type(2) p");
	}
	
	public By getByAprLabel(int index) {
		return By.cssSelector("[data-auto='HeroOfferBox" + index + "'] [data-auto='defaultAPR'] div:nth-of-type(2) p");
	}
	
	@Override
	public void initElements() throws Exception {
		try {
			this.userLoanAmountLabel = waitUntilVisible(this.byUserLoanAmountLabel);
		} catch(Exception e) {
			log.info("initElements() exception: " + e.getMessage());
			System.out.println(this.driver.getPageSource());
			throw new Exception(e);
		}
	}
	
	public void loadLoanPayoffData() throws Exception {
		initElements();
		String userLoanAmount = userLoanAmountLabel.getText();
		loanPayoffData.put("Loan Amount", userLoanAmount);
		
		//capture default payments
		loanPayoffData.put(DEBT_PAYOFF_FASTER, loadPayment(0));
		loanPayoffData.put(DEBT_PAYOFF_LOWER_PAYMENT, loadPayment(1));
	}
	
	private Map<String, String> loadPayment(int index) throws Exception {
		Map<String, String> payment = new HashMap<>();
		
		WebElement element = waitUntilVisible(this.getByMonthlyPaymentLabel(index));
		String text = element.getText();
		payment.put("Monthly Payment", text);
		
		element = waitUntilVisible(this.getByTermLabel(index));
		text = element.getText();
		payment.put("Term", text);

		element = waitUntilVisible(this.getByInterestRateLabel(index));
		text = element.getText();
		payment.put("Interest Rate", text);

		element = waitUntilVisible(this.getByAprLabel(index));
		text = element.getText();
		payment.put("APR", text);

		return payment;
	}
}
