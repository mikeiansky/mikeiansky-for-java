package com.winson.app002;

import com.winson.app003.Hello003;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author mike ian
 * @date 2023/6/2
 * @desc
 **/
public class Hello002Test extends TestCase {


    @Test
    public void testHello() throws Exception {
        Hello002.hello("ciwei");
        Hello003.hello("ciwei");
    }

}