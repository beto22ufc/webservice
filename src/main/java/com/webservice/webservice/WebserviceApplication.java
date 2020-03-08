package com.webservice.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.webservice.dao.SetupDao;
import com.webservice.resource.CityResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.webservice"})
public class WebserviceApplication {

	public static void main(String[] args) {
		SetupDao setupDao = new SetupDao();
		setupDao.run();
		SpringApplication.run(WebserviceApplication.class, args);
	}

}
