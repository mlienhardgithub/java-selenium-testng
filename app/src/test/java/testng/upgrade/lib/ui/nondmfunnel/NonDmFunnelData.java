package testng.upgrade.lib.ui.nondmfunnel;

import testng.upgrade.lib.ui.components.Data;

public class NonDmFunnelData extends Data {
	private String loanAmount;
	private String loanPurposeText;
	private String loanPurposeValue;
	
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanPurposeText() {
		return loanPurposeText;
	}
	public void setLoanPurposeText(String loanPurposeText) {
		this.loanPurposeText = loanPurposeText;
	}
	public String getLoanPurposeValue() {
		return loanPurposeValue;
	}
	public void setLoanPurposeValue(String loanPurposeValue) {
		this.loanPurposeValue = loanPurposeValue;
	}
}
