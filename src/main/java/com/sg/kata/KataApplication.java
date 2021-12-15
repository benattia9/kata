package com.sg.kata;

import java.util.Date;

import com.sg.kata.dao.ClientRepository;
import com.sg.kata.dao.CompteRepository;
import com.sg.kata.dao.OperationRepository;
import com.sg.kata.entities.Client;
import com.sg.kata.entities.Compte;
import com.sg.kata.entities.CompteCourant;
import com.sg.kata.entities.CompteEpargne;
import com.sg.kata.entities.Retrait;
import com.sg.kata.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KataApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("slim", "slim.ben-attia@talan.com"));
		Client c2 = clientRepository.save(new Client("rachid", "rachid@talan.com"));

		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 9000, c1, 6000));

		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 4444, c2, 5.5));

		operationRepository.save(new Versement(new Date(), 9000, cp1));

		operationRepository.save(new Retrait(new Date(), 6000, cp1));

		operationRepository.save(new Versement(new Date(), 2300, cp2));

		operationRepository.save(new Retrait(new Date(), 8000, cp2));

	}

}

