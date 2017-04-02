package com.epam.com.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.epam.com.product.Product;


@SpringBootApplication
public class App implements CommandLineRunner
{
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private ProductClient productClient;
    
	public void run(String... arg0) throws Exception {
		
	}
    
    
}
