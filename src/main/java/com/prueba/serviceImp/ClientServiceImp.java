package com.prueba.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.prueba.dto.ClientDTO;
import com.prueba.util.UUIDUtils;
import com.prueba.entity.ClientEntity;
import com.prueba.service.ClientService;
import org.springframework.http.HttpStatus;
import javax.persistence.NoResultException;
import com.prueba.translate.ClientTranslate;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.prueba.repository.ClientRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientTranslate clientTranslate;

	@Autowired
	private ClientRepositoryJPA clientRepositoryJPA;

	@Override
	public List<ClientDTO> findAll() {
		List<ClientEntity> clients = clientRepositoryJPA.findAll();
		List<ClientDTO> clientsDTO = new ArrayList<>();
		if (!clients.isEmpty()) {
			clientsDTO = clientTranslate.translateTo(clients);
		}
		return clientsDTO;
	}

	@Override
	public List<ClientDTO> findAllActive() {
		List<ClientEntity> clients = clientRepositoryJPA.findAllInStatus();
		List<ClientDTO> clientsDTO = new ArrayList<>();
		if (!clients.isEmpty()) {
			clientsDTO = clientTranslate.translateTo(clients);
		}
		return clientsDTO;
	}

	@Override
	public ResponseEntity<String> save(ClientDTO clientDTO) {
		try {
			clientDTO.setId(UUIDUtils.randomUUID());
			ClientEntity clientEntity = clientTranslate.translateTo(clientDTO);
			try {
				ClientEntity exist = clientRepositoryJPA.findByDocumentTypeAndDocumentNumber(
						clientEntity.getDocumentType(), clientEntity.getDocumentNumber());
				return new ResponseEntity<String>(
						"El cliente con documento " + exist.getDocumentNumber() + " ya esta registrado",
						HttpStatus.BAD_GATEWAY);
			} catch (NoResultException | NullPointerException ex) {
				clientRepositoryJPA.save(clientEntity);
				return new ResponseEntity<String>("Cliente registrado exitosamente", HttpStatus.OK);
			} catch (Exception ex) {
				return new ResponseEntity<String>("Error al guardar el cliente, consulte el log",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Informacion incompleta, por favor digite todos los campos",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> update(ClientDTO clientDTO) {
		try {
			ClientEntity clientEntity = clientTranslate.translateTo(clientDTO);
			try {
				ClientEntity exist = clientRepositoryJPA.findByDocumentTypeAndDocumentNumber(
						clientEntity.getDocumentType(), clientEntity.getDocumentNumber());
				String mensaje = "El cliente no esta registrado o modificacion de documento no permitida";
				if (exist != null) {
					clientRepositoryJPA.save(clientEntity);
					mensaje = "Cliente actualizado correctamente";
					return new ResponseEntity<String>(mensaje, HttpStatus.OK);
				}
				return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
			} catch (NoResultException | NullPointerException ex) {
				return new ResponseEntity<String>(
						"El cliente no esta registrado o modificacion de documento no permitida",
						HttpStatus.BAD_REQUEST);
			} catch (Exception ex) {
				return new ResponseEntity<String>("Error al actualizar el cliente consulte el log",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Informacion incompleta, por favor digite todos los campos",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> updateStatus(String id, boolean status) {
		Optional<ClientEntity> clientUpdateState = clientRepositoryJPA.findById(id);
		clientUpdateState.get().setStatus(status);
		clientRepositoryJPA.save(clientUpdateState.get());
		return new ResponseEntity<String>("Cliente desactivado", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		try {
			deleteById(id);
			return new ResponseEntity<String>("Cliente eliminado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("El cliente no puede ser eliminado debido a que posee cuentas activas",
					HttpStatus.BAD_REQUEST);
		}

	}

	private void deleteById(String id) throws Exception {
		clientRepositoryJPA.deleteById(id);
	}
}