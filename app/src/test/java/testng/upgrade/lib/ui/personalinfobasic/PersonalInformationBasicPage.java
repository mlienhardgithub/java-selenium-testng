package testng.upgrade.lib.ui.personalinfobasic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testng.upgrade.lib.ui.components.Page;

public class PersonalInformationBasicPage extends Page {
	protected static Log log = LogFactory.getLog(PersonalInformationBasicPage.class);

	public PersonalInformationBasicPage() throws Exception {
		super();
		this.name = this.getClass().getSimpleName();
		this.printName();
		this.expectedUrl = "/personal-information";
		this.verifyCurrentUrl();
		this.expectedUrl = "?step=contact";
		this.verifyCurrentUrl();
		initElements();
	}

	@Override
	public void initElements() throws Exception {
		this.form = new PersonalInformationBasicForm();
		this.form.setParent(this);
	}
	
	@Override
	public PersonalInformationBasicData initData(){
		//call setData() and getData() in case they are overridden
		super.setData(new PersonalInformationBasicData());
		this.form.initData(super.getData());
		return (PersonalInformationBasicData) super.getData();
	}
	@Override
	public PersonalInformationBasicData initData(Object data){
		//call setData() and getData() in case they are overridden
		super.setData(data);
		this.form.setData(data);
		return (PersonalInformationBasicData) super.getData();
	}
	@Override
	public void setData(Object data){
		super.setData(data);
		this.form.setData(data);
	}
	@Override
	public PersonalInformationBasicData getData(){
		return (PersonalInformationBasicData) super.getData();
	}
	
	public Page inputAndSubmitForm() throws Exception {
		return this.form.inputAndSubmitForm();
	}
}
