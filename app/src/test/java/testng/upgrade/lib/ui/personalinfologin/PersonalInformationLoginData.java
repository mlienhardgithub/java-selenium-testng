package testng.upgrade.lib.ui.personalinfologin;

import testng.upgrade.lib.ui.components.Data;

public class PersonalInformationLoginData extends Data {
	private String emailPrefix;
	private String emailSuffix;
	private String email;	
	private String password;
	
	public String getEmailPrefix() {
		return emailPrefix;
	}
	public void setEmailPrefix(String emailPrefix) {
		this.emailPrefix = emailPrefix;
	}
	public String getEmailSuffix() {
		return emailSuffix;
	}
	public void setEmailSuffix(String emailSuffix) {
		this.emailSuffix = emailSuffix;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
