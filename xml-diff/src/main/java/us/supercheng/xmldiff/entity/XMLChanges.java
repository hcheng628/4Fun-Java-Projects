package us.supercheng.xmldiff.entity;

public class XMLChanges {
	private String pre_Val;
	private String new_Val;
	private String xPath;
	
	public XMLChanges(String in_pre_Val, String in_new_Val, String in_xPath){
		this.pre_Val = in_pre_Val;
		this.new_Val = in_new_Val;
		this.xPath = in_xPath;
	}
	
	public String getPre_Val() {
		return pre_Val;
	}
	public void setPre_Val(String pre_Val) {
		this.pre_Val = pre_Val;
	}
	public String getNew_Val() {
		return new_Val;
	}
	public void setNew_Val(String new_Val) {
		this.new_Val = new_Val;
	}
	public String getxPath() {
		return xPath;
	}
	public void setxPath(String xPath) {
		this.xPath = xPath;
	}
}