package com.github.sepulvida.agendaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.sepulvida.agendaapi.model.entity.Contato;
import com.github.sepulvida.agendaapi.model.repository.ContatoRepository;

@SpringBootApplication
public class AgendaApiApplication {

	/*
	@Bean
	public CommandLineRunner run (@Autowired ContatoRepository contatoRepository){
		return args -> {
			
		Contato contato = new Contato();
		contato.setNome("Kennede");
		contato.setEmail("kennede.sepulvida@gmail.com");
		contato.setFavorito(true);
		contatoRepository.save(contato);
		
		Contato contato2 = new Contato();
		contato2.setNome("fulano");
		contato2.setEmail("fulada.balada@gmail.com");
		contato2.setFavorito(false);
		contatoRepository.save(contato2);
			
		};
		
	}
	*/
	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
