package util;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class QueryUtil {
    public static String queryByID(String id) throws SAXException, IOException, ParserConfigurationException {

        NodeList nodeList;
        Element element = null;

        final String dir = System.getProperty("user.dir");
        nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(dir + "\\queries.xml"))
                .getElementsByTagName(Constants.TAG_NAME);

        for (int value = 0; value < nodeList.getLength(); value++) {
            element = (Element) nodeList.item(value);
            if (element.getAttribute(Constants.ATTRIB_ID).equals(id))
                break;
        }
        return element.getTextContent().trim();
    }
}
