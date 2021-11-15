package com.prueba.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.entity.ClientEntity;

@Repository
public interface ClientRepositoryJPA extends CrudRepository<ClientEntity, String>, JpaSpecificationExecutor<ClientEntity>, JpaRepository<ClientEntity, String>{
	
	@Query(value = "SELECT m FROM ClientEntity m WHERE m.documentType = :documentType AND m.documentNumber = :documentNumber")
	ClientEntity findByDocumentTypeAndDocumentNumber(@Param("documentType") final String documentType, @Param("documentNumber") final String documentNumber) throws Exception;
	
	@Query(value = "SELECT m FROM ClientEntity m WHERE m.status = true")
	List<ClientEntity> findAllInStatus();
	
}