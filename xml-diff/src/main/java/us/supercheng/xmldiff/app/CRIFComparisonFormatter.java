package us.supercheng.xmldiff.app;

import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.Comparison.Detail;
import org.xmlunit.diff.ComparisonFormatter;
import org.xmlunit.diff.ComparisonType;

public class CRIFComparisonFormatter implements ComparisonFormatter {

	public String getDescription(Comparison difference) {
		// System.out.println("------------- ---" + "Type: " +  difference.getType() + "--- -------------");
		// System.out.println("Original Attribute Value: " + difference.getControlDetails().getValue() + " Original Attribute  XPath: " + difference.getControlDetails().getXPath());
		// System.out.println("New Attribute Value: " + difference.getTestDetails().getValue() + " New Attribute XPath: " + difference.getTestDetails().getXPath());
		if (difference.getType() == ComparisonType.ATTR_VALUE) {
			return "<" + IXMLDiffKeywords.EachXMLChangeRootTag + " " + IXMLDiffKeywords.New_Value + "='"
					+ difference.getTestDetails().getValue() + "' " + IXMLDiffKeywords.OLD_Value + "='"
					+ difference.getControlDetails().getValue() + "' " + IXMLDiffKeywords.XPath + "='"
					+ difference.getControlDetails().getXPath() + "'/>";

		} else if (difference.getType() == ComparisonType.ATTR_NAME_LOOKUP) {
			if (difference.getControlDetails().getValue() == null) {
				return "<" + IXMLDiffKeywords.EachXMLChangeRootTag + " " + IXMLDiffKeywords.New_Attr + "='"
						+ difference.getTestDetails().getValue() + "' " + IXMLDiffKeywords.XPath + "='"
						+ difference.getTestDetails().getXPath() + "'/>";
			}
			if (difference.getTestDetails().getValue() == null) {
				return "<" + IXMLDiffKeywords.EachXMLChangeRootTag + " " + IXMLDiffKeywords.Removed_Attr + "='"
						+ difference.getControlDetails().getValue() + "' " + IXMLDiffKeywords.XPath + "='"
						+ difference.getControlDetails().getXPath() + "'/>";
			}
		} else if (difference.getType() == ComparisonType.CHILD_LOOKUP) {
			if (difference.getTestDetails().getValue() == null) {
				return "<" + IXMLDiffKeywords.EachXMLChangeRootTag + " " + IXMLDiffKeywords.Removed_Node + "='"
						+ difference.getControlDetails().getValue() + "' " + IXMLDiffKeywords.XPath + "='"
						+ difference.getControlDetails().getXPath() + "'/>";
			}
			if (difference.getControlDetails().getValue() == null) {
				return "<" + IXMLDiffKeywords.EachXMLChangeRootTag + " " + IXMLDiffKeywords.New_Node + "='"
						+ difference.getTestDetails().getValue() + "' " + IXMLDiffKeywords.XPath + "='"
						+ difference.getTestDetails().getXPath() + "'/>";
			}
		}
		return null;
		// }
	}

	public String getDetails(Detail details, ComparisonType type, boolean formatXml) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * private String getShortString(Node node, String xpath, ComparisonType
	 * type) { StringBuilder sb = new StringBuilder(); if (type ==
	 * ComparisonType.HAS_DOCTYPE_DECLARATION) { Document doc = (Document) node;
	 * appendDocumentType(sb, doc.getDoctype());
	 * appendDocumentElementIndication(sb, doc); } else if (node instanceof
	 * Document) { Document doc = (Document) node;
	 * appendDocumentXmlDeclaration(sb, doc);
	 * appendDocumentElementIndication(sb, doc); } else if (node instanceof
	 * DocumentType) { final DocumentType docType = (DocumentType) node;
	 * appendDocumentType(sb, docType); appendDocumentElementIndication(sb,
	 * docType.getOwnerDocument()); } else if (node instanceof Attr) {
	 * appendAttribute(sb, (Attr) node); } else if (node instanceof Element) {
	 * appendElement(sb, (Element) node); } else if (node instanceof Text) {
	 * appendText(sb, (Text) node); } else if (node instanceof Comment) {
	 * appendComment(sb, (Comment) node); } else if (node instanceof
	 * ProcessingInstruction) { appendProcessingInstruction(sb,
	 * (ProcessingInstruction) node); } else if (node == null) {
	 * sb.append("<NULL>"); } else { sb.append("<!--NodeType "
	 * ).append(node.getNodeType()) .append(' ').append(node.getNodeName())
	 * .append('/').append(node.getNodeValue()) .append("-->"); } if (xpath !=
	 * null && xpath.length() > 0) { sb.append(" at ").append(xpath); } return
	 * sb.toString(); } private static void
	 * appendProcessingInstruction(StringBuilder sb, ProcessingInstruction
	 * instr) { sb.append("<?") .append(instr.getTarget()) .append('
	 * ').append(instr.getData()) .append("?>"); }
	 * 
	 * private static void appendComment(StringBuilder sb, Comment aNode) {
	 * sb.append("<!--") .append(aNode.getNodeValue()) .append("-->"); }
	 * 
	 * private static void appendText(StringBuilder sb, Text aNode) {
	 * sb.append("<") .append(aNode.getParentNode().getNodeName()) .append(
	 * " ...>");
	 * 
	 * if (aNode instanceof CDATASection) { sb.append("<![CDATA[")
	 * .append(aNode.getNodeValue()) .append("]]>"); } else {
	 * sb.append(aNode.getNodeValue()); }
	 * 
	 * sb.append("</") .append(aNode.getParentNode().getNodeName())
	 * .append(">"); }
	 * 
	 * private static void appendElement(StringBuilder sb, Element aNode) {
	 * sb.append("<") .append(aNode.getNodeName()).append("...") .append(">"); }
	 * 
	 * private static void appendAttribute(StringBuilder sb, Attr aNode) {
	 * sb.append("<").append(aNode.getOwnerElement().getNodeName()); sb.append('
	 * ') .append(aNode.getNodeName()).append("=\"")
	 * .append(aNode.getNodeValue()).append("\"...>"); } private Object
	 * getValue(Object value, ComparisonType type) { return type ==
	 * ComparisonType.NODE_TYPE ? nodeType((Short) value) : value; } private
	 * static String nodeType(short type) { switch(type) { case
	 * Node.ELEMENT_NODE: return "Element"; case Node.DOCUMENT_TYPE_NODE: return
	 * "Document Type"; case Node.ENTITY_NODE: return "Entity"; case
	 * Node.ENTITY_REFERENCE_NODE: return "Entity Reference"; case
	 * Node.NOTATION_NODE: return "Notation"; case Node.TEXT_NODE: return
	 * "Text"; case Node.COMMENT_NODE: return "Comment"; case
	 * Node.CDATA_SECTION_NODE: return "CDATA Section"; case
	 * Node.ATTRIBUTE_NODE: return "Attribute"; case
	 * Node.PROCESSING_INSTRUCTION_NODE: return "Processing Instruction";
	 * default: break; } return Short.toString(type); }
	 * 
	 * private static boolean appendDocumentType(StringBuilder sb, DocumentType
	 * type) { if (type == null) { return false; } sb.append("<!DOCTYPE "
	 * ).append(type.getName()); boolean hasNoPublicId = true; if
	 * (type.getPublicId()!=null && type.getPublicId().length() > 0) {
	 * sb.append(" PUBLIC \"").append(type.getPublicId()).append('"');
	 * hasNoPublicId = false; } if (type.getSystemId()!=null &&
	 * type.getSystemId().length() > 0) { if (hasNoPublicId) { sb.append(
	 * " SYSTEM"); } sb.append(" \"").append(type.getSystemId()).append("\""); }
	 * sb.append(">"); return true; }
	 * 
	 * private static void appendDocumentElementIndication(StringBuilder sb,
	 * Document doc) { sb.append("<");
	 * sb.append(doc.getDocumentElement().getNodeName()); sb.append("...>"); }
	 * private static boolean appendDocumentXmlDeclaration(StringBuilder sb,
	 * Document doc) { if ("1.0".equals(doc.getXmlVersion()) &&
	 * doc.getXmlEncoding() == null && !doc.getXmlStandalone()) { // only
	 * default values => ignore return false; } sb.append("<?xml version=\"");
	 * sb.append(doc.getXmlVersion()); sb.append("\""); if (doc.getXmlEncoding()
	 * != null) { sb.append(" encoding=\""); sb.append(doc.getXmlEncoding());
	 * sb.append("\""); } if (doc.getXmlStandalone()) { sb.append(
	 * " standalone=\"yes\""); } sb.append("?>"); return true; }
	 */
}