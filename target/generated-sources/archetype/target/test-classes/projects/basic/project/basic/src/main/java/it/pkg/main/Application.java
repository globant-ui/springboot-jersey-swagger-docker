package it.pkg.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(
		   scanBasePackages = {
		     "it.pkg.config", "it.pkg.rest"
		   }
		  )
		  public class Application extends SpringBootServletInitializer {
		  
		   @Override
		   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		     return builder.sources(Application.class);
		   }
		 
		   public static void main(String[] args) {
		     SpringApplication.run(Application.class, args);
		   }
		 }
