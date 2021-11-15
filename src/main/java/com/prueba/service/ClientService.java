package com.prueba.service;

import java.util.List;
import com.prueba.dto.ClientDTO;
import org.springframework.http.ResponseEntity;

public interface ClientService {

	List<ClientDTO> findAll();

	ResponseEntity<String> save(ClientDTO clientDTO);

	ResponseEntity<String> update(ClientDTO clientDTO);

	ResponseEntity<String> updateStatus(String id, boolean status);

	ResponseEntity<String> delete(String id);

	List<ClientDTO> findAllActive();

}