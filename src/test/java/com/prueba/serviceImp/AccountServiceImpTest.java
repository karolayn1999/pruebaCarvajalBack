package com.prueba.serviceImp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.dto.AccountDTO;
import com.prueba.dto.ClientDTO;
import com.prueba.entity.AccountEntity;
import com.prueba.entity.ClientEntity;
import com.prueba.repository.AccountRepositoryJPA;
import com.prueba.repository.ClientRepositoryJPA;
import com.prueba.translate.AccountTranslate;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceImpTest {

	@Mock AccountTranslate accountTranslate;
	@Mock AccountRepositoryJPA accountRepositoryJPA;
	@Mock ClientRepositoryJPA clientRepositoryJPA;
	@InjectMocks AccountServiceImp accountServiceImp;
	
	@Test
	public void testListAccout() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		accountDTOList.add(accountDTO);
		accountEntityList.add(accountEntity);
		when(accountRepositoryJPA.findAll()).thenReturn(accountEntityList);
		when(accountTranslate.translateTo(accountEntityList)).thenReturn(accountDTOList);
		List<AccountDTO> result = accountServiceImp.findAll(); 
		assertNotNull(result);
	}
	
	@Test
	public void testListAccoutElse() {
		List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		when(accountRepositoryJPA.findAll()).thenReturn(accountEntityList);
		List<AccountDTO> result = accountServiceImp.findAll(); 
		assertEquals(accountDTOList,result);
	}
	
	@Test
	public void testListAccoutAllByClient() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setDocumentNumber("TEST");
		clientEntity.setDocumentType("TEST");
		clientEntity.setName("TEST");
		
		Optional<ClientEntity> clientEntityList = Optional.of(clientEntity);
		List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		accountDTOList.add(accountDTO);
		accountEntityList.add(accountEntity);

		when(clientRepositoryJPA.findById("TEST")).thenReturn(clientEntityList);
		when(accountRepositoryJPA.findAllByClientId(clientEntityList.get())).thenReturn(accountEntityList);
		when(accountTranslate.translateTo(accountEntityList)).thenReturn(accountDTOList);
		List<AccountDTO> result = accountServiceImp.findAllByClient("TEST"); 
		assertNotNull(result);
	}
	
	@Test
	public void testListAccoutAllByClientElse() {
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setDocumentNumber("TEST");
		clientEntity.setDocumentType("TEST");
		clientEntity.setName("TEST");
		
		Optional<ClientEntity> clientEntityList = Optional.of(clientEntity);
		List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		
		when(clientRepositoryJPA.findById("TEST")).thenReturn(clientEntityList);
		when(accountRepositoryJPA.findAllByClientId(clientEntityList.get())).thenReturn(accountEntityList);
		List<AccountDTO> result = accountServiceImp.findAll(); 
		assertEquals(accountDTOList,result);
	}
	
	@Test
	public void testSaveAccout() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		accountDTO.setAccountType("TEST");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		accountEntity.setAccountType("TEST1");
		
		when(accountTranslate.translateTo(accountDTO)).thenReturn(accountEntity);
		when(accountRepositoryJPA.findByAccountTypeAndAccountNumber("TEST","TEST")).thenReturn(accountEntity);
		ResponseEntity<String> result = accountServiceImp.save(accountDTO); 
		assertEquals("Cuenta registrada correctamente",result.getBody());
	}
	
	@Test
	public void testSaveAccoutValidation() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		accountDTO.setAccountType("TEST");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		accountEntity.setAccountType("TEST");
		
		when(accountTranslate.translateTo(accountDTO)).thenReturn(accountEntity);
		when(accountRepositoryJPA.findByAccountTypeAndAccountNumber("TEST","TEST")).thenReturn(accountEntity);
		ResponseEntity<String> result = accountServiceImp.save(accountDTO); 
		assertEquals("La cuenta con el numero TEST ya esta registrada",result.getBody());
	}
	
	@Test
	public void testUpdateAccout() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		accountDTO.setAccountType("TEST");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		accountEntity.setAccountType("TEST");
		
		when(accountTranslate.translateTo(accountDTO)).thenReturn(accountEntity);
		when(accountRepositoryJPA.findByAccountTypeAndAccountNumber("TEST","TEST")).thenReturn(accountEntity);
		ResponseEntity<String> result = accountServiceImp.update(accountDTO); 
		assertEquals("Cuenta actualizada correctamente",result.getBody());
	}
	
	@Test
	public void testUpdateAccoutValidation() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		accountDTO.setAccountType("TEST");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		accountEntity.setAccountType("TEST1");
		
		when(accountTranslate.translateTo(accountDTO)).thenReturn(accountEntity);
		when(accountRepositoryJPA.findByAccountTypeAndAccountNumber("TEST","TEST")).thenReturn(accountEntity);
		ResponseEntity<String> result = accountServiceImp.update(accountDTO); 
		assertEquals("La cuenta no esta registrada o modificacion no permitida",result.getBody());
	}
}
