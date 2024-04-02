package testng.upgrade.lib.ui.nondmfunnel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.upgrade.lib.ui.components.Page;

public class NonDmFunnelPage extends Page {
	protected static Log log = LogFactory.getLog(NonDmFunnelPage.class);

	public NonDmFunnelPage() throws Exception {
		super();
		this.name = this.getClass().getSimpleName();
		this.printName();
		this.expectedUrl = "/nonDMFunnel";
		this.verifyCurrentUrl();
		initElements();
	}
	
	@Override
	public void initElements() throws Exception {
		this.form = new NonDmFunnelForm();
		this.form.setParent(this);
	}
	
	@Override
	public NonDmFunnelData initData(){
		//call setData() and getData() in case they are overridden
		super.setData(new NonDmFunnelData());
		this.form.initData(super.getData());
		return (NonDmFunnelData) super.getData();
	}
	@Override
	public NonDmFunnelData initData(Object data){
		//call setData() and getData() in case they are overridden
		super.setData(data);
		this.form.setData(data);
		return (NonDmFunnelData) super.getData();
	}
	@Override
	public void setData(Object data){
		super.setData(data);
		this.form.setData(data);
	}
	@Override
	public NonDmFunnelData getData(){
		return (NonDmFunnelData) super.getData();
	}
	
	public Page inputAndSubmitForm() throws Exception {
		return this.form.inputAndSubmitForm();
	}
}
