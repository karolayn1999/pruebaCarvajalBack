package com.prueba.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.prueba.dto.AccountDTO;
import com.prueba.service.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AccountApi {

	@Autowired
	private AccountService accountService;

	@GetMapping("/list")
	public ResponseEntity<List<AccountDTO>> list() {
		List<AccountDTO> accountsDTO = accountService.findAll();
		return new ResponseEntity<List<AccountDTO>>(accountsDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}")
	@RequestMapping("/listByClient")
	public ResponseEntity<List<AccountDTO>> listByClient(@RequestParam("userId") String id) {
		List<AccountDTO> accountsDTO = accountService.findAllByClient(id);
		return new ResponseEntity<List<AccountDTO>>(accountsDTO, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody AccountDTO accountDTO) {
		return accountService.save(accountDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody AccountDTO accountDTO) {
		return accountService.update(accountDTO);
	}

	@DeleteMapping(value = "/{accountId}")
	@RequestMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("accountId") String id) {
		return accountService.delete(id);
	}

}
