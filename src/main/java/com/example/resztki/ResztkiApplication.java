package com.example.resztki;

import com.example.resztki.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		FileStorageProperties.class
})
@SpringBootApplication
public class ResztkiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResztkiApplication.class, args);
	}

}
