package com.winson.utils.encoding;

import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV10 {

    public static void main(String[] args) {
        System.out.println(Charset.defaultCharset());
        String str = "这里是中文";
        System.out.println(str);
    }

}
