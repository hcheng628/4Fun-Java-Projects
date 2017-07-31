package us.supercheng.xmldiff.util;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import java.io.*;

/**
 * Created by cl799honchen on 7/31/2017.
 */
public class StringHelper {
    public String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            br = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (Exception var13) {
                    var13.printStackTrace();
                }
            }

        }
        return sb.toString();
    }

    public Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (ParserConfigurationException var5) {
            throw new RuntimeException(var5);
        } catch (SAXException var6) {
            throw new RuntimeException(var6);
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        }
    }

    public String selectStringValue(XPath xpath, String expression, Document doc) {
        if(this.assertIsNotNullOrEmpty(expression)) {
            try {
                XPathExpression cfPath = xpath.compile(expression);
                return cfPath.evaluate(doc);
            } catch (Exception var4) {
               throw new RuntimeException(var4);
            }
        }
        return null;
    }

    public String convertDocumentToString(Document inDocument, String outputKeysOmit_XML_Declation, String outputKeysIndent, String outputKeysMethod, String outputKeysEncoding) throws TransformerException {
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("omit-xml-declaration", outputKeysOmit_XML_Declation);
        transformer.setOutputProperty("indent", outputKeysIndent);
        transformer.setOutputProperty("method", outputKeysMethod);
        transformer.setOutputProperty("encoding", outputKeysEncoding);
        transformer.transform(new DOMSource(inDocument), new StreamResult(sw));
        return sw.toString();
    }

    public boolean assertIsNotNullOrEmpty(String s) {
        return s != null && !s.trim().equals("");
    }
}
