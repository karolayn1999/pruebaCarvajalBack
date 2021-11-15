package com.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.prueba.translate.AccountTranslate;
import com.prueba.translate.CardTranslate;
import com.prueba.translate.ClientTranslate;
import com.prueba.util.Encrypt;

@SpringBootApplication
public class PruebaCarvajalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaCarvajalApplication.class, args);
	}

	@Bean
	public ClientTranslate clientTranslate() {
		return new ClientTranslate();
	}

	@Bean
	public AccountTranslate accountTranslate() {
		return new AccountTranslate();
	}

	@Bean
	public CardTranslate cardTranslate() {
		return new CardTranslate();
	}
	
	@Bean
	public Encrypt encrypt() {
		return new Encrypt();
	}

}