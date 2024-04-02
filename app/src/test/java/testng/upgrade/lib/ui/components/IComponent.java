package testng.upgrade.lib.ui.components;

public interface IComponent extends ILoadData {
	void setParent(IComponent parent);
	IComponent getParent();
	void initElements() throws Exception;
}
