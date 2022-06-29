package com.winson.maven.plugin;

import com.google.inject.name.Named;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/6/28
 **/
@Mojo(name = "winson-hello")
public class HelloMojo extends AbstractMojo {

    @Parameter
    private String dirPath;

    @Parameter
    private String nothing;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("------ > 001");
        getLog().info("Hello - winson - maven - plugin");
        System.out.println("------ > 002");
        System.out.println("------ > Thread : " + Thread.currentThread().getName());
        for (Object key : getPluginContext().keySet()) {
            System.out.println("key:" + key + " , value class : " + getPluginContext().get(key).getClass());
        }
//        CountDownLatch latch = new CountDownLatch(1);
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("--------- countdown ...... ");
//        }
        System.out.println("dirPath : " + dirPath);
        System.out.println("nothing : " + nothing);
        System.out.println("project : " + project);
        System.out.println("------ > 003");
    }

}
