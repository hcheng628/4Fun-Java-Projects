package us.supercheng.xmldiff.app;

public interface IXMLDiffKeywords {
	final String XMLChangesRootTag = "XMLChanges";
	final String EachXMLChangeRootTag = "XMLChange";
	final String OLD_Value = "old_value";
	final String New_Value = "new_value";
	final String New_Attr = "new_attribute";
	final String Removed_Attr = "removed_attribute";
	final String New_Node = "new_node";
	final String Removed_Node = "removed_node";
	final String XPath = "xpath";
	
	final String OutputKeysOmit_XML_Declation = "yes";
	final String OutputKeysIndent = "no";
	final String OutputKeysMethod = "xml";
	final String OutputKeysEncoding = "UTF-8";
}