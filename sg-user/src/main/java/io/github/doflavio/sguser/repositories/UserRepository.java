package io.github.doflavio.sguser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sguser.domains.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
