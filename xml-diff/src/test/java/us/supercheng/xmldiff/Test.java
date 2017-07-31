package us.supercheng.xmldiff;

import javax.xml.transform.TransformerException;
import us.supercheng.xmldiff.app.XMLDiff;
import us.supercheng.xmldiff.util.StringHelper;

public class Test {

	@org.junit.Test
	public void test() throws TransformerException {
		StringHelper stringHelper = new StringHelper();
		XMLDiff xmlDiffClient = new XMLDiff();
		String content_A = stringHelper.getStringFromInputStream(getClass().getResourceAsStream("/" + "A.xml"));
		String content_B = stringHelper.getStringFromInputStream(getClass().getResourceAsStream("/" + "B.xml"));
		System.out.println(xmlDiffClient.doXMLDiff(content_A, content_B));
	}
}
