package com.winson.document;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author com.winson
 * @date 2020/12/18
 * @desc excel 测试类
 **/
public class TestExcel {

    public static int index = 0;

    public static void main(String[] args) {
        String emailRegex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        int i;
        int count = 0;
        Sheet sheet;
        Workbook book;
        Map<String,String> record = new HashMap<>();
//        List<String> userIdList = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        try {
            //t.xls为要读取的excel文件名
            book = Workbook.getWorkbook(new File("D:\\bas_company_user.xls"));
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
//                String mobile = sheet.getCell(2, i).getContents();
//                String position = sheet.getCell(10, i).getContents();
//                String name = sheet.getCell(12, i).getContents();
//                String school = sheet.getCell(14, i).getContents();
//                String graduate = sheet.getCell(15, i).getContents();
//                String downloadUrl = sheet.getCell(16, i).getContents();
//                if (downloadUrl != null && downloadUrl.startsWith("http")) {
//                    // 姓名-投递岗位-学校-年级
//                    String suffix = downloadUrl.substring(downloadUrl.lastIndexOf("."));
//                    String fileName = name + "-" + position + "-" + school + "-" + graduate + "-" + mobile+suffix;
//                    System.out.println("download-->"+fileName);
////                    download(fileName,downloadUrl);
//                }

//                String userId = sheet.getCell(0, i).getContents();
//                System.out.println(userId);
//                if(record.get(userId)!=null){
//                    System.out.println("repeat user id : " + userId);
//                }
//                record.put(userId, userId);
//                userIdList.add(userId);

//                String name = sheet.getCell(2, i).getContents();
//                String school = sheet.getCell(3, i).getContents();
//                String major = sheet.getCell(4, i).getContents();
//                String degree = sheet.getCell(5, i).getContents();
//                String mobile = sheet.getCell(6, i).getContents();
//                String downloadUrl = sheet.getCell(7, i).getContents();
//                String suffix = downloadUrl.substring(downloadUrl.lastIndexOf("."));
//                String fileName = name+"-"+school+"-"+major+"-"+degree+"-"+mobile+"-附件简历"+suffix;
//                System.out.println(fileName);
//                download(fileName,"https://img.shixijob.net/"+downloadUrl);
                String userId = sheet.getCell(0, i).getContents();
                String email = sheet.getCell(1, i).getContents();
//                System.out.println(name);
//                userIdList.add(Integer.valueOf(userId));

                if(!email.matches(emailRegex)){
//                    System.out.println("userId : "+userId+" , email : '" + email.trim() +"'");
                    String sql = "update  ald_bas.bas_company_user set email = '"+email.trim()+"'" + " where user_id = " + userId + " ;";
                    System.out.println(sql);
                    count++;
                }

                i++;
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("not match email : " + count);
//        System.out.println(String.join(",",userIdList));

//        int size = 500;
//        Map<Integer,List<Integer>> result = userIdList.stream().collect(Collectors.groupingBy(uid-> {
//            int key = index/size;
//            index++;
//            return key;
//        },Collectors.toList()));
//
//        System.out.println(result.size());
//
//        int total = 0;
//        for (Integer key : result.keySet()) {
//            System.out.println(result.get(key));
//            total += result.get(key).size();
//        }
//
//        System.out.println(total);
//        System.out.println(userIdList);
//        System.out.println(userIdList.size());

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
