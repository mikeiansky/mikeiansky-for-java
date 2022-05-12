package com.winson.jdkapi.collection;

import org.apache.poi.ss.formula.functions.T;

import java.util.HashSet;

/**
 * @author winson
 * @date 2022/5/11
 **/
public class HashSetDemoV1 {

    public static class Tag{
        public String tag;
        public int version;

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Tag)){
                return false;
            }
            Tag ot = (Tag) obj;
            return ot.version == version;
        }

        @Override
        public int hashCode() {
            return version;
        }

    }

    public static void main(String[] args) {

        Tag t1 = new Tag();
        Tag t2 = new Tag();

        System.out.println(t1.equals(t2));
//        System.out.println(t1 == t2);

        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());

        HashSet<Tag> tagSet = new HashSet<>();
        tagSet.add(t1);
        tagSet.add(t2);

        System.out.println(tagSet.size());

        Tag t3 = new Tag();
        t3.version = 3;
        tagSet.add(t3);
        System.out.println(tagSet.size());

        // 改变之后能够添加进去，形成了一个新的元素
        t3.version = 4;
        tagSet.add(t3);
        System.out.println(tagSet.size());
        for (Tag tag : tagSet) {
            System.out.println(tag);
        }

    }

}
