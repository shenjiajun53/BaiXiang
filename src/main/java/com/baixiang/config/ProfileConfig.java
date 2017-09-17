package com.baixiang.config;

import com.baixiang.model.TomcatInitBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("debug")
    TomcatInitBean getDevTomcatInitBean() {
        return new TomcatInitBean(5006, "/baixiang");
    }

    @Bean
    @Profile("release")
    TomcatInitBean getProdTomcatInitBean() {
        return new TomcatInitBean(8080, "/baixiang");
    }
}
