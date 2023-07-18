package com.projeto.pessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PessoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PessoalApplication.class, args);
	}

}
