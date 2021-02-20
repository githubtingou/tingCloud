package com.ting.activity.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * activity配置类
 *
 * @author ting
 * @version 1.0
 * @date 2021/01/28
 */
@Configuration
public class ActivityConfig implements ProcessEngineConfigurationConfigurer {
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 重写id生成策略
        springProcessEngineConfiguration.setIdGenerator(new UUIDGenerator());
    }
}
