package com.winson.utils.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class ParseTYCSearchCompany {

    public static void main(String[] args) throws Exception {
        String path = "D:\\work\\java\\winson-for-java\\java-api\\src\\main\\java\\com\\winson\\utils\\reptile\\search_lyjgd.xml";
        FileInputStream in = new FileInputStream(path);
        byte[] buf = new byte[1024 * 1024];
        int length = in.read(buf);
        String source = new String(buf, 0 , length);
//        System.out.println(content);

        System.out.println(getCompanyDetailUrl("广州市乐有家房产经纪有限公司猎德大道第一分公司", source));
    }

    public static String getCompanyDetailUrl(String targetCompany , String source){
        String targetCompanyName = "<em>"+targetCompany+"</em>";
        Document document = Jsoup.parse(source);
        Elements elements = document.getElementsByClass("search-block header-block-container");
        for (Element element : elements) {
            Elements headers = element.getElementsByClass("header");
            for (Element header : headers) {
                Elements ahrefs = header.getElementsByTag("a");
                for (Element ahref : ahrefs) {
                    String content = ahref.html();
                    if(content!=null && content.equals(targetCompanyName)){
//                        System.out.println(ahref.html());
//                        System.out.println(ahref.attr("href"));
                        return ahref.attr("href");
                    }
                }
            }
        }
        return null;
    }


}
