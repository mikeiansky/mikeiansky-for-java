package com.winson.pojo;

import org.jetbrains.annotations.NotNull;

/**
 * @author winson
 * @date 2021/10/10
 **/
public class Animal implements Comparable<Animal>{

    public int size;

    public void eat(){
        System.out.println("animal eat");
    }

    public void sleep(){
        System.out.println("animal sleep");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "size=" + size +
                '}';
    }

    @Override
    public int compareTo(@NotNull Animal o) {
        return size - o.size;
    }

}
