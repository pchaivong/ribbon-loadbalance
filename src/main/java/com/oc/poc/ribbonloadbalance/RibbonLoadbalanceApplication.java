package com.oc.poc.ribbonloadbalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonLoadbalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonLoadbalanceApplication.class, args);
	}
}
