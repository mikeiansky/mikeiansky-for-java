package com.winson.autoconfig;

import com.winson.lib.two.LibTwoManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;

/**
 * @author winson
 * @date 2022/4/24
 **/
@org.springframework.context.annotation.Configuration
@ConditionalOnClass(LibTwoManager.class)
public class WinsonAutoConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("WinsonAutoConfig =======> afterPropertiesSet");
    }

}
