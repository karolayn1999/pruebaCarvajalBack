package com.prueba.api;

import java.util.ArrayList;
import java.util.List;
import com.prueba.dto.CardDTO;
import com.prueba.dto.ClientDTO;
import com.prueba.service.CardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CardApi {
	
	@Autowired
	private CardService cardService;
	
	@GetMapping("/list")
	public ResponseEntity<List<CardDTO>> list() {
		List<CardDTO> cardDTO = new ArrayList<>();
		cardDTO = cardService.findAll();
		return new ResponseEntity<List<CardDTO>>(cardDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{accountId}")
	@RequestMapping("/listByAccount")
	public ResponseEntity<List<CardDTO>> listByAccount(@RequestParam("accountId") String id) {
		List<CardDTO> cardDTO = new ArrayList<>();
		cardDTO = cardService.findAllByAccount(id);
		return new ResponseEntity<List<CardDTO>>(cardDTO, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody CardDTO cardDTO) {
		return cardService.save(cardDTO);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody CardDTO cardDTO) {
		return cardService.update(cardDTO);
	}
	
	@DeleteMapping(value = "/{cardId}")
	@RequestMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("cardId") String id) {
		return cardService.delete(id);
	}
	
}