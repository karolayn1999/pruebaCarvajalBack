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

import com.prueba.dto.AccountDTO;
import com.prueba.dto.CardDTO;
import com.prueba.service.AccountService;
import com.prueba.service.CardService;

@RunWith(MockitoJUnitRunner.class)
public class AccountApiTest {

	@Mock AccountService accountService;
	@InjectMocks AccountApi accountApi;
	
	@Test
	public void testListAccout() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		accountDTOList.add(accountDTO);
		when(accountService.findAll()).thenReturn(accountDTOList);
		ResponseEntity<List<AccountDTO>> result = accountApi.list(); 
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testListAccoutByClient() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		accountDTOList.add(accountDTO);
		when(accountService.findAllByClient("TEST")).thenReturn(accountDTOList);
		ResponseEntity<List<AccountDTO>> result = accountApi.listByClient("TEST"); 
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testSaveAccout() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Tarjeta registrada correctamente",
			    HttpStatus.OK
			);

		when(accountService.save(accountDTO)).thenReturn(responseEntity);
		ResponseEntity<String> result = accountService.save(accountDTO); 
		assertEquals("Tarjeta registrada correctamente",result.getBody());
	}
	
	@Test
	public void testUpdateAccout() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cuenta actualizada correctamente",
			    HttpStatus.OK
			);

		when(accountService.update(accountDTO)).thenReturn(responseEntity);
		ResponseEntity<String> result = accountService.update(accountDTO); 
		assertEquals("Cuenta actualizada correctamente", result.getBody());
	}
	
	@Test
	public void testDeleteAccout() {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cuenta eliminado",
			    HttpStatus.OK
			);
		when(accountService.delete(any())).thenReturn(responseEntity);
		ResponseEntity<String> result = accountService.delete("TEST"); 
		assertEquals("Cuenta eliminado", result.getBody());
	}
}
