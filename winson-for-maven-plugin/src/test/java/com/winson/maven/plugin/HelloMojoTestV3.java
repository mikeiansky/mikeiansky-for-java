package com.winson.maven.plugin;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.PlexusTestCase;

import java.io.File;

/**
 * @author winson
 * @date 2022/6/29
 **/
public class HelloMojoTestV3 extends PlexusTestCase {
    /**
     * {@inheritDoc}
     */
    protected void setUp()
            throws Exception {
        // required
        System.out.println("setUp ------> 1");
        super.setUp();
        System.out.println("setUp ------> 2");
    }

    /**
     * {@inheritDoc}
     */
    protected void tearDown()
            throws Exception {
        // required
        System.out.println("tearDown ------> 1");
        super.tearDown();
        System.out.println("tearDown ------> 2");
    }

    /**
     * @throws Exception if any
     */
    public void testSomethingV1() throws Exception {
        System.out.println("testSomethingV1 ---> 1");
        String path = "D:\\work\\java\\winson-for-java\\winson-for-maven-plugin\\src\\test\\resources\\pom.xml";
        File pom = getTestFile(path);
        File file = new File(path);
        System.out.println("file exists : " + file.exists());
        System.out.println("pom exist : " + pom.exists());
        Object obj = lookup("winson-hello");
        System.out.println(obj);
//        assertNotNull(pom);
//        assertTrue(pom.exists());
//        HelloMojo myMojo = (HelloMojo) lookupMojo("winson-hello", path);
//        assertNotNull(myMojo);
//        myMojo.execute();
    }

}