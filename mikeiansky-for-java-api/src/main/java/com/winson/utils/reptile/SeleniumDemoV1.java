package com.winson.utils.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class SeleniumDemoV1 {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\service\\chromedriver\\chromedriver.exe");

//        String url1 = "https://www.qcc.com/web/search?key=%E7%8F%A0%E6%B5%B7%E6%B4%BE%E8%AF%BA%E7%A7%91%E6%8A%80%E8%82%A1%E4%BB%BD%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8";
//        String url2 = "https://www.qcc.com/firm/fc7d253d4525fdcf4624f0391f5330fa.html";
//        String url3 = "https://www.qcc.com/";
//        String url4 = "https://aiqicha.baidu.com/company_detail_35436941599506";
//        String url5 = "https://aiqicha.baidu.com/s?q=%E5%B9%BF%E5%B7%9E%E5%B8%82%E4%B9%90%E6%9C%89%E5%AE%B6%E6%88%BF%E4%BA%A7%E7%BB%8F%E7%BA%AA%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E7%8C%8E%E5%BE%B7%E5%A4%A7%E9%81%93%E7%AC%AC%E4%B8%80%E5%88%86%E5%85%AC%E5%8F%B8&t=0";
//        String url6 = "https://www.tianyancha.com/company/4011289085";
        String url7 = "https://www.tianyancha.com/search?key=%E5%B9%BF%E5%B7%9E%E5%B8%82%E4%B9%90%E6%9C%89%E5%AE%B6%E6%88%BF%E4%BA%A7%E7%BB%8F%E7%BA%AA%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8";

        WebDriver driver = new ChromeDriver();
//        driver.get(url5);
//        driver.get(url1);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        driver.get(url7);

        String source = driver.getPageSource();
        Document doc = Jsoup.parse(source);
        Elements elements = doc.getElementsByClass("login-errormsg collapse");
//        System.out.println(elements.size());
        for (Element element : elements) {
            System.out.println(element.html());
        }


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        int size = 100;
//        for (int i = 0; i < size; i++) {
//            driver.get(url7);
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }
//
////        String title = driver.getTitle();
////        System.out.printf("winson::::title:::::"+title);
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        driver.close();

    }

}
