package com.winson.utils.reptile;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class SearchParentCompany10000 {

    public static class MyCompany {
        public int companyId;
        public String companyFullName;
    }

    public static final String searchUrlPrefix = "https://www.tianyancha.com/search?key=";
    public static boolean needExist = false;
    public static final Scanner scan = new Scanner(System.in);
    public static final String outPath = "D:\\work\\temp\\out\\out-10000.txt";

    public static int readLine() {
        int count = 0;
        try {
            FileInputStream in = new FileInputStream(outPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (reader.readLine() != null) {
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        int count = readLine();
        count++;
        String filePath = "D:\\work\\temp\\single_bas_company_2021-09-24.xlsx";
        List<MyCompany> companyFullNameList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = sheets.getSheet("bas_company");
//        int size = 18772;
        int offset = 10000;
        int from = count;
        int realFrom = from + offset;
        int size = 18772;
        for (int i = realFrom; i < size; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell idCell = row.getCell(0);
            XSSFCell nameCell = row.getCell(1);
            XSSFCell fullNameCell = row.getCell(2);
            if (fullNameCell != null) {
                MyCompany company = new MyCompany();
                company.companyFullName = fullNameCell.toString();
                company.companyId = (int) Float.parseFloat(idCell.toString());
                companyFullNameList.add(company);
//                System.out.println(company.companyFullName);
            }
        }
        // lock table test
//        boolean exit = true;
//        if(exit){
//            return;
//        }

        System.out.println(companyFullNameList.size());

        FileOutputStream out = new FileOutputStream(outPath, true);

        System.setProperty("webdriver.chrome.driver", "D:\\service\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(searchUrlPrefix + "aaaa");
        scan.next();
//        driver.get(searchUrlPrefix + "aaaa");
//        System.in.read();


        int sleeptime = 500;
        System.out.println("start ... ");
        for (MyCompany company : companyFullNameList) {
            String detailUrl = searchDetailUrl(driver, company.companyFullName);
            if (needExist) {
                break;
            }
            String parentCompanyName = null;
            if (detailUrl != null && detailUrl.startsWith("http")) {
//                Thread.sleep(sleeptime);
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
            out.write(data.getBytes());
            out.flush();
//            Thread.sleep(sleeptime);
        }

        out.flush();
        out.close();
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
                scan.next();
                driver.get(searchUrl);
            }
//            if (catchCheckUrl(driver.getCurrentUrl())) {
//                needExist = true;
//                return null;
//            }
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
//        if (catchCheckUrl(driver.getCurrentUrl())) {
//            needExist = true;
//            return null;
//        }
        String source = driver.getPageSource();
        return ParseTYCSearchCompanyDetail.getParentCompany(source);
    }

}
