package com.devsuperior.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.gerenciamento.entity.VendaItens;

@Repository
public interface VendaItemRepository extends JpaRepository<VendaItens, Long> {
}
