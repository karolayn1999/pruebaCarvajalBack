package com.prueba.repository;

import com.prueba.entity.AccountEntity;
import com.prueba.entity.ClientEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryJPA extends CrudRepository<AccountEntity, String>, JpaSpecificationExecutor<AccountEntity>, JpaRepository<AccountEntity, String> {

	@Query(value = "SELECT m FROM AccountEntity m WHERE m.client = :client")
	List<AccountEntity> findAllByClientId(@Param("client") ClientEntity clientEntity);

	@Query(value = "SELECT m FROM AccountEntity m WHERE m.accountType = :accountType AND m.accountNumber = :accountNumber")
	AccountEntity findByAccountTypeAndAccountNumber(@Param("accountType") String accountType, @Param("accountNumber") String accountNumber);

}