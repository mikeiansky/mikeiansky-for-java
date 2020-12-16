package com.winson.calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author winson
 * @date 2020/12/11
 * @desc 科学计算的测试类
 **/
public class TestMath {

    public static void main(String[] args) throws ParseException {
        String tdStr = "2020-12-15 00:00:00";
        String cdStr = "2020-12-16 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetDate = sdf.parse(tdStr);
        Date currentDate = sdf.parse(cdStr);
//        System.out.println(currentDate.getTime()-targetDate.getTime());
        System.out.println(Math.pow(1.1,50));
    }

}
