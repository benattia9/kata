package com.sg.kata.controller;

import java.util.List;
import com.sg.kata.constantes.ConstantesClient;
import com.sg.kata.dao.ClientRepository;
import com.sg.kata.dao.OperationRepository;
import com.sg.kata.entities.Client;
import com.sg.kata.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import exception.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SGController {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OperationRepository operationRepository;

	@RequestMapping("/")
	@ResponseBody

	public String welcome() {
		return "Welcome to Bank account kata";
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Client> getClients() {
		return clientRepository.findAll();
	}

	@RequestMapping(value = "/clients/{code}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Client> getClientById(@PathVariable("code") Long code) throws ResourceNotFoundException {
		System.out.println("(Service Client) Get Client " + code);
		Client client = clientRepository.findById(code)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantesClient.NOT_FOUND_CLIENT  + code));
		return ResponseEntity.ok().body(client);
	}
	
	@RequestMapping(value = "/operations", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Operation> getOperations() {
		return operationRepository.findAll();
	}



}
