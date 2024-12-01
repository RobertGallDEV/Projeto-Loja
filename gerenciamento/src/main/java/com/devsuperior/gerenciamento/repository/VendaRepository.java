package com.devsuperior.gerenciamento.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsuperior.gerenciamento.entity.Vendas;

@Repository
public interface VendaRepository extends JpaRepository<Vendas, Long> {
	@Query("Select v FROM Vendas v WHERE v.dataCadastro BETWEEN :inicio AND :fim ")
	 Optional<List<Vendas>> findByDataBetween(@Param("inicio") LocalDate inicio,@Param("fim") LocalDate fim);
    
}

