package com.winson.jdkapi.resource.xml;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author winson
 * @date 2022/4/28
 **/
public class MySaxXml {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        System.out.println("MySaxXml -----> 1");


        URL url1 = MySaxXml.class.getResource("/log4j2.xml");
//        URL url2 = MySaxXml.class.getResource("log4j2.xml");
        File file1 = new File(url1.getFile());
        System.out.println(file1.exists());
        System.out.println(new String(IOUtils.toByteArray(url1)));

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(file1, new DefaultHandler() {

            @Override
            public void startDocument() throws SAXException {
                System.out.println("startDocument ==> ");
            }

            @Override
            public void endDocument() throws SAXException {
                System.out.println("endDocument ==> ");
            }

            @Override
            public void startPrefixMapping(String prefix, String uri) throws SAXException {
                System.out.println("startPrefixMapping ==> prefix : " + prefix + " , uri : " + uri);
            }

            @Override
            public void endPrefixMapping(String prefix) throws SAXException {
                System.out.println("endPrefixMapping ==> prefix : " + prefix);
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                System.out.println("startElement ==> localName : " + localName + " , qName : " + qName + " , attributes-length : " + attributes.getLength());
                for (int i = 0; i < attributes.getLength(); i++) {
                    String qname = attributes.getQName(i);
                    System.out.println("qname : " + attributes.getQName(i) + " , value : " + attributes.getValue(i));
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                System.out.println("endElement ==> localName : " + localName + " , qName : " + qName);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
//                System.out.println("characters ==> start : " + start + " , length : " + length + " , ch : " + new String(ch));
//                System.out.println("characters ==> start : " + start + " , length : " + length );
                System.out.println("characters ==> start : " + start + " , length : " + length + " , ch : " + new String(ch, start, length));
            }
        });


        System.out.println("MySaxXml -----> 2");
    }

}
