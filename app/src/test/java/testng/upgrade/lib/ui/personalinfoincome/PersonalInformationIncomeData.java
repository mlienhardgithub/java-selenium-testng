package testng.upgrade.lib.ui.personalinfoincome;

import testng.upgrade.lib.ui.components.Data;

public class PersonalInformationIncomeData extends Data {
	private String individualIncome;
	private String additionalIncome;
	
	public String getIndividualIncome() {
		return individualIncome;
	}
	public void setIndividualIncome(String individualIncome) {
		this.individualIncome = individualIncome;
	}
	public String getAdditionalIncome() {
		return additionalIncome;
	}
	public void setAdditionalIncome(String additionalIncome) {
		this.additionalIncome = additionalIncome;
	}
}
