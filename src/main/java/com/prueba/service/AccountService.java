package com.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prueba.dto.AccountDTO;

public interface AccountService {

	List<AccountDTO> findAll();

	List<AccountDTO> findAllByClient(String id);

	ResponseEntity<String> update(AccountDTO accountDTO);

	ResponseEntity<String> save(AccountDTO accountDTO);

	ResponseEntity<String> delete(String id);

	AccountDTO findById(String id);

}
