package io.github.doflavio.sgauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.doflavio.sgauth.entities.User;
import io.github.doflavio.sgauth.services.UserService;

@RestController()
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping(value="/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		try {
			System.out.println("Estou aqui no buscar por email do oauth");
			User user = service.findByEmail(email);
			return ResponseEntity.ok(user);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
}
