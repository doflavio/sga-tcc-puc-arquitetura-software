package io.github.doflavio.sgaauthauthorization.domain.entities;

import java.util.stream.Collectors;

import javax.persistence.Entity;

import io.github.doflavio.sgaauthauthorization.domain.dtos.ClienteDTO;
import io.github.doflavio.sgaauthauthorization.domain.enums.Perfil;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(ClienteDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	

}
