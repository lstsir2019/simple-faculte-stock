package com.faculte.simplefacultestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.faculte.simplefacultestock.domain.rest")
public class SimpleFaculteStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFaculteStockApplication.class, args);
	}

}
