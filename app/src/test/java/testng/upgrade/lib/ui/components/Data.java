package testng.upgrade.lib.ui.components;

public abstract class Data implements ILoadData {
	protected Object data;
	public Object initData(){
		//call setData() and getData() in case they are overridden
		this.setData(new Object());
		return this.getData();
	}
	public Object initData(Object data){
		//call setData() and getData() in case they are overridden
		this.setData(data);
		return this.getData();
	}
	public void setData(Object data){
		this.data = data;
	}
	public Object getData(){
		return this.data;
	}
}
