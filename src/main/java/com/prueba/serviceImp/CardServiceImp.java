package com.prueba.serviceImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba.dto.AccountDTO;
import com.prueba.dto.CardDTO;
import com.prueba.entity.AccountEntity;
import com.prueba.entity.CardEntity;
import com.prueba.repository.CardRepositoryJPA;
import com.prueba.service.AccountService;
import com.prueba.service.CardService;
import com.prueba.translate.AccountTranslate;
import com.prueba.translate.CardTranslate;
import com.prueba.util.Encrypt;
import com.prueba.util.UUIDUtils;

@Service
public class CardServiceImp implements CardService {

	@Autowired
	private CardRepositoryJPA cardRepositoryJPA;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CardTranslate cardTranslate;

	@Autowired
	private AccountTranslate accountTranslate;

	@Autowired
	private Encrypt encryptor;

	@Override
	public List<CardDTO> findAll() {
		List<CardEntity> cards = cardRepositoryJPA.findAll();
		List<CardDTO> cardsDTO = new ArrayList<>();
		if (!cards.isEmpty()) {
			cardsDTO = cardTranslate.translateTo(cards);
		}
		return cardsDTO;
	}

	@Override
	public List<CardDTO> findAllByAccount(String id) {
		List<CardDTO> cardsDTO = new ArrayList<>();
		try {
			AccountDTO accountDTO = accountService.findById(id);
			AccountEntity accountEntity = accountTranslate.translateTo(accountDTO);
			List<CardEntity> cards = cardRepositoryJPA.findAllByIdAccount(accountEntity);
			if (!cards.isEmpty()) {
				cardsDTO = cardTranslate.translateTo(cards);
			}
			return cardsDTO;
		} catch (Exception e) {
			return cardsDTO;
		}

	}

	@Override
	public ResponseEntity<String> save(CardDTO cardDTO) {
		try {
			cardDTO.setId(UUIDUtils.randomUUID());
			cardDTO.setSecretNumber(encryptor.encryptText(cardDTO.getCardNumber()));
			char[] chars = cardDTO.getCardNumber().toCharArray();
			for (int i = 0; i < chars.length - 4; i++) {
				chars[i] = 'X';
			}
			String respuesta = "";
			for (char c : chars) {
				respuesta = respuesta + c;
			}
			cardDTO.setCardNumber(respuesta);
			cardDTO.setIdAccount(accountService.findById(cardDTO.getIdAccount().getId()));
			CardEntity cardEntity = cardTranslate.translateTo(cardDTO);
			try {
				CardEntity exist = cardRepositoryJPA.findBysecretNumber(cardEntity.getSecretNumber());
				return new ResponseEntity<String>(
						"La tarjeta con el numero " + exist.getCardNumber() + " ya esta registrada",
						HttpStatus.BAD_GATEWAY);
			} catch (NoResultException | NullPointerException ex) {
				cardRepositoryJPA.save(cardEntity);
				return new ResponseEntity<String>("Tarjeta registrada correctamente", HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>("Informacion incompleta, por favor digite todos los campos",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> update(CardDTO cardDTO) {
		try {
			cardDTO.setIdAccount(accountService.findById(cardDTO.getIdAccount().getId()));
			CardEntity cardEntity = cardTranslate.translateTo(cardDTO);
			try {
				CardEntity exist = cardRepositoryJPA.findBysecretNumber(cardEntity.getSecretNumber());
				String mensaje = "La tarjeta no esta registrada o modificacion no permitida";
				if (exist != null) {
					cardRepositoryJPA.save(cardEntity);
					mensaje = "Tarjeta actualizada correctamente";
					return new ResponseEntity<String>(mensaje, HttpStatus.OK);
				}
				return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
			} catch (NoResultException | NullPointerException ex) {
				return new ResponseEntity<String>("La tarjeta no esta registrada o modificacion no permitida",
						HttpStatus.BAD_REQUEST);
			} catch (Exception ex) {
				System.out.println("error "+ex);
				return new ResponseEntity<String>("Error al actualizar la tarjeta, consulte el log",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Informacion incompleta, por favor digite todos los campos",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		cardRepositoryJPA.deleteById(id);
		return new ResponseEntity<String>("Tarjeta eliminada", HttpStatus.OK);
	}

}
