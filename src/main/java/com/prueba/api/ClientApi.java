package com.prueba.api;

import java.util.ArrayList;
import java.util.List;
import com.prueba.dto.ClientDTO;
import com.prueba.service.ClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ClientApi {

	@Autowired
	private ClientService clientService;

	@GetMapping("/list")
	public ResponseEntity<List<ClientDTO>> list() {
		List<ClientDTO> clientsDTO = new ArrayList<>();
		clientsDTO = clientService.findAll();
		return new ResponseEntity<List<ClientDTO>>(clientsDTO, HttpStatus.OK);
	}
	
	@GetMapping("/listActive")
	public ResponseEntity<List<ClientDTO>> listActive() {
		List<ClientDTO> clientsDTO = new ArrayList<>();
		clientsDTO = clientService.findAllActive();
		return new ResponseEntity<List<ClientDTO>>(clientsDTO, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ClientDTO clientDTO) {
		return clientService.save(clientDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody ClientDTO clientDTO) {
		return clientService.update(clientDTO);
	}

	@PutMapping(value = "/{userId}/{status}")
	@RequestMapping("/updateStatus")
	public ResponseEntity<String> updateStatus(@RequestParam("userId") String id, @RequestParam("status") boolean status) {
		return clientService.updateStatus(id, status);
	}

	@DeleteMapping(value = "/{userId}")
	@RequestMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("userId") String id) {
		return clientService.delete(id);
	}

}