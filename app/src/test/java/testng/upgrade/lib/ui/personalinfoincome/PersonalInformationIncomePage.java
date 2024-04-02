package testng.upgrade.lib.ui.personalinfoincome;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testng.upgrade.lib.ui.components.Page;

public class PersonalInformationIncomePage extends Page {
	protected static Log log = LogFactory.getLog(PersonalInformationIncomePage.class);

	public PersonalInformationIncomePage() throws Exception {
		super();
		this.name = this.getClass().getSimpleName();
		this.printName();
		this.expectedUrl = "/personal-information";
		this.verifyCurrentUrl();
		this.expectedUrl = "?step=income";
		this.verifyCurrentUrl();
		initElements();
	}

	@Override
	public void initElements() throws Exception {
		this.form = new PersonalInformationIncomeForm();
		this.form.setParent(this);
	}
	
	@Override
	public PersonalInformationIncomeData initData(){
		//call setData() and getData() in case they are overridden
		super.setData(new PersonalInformationIncomeData());
		this.form.initData(super.getData());
		return (PersonalInformationIncomeData) super.getData();
	}
	@Override
	public PersonalInformationIncomeData initData(Object data){
		//call setData() and getData() in case they are overridden
		super.setData(data);
		this.form.setData(data);
		return (PersonalInformationIncomeData) super.getData();
	}
	@Override
	public void setData(Object data){
		super.setData(data);
		this.form.setData(data);
	}
	@Override
	public PersonalInformationIncomeData getData(){
		return (PersonalInformationIncomeData) super.getData();
	}
	
	public Page inputAndSubmitForm() throws Exception {
		return this.form.inputAndSubmitForm();
	}
}
