package com.winson.utils.reptile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class SearchParentCompany {

    public static class MyCompany {
        public int companyId;
        public String companyFullName;
    }

    public static final String searchUrlPrefix = "https://www.tianyancha.com/search?key=";
    public static boolean needExist = false;

    public static void main(String[] args) throws Exception {

        String filePath = "D:\\work\\java\\winson-for-java\\java-api\\src\\main\\java\\com\\winson\\utils\\reptile\\has_parent_company.xlsx";
        List<MyCompany> companyFullNameList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = sheets.getSheet("bas_company");
//        int size = 23400;
        int size = 3976;
        int from = 1;
        for (int i = from; i < size; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell idCell = row.getCell(0);
            XSSFCell nameCell = row.getCell(1);
            XSSFCell fullNameCell = row.getCell(2);
            if (fullNameCell != null) {
                MyCompany company = new MyCompany();
                company.companyFullName = fullNameCell.toString();
                company.companyId = (int) Float.parseFloat(idCell.toString());
                companyFullNameList.add(company);
            }
        }

        System.out.println(companyFullNameList.size());

        FileOutputStream out = new FileOutputStream("D:\\temp\\out.txt", true);

        System.setProperty("webdriver.chrome.driver", "D:\\service\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(searchUrlPrefix+"aaaa");

        System.in.read();
        System.out.println("start ... ");
        for (MyCompany company : companyFullNameList) {
            String detailUrl = searchDetailUrl(driver, company.companyFullName);
            if (needExist) {
                break;
            }
            String parentCompanyName = null;
            if (detailUrl != null && detailUrl.startsWith("http")) {
                Thread.sleep(1000);
                parentCompanyName = searchParentCompany(driver, detailUrl);
            }
            if (needExist) {
                break;
            }
            if (parentCompanyName == null) {
                parentCompanyName = "no_parent_company";
            }
            String realParentCompanyName = parentCompanyName;
            String date = company.companyId + "," + company.companyFullName + "," + realParentCompanyName + "\r\n";
            out.write(date.getBytes());
            out.flush();
            Thread.sleep(4000);
        }

        out.flush();
        out.close();
        System.out.println("end ... ");

    }

    public static boolean catchCheckUrl(String url){
        return url.startsWith("https://antirobot.tianyancha.com") || url.startsWith("https://www.tianyancha.com/login");
    }

    public static String searchDetailUrl(WebDriver driver, String company) {
        try {
            String ecStr = URLEncoder.encode(company, "utf-8");
            String searchUrl = searchUrlPrefix + ecStr;
            driver.get(searchUrl);
            if (catchCheckUrl(driver.getCurrentUrl())) {
                needExist = true;
                return null;
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
        if (catchCheckUrl(driver.getCurrentUrl())) {
            needExist = true;
            return null;
        }
        String source = driver.getPageSource();
        return ParseTYCSearchCompanyDetail.getParentCompany(source);
    }

}
