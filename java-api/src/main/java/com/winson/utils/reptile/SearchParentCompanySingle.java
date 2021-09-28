package com.winson.utils.reptile;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class SearchParentCompanySingle {

    public static class MyCompany {
        public int companyId;
        public String companyFullName;
    }

    public static final String searchUrlPrefix = "https://www.tianyancha.com/search?key=";
    public static boolean needExist = false;
    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "D:\\service\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(searchUrlPrefix + "aaaa");
        scan.next();

        Set<MyCompany> companyFullNameList = new HashSet<>();
        MyCompany myCompany = new MyCompany();
        myCompany.companyId = 1;
        myCompany.companyFullName = "深圳市乐有家房产交易交易有限公司碧荔分公司";
        companyFullNameList.add(myCompany);

        System.out.println("start ... ");
        for (MyCompany company : companyFullNameList) {
            String detailUrl = searchDetailUrl(driver, company.companyFullName);
            if (needExist) {
                break;
            }
            String parentCompanyName = null;
            if (detailUrl != null && detailUrl.startsWith("http")) {
                parentCompanyName = searchParentCompany(driver, detailUrl);
            }
            if (needExist) {
                break;
            }
            if (parentCompanyName == null) {
                parentCompanyName = "no_parent_company";
            }
            String realParentCompanyName = parentCompanyName;
            String data = company.companyId + "," + company.companyFullName + "," + realParentCompanyName + "\r\n";
            System.err.println("查询总公司情况：" + data);
        }
        System.out.println("end ... ");

    }

    public static boolean catchCheckUrl(String url) {
        boolean result = url.startsWith("https://antirobot.tianyancha.com") || url.startsWith("https://www.tianyancha.com/login");
        if (result) {
            JFrameTest.showAlter();
        }
        return result;
    }

    public static String searchDetailUrl(WebDriver driver, String company) {
        try {
            String ecStr = URLEncoder.encode(company, "utf-8");
            String searchUrl = searchUrlPrefix + ecStr;
            driver.get(searchUrl);
            while (catchCheckUrl(driver.getCurrentUrl())){
                System.err.println("1111 xxxxxxxxxxxxxxxxxxxxxxxxx");
                scan.next();
                driver.get(searchUrl);
            }
            String source = driver.getPageSource();
            String url = ParseTYCSearchCompany.getCompanyDetailUrl(company, source);
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String searchParentCompany(WebDriver driver, String url) {
        driver.get(url);
        while (catchCheckUrl(driver.getCurrentUrl())){
            scan.next();
            driver.get(url);
        }
        String source = driver.getPageSource();
        return ParseTYCSearchCompanyDetail.getParentCompany(source);
    }

}
