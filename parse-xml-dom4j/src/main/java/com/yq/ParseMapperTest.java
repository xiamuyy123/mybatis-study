package com.yq;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class ParseMapperTest {
    public static void main(String[] args) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(ClassLoader.getSystemResourceAsStream("CarMapper.xml"));
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");

        List<Element> elements = mapper.elements();
        for (Element element : elements) {
            String name = element.getName();
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String sql = element.getTextTrim().replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(name);
            System.out.println(id);
            System.out.println(resultType);
            System.out.println(sql);
        }
    }
}
