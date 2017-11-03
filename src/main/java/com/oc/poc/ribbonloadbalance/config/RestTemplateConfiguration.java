package com.oc.poc.ribbonloadbalance.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pchaivong on 11/3/2017 AD.
 */

@Configuration
@RibbonClient(name ="simple-hostname", configuration = RibbonConfiguration.class)
public class RestTemplateConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){ return new RestTemplate(); }
}
