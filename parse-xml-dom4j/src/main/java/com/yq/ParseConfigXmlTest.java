package com.yq;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

public class ParseConfigXmlTest {
    public static void main(String[] args) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(ClassLoader.getSystemResourceAsStream("mybatis-config.xml"));
        String xpath = "/configuration/environments";
        Element envElement = (Element) document.selectSingleNode(xpath);
        String defaultId = envElement.attributeValue("default");

        String envPath = "/configuration/environments/environment[@id='"+defaultId+"']";
        Element env = (Element) document.selectSingleNode(envPath);
        String transactionManager = env.element("transactionManager").attributeValue("type");
        System.out.println(transactionManager);
        Element dataSource = env.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        List<Element> elementList = dataSource.elements("property");
        for (Element element : elementList) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            System.out.println(name+":"+value);
        }
        String path="//mapper";
        List<Node> nodeList = document.selectNodes(path);
        for (Node node : nodeList) {
            System.out.println(((Element) node).attributeValue("resource"));
        }

    }
    }
