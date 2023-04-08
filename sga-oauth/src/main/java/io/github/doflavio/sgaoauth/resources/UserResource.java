package io.github.doflavio.sgaoauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.doflavio.sgaoauth.entities.User;
import io.github.doflavio.sgaoauth.services.UserService;

@RestController()
@RequestMapping(value = "sg-oauth")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping(value="/users/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		try {
			User user = service.findByEmail(email);
			return ResponseEntity.ok(user);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
}
