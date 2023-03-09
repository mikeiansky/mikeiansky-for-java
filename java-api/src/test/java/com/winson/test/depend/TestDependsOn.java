package com.winson.test.depend;

//import org.junit.Test;

import org.testng.annotations.Test;

/**
 * @author Mike Ian
 * @date 2023/3/9
 **/
public class TestDependsOn {

    @Test
    public void testThird(){
        System.out.println("this is third test!");
    }

    @Test(dependsOnMethods = "testThird")
    public void testSecond(){
        System.out.println("this is second test!");
    }

    @Test(dependsOnMethods = {"testSecond"})
    public void testFirst(){
        System.out.println("this is first test");
    }


}
