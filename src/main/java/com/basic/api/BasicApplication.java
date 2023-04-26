package com.basic.api;

import com.basic.api.config.BasicWebApplicationInitializer;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@ServletComponentScan //@WebFilter 와 같은 어노테이션 스캔가능
@SpringBootApplication
@Import({BasicWebApplicationInitializer.class})
public class BasicApplication {
	public static void main(String[] args) {
		System.out.println("##### EgovBootApplication Start #####");
		SpringApplication springApplication = new SpringApplication(BasicApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		//springApplication.setLogStartupInfo(false);
		springApplication.run(args);

		System.out.println("##### EgovBootApplication End #####");
	}
}
