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

import com.prueba.dto.CardDTO;
import com.prueba.dto.ClientDTO;
import com.prueba.service.CardService;
import com.prueba.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class CardApiTest {
	@Mock CardService cardService;
	@InjectMocks CardApi cardApi;
	
	@Test
	public void testListCard() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCardNumber("TEST");
		cardDTO.setCardType("TEST");
		cardDTO.setId("TEST");
		
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		cardDTOList.add(cardDTO);
		when(cardService.findAll()).thenReturn(cardDTOList);
		ResponseEntity<List<CardDTO>> result = cardApi.list(); 
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testListCardByAccount() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCardNumber("TEST");
		cardDTO.setCardType("TEST");
		cardDTO.setId("TEST");
		
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		cardDTOList.add(cardDTO);
		when(cardService.findAllByAccount("TEST")).thenReturn(cardDTOList);
		ResponseEntity<List<CardDTO>> result = cardApi.listByAccount("TEST"); 
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testSaveCard() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Tarjeta registrada correctamente",
			    HttpStatus.OK
			);

		when(cardService.save(cardDTO)).thenReturn(responseEntity);
		ResponseEntity<String> result = cardApi.save(cardDTO); 
		assertEquals("Tarjeta registrada correctamente",result.getBody());
	}
	
	@Test
	public void testUpdateCard() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId("TEST");
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cliente actualizado correctamente",
			    HttpStatus.OK
			);

		when(cardService.update(cardDTO)).thenReturn(responseEntity);
		ResponseEntity<String> result = cardService.update(cardDTO); 
		assertEquals("Cliente actualizado correctamente", result.getBody());
	}
	
	@Test
	public void testDeleteClients() {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
			    "Cliente eliminado",
			    HttpStatus.OK
			);
		when(cardService.delete(any())).thenReturn(responseEntity);
		ResponseEntity<String> result = cardService.delete("TEST"); 
		assertEquals("Cliente eliminado", result.getBody());
	}
}
