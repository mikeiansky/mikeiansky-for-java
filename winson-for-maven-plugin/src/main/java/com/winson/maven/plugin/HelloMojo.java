package com.winson.maven.plugin;

import com.google.inject.name.Named;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/6/28
 **/
@Mojo(name = "hello")
public class HelloMojo extends AbstractMojo {

    @Parameter
    private String company;

    @Parameter(defaultValue = "${project.build.directory}")
    private String buildDir;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Parameter(property = "age")
    private String age;

    @Parameter
    private String second;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello - winson - maven - plugin");
        getLog().info("------ > Thread : " + Thread.currentThread().getName());
        for (Object key : getPluginContext().keySet()) {
            getLog().info("key:" + key + " , value class : " + getPluginContext().get(key).getClass());
        }
        getLog().info("--------- custom properties --------");
        getLog().info("company : " + company);
        getLog().info("project : " + project);
        getLog().info("age : " + age);
        getLog().info("second : " + second);
        getLog().info("project build directory : " + project.getBuild().getDirectory());
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        getLog().info("complete");
    }

}
