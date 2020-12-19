package com.winson.document;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author winson
 * @date 2020/12/18
 * @desc excel 测试类
 **/
public class TestExcel {

    public static void main(String[] args) {
        int i;
        Sheet sheet;
        Workbook book;
        try {
            //t.xls为要读取的excel文件名
            book = Workbook.getWorkbook(new File("D:\\form.xls"));
            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(0);
            int rows = sheet.getRows();
            //获取左上角的单元格
//            cell1 = sheet.getCell(0, 0);
//            System.out.println("标题：" + cell1.getContents());
            i = 1;
            while (i < rows) {
                //获取每一行的单元格
                // 手机号码
                String mobile = sheet.getCell(2, i).getContents();
                String position = sheet.getCell(10, i).getContents();
                String name = sheet.getCell(12, i).getContents();
                String school = sheet.getCell(14, i).getContents();
                String graduate = sheet.getCell(15, i).getContents();
                String downloadUrl = sheet.getCell(16, i).getContents();
                if (downloadUrl != null && downloadUrl.startsWith("http")) {
                    // 姓名-投递岗位-学校-年级
                    String suffix = downloadUrl.substring(downloadUrl.lastIndexOf("."));
                    String fileName = name + "-" + position + "-" + school + "-" + graduate + "-" + mobile+suffix;
                    System.out.println("download-->"+fileName);
//                    download(fileName,downloadUrl);
                }
                i++;
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void download(String fileName, String downloadUrl) {
        try {
            URL url = new URL(downloadUrl);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream str = conn.getInputStream();
            byte[] bs = new byte[1024];
            int len = 0;
            File saveDir = new File("D:\\temp");
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream out = new FileOutputStream(file);
            while ((len = str.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
            out.flush();
            out.close();
            str.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
