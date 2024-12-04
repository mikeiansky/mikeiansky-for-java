package com.winson.utils.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class ParseTYCSearchCompanyDetail {

    public static void main(String[] args) throws Exception {

        String path = "D:\\work\\java\\winson-for-java\\java-api\\src\\main\\java\\com\\winson\\utils\\reptile\\search_company_detail.xml";
        FileInputStream in = new FileInputStream(path);
        byte[] buf = new byte[1024 * 1024];
        int length = in.read(buf);
        String source = new String(buf, 0, length);

        System.out.println(getParentCompany(source));

    }

    public static String getParentCompany(String source) {
        Document document = Jsoup.parse(source);
//        System.out.println(document.getElementsByClass("block-data"));
        Elements parentCompanyElements = document.getElementsByAttributeValue("tyc-event-ch", "CompangyDetail.zonggongsi");
        if (parentCompanyElements != null && parentCompanyElements.size() > 0) {
            Elements ahrefs = parentCompanyElements.get(0).getElementsByTag("a");
            if (ahrefs != null && ahrefs.size() > 0) {
                return ahrefs.get(0).html();
            }
        }
        return "no_parent_company";
    }

}
