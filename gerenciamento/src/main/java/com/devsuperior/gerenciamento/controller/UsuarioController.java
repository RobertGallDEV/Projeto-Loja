package com.devsuperior.gerenciamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.gerenciamento.dto.UsuarioCreateDTO;
import com.devsuperior.gerenciamento.dto.UsuarioRequestDTO;
import com.devsuperior.gerenciamento.exception.ConfirmacaoDeSenha;
import com.devsuperior.gerenciamento.exception.UsusuarioExistente;
import com.devsuperior.gerenciamento.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	// resolvido
	// fazer a necessidade de estar logado
	@PostMapping("/criar")
	public ResponseEntity<UsuarioRequestDTO> criarUsuario(@RequestBody UsuarioCreateDTO usuario)
			throws ConfirmacaoDeSenha, UsusuarioExistente {
		return ResponseEntity.ok(usuarioService.salvar(usuario));
	}

	// fazer a necessidade de estar logado
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioRequestDTO>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	// resolvido
	// fazer a necessidade de estar logado
	@GetMapping("/buscar/{nome}")
	public ResponseEntity<UsuarioRequestDTO> buscarUsuarioPorUsuarios(@PathVariable String nome) {
		return ResponseEntity.ok(usuarioService.buscarPorNome(nome));
	}

	// resolvido
	// fazer a necessidade de estar logado
	// Pode ser feito uma verificação se a pessoa tem cargo correto, pedindo que
	// coloque a senha novamente
	@PutMapping("/atualizar/{nome}")
	public ResponseEntity<UsuarioRequestDTO> atualizarUsuario(@PathVariable String nome, @RequestBody UsuarioCreateDTO usuario)
			throws ConfirmacaoDeSenha {
		return ResponseEntity.ok(usuarioService.atualizarCadastro(nome, usuario));
	}

	// resolvido
	// quero saber como vai fazer a forma de logado.
	@DeleteMapping("/excluir/{nome}")
	public ResponseEntity<String> deletarUsuario(@PathVariable String nome, @PathVariable String senha) throws ConfirmacaoDeSenha {
		return ResponseEntity.ok(usuarioService.deletar(nome, senha));
	}
}
