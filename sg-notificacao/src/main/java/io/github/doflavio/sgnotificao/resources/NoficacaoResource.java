package io.github.doflavio.sgnotificao.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(value = "/notificacoes")
public class NoficacaoResource {
	
	private static Logger logger = LoggerFactory.getLogger(NoficacaoResource.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment env;
	
	@GetMapping(value="/configs")
	public ResponseEntity<String> getConfigs() {
		logger.info("PORT = " + env.getProperty("local.server.port"));
		logger.info("CONFIG = " + testConfig );
		return ResponseEntity.ok(testConfig);
	}

}
