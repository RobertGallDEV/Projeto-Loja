package com.devsuperior.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.gerenciamento.entity.Vendas;

@Repository
public interface VendaRepository extends JpaRepository<Vendas, Long> {
	/* Optional<List<Vendas>> findByDataBetween(LocalDate incio, LocalDate fim); */
    
}

