package com.winson.basic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author winson
 * @date 2021/7/6
 **/
public class CompareDemo {

    public static class CompareObj{
        public int index;

        @Override
        public String toString() {
            return "CompareObj{" +
                    "index=" + index +
                    '}';
        }

    }

    public static void main(String[] args) {

        List<CompareObj> compareObjList = new ArrayList<>();

        CompareObj co1 = new CompareObj();
        co1.index = 4;
        compareObjList.add(co1);

        CompareObj co2 = new CompareObj();
        co2.index = 2;
        compareObjList.add(co2);

        CompareObj co3 = new CompareObj();
        co3.index = 7;
        compareObjList.add(co3);
        compareObjList.sort(new Comparator<CompareObj>() {
            @Override
            public int compare(CompareObj o1, CompareObj o2) {
                System.out.println("----- once ------");
                System.out.println("o1 : " + o1 + " , o2 : " + o2);
                return o1.index - o2.index;
            }
        });
        System.out.println(compareObjList);
    }


}
