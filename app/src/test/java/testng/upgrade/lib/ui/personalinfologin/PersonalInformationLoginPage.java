package testng.upgrade.lib.ui.personalinfologin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testng.upgrade.lib.ui.components.Page;

public class PersonalInformationLoginPage extends Page {
	protected static Log log = LogFactory.getLog(PersonalInformationLoginPage.class);

	public PersonalInformationLoginPage() throws Exception {
		super();
		this.name = this.getClass().getSimpleName();
		this.printName();
		this.expectedUrl = "/personal-information";
		this.verifyCurrentUrl();
		this.expectedUrl = "?step=login";
		this.verifyCurrentUrl();
		initElements();
	}

	@Override
	public void initElements() throws Exception {
		this.form = new PersonalInformationLoginForm();
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
