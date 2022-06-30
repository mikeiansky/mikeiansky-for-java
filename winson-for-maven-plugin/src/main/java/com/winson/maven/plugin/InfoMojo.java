package com.winson.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * @author winson
 * @date 2022/6/30
 **/
@Mojo(name = "info",
        defaultPhase = LifecyclePhase.PACKAGE,
//        requiresProject = true,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME
)
public class InfoMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("info - mojo - execute");
        getLog().info("info - mojo - complete");
    }

}
