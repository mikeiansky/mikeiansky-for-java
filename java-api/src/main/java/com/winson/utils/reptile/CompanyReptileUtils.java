package com.winson.utils.reptile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/9/18
 **/
public class CompanyReptileUtils {

    // 企查查
    // https://www.qcc.com/firm/9618c2f06a4570efaf7a898ff49dfd05.html

    public static final String COOKIE = "acw_tc=7793462216323898961168657e157ee1fe1f3c80b74b28f800cf23d37a";
//    public static final String COOKIE = "QCCSESSID=c63c0f6224a5ca99bea7aa6233; Domain=.qcc.com; Path=/; HttpOnly";

    public static String queryList(String key) {
        String urlKey = null;
        try {
            urlKey = URLEncoder.encode(key, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://www.qcc.com/web/search?key=" + urlKey;
        System.out.println("query list url is : " + url);
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Cookie", COOKIE)
                    .build();
            Response response = client.newCall(request).execute();
            String listResult = response.body().string();
//            System.out.println("response : " + listResult);
            String href = parseHtml(listResult);
            if (href != null) {
                return queryDetail(href);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryDetail(String queryUrl) {
        System.out.println("queryDetail url : " + queryUrl);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(queryUrl)
                .method("GET", null)
                .addHeader("Cookie", COOKIE)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
//            System.out.println("body : " + result);
//            System.out.println("response code ----> ");
//            System.out.println(response.code());
            return parseDetail(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseHtml(String html) {
//        System.out.println("parse Html --------> start");
        Document doc = Jsoup.parse(html);
        String key = "https://www.qcc.com/firm";
        Elements as = doc.getElementsByTag("a");
        for (Element ahref : as) {
            String href = ahref.attr("href");
            if (href != null && href.startsWith(key)) {
                return href;
            }
        }

//        System.out.println("parse Html --------> end");
        return null;
    }

    public static String parseDetail(String html) {
//        System.out.println("parse detail -----------> ");


        String target = "总公司";

        Document doc = Jsoup.parse(html);

        Elements errors = doc.getElementsByClass("error-right");
        if (errors != null && errors.size() > 0) {
            Elements errorH2Es = errors.get(0).getElementsByTag("h2");
            String errorMsg = "unknow";
            if (errorH2Es != null && errorH2Es.size() > 0) {
                errorMsg = errorH2Es.get(0).html();
            }
            return "请求出错:" + errorMsg;
        }

        Elements h3s = doc.getElementsByTag("h3");
        boolean existsParentCompany = false;
        for (Element h3 : h3s) {
            if (h3.html().contains(target)) {
                existsParentCompany = true;
                break;
            }
        }

        if (!existsParentCompany) {
            return "当前公司是总公司";
        }

        Elements elements = doc.getElementsByClass("app-ntable");
//        System.out.println(elements.html());
        Element element = elements.get(0);

        Elements trElements = element.getElementsByTag("tr");
        String parentCompany = trElements.get(1).getElementsByTag("td").get(1).getElementsByTag("a").html();
        return parentCompany;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\work\\java\\winson-for-java\\java-api\\src\\main\\java\\com\\winson\\utils\\reptile\\bas_company.xlsx";
        List<String> companyFullNameList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = sheets.getSheet("bas_company");
//        int size = 20000;
        int size = 1;
        for (int i = 1; i < size; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell nameCell = row.getCell(1);
            XSSFCell fullNameCell = row.getCell(2);
            if (fullNameCell != null) {
//                System.out.println("i:" + i + " - " + fullNameCell.toString());
                companyFullNameList.add(fullNameCell.toString());
            }
        }

        for (String companyFullName : companyFullNameList) {
            //        String company = "广州市乐有家房产经纪有限公司猎德大道第一分公司";
            String parentCompany = queryList(companyFullName);
            System.out.println(companyFullName + "==总公司[" + parentCompany + "]");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String company = "广州市乐有家房产经纪有限公司猎德大道第一分公司";
        String parentCompany = queryList(company);
        System.out.println(company + "===总公司【" + parentCompany + "】");

        System.out.println("app end .... ");
    }

}
