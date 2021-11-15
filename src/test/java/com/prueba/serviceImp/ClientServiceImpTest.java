package com.prueba.serviceImp;

import static org.junit.Assert.assertNotNull;
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
import org.springframework.http.ResponseEntity;

import com.prueba.dto.AccountDTO;
import com.prueba.dto.CardDTO;
import com.prueba.dto.ClientDTO;
import com.prueba.entity.AccountEntity;
import com.prueba.entity.CardEntity;
import com.prueba.entity.ClientEntity;
import com.prueba.repository.ClientRepositoryJPA;
import com.prueba.translate.AccountTranslate;
import com.prueba.translate.ClientTranslate;
import com.prueba.util.Encrypt;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceImpTest {

	@Mock ClientTranslate clientTranslate;
	@Mock ClientRepositoryJPA clientRepositoryJPA;
	@InjectMocks ClientServiceImp clientServiceImp;
	
	@Test
	public void testListClient() {
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId("TEST");
		clientEntity.setDocumentNumber("TEST");
		
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId("TEST");
		clientDTO.setDocumentNumber("TEST");
		
		List<ClientEntity> clientEntityList = new ArrayList<ClientEntity>();
		List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
		clientDTOList.add(clientDTO);
		clientEntityList.add(clientEntity);
		
		when(clientRepositoryJPA.findAll()).thenReturn(clientEntityList);
		when(clientTranslate.translateTo(clientEntityList)).thenReturn(clientDTOList);
		List<ClientDTO> result = clientServiceImp.findAll(); 
		assertNotNull(result);
	}
	
	@Test
	public void testListAccoutElse() {
		List<ClientEntity> clientEntityList = new ArrayList<ClientEntity>();
		List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
		when(clientRepositoryJPA.findAll()).thenReturn(clientEntityList);
		List<ClientDTO> result = clientServiceImp.findAll(); 
		assertEquals(clientDTOList,result);
	}
	
	@Test
	public void testSaveClient() throws Exception {
		
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId("TEST");
		clientDTO.setDocumentNumber("TEST");
		
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId("TEST");
		clientEntity.setDocumentNumber("TEST");
		
		
		when(clientTranslate.translateTo(clientDTO)).thenReturn(clientEntity);
		when(clientRepositoryJPA.findByDocumentTypeAndDocumentNumber("TEST","TEST")).thenReturn(clientEntity);
		
		ResponseEntity<String> result = clientServiceImp.save(clientDTO); 
		assertEquals("Cliente registrado exitosamente",result.getBody());
	}
	
	@Test
	public void testSaveClientException() throws Exception {
		
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId("TEST");
		clientDTO.setDocumentNumber("TEST");
		clientDTO.setDocumentType("TEST");
		
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId("TEST");
		clientEntity.setDocumentNumber("TEST");
		clientEntity.setDocumentType("TEST");
		
		
		when(clientTranslate.translateTo(clientDTO)).thenReturn(clientEntity);
		when(clientRepositoryJPA.findByDocumentTypeAndDocumentNumber("TEST","TEST")).thenReturn(clientEntity);
		
		ResponseEntity<String> result = clientServiceImp.save(clientDTO); 
		assertEquals("El cliente con documento TEST ya esta registrado",result.getBody());
	}
	
	@Test
	public void testUpdateClientException() throws Exception {
		
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId("TEST");
		clientDTO.setDocumentNumber("TEST");
		
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId("TEST");
		clientEntity.setDocumentNumber("TEST");

		when(clientTranslate.translateTo(clientDTO)).thenReturn(clientEntity);
		when(clientRepositoryJPA.findByDocumentTypeAndDocumentNumber("TEST","TEST")).thenReturn(clientEntity);
		
		ResponseEntity<String> result = clientServiceImp.update(clientDTO); 
		assertEquals("El cliente no esta registrado o modificacion de documento no permitida",result.getBody());
	}
	
	@Test
	public void testUpdateClient() throws Exception {
		
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId("TEST");
		clientDTO.setDocumentNumber("TEST");
		clientDTO.setDocumentType("TEST");
		
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId("TEST");
		clientEntity.setDocumentNumber("TEST");
		clientEntity.setDocumentType("TEST");
		
		
		when(clientTranslate.translateTo(clientDTO)).thenReturn(clientEntity);
		when(clientRepositoryJPA.findByDocumentTypeAndDocumentNumber("TEST","TEST")).thenReturn(clientEntity);
		
		ResponseEntity<String> result = clientServiceImp.update(clientDTO); 
		assertEquals("Cliente actualizado correctamente",result.getBody());
	}
	
	
}
