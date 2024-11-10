package com.devsuperior.gerenciamento.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.gerenciamento.dto.UsuarioCreateDTO;
import com.devsuperior.gerenciamento.dto.UsuarioRequestDTO;
import com.devsuperior.gerenciamento.entity.Usuario;
import com.devsuperior.gerenciamento.exception.ConfirmacaoDeSenha;
import com.devsuperior.gerenciamento.exception.UsusuarioExistente;
import com.devsuperior.gerenciamento.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Usuario> usuario = usuarioRepository.findByNome(username);
		
		if (usuario.isPresent()) {
			return new User(usuario.get().getUsuario(), usuario.get().getSenha(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.get().getRole().name())));

		} else {
			throw new UsernameNotFoundException("Usuário não encontrado: " + username);
		}
	}

	public UsuarioRequestDTO salvar(UsuarioCreateDTO usuario) throws ConfirmacaoDeSenha, UsusuarioExistente {

		if (usuario.getUsuario() == usuarioRepository.findByUsuario(usuario.getUsuario())) {

			if (usuario.getSenha() == usuario.getConfirmaSenha()) {
				usuarioRepository.save(usuario);

				UsuarioRequestDTO request = new UsuarioRequestDTO();

				request.setEstado(usuario.isEstado());
				request.setNome(usuario.getNome());
				request.setRole(usuario.getRole());
				request.setUsuario(usuario.getUsuario());

				return request;
			} else {
				throw new ConfirmacaoDeSenha("As senhas não corresponde.");
			}

		} else {
			throw new UsusuarioExistente("Usuario encontrado no banco, por favor insira outro nome.");
		}

	}

	public UsuarioRequestDTO buscarPorId(Long id) {
		Optional<Usuario> usu = usuarioRepository.findById(id);

		if (usu.isPresent()) {
			UsuarioRequestDTO dto = new UsuarioRequestDTO();
			dto.setEstado(usu.get().isEstado());
			dto.setNome(usu.get().getNome());
			dto.setUsuario(usu.get().getUsuario());
			dto.setRole(usu.get().getRole());

			return dto;

		} else {
			throw new UsernameNotFoundException("Usuario com o " + id + " não existe");
		}
	}

	public String deletar(String nome, String senha) throws ConfirmacaoDeSenha {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByNome(nome);

		if (usuarioExistente.isPresent()) {

			if (usuarioExistente.get().getSenha() == senha) {
				usuarioRepository.deleteByNome(usuarioExistente.get().getNome());
				return "Deletado com sucesso";
			} else {
				throw new ConfirmacaoDeSenha("Senha não corresponde, nada foi deletado");
			}

		} else {
			throw new UsernameNotFoundException(
					"Não existe alguem com esse nome no banco, por favor verifique as credenciais.");
		}
	}

	public List<UsuarioRequestDTO> listarTodos() {
		List<Usuario> usuario = usuarioRepository.findAll();

		List<UsuarioRequestDTO> usuarioRetorna = new ArrayList<UsuarioRequestDTO>();

		for (Usuario usu : usuario) {
			UsuarioRequestDTO dto = new UsuarioRequestDTO();
			dto.setEstado(usu.isEstado());
			dto.setNome(usu.getNome());
			dto.setUsuario(usu.getUsuario());
			dto.setRole(usu.getRole());

			usuarioRetorna.add(dto);
		}

		return usuarioRetorna;

	}

	public UsuarioRequestDTO buscarPorNome(String nome) {
		Optional<Usuario> usu = usuarioRepository.findByNome(nome);

		if (usu.isPresent()) {
			UsuarioRequestDTO dto = new UsuarioRequestDTO();
			dto.setEstado(usu.get().isEstado());
			dto.setNome(usu.get().getNome());
			dto.setUsuario(usu.get().getUsuario());
			dto.setRole(usu.get().getRole());

			return dto;

		} else {
			throw new UsernameNotFoundException("Usuario" + nome + " não existe");
		}

	}

	public UsuarioRequestDTO atualizarCadastro(String nome, UsuarioCreateDTO dto) throws ConfirmacaoDeSenha {

		Optional<Usuario> usuario = usuarioRepository.findByNome(nome);

		if (usuario.isPresent()) {

			UsuarioCreateDTO usuarioAlteracao = new UsuarioCreateDTO();

			usuarioAlteracao.setNome(dto.getNome());
			usuarioAlteracao.setRole(dto.getRole());
			usuarioAlteracao.setUsuario(dto.getUsuario());

			if (dto.getSenha() == dto.getConfirmaSenha()) {
				usuarioAlteracao.setSenha(dto.getSenha());
				usuarioAlteracao.setConfirmaSenha(dto.getConfirmaSenha());

				UsuarioCreateDTO usuarioAtualizado = usuarioRepository.save(usuarioAlteracao);

				UsuarioRequestDTO request = new UsuarioRequestDTO();

				request.setEstado(usuarioAtualizado.isEstado());
				request.setNome(usuarioAtualizado.getNome());
				request.setRole(usuarioAtualizado.getRole());
				request.setUsuario(usuarioAtualizado.getUsuario());

				return request;
			} else {
				throw new ConfirmacaoDeSenha("As senhas não corresponde, nada foi salvo.");
			}

		} else {
			throw new UsernameNotFoundException("Cadastro não encontrado" + nome);
		}

	}

}
