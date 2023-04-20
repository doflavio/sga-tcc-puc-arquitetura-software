package io.github.doflavio.sgoauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.doflavio.sgoauth.entities.User;



@Component
@FeignClient(name = "sg-user", path = "users")
public interface UserFeignClient {

	@GetMapping(value = "/search")
	ResponseEntity <User> findByEmail(@RequestParam String email);

}
