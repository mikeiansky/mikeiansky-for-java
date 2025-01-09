package io.github.mikeiansky.java.base.jdk.generic;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.Data;

/**
 * @author mike ian
 * @date 2025/1/9
 * @desc 带有泛型的序列化
 **/
public class SerializeWithGenericDemo {

    @Data
    public static class Response<T> {
        private int code;
        private String message;
        private T data;
    }

    @Data
    public static class One {
        private String tagOne;
    }

    @Data
    public static class Two {
        private String tagTwo;
    }

    @Data
    public static class Three {
        private String tagThree;
    }

    public static void main(String[] args) {
        Response<One> response = new Response<>();
        response.setCode(200);
        response.setMessage("success");
        One one = new One();
        one.setTagOne("main-one");
        response.setData(one);
        String value = JSON.toJSONString(response);
        System.out.println(value);

        Response<One> deserializeOne = JSON.parseObject(value, new TypeReference<>() {
        });
        System.out.println("deserializeOne : " + deserializeOne);
        System.out.println("deserializeOne.data.class : " + deserializeOne.data.getClass());
        Response<One> deserializeOne2 = JSON.parseObject(value, Response.class);
        System.out.println("deserializeOne2 : " + deserializeOne2);
//        System.out.println("deserializeOne2.data.class : " + deserializeOne2.data.getClass());

        Response<Two> deserializeTwo = JSON.parseObject(value, new TypeReference<>() {
        });
        System.out.println("deserializeTwo : " + deserializeTwo);
        System.out.println("deserializeTwo.data.class : " + deserializeTwo.data.getClass());

        Response<Three> deserializeThree = JSON.parseObject(value, new TypeReference<>() {
        });
        System.out.println("deserializeThree : " + deserializeThree);
        System.out.println("deserializeThree.data.class : " + deserializeThree.data.getClass());
    }

}
