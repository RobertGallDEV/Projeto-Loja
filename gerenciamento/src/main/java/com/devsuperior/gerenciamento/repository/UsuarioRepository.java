package com.devsuperior.gerenciamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.gerenciamento.dto.UsuarioCreateDTO;
import com.devsuperior.gerenciamento.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByNome(String nome);

	void deleteByNome(String nome);

	Optional<Usuario> findByUsuario(String usuario);

	UsuarioCreateDTO save(UsuarioCreateDTO usuarioCreateDTO);

}
