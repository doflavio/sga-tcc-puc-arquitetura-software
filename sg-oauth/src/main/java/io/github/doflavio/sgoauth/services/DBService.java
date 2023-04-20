package io.github.doflavio.sgoauth.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgoauth.model.Permission;
import io.github.doflavio.sgoauth.model.User;
import io.github.doflavio.sgoauth.repositories.PermissionRepository;
import io.github.doflavio.sgoauth.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private UserRepository pessoaRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;

	public void instanciaDB() {
		
		Pbkdf2PasswordEncoder pbkdf2Encoder = criarPbkdf2PasswordEncoder();

		Permission permission1 = new Permission(1L,"ADMIN");
		Permission permission2 = new Permission(1L,"MANAGER");
		Permission permission3 = new Permission(1L,"COMMON_USER");
		
		List<Permission> listaPermissao1 = new ArrayList<>(); //ADMIN e MANAGER
		listaPermissao1.add(permission1);
		listaPermissao1.add(permission2);
		
		List<Permission> listaPermissao2 = new ArrayList<>();
		listaPermissao1.add(permission3);
		
		
		//User user1 = new User(1L,"leandro", "Leandro Costa", "1e3cdeeaaaeeda173ff6d002e7cb5e3f91ebc354dcff52156c9eaba1793a3a5e5bee306c11099e22", true, true, true,true,listaPermissao1);
		//User user2 = new User(2L,"flavio", "Flavio Costa", "362ad02420268beeb22d3a1f0d92749df461d7f4b74c9433d7415bdeef1b2902f4eb1edaecb37cb3", true, true, true,true,listaPermissao2);
		
		
		User user1 = new User(1L,"leandro", "Leandro Costa", pbkdf2Encoder.encode("admin123"), true, true, true,true,listaPermissao1);
		User user2 = new User(2L,"flavio", "Flavio Costa", pbkdf2Encoder.encode("admin123"), true, true, true,true,listaPermissao2);
		
		permissionRepository.saveAll(Arrays.asList(permission1,permission2,permission3));
		pessoaRepository.saveAll(Arrays.asList(user1,user2));
	}

	private Pbkdf2PasswordEncoder criarPbkdf2PasswordEncoder() {
		return new Pbkdf2PasswordEncoder(
					"", 8, 185000,
					SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
	}
}