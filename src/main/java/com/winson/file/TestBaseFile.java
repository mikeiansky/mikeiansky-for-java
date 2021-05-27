package com.winson.file;

import java.io.File;

/**
 * @author winson
 * @date 2021/5/27
 **/
public class TestBaseFile {

    public static void main(String[] args) {
        System.out.println("test base file start ... ");

        File dir = new File("D:\\temp\\winson\\100");
        File[] files = dir.listFiles();
        if(files != null){
            for (File file : files) {
                file.delete();
            }
        }
        dir.mkdirs();

        System.out.println("test base file end ... ");
    }

}
