package com.prueba.translate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prueba.dto.CardDTO;
import com.prueba.entity.CardEntity;

public class CardTranslate implements Translate<CardDTO, CardEntity> {
	
	@Autowired
	private AccountTranslate accountTranslate;

	@Override
	public CardDTO translateTo(CardEntity cardEntity) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId(cardEntity.getId());
		cardDTO.setCardType(cardEntity.getCardType());
		cardDTO.setCardNumber(cardEntity.getCardNumber());
		cardDTO.setDueDate(cardEntity.getDueDate());
		cardDTO.setIssuanceDate(cardEntity.getIssuanceDate());
		cardDTO.setSecurityCode(cardEntity.getSecurityCode());
		cardDTO.setIdAccount(accountTranslate.translateTo(cardEntity.getIdAccount()));
		cardDTO.setSecretNumber(cardEntity.getSecretNumber());
		cardDTO.setQuota(cardEntity.getQuota());
		return cardDTO;
	}

	public List<CardDTO> translateTo(List<CardEntity> cards) {
		List<CardDTO> cardsDTO = new ArrayList<>();
		for (CardEntity cardEntity : cards) {
			CardDTO cardDTO = translateTo(cardEntity);
			cardsDTO.add(cardDTO);
		}
		return cardsDTO;
	}

	public CardEntity translateTo(CardDTO cardDTO) throws Exception {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId(cardDTO.getId());
		cardEntity.setCardType(cardDTO.getCardType());
		cardEntity.setCardNumber(cardDTO.getCardNumber());
		cardEntity.setDueDate(cardDTO.getDueDate());
		cardEntity.setIssuanceDate(cardDTO.getIssuanceDate());
		cardEntity.setSecurityCode(cardDTO.getSecurityCode());
		cardEntity.setSecretNumber(cardDTO.getSecretNumber());
		cardEntity.setQuota(cardDTO.getQuota());
		cardEntity.setIdAccount(accountTranslate.translateTo(cardDTO.getIdAccount()));
		return cardEntity;
	}

}
