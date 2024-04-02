package testng.upgrade.lib.ui.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testng.upgrade.lib.ui.components.Page;
import testng.upgrade.lib.ui.personalinfologin.PersonalInformationLoginData;

public class LoginPage extends Page {
	protected static Log log = LogFactory.getLog(LoginPage.class);

	public LoginPage() throws Exception {
		super();
		this.name = this.getClass().getSimpleName();
		this.printName();
		this.expectedUrl = "/portal/login";
		this.verifyCurrentUrl();
		initElements();
	}

	@Override
	public void initElements() throws Exception {
		this.form = new LoginForm();
		this.form.setParent(this);
	}
	
	@Override
	public PersonalInformationLoginData initData(){
		//call setData() and getData() in case they are overridden
		super.setData(new PersonalInformationLoginData());
		this.form.initData(super.getData());
		return (PersonalInformationLoginData) super.getData();
	}
	@Override
	public PersonalInformationLoginData initData(Object data){
		//call setData() and getData() in case they are overridden
		super.setData(data);
		this.form.setData(data);
		return (PersonalInformationLoginData) super.getData();
	}
	@Override
	public void setData(Object data){
		super.setData(data);
		this.form.setData(data);
	}
	@Override
	public PersonalInformationLoginData getData(){
		return (PersonalInformationLoginData) super.getData();
	}
	
	public Page inputAndSubmitForm() throws Exception {
		return this.form.inputAndSubmitForm();
	}
}
