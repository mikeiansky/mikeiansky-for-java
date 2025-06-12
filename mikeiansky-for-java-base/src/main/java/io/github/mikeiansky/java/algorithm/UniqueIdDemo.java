package io.github.mikeiansky.java.algorithm;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 *
 * @author mike ian
 * @date 2025/6/12
 * @desc
 **/
public class UniqueIdDemo {

    public static void main(String[] args) {

        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();
        System.out.println(uuid);

        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(simpleUUID);

        //生成类似：5b9e306a4df4f8c54a39fb0c
        String id1 = ObjectId.next();
        System.out.println(id1);

        //方法2：从Hutool-4.1.14开始提供
        String id2 = IdUtil.objectId();
        System.out.println(id2);

        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long sid1 = snowflake.nextId();
        System.out.println(sid1);

        //简单使用
        long sid2 = IdUtil.getSnowflakeNextId();
        System.out.println(sid2);

    }

}
