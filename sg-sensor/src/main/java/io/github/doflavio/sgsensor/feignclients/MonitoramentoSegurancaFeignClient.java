package io.github.doflavio.sgsensor.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.doflavio.sgnotificacao.models.User;

@Component
@FeignClient(name = "sg-monitoramento-seguranca", path = "incidentes")
public interface MonitoramentoSegurancaFeignClient {

	@GetMapping(value = "/search")
	ResponseEntity <User> findByEmail(@RequestParam String email);

}
