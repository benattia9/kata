package controller;

import static org.junit.Assert.*;
import com.sg.kata.KataApplication;
import com.sg.kata.dao.CompteRepository;
import com.sg.kata.entities.Client;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.kata.KataApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KataApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SGControllerTest {

	@MockBean
	private CompteRepository compteRepository;
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {

	}

	@Test
	void testGetAllClients() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/clients", HttpMethod.GET, entity,
				String.class);

		assertNotNull(response.getBody());

	}

	@Test
	void testGetClientById() {
		Client client = restTemplate.getForObject(getRootUrl() + "/clients/1", Client.class);
		System.out.println(client.getNom());
		assertNotNull(client);
	}
	
	@Test
	void testGetAllOperations() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/operations", HttpMethod.GET, entity,
				String.class);

		assertNotNull(response.getBody());

	}

}
