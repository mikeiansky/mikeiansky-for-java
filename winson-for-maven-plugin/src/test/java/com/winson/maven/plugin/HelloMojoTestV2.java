package com.winson.maven.plugin;

import junit.framework.TestCase;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author winson
 * @date 2022/6/29
 **/
public class HelloMojoTestV2 {

    @Rule
    public MojoRule rule = new MojoRule() {

        @Override
        protected void before() throws Throwable {
            System.out.println("------> before");
//            File file = new File("D:\\work\\java\\winson-for-java\\winson-for-maven-plugin\\pom.xml");
//            configureMojo(new HelloMojo(), "winson-for-maven-plugin", file);
        }

        @Override
        protected void after() {
            System.out.println("------> after");
        }
    };

    /**
     * @throws Exception if any
     */
    @Test
    public void testSomething()
            throws Exception {
//        File pom = rule.getTestFile("src/test/resources/unit/project-to-test/pom.xml");
//        assertNotNull(pom);
//        assertTrue(pom.exists());
        System.out.println("-------> 1");
        File file = new File("D:\\work\\java\\winson-for-java\\winson-for-maven-plugin\\src\\test\\resources\\pom.xml");

        System.out.println("-------> 2");
        HelloMojo myMojo = (HelloMojo) rule.lookupMojo("winson-hello", file);
        assertNotNull(myMojo);
        System.out.println("-------> 3");
        myMojo.execute();
        System.out.println("-------> 4");
    }

}