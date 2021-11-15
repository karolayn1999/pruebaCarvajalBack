package com.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prueba.dto.CardDTO;

public interface CardService {

	List<CardDTO> findAll();

	List<CardDTO> findAllByAccount(String id);

	ResponseEntity<String> save(CardDTO cardDTO);

	ResponseEntity<String> update(CardDTO cardDTO);

	ResponseEntity<String> delete(String id);

}
