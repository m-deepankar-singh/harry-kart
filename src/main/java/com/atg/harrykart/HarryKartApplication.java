package com.atg.harrykart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Application used to find the Top 3 Winners of Horses in the HarryKart Game
 * @author Parasuram
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HarryKartApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarryKartApplication.class, args);
	}

}
