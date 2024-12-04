package com.winson.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class HeapOOMDemo {

    static class OOMObject{

    }

    static class InnerObject{

    }

    static class NormalObject{
        private static InnerObject innerObject = new InnerObject();
    }

    private static NormalObject normalObject;

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        normalObject = new NormalObject();
        while (true){
            list.add(new OOMObject());
        }
    }

}
