package com.example.artistas_alura;

import com.example.artistas_alura.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistasAluraApplication implements CommandLineRunner {

	@Autowired
	private MainService mainService;

	public static void main(String[] args) {
		SpringApplication.run(ArtistasAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainService.execute();
	}
}