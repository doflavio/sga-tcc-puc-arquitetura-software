package io.github.doflavio.sgaoauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.doflavio.sgaoauth.entities.User;



@Component
@FeignClient(name = "sg-user", path = "users")
public interface UserFeignClient {

	@GetMapping(value = "/search")
	ResponseEntity <User> findByEmail(@RequestParam String email);

}
