package com.winson.utils.reptile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author winson
 * @date 2021/9/18
 **/
public class CompanyReptileUtils {

    // 企查查
    // https://www.qcc.com/firm/9618c2f06a4570efaf7a898ff49dfd05.html

    public static void queryList() {
//        String url1 = "https://www.qcc.com";

//        String url1 = "https://www.qcc.com/web/search?key=apple";
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(url1)
//                .method("GET", null)
////                .addHeader("Cookie", "QCCSESSID=20084a46e450ea9d41d8bdde7c; acw_tc=7793461c16319516755346092e090a29f2da048fe35475e0c7d1858261")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String url = "https://www.qcc.com/web/search?key=apple";
        String url = "https://www.qcc.com/web/search?key=%E6%B7%B1%E5%9C%B3%E5%B8%82%E4%B9%90%E6%9C%89%E5%AE%B6%E6%88%BF%E4%BA%A7%E4%BA%A4%E6%98%93%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E9%98%B3%E5%85%89%E6%B5%B7%E6%BB%A8%E4%BA%8C%E5%88%86%E5%85%AC%E5%8F%B8";

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Cookie", "acw_tc=7793462716319523112962421e8eb097f56d1dcf24fd6dd3f874c04207")
                    .build();
            Response response = client.newCall(request).execute();
            String listResult = response.body().string();
            System.out.println("response : " + listResult);
            parseHtml(listResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryDetail() {
        String url1 = "https://www.qcc.com/web/search?key=apple";
        String url2 = "https://www.qcc.com/firm/9618c2f06a4570efaf7a898ff49dfd05.html";

        String queryUrl = url2;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(queryUrl)
                .method("GET", null)
                .addHeader("Cookie", "acw_tc=7793461d16319490689426733eb500f77bda92b145f69b1e082d0d40d7")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println("body : " + response.body().string());
            System.out.println("response code ----> ");
            System.out.println(response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseHtml(String html){
        System.out.println("parse Html --------> start");
        Document doc = Jsoup.parse(html);
//        Elements elements = doc.getElementsByTag("table");
//        for (Element element : elements) {
//            System.out.println("table for ---> ");
////            System.out.println(element.html());
//
//            Elements trs = element.getElementsByTag("tr");
//            for (Element tr : trs) {
//                System.out.println("tr ---> ");
//                System.out.println(tr.html());
//                System.out.println("tr <-- ");
//            }
//
//            System.out.println("table for <<<<");
//        }
        String key = "https://www.qcc.com/firm";
        Elements as = doc.getElementsByTag("a");
        for (Element ahref : as) {
            String href = ahref.attr("href");
            if(href != null && href.startsWith(key)){
                System.out.println("href ===> " + href);
                return;
            }
        }

        System.out.println("parse Html --------> end");
    }

    public static void main(String[] args) {
//        String urlStr = "https://www.qcc.com/firm/9618c2f06a4570efaf7a898ff49dfd05.html";
//        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            int resCode = connection.getResponseCode();
//            System.out.println("result code : " + resCode);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        queryList();
        queryDetail();

//        String url = "https://www.qcc.com/web/search?key=apple";
//        WebDriver driver = new ChromeDriver();
//        driver.get(url);
//        System.out.println(driver.getTitle());


    }

}
