package com.hongguo.mysql.mybatis;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XPathTest {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        // 开启验证
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("warning:" + exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("error:" + exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("fatalError:" + exception.getMessage());
            }
        });

        Document document = documentBuilder.parse("mysql-mybatis-lecture/src/main/resources/inventory.xml");
        System.out.println(document.getDocumentURI());

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression expression = xPath.compile("//book[author='Neal Stephenson']/title/text()");
        Object result = expression.evaluate(document, XPathConstants.NODESET);
        System.out.println("查询作者Neal Stephenson 的图书标题:");
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("查询1997年之后的图书标题:");
        nodes = (NodeList) xPath.evaluate("//book[@year > 1997]/title/text()", document, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("查询1997年之后的图书属性和标题:");
        nodes = (NodeList) xPath.evaluate("//book[@year > 1997]/@*|//book[@year > 1997]/title/text()", document, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }
}
