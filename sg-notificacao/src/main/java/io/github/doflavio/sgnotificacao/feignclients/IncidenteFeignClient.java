package io.github.doflavio.sgnotificacao.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.doflavio.sgnotificacao.dtos.NotificacaoEnvioEmailDTO;

@Component
@FeignClient(name = "sg-monitoramento-seguranca", path = "incidentes")
public interface IncidenteFeignClient {

	@PutMapping(value = "/{id}/envio-email")
	public ResponseEntity<String> confirmarEnvioEmail(@PathVariable Integer id, @RequestBody NotificacaoEnvioEmailDTO objDTO);

}
