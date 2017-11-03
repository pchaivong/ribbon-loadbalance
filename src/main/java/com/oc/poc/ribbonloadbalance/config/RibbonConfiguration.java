package com.oc.poc.ribbonloadbalance.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.client.config.IClientConfigKey;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import io.fabric8.spring.cloud.kubernetes.ribbon.KubernetesServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pchaivong on 11/3/2017 AD.
 */

public class RibbonConfiguration {

    private static final String NAME = "simple-hostname";


    @Autowired
    private IClientConfig ribbonClientConfig;

    @Bean
    public IPing pingUril(IClientConfig config) {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config){
        return new WeightedResponseTimeRule();
    }

    @Bean
    public IClientConfig clientConfig(){
        return IClientConfig.Builder.newBuilder()
                .withDefaultValues()
                .withLoadBalancerEnabled(true)
                .withServerListRefreshIntervalMills(1000)
                .build()
                .set(IClientConfigKey.Keys.NIWSServerListClassName, KubernetesServerList.class.getName());
    }
}
