package com.prueba.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba.dto.AccountDTO;
import com.prueba.entity.AccountEntity;
import com.prueba.entity.ClientEntity;
import com.prueba.repository.AccountRepositoryJPA;
import com.prueba.repository.ClientRepositoryJPA;
import com.prueba.service.AccountService;
import com.prueba.translate.AccountTranslate;
import com.prueba.util.UUIDUtils;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private AccountTranslate accountTranslate;

	@Autowired
	private AccountRepositoryJPA accountRepositoryJPA;

	@Autowired
	private ClientRepositoryJPA clientRepositoryJPA;

	@Override
	public List<AccountDTO> findAll() {
		List<AccountEntity> accounts = accountRepositoryJPA.findAll();
		List<AccountDTO> accountsDTO = new ArrayList<>();
		if (!accounts.isEmpty()) {
			accountsDTO = accountTranslate.translateTo(accounts);
		}
		return accountsDTO;
	}

	@Override
	public List<AccountDTO> findAllByClient(String id) {
		Optional<ClientEntity> clientEntity = clientRepositoryJPA.findById(id);
		List<AccountEntity> accounts = accountRepositoryJPA.findAllByClientId(clientEntity.get());
		List<AccountDTO> accountsDTO = new ArrayList<>();
		if (!accounts.isEmpty()) {
			accountsDTO = accountTranslate.translateTo(accounts);
		}
		return accountsDTO;
	}

	@Override
	public ResponseEntity<String> save(AccountDTO accountDTO) {
		try {
			accountDTO.setId(UUIDUtils.randomUUID());
			AccountEntity accountEntity = accountTranslate.translateTo(accountDTO);
			try {
				AccountEntity exist = accountRepositoryJPA.findByAccountTypeAndAccountNumber(
						accountEntity.getAccountType(), accountEntity.getAccountNumber());
				return new ResponseEntity<String>(
						"La cuenta con el numero " + exist.getAccountNumber() + " ya esta registrada",
						HttpStatus.BAD_GATEWAY);
			} catch (NoResultException | NullPointerException ex) {
				accountRepositoryJPA.save(accountEntity);
				return new ResponseEntity<String>("Cuenta registrada correctamente", HttpStatus.OK);
			} catch (Exception ex) {
				return new ResponseEntity<String>("Error al registrar la cuenta, consulte el log",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Informacion incompleta, por favor digite todos los campos",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> update(AccountDTO accountDTO) {
		try {
			AccountEntity accountEntity = accountTranslate.translateTo(accountDTO);
			try {
				AccountEntity exist = accountRepositoryJPA.findByAccountTypeAndAccountNumber(
						accountEntity.getAccountType(), accountEntity.getAccountNumber());
				String mensaje = "La cuenta no esta registrada o modificacion no permitida";
				if (exist != null) {
					accountRepositoryJPA.save(accountEntity);
					mensaje = "Cuenta actualizada correctamente";
					return new ResponseEntity<String>(mensaje, HttpStatus.OK);
				}
				return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
			} catch (NoResultException | NullPointerException ex) {
				return new ResponseEntity<String>(
						"La cuenta no esta registrada o modificacion no permitida",
						HttpStatus.BAD_REQUEST);
			} catch (Exception ex) {
				return new ResponseEntity<String>("Error al actualizar la cuenta, consulte el log",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Informacion incompleta, por favor digite todos los campos",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		try {
			deleteById(id);
			return new ResponseEntity<String>("Cuenta eliminado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("La cuenta no puede ser eliminada debido a que posee tarjetas activas", HttpStatus.OK);
		}
	}
	
	private void deleteById(String id) throws Exception {
		accountRepositoryJPA.deleteById(id);
	}

	@Override
	public AccountDTO findById(String id) {
		Optional<AccountEntity> accountDTO = accountRepositoryJPA.findById(id);
		return accountTranslate.translateTo(accountDTO.get());
	}

}
