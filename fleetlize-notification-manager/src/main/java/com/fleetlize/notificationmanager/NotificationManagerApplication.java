package com.fleetlize.notificationmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NotificationManagerApplication {

	public static void main(final String...args) {
		SpringApplication.run(NotificationManagerApplication.class, args);
	}

}
