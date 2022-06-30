package com.winson.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author winson
 * @date 2022/6/30
 **/
@Mojo(name = "info")
public class InfoMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("info - mojo - execute");
        getLog().info("info - mojo - complete");
    }

}
