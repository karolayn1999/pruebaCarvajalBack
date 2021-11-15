package com.prueba.serviceImp;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.prueba.dto.AccountDTO;
import com.prueba.dto.CardDTO;
import com.prueba.entity.AccountEntity;
import com.prueba.entity.CardEntity;
import com.prueba.entity.ClientEntity;
import com.prueba.repository.AccountRepositoryJPA;
import com.prueba.repository.CardRepositoryJPA;
import com.prueba.repository.ClientRepositoryJPA;
import com.prueba.service.AccountService;
import com.prueba.translate.AccountTranslate;
import com.prueba.translate.CardTranslate;
import com.prueba.util.Encrypt;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CardServiceImpTest {

	@Mock CardRepositoryJPA cardRepositoryJPA;
	@Mock AccountService accountService;
	@Mock CardTranslate cardTranslate;
	@Mock AccountTranslate accountTranslate;
	@Mock Encrypt encryptor;
	@InjectMocks CardServiceImp cardServiceImp;
	
	@Test
	public void testListAccout() {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");
		
		List<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		cardDTOList.add(cardDTO);
		cardEntityList.add(cardEntity);
		
		when(cardRepositoryJPA.findAll()).thenReturn(cardEntityList);
		when(cardTranslate.translateTo(cardEntityList)).thenReturn(cardDTOList);
		List<CardDTO> result = cardServiceImp.findAll(); 
		assertNotNull(result);
	}
	
	@Test
	public void testListAccoutElse() {
		List<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		when(cardRepositoryJPA.findAll()).thenReturn(cardEntityList);
		List<CardDTO> result = cardServiceImp.findAll(); 
		assertEquals(cardDTOList,result);
	}
	
	@Test
	public void testListAccoutAllByClient() throws Exception {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");

		List<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		cardDTOList.add(cardDTO);
		cardEntityList.add(cardEntity);
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");

		when(accountService.findById("TEST")).thenReturn(accountDTO);
		when(accountTranslate.translateTo(accountDTO)).thenReturn(accountEntity);
		when(cardRepositoryJPA.findAllByIdAccount(accountEntity)).thenReturn(cardEntityList);
		when(cardTranslate.translateTo(cardEntity)).thenReturn(cardDTO);
		
		List<CardDTO> result = cardServiceImp.findAllByAccount("TEST"); 
		assertNotNull(result);
	}
	
	@Test
	public void testListAccoutAllByClientElse() throws Exception {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");

		List<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		cardDTOList.add(cardDTO);
		cardEntityList.add(cardEntity);
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");

		when(accountService.findById("TEST")).thenReturn(accountDTO);
		when(accountTranslate.translateTo(accountDTO)).thenReturn(accountEntity);
		when(cardRepositoryJPA.findAllByIdAccount(accountEntity)).thenReturn(cardEntityList);
		
		List<CardDTO> result = cardServiceImp.findAllByAccount("TEST"); 
		assertNotNull(result);
	}
	
	@Test
	public void testSaveAccout() throws Exception {
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");
		cardEntity.setIdAccount(accountEntity);
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");
		cardDTO.setCardType("TEST");
		cardDTO.setDueDate(any());
		cardDTO.setIdAccount(accountDTO);
		
		
		
		when(encryptor.encryptText("TEST")).thenReturn("TESTENCRYPT");
		when(accountService.findById("TEST")).thenReturn(accountDTO);
		
		when(cardRepositoryJPA.findBysecretNumber("TEST")).thenReturn(cardEntity);
		
		ResponseEntity<String> result = cardServiceImp.save(cardDTO); 
		assertEquals("Tarjeta registrada correctamente",result.getBody());
	}
	
	@Test
	public void testSaveAccoutIncomplete() throws Exception {
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");

		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");
		cardDTO.setCardType("TEST");
		cardDTO.setDueDate(any());
		
		
		when(encryptor.encryptText("TEST")).thenReturn("TESTENCRYPT");
		when(accountService.findById("TEST")).thenReturn(accountDTO);
		
		when(cardRepositoryJPA.findBysecretNumber("TEST")).thenReturn(cardEntity);
		
		ResponseEntity<String> result = cardServiceImp.save(cardDTO); 
		assertEquals("Informacion incompleta, por favor digite todos los campos",result.getBody());
	}

	
	@Test
	public void testUpdateAccout() throws Exception {
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");
		cardEntity.setIdAccount(accountEntity);
		cardEntity.setSecretNumber("test");
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");
		cardDTO.setCardType("TEST");
		cardDTO.setDueDate(any());
		cardDTO.setIdAccount(accountDTO);

		when(encryptor.encryptText("TEST")).thenReturn("TESTENCRYPT");
		when(accountService.findById("TEST")).thenReturn(accountDTO);
		when(cardTranslate.translateTo(cardDTO)).thenReturn(cardEntity);
		when(cardRepositoryJPA.findBysecretNumber(cardEntity.getSecretNumber())).thenReturn(cardEntity);
		
		ResponseEntity<String> result = cardServiceImp.update(cardDTO); 
		assertEquals("Tarjeta actualizada correctamente",result.getBody());
	}
	
	@Test
	public void testUpdateAccoutIncomplete() throws Exception {
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId("TEST");
		accountEntity.setAccountNumber("TEST");
		
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId("TEST");
		cardEntity.setCardNumber("TEST");

		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId("TEST");
		accountDTO.setAccountNumber("TEST");
		
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		cardDTO.setCardNumber("TEST");
		cardDTO.setCardType("TEST");
		cardDTO.setDueDate(any());
		
		
		when(encryptor.encryptText("TEST")).thenReturn("TESTENCRYPT");
		when(accountService.findById("TEST")).thenReturn(accountDTO);
		
		when(cardRepositoryJPA.findBysecretNumber("TEST")).thenReturn(cardEntity);
		
		ResponseEntity<String> result = cardServiceImp.update(cardDTO); 
		assertEquals("Informacion incompleta, por favor digite todos los campos",result.getBody());
	}
}
