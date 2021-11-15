package com.prueba.translate;

import com.prueba.dto.ClientDTO;
import com.prueba.entity.ClientEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientTranslate implements Translate<ClientDTO, ClientEntity> {
	
	@Override
	public ClientDTO translateTo(ClientEntity clientEntity) {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(clientEntity.getId());
		clientDTO.setDocumentType(clientEntity.getDocumentType());
		clientDTO.setDocumentNumber(clientEntity.getDocumentNumber());
		clientDTO.setBirthDate(clientEntity.getBirthDate());
		clientDTO.setName(clientEntity.getName());
		clientDTO.setStatus(clientEntity.isStatus());
		return clientDTO;
	}
	
	public List<ClientDTO> translateTo(List<ClientEntity> input) {
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for (ClientEntity clientEntity : input) {
			ClientDTO clientDTO = translateTo(clientEntity);
			clientsDTO.add(clientDTO);
		}
		return clientsDTO;
	}
	
	public ClientEntity translateTo(ClientDTO clientDTO) throws Exception {
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId(clientDTO.getId());
		clientEntity.setDocumentType(clientDTO.getDocumentType());
		clientEntity.setDocumentNumber(clientDTO.getDocumentNumber());
		clientEntity.setBirthDate(clientDTO.getBirthDate());
		clientEntity.setName(clientDTO.getName());
		clientEntity.setStatus(true);
		return clientEntity;
	}

	
	
	

}
