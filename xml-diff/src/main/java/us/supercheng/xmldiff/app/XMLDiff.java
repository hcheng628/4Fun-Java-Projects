package us.supercheng.xmldiff.app;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.ElementSelectors;
import us.supercheng.xmldiff.util.StringHelper;

public class XMLDiff {	
	public String doXMLDiff(String versionXML_A, String versionXML_B) throws TransformerException{
		StringHelper stringHelper = new StringHelper();
		String tempStr = "<" + IXMLDiffKeywords.XMLChangesRootTag +">";
		Diff ds = DiffBuilder.compare(Input.fromString(versionXML_A))
				    .withTest(Input.fromString(versionXML_B))
				    .checkForSimilar()
				    .normalizeWhitespace()
				    //.checkForSimilar()
				    // .normalizeWhitespace()
				    .ignoreComments()
				    .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)) // My Favourite
				    .build();
		CRIFComparisonFormatter formatter = new CRIFComparisonFormatter();
        for(Difference eachDiff : ds.getDifferences()){
        	if(formatter.getDescription(eachDiff.getComparison()) != null){
        		tempStr += formatter.getDescription(eachDiff.getComparison());
        	}
        }
        tempStr  += "</" + IXMLDiffKeywords.XMLChangesRootTag + ">";
        
        Document doc = stringHelper.convertStringToDocument(tempStr);
        Document docOriginal = stringHelper.convertStringToDocument(versionXML_A);
        Document docNew =  stringHelper.convertStringToDocument(versionXML_B);
        NodeList xmlChangeList = doc.getElementsByTagName(IXMLDiffKeywords.EachXMLChangeRootTag);
        for(int i=0;i<xmlChangeList.getLength();i++){
        	NamedNodeMap attrs = xmlChangeList.item(i).getAttributes();
        	if(attrs.getNamedItem(IXMLDiffKeywords.Removed_Attr) != null){ // Read XPath from Original XML and display Value
    			Node _node = attrs.getNamedItem(IXMLDiffKeywords.Removed_Attr);
    			((Element)xmlChangeList.item(i)).setAttribute(IXMLDiffKeywords.OLD_Value,
						stringHelper.selectStringValue(XPathFactory.newInstance().newXPath(), attrs.getNamedItem(IXMLDiffKeywords.XPath).getNodeValue(), docOriginal));
        	}
        	if(attrs.getNamedItem(IXMLDiffKeywords.New_Attr) != null){ // Read XPath from New XML and display Value
    			Node _node = attrs.getNamedItem(IXMLDiffKeywords.New_Attr);
    			((Element)xmlChangeList.item(i)).setAttribute(IXMLDiffKeywords.New_Value,
						stringHelper.selectStringValue(XPathFactory.newInstance().newXPath(), attrs.getNamedItem(IXMLDiffKeywords.XPath).getNodeValue(), docNew));
        	}
        }
        return stringHelper.convertDocumentToString(doc, IXMLDiffKeywords.OutputKeysOmit_XML_Declation,
        		IXMLDiffKeywords.OutputKeysIndent, IXMLDiffKeywords.OutputKeysMethod, IXMLDiffKeywords.OutputKeysEncoding);
	}
}