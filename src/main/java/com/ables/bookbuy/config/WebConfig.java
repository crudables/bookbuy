package com.ables.bookbuy.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ables.bookbuy.servlets.ImageServlet;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
@Bean
public ServletRegistrationBean imageServlet(){
	ServletRegistrationBean imageBean = new ServletRegistrationBean(new ImageServlet(), "/image");
	imageBean.setLoadOnStartup(1);
	return imageBean;
}
}
