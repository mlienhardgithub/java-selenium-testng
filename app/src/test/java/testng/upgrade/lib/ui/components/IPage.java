package testng.upgrade.lib.ui.components;

public interface IPage extends IComponent  {
	void setName(String name);
	String getName();
	void printName();
	void setForm(Form form) throws Exception;
	Form getForm() throws Exception;
	void setComponent(Component component) throws Exception;
	Component getComponent() throws Exception;
}
