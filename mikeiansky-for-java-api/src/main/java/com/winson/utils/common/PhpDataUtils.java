package com.winson.utils.common;

import lombok.Data;
//import org.phprpc.util.AssocArray;
//import org.phprpc.util.Cast;
//import org.phprpc.util.PHPSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mike ian
 * @date 2023/5/16
 * @desc
 **/
public class PhpDataUtils {

    @Data
    public static class MyTag {
        Integer id;
        String name;
        String tag;
    }

    public static void main(String[] args) {
        try {
//            PHPSerializer ps = new PHPSerializer();
//            List<MyTag> s = new ArrayList<>();
//            AssocArray aa;
//
//            aa = (AssocArray) ps.unserialize(new String("a:3:{i:0;a:2:{s:2:\"id\";i:15;s:3:\"tag\";s:5:\"C/C++\";}i:1;a:2:{s:2:\"id\";i:16;s:3:\"tag\";s:4:\"Java\";}i:2;a:2:{s:2:\"id\";i:18;s:3:\"tag\";s:3:\"PHP\";}}").getBytes());
//            for(int i=0;i<aa.size();i++){
//                s.add((MyTag) Cast.cast(aa.get(i), MyTag.class));
//            }
//
//            System.out.println(s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
