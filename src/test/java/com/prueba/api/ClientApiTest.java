package com.prueba.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.dto.ClientDTO;
import com.prueba.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientApiTest {
	@Mock ClientService clientService;
	@InjectMocks ClientApi clientApi;
	
	@Test
	public void testListClients() {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setDocumentNumber("TEST");
		clientDTO.setDocumentType("TEST");
		clientDTO.setName("TEST");
		
		List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
		clientDTOList.add(clientDTO);
		when(clientService.findAll()).thenReturn(clientDTOList);
		ResponseEntity<List<ClientDTO>> result = clientApi.list(); 
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testListActiveClients() {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setDocumentNumber("TEST");
		clientDTO.setDocumentType("TEST");
		clientDTO.setName("TEST");
		
		List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
		clientDTOList.add(clientDTO);
		when(clientService.findAllActive()).thenReturn(clientDTOList);
		ResponseEntity<List<ClientDTO>> result = clientApi.listActive(); 
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testSaveClients() {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setDocumentNumber("TEST");
		clientDTO.setDocumentType("TEST");
		clientDTO.setName("TEST");
		clientDTO.setId("TEST");
		clientDTO.setStatus(true);
		clientDTO.setBirthDate(any());
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "El cliente con documento "+clientDTO.getId()+" ya esta registrado",
			    HttpStatus.OK
			);

		when(clientService.save(clientDTO)).thenReturn(responseEntity);
		ResponseEntity<String> result = clientApi.save(clientDTO); 
		assertEquals("El cliente con documento TEST ya esta registrado",result.getBody());
	}
	
	@Test
	public void testUpdateClients() {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setDocumentNumber("TEST");
		clientDTO.setDocumentType("TEST");
		clientDTO.setName("TEST");
		clientDTO.setId("TEST");
		clientDTO.setStatus(true);
		clientDTO.setBirthDate(any());
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cliente actualizado correctamente",
			    HttpStatus.OK
			);

		when(clientService.update(clientDTO)).thenReturn(responseEntity);
		ResponseEntity<String> result = clientApi.update(clientDTO); 
		assertEquals("Cliente actualizado correctamente", result.getBody());
	}
	
	@Test
	public void testUpdateStatusClients() {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cliente actualizado correctamente",
			    HttpStatus.OK
			);
		when(clientService.updateStatus("TEST", true)).thenReturn(responseEntity);
		ResponseEntity<String> result = clientApi.updateStatus("TEST", true); 
		assertEquals("Cliente actualizado correctamente", result.getBody());
	}
	
	@Test
	public void testDeleteClients() {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cliente eliminado",
			    HttpStatus.OK
			);
		when(clientService.delete(any())).thenReturn(responseEntity);
		ResponseEntity<String> result = clientApi.delete("TEST"); 
		assertEquals("Cliente eliminado", result.getBody());
	}
}
