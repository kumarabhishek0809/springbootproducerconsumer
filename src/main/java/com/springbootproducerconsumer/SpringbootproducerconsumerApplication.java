package com.springbootproducerconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude={DataSourceAutoConfiguration.class})
public class SpringbootproducerconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootproducerconsumerApplication.class, args);
	}

}
