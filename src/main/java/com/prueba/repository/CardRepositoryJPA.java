package com.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.entity.AccountEntity;
import com.prueba.entity.CardEntity;

@Repository
public interface CardRepositoryJPA extends CrudRepository<CardEntity, String>, JpaSpecificationExecutor<CardEntity>, JpaRepository<CardEntity, String>{

	@Query(value = "SELECT m FROM CardEntity m WHERE m.idAccount = :idAccount")
	List<CardEntity> findAllByIdAccount(@Param("idAccount") AccountEntity accountEntity);

	CardEntity findBysecretNumber(String secretNumber);

}
