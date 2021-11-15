package com.prueba.translate;

import com.prueba.dto.AccountDTO;
import com.prueba.entity.AccountEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountTranslate implements Translate<AccountDTO, AccountEntity> {

	@Autowired
	private ClientTranslate clientTranslate;

	@Override
	public AccountDTO translateTo(AccountEntity accountEntity) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(accountEntity.getId());
		accountDTO.setAccountType(accountEntity.getAccountType());
		accountDTO.setAccountNumber(accountEntity.getAccountNumber());
		accountDTO.setStatus(accountEntity.getStatus());
		accountDTO.setTotalBalance(accountEntity.getTotalBalance());
		accountDTO.setClient(clientTranslate.translateTo(accountEntity.getClient()));
		return accountDTO;
	}

	public List<AccountDTO> translateTo(List<AccountEntity> accounts) {
		List<AccountDTO> accountsDTO = new ArrayList<>();
		for (AccountEntity accountEntity : accounts) {
			AccountDTO accountDTO = translateTo(accountEntity);
			accountsDTO.add(accountDTO);
		}
		return accountsDTO;
	}

	public AccountEntity translateTo(AccountDTO accountDTO) throws Exception {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(accountDTO.getId());
		accountEntity.setAccountType(accountDTO.getAccountType());
		accountEntity.setAccountNumber(accountDTO.getAccountNumber());
		accountEntity.setStatus(accountDTO.getStatus());
		accountEntity.setTotalBalance(accountDTO.getTotalBalance());
		accountEntity.setClient(clientTranslate.translateTo(accountDTO.getClient()));
		return accountEntity;
	}

}
